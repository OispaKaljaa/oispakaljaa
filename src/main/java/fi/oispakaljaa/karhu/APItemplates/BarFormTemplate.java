package fi.oispakaljaa.karhu.APItemplates;

import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.domain.Drink;

/**
 * Created by Hugo on 30.12.2016.
 */
public class BarFormTemplate {
    private Bar bar;
    private Drink drink;

    public BarFormTemplate() {}

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
}
