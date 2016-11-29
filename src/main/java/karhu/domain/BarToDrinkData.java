package karhu.domain;

import javax.persistence.Entity;

/**
 * Created by julkku on 11/29/16.
 */

@Entity
public class BarToDrinkData {

    private Bar bar;
    private Drink drink;
    private int dl;
    private int cents;

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getDl() {
        return dl;
    }

    public void setDl(int dl) {
        this.dl = dl;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }
}
