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

//    private Location location; //ONKS TÄÄ SIT OIKEIN

    private Drink cheapestBisse;

    @OneToMany(mappedBy = "bar")
    private List<Drink> drinks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
*/

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
