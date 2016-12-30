package fi.oispakaljaa.karhu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import fi.oispakaljaa.karhu.APItemplates.BarFormTemplate;
import fi.oispakaljaa.karhu.APItemplates.google.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import fi.oispakaljaa.karhu.repository.BarRepository;

import fi.oispakaljaa.karhu.repository.DrinkRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/bars")
public class BarController {
    @Autowired
    BarRepository barRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> list() {
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", barRepository.findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> getBar(@PathVariable("barId") Long id) {
        Bar b = barRepository.findOne(id);
        if (b == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", b), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OispakaljaaTemplate> postBar(@RequestBody String body) {
        BarFormTemplate template = new Gson().fromJson(body, BarFormTemplate.class); // Using GSON only for this since spring is unable to parse it otherwise.
        if (barRepository.findByName(template.getBar().getName()).size() != 0)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "", null), HttpStatus.I_AM_A_TEAPOT);
        Drink d = drinkRepository.save(template.getDrink());
        Bar b = barRepository.save(template.getBar());
        d.setBar(b);
        drinkRepository.save(d);
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", b), HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.DELETE)
    public ResponseEntity<OispakaljaaTemplate> deleteBar(@PathVariable("barId") Long id) {
        Bar bar = barRepository.findOne(id);
        if (bar == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        barRepository.delete(bar);
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", bar), HttpStatus.OK);

        // se täytyis varmistaa, että jos baari poistetaan niin myös kaikki kyseisen baarin juomat poistetaan
    }

}
