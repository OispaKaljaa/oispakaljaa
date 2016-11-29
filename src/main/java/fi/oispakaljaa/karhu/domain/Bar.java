package fi.oispakaljaa.karhu.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by julkku on 11/29/16.
 */
@Entity
public class Bar extends AbstractPersistable<Long> {

    private String name;

    private double longitude;

    private double latitude;

    private Drink cheapestBisse;

    @OneToMany(mappedBy = "bar")
    private List<Drink> drinks;

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

    public Drink getCheapestBisse() {
        return cheapestBisse;
    }

    public void setCheapestBisse(Drink cheapestBisse) {
        this.cheapestBisse = cheapestBisse;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
