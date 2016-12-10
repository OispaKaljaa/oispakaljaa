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

    @Query("SELECT d FROM Drink d")
    List<Drink> orderByPrice(Sort sort);

    @Query("SELECT d, b FROM Drink d, Bar b WHERE d.bar = b.id AND "
            + "b.longitude - ?1 < ?3 AND "
            + "?1 - b.longitude < ?3 AND "
            + "b.latitude - ?2 < ?3 AND "
            + "?2 - b.latitude < ?3")
    List<Drink> orderByPriceWhereLocationIsNearby(double longitude, double latitude, double distance, Sort sort);

}
