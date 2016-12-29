package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.APItemplates.google.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import fi.oispakaljaa.karhu.repository.BarRepository;
import fi.oispakaljaa.karhu.repository.DrinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bars/{barId}/drinks")
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    BarRepository barRepository;

    @Autowired
    public void setUp() {
        Bar b1 = new Bar();
        b1.setName("willi wäinö");
        b1.setAddress("kalevankatu+4+Helsinki");
        barRepository.save(b1);

        Bar b2 = new Bar();
        b2.setName("swengi");
        b2.setAddress("Iso+Roobertinkatu+10+Helsinki");
        barRepository.save(b2);

        Drink d = new Drink();
        d.setDrinkType("beer");
        d.setPrice(550);
        d.setAlcoholPercentage(456);
        d.setVolume(5);
        d.setBar(b1);
        drinkRepository.save(d);

        d = new Drink();
        d.setDrinkType("beer");
        d.setPrice(900);
        d.setAlcoholPercentage(550);
        d.setVolume(4);
        d.setBar(b2);
        drinkRepository.save(d);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> listDrinksByBar(@PathVariable(value = "barId") Long id) {
        Bar b = barRepository.findOne(id);
        if (b == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", drinkRepository.findByBar(b)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OispakaljaaTemplate> postDrink(@PathVariable("barId") Long id, @RequestBody Drink drink) {
        Bar b = barRepository.findOne(id);
        if (b == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        drink.setBar(b);
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", drinkRepository.save(drink)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> getDrink(@PathVariable("drinkId") Long barId,
                                                        @PathVariable("drinkId") Long drinkId) {
        Bar b = barRepository.findOne(barId);
        if (b == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        Drink d = drinkRepository.findByBarAndId(b, drinkId);
        if (d == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Drink not found", null), HttpStatus.NOT_FOUND);

        return new ResponseEntity<OispakaljaaTemplate>(new OispakaljaaTemplate("OK", "", d), HttpStatus.OK);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.DELETE)
    public ResponseEntity<OispakaljaaTemplate> deleteDrink(@PathVariable("barId") Long barId,
                                                           @PathVariable("drinkId") Long drinkId) {
        Drink drink = drinkRepository.findOne(drinkId);
        if (drink == null) {
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Drink not found", null), HttpStatus.NOT_FOUND);
        }
        drinkRepository.delete(drink);
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", drink), HttpStatus.OK);
    }

}
