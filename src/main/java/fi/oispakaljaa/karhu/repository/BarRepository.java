package fi.oispakaljaa.karhu.repository;

import fi.oispakaljaa.karhu.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarRepository extends JpaRepository<Bar, Long> {
    List<Bar> findByName(String name);
}


