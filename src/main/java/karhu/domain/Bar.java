package karhu.domain;

import org.springframework.beans.factory.parsing.Location;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by julkku on 11/29/16.
 */
@Entity
public class Bar extends AbstractPersistable<Long> {

    private String name;

    private Location location; //ONKS TÄÄ SIT OIKEIN

    private BarToDrinkData halvinKalja;

    @ManyToOne
    private List<BarToDrinkData> drinkData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BarToDrinkData getHalvinKalja() {
        return halvinKalja;
    }

    public void setHalvinKalja(BarToDrinkData halvinKalja) {
        this.halvinKalja = halvinKalja;
    }

    public List<BarToDrinkData> getDrinkData() {
        return drinkData;
    }

    public void setDrinkData(List<BarToDrinkData> drinkData) {
        this.drinkData = drinkData;
    }
}
