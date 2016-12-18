package fi.oispakaljaa.karhu.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by julkku on 11/29/16.
 */
@Entity
public class Bar extends AbstractPersistable<Long> {

    private String name;

    private double longitude;

    private double latitude;

    private String Address;

    @ManyToMany
    private List<Account> favourites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<Account> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Account> favourites) {
        this.favourites = favourites;
    }
}
