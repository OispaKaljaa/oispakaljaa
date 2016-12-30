package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.APItemplates.google.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import fi.oispakaljaa.karhu.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api")
public class RecommendController {
    @Autowired
    QueryService queryService;

    @RequestMapping(value = "recommend/", method = RequestMethod.GET)
    public Callable<ResponseEntity<OispakaljaaTemplate>> listAllDrinksByPrice(@RequestParam String pos) {
        return () -> new ResponseEntity<>(new OispakaljaaTemplate("OK", "", queryService.recommendBars(pos)), HttpStatus.OK);
    }

}
