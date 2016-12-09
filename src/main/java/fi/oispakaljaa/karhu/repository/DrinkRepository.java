package fi.oispakaljaa.karhu.repository;

import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Bar findByBar(Bar bar);
}


