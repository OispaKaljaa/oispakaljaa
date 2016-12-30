package fi.oispakaljaa.karhu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double intoxFactor;
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

    public Integer getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(Integer alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getIntoxFactor() {
        return ((alcoholPercentage / 10000.d) * (volume / 10.d)) + (1.d / price);
    }
}
