package karhu.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by julkku on 11/29/16.
 */


@Entity
public abstract class Drink {

    private String name;

    @OneToMany
    private List<BarToDrinkData> barData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BarToDrinkData> getBarData() {
        return barData;
    }

    public void setBarData(List<BarToDrinkData> barData) {
        this.barData = barData;
    }
}
