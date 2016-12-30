package fi.oispakaljaa.karhu.repository;

import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByBar(Bar bar);
    Drink findByBarAndId(Bar bar, Long Id);
}
