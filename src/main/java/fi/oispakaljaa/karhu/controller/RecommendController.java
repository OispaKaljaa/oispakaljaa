package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.APItemplates.google.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecommendController {

    @Autowired
    DrinkRepository drinkRepository;

    @RequestMapping(value = "recommend", method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> listAllDrinksByPrice() {
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", drinkRepository.orderByPrice(new Sort("price"))), HttpStatus.OK);
    }

    // Tällä voidaan hakea juomat ja baarit tietyltä säteeltä ja järjestää halvimman bissen mukaan. 
    // En tiedä, olisiko Google Mapsin avulla mahdollista parempi toteutus, mutta näin näyttäisi toimivan..
    @RequestMapping(value = "recommend/{lon},{lat},{dis}", method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> listAllDrinksByPrice(@PathVariable double lon, @PathVariable double lat, @PathVariable double dis) {
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", drinkRepository.orderByPriceWhereLocationIsNearby(lon, lat, dis, new Sort("price"))), HttpStatus.OK);
    }

}
