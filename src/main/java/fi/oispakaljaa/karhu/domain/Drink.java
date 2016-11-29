package fi.oispakaljaa.karhu.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * Created by julkku on 11/29/16.
 */


@Entity
public class Drink extends AbstractPersistable<Long> {

    private String name; // KARJALA /

    private String drinkType; // BEER / CIDER

    private Bar bar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }
}
