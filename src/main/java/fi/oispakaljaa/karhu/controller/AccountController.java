package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.APItemplates.google.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.domain.Account;
import fi.oispakaljaa.karhu.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/me")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@ModelAttribute Account account, HttpServletResponse res) throws IOException {
        accountRepository.save(account);
        res.sendRedirect("/signin");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String username = auth.getName();
            Account account = accountRepository.findByUsername(username);
            if (account != null) {
                return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", account), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Not found", null), HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity(new OispakaljaaTemplate("Error", "Unauthorized", null), HttpStatus.UNAUTHORIZED );
    }
}
