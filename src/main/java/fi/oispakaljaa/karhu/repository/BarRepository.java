package fi.oispakaljaa.karhu.repository;

import fi.oispakaljaa.karhu.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {
}


