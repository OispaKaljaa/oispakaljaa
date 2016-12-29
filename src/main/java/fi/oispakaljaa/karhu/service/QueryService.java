package fi.oispakaljaa.karhu.service;

import fi.oispakaljaa.karhu.APItemplates.google.Element;
import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import fi.oispakaljaa.karhu.repository.BarRepository;
import fi.oispakaljaa.karhu.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Hugo on 9.12.2016.
 */
@Service
public class QueryService {

    @Autowired
    BarRepository barRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    GoogleAPIService googleAPIService;

      public List<Map<Object, Object>> recommendBars(String position) {
        List<Bar> bars = barRepository.findAll();
        List<Map<Object, Object>> elements = googleAPIService.assignDistanceToBars(position, bars);
        if (elements == null)
            return null;

        return elements.stream()
            .sorted((a, b) -> {
                Integer aDist = ((Integer) a.get("distance"));
                Integer bDist = ((Integer) b.get("distance"));

                // Since we only support one drink currently, we'll do this.
                Drink aDrink = drinkRepository.findByBar(((Bar) a.get("bar"))).get(0);
                Drink bDrink = drinkRepository.findByBar(((Bar) a.get("bar"))).get(0);

                if (aDrink == null || bDrink == null)
                    return 0;

                Double aWeight = aDrink.getIntoxFactor() + (1.d / (aDist / 1000.d));
                Double bWeight = bDrink.getIntoxFactor() + (1.d / (bDist / 1000.d));

                return -aWeight.compareTo(bWeight);
            })
            .limit(5)
            .collect(Collectors.toList());
    }
}
