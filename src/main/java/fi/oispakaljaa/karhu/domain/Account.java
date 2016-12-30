package fi.oispakaljaa.karhu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.Column;

@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt; // we don't want any plaintext password or salts in our response json.
    private boolean admin;
    @ManyToMany(mappedBy = "favourites")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Bar> favouriteBars;

    public Account() {
        this.admin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, this.salt);
    }

    public String getSalt() {
        return salt;
    }

    public List<Bar> getFavouriteBars() {
        return favouriteBars;
    }
}
