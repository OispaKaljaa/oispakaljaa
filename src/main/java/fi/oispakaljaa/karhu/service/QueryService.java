package fi.oispakaljaa.karhu.service;

import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hugo on 9.12.2016.
 */

@Service
public class QueryService {

    @Autowired
    BarRepository barRepository;

    @Autowired
    GoogleAPIService googleAPIService;

    public List<Bar> recommendBars() {
        List<Bar> bars = barRepository.findAll();
        googleAPIService.getDistance("60.171080599999996,24.949038599999998", "Kamppi.Helsinki");
        return bars.stream()
            .sorted(Comparator.comparing(b -> b.getCheapestBisse().getPrice()))
            .collect(Collectors.toList());
    }
}
