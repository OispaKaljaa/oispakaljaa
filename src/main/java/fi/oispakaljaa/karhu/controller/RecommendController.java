package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.domain.Drink;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController {

    @Autowired
    DrinkRepository drinkRepository;

    @RequestMapping(value = "recommend", method = RequestMethod.GET)
    public List<Drink> listAllDrinksByPrice() {
        return drinkRepository.orderByPrice(new Sort("price"));
    }

    // Tällä voidaan hakea juomat ja baarit tietyltä säteeltä ja järjestää halvimman bissen mukaan. 
    // En tiedä, olisiko Google Mapsin avulla mahdollista parempi toteutus, mutta näin näyttäisi toimivan..
    @RequestMapping(value = "recommend/{lon},{lat},{dis}", method = RequestMethod.GET)
    public List<Drink> listAllDrinksByPrice(@PathVariable double lon, @PathVariable double lat, @PathVariable double dis) {
        return drinkRepository.orderByPriceWhereLocationIsNearby(lon, lat, dis, new Sort("price"));
    }

}
