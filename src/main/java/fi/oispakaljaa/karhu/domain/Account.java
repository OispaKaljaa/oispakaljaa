package fi.oispakaljaa.karhu.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.Column;

@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;
    private String password;
    private String salt;

    @ManyToMany
    private List<Bar> favouriteBars;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }



}
