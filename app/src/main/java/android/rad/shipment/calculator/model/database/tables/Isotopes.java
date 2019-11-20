package android.rad.shipment.calculator.model.database.tables;

import androidx.room.Entity;

@Entity(primaryKeys = {"name", "abbr"})
public class Isotopes {
    private String name;
    private String abbr;

    public Isotopes(String name, String abbr) {
        this.name = name;
        this.abbr = abbr;
    }

//    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
//    public String getName() { return name; }
//
//    public String getAbbr() { return abbr; }
//    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
//    public void setName(String name) { this.name = name; }
//
//    public void setAbbr(String abbr) { this.abbr = abbr; }
}
