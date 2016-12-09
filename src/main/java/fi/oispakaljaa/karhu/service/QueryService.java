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

    public List<Bar> recommendBars() {
        List<Bar> bars = barRepository.findAll();
        return bars.stream()
            .sorted(Comparator.comparing(b -> b.getCheapestBisse().getPrice()))
            .collect(Collectors.toList());
    }
}
