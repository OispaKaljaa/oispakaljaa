package fi.oispakaljaa.karhu.controller;

import com.google.gson.Gson;
import fi.oispakaljaa.karhu.APItemplates.BarFormTemplate;
import fi.oispakaljaa.karhu.APItemplates.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.domain.Account;
import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import fi.oispakaljaa.karhu.repository.AccountRepository;
import fi.oispakaljaa.karhu.repository.BarRepository;

import fi.oispakaljaa.karhu.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/bars")
public class BarController {
    @Autowired
    BarRepository barRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> list() {
        return new ResponseEntity<>(
                new OispakaljaaTemplate("OK", "", barRepository.findAll()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> getBar(@PathVariable("barId") Long id) {
        Bar b = barRepository.findOne(id);
        if (b == null)
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "Bar not found", null),
                    HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(
                new OispakaljaaTemplate("OK", "", b),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OispakaljaaTemplate> postBar(@RequestBody String body) {
        if (!isAuthenticated(false))
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "Unauthorized", null),
                    HttpStatus.UNAUTHORIZED);

        // BarFormTemplate is a class that is only used for the form submission. It might be useless, but this was the only solution I had time to implement.
        // Of course, I could have made 2 request from front-end (one to bars and then one to drink), but I still think it's better to keep the request amount as low as possible.
        BarFormTemplate template = new Gson().fromJson(body, BarFormTemplate.class); // Using GSON only for this since spring is unable to parse it.
        if (barRepository.findByName(template.getBar().getName()).size() != 0)
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "", null),
                    HttpStatus.I_AM_A_TEAPOT);
        Drink d = drinkRepository.save(template.getDrink());
        Bar b = barRepository.save(template.getBar());
        d.setBar(b);
        drinkRepository.save(d);
        return new ResponseEntity<>(
                new OispakaljaaTemplate("OK", "", b),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.DELETE)
    public ResponseEntity<OispakaljaaTemplate> deleteBar(@PathVariable("barId") Long id) {
        if (!isAuthenticated(true))
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "Unauthorized", null),
                    HttpStatus.UNAUTHORIZED);
        Bar bar = barRepository.findOne(id);
        if (bar == null)
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "Bar not found", null),
                    HttpStatus.NOT_FOUND);

        barRepository.delete(bar);
        return new ResponseEntity<>(
                new OispakaljaaTemplate("OK", "", bar),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.PUT)
    public ResponseEntity<OispakaljaaTemplate> addFavourite(@PathVariable("barId") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByUsername(auth.getName());
        Bar bar = barRepository.findOne(id);
        if (account == null)
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "Unauthorized", null),
                    HttpStatus.UNAUTHORIZED);
        if (bar != null) {
            long count = account.getFavouriteBars().stream().filter(b -> b.getName() == bar.getName()).count();
            if (count > 0)
                return new ResponseEntity<>(
                        new OispakaljaaTemplate("Error", "You cannot favourite a bar twice", null),
                        HttpStatus.FORBIDDEN);
            bar.getFavourites().add(account);
            barRepository.save(bar);
        } else
            return new ResponseEntity<>(
                    new OispakaljaaTemplate("Error", "Bar not found", null),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(
                new OispakaljaaTemplate("OK", "", account),
                HttpStatus.OK);
    }

    // Since the security configuration isn't working correctly...
    private boolean isAuthenticated(boolean admin) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
            return false;
        boolean authenticated = accountRepository.findByUsername(auth.getName()) != null;
        if (admin) {
            authenticated = authenticated && auth.getAuthorities().stream().filter(a -> a.getAuthority().equals("ADMIN")).count() == 0;
        }
        return authenticated;
    }

}
