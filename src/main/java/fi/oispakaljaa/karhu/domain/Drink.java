package fi.oispakaljaa.karhu.domain;

import java.util.Date;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Drink extends AbstractPersistable<Long> {

    private String name; // KARJALA /

    private String drinkType; // BEER / CIDER

    private Integer price;

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

}
