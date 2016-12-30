package fi.oispakaljaa.karhu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julkku on 11/29/16.
 */
@Entity
public class Bar extends AbstractPersistable<Long> {
    private String name;
    private String address;
    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Account> favourites;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int nFavourites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getFavourites() {
        if (favourites == null)
            favourites = new ArrayList<>();
        return favourites;
    }

    public void setFavourites(List<Account> favourites) {
        this.favourites = favourites;
    }

    public int getnFavourites() {
        if (favourites != null)
            return favourites.size();
        else
            return 0;
    }
}
