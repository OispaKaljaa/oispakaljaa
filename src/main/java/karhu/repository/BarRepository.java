package karhu.repository;

import karhu.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {
    //ÄHHH MITEN TÄÄ KANTSIS TEHÄ SILLEEN ET PYSTYY TEHÄ FIND BY DRINK
}


