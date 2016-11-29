
package fi.oispakaljaa.karhu.repository;
 
import fi.oispakaljaa.karhu.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}