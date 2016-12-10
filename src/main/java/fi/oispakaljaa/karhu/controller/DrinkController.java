package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import fi.oispakaljaa.karhu.repository.BarRepository;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "bars/{barId}/drinks")
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    BarRepository barRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Drink> listDrinksByBar(@PathVariable(value = "barId") Long id) {
        return drinkRepository.findByBar(barRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Drink postDrink(@PathVariable("barId") Long id, @RequestBody Drink drink) {
        Bar bar = barRepository.findOne(id);
        drink.setBar(bar);
        return drinkRepository.save(drink);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.GET)
    public Drink getDrink(@PathVariable("drinkId") Long barId,
            @PathVariable("drinkId") Long drinkId) {
        return drinkRepository.findByBarAndId(barRepository.findOne(barId), drinkId);
    }

    @RequestMapping(value = "/{drinkId}", method = RequestMethod.DELETE)
    public Drink deleteDrink(@PathVariable("barId") Long barId,
            @PathVariable("drinkId") Long drinkId) {
        Drink drink = drinkRepository.findOne(drinkId);
        drinkRepository.delete(drink);
        return drink;
    }

}
