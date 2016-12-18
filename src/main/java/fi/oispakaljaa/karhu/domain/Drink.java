package fi.oispakaljaa.karhu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Drink extends AbstractPersistable<Long> {

    private String name;

    private String drinkType;

    private Integer price;

    private Integer volume; //dl

    private Integer alcoholPercentage; // 4,56 -> 456

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Bar bar;

    @Temporal(TemporalType.DATE)
    private Date added;

    @Temporal(TemporalType.DATE)
    private Date lastEdit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Date getAdded() {
        return added;
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public double getIntoxFactor() {
        return ((double) alcoholPercentage * (double) volume * 0.1) / ((double) price * 100);
    }
}
