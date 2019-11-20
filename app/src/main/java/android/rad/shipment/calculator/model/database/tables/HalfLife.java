package android.rad.shipment.calculator.model.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = Isotopes.class,
//                                    parentColumns = "abbr",
//                                    childColumns = "abbr"))
@Entity
public class HalfLife {
    @PrimaryKey
    private String abbr;
    private float value;

    public HalfLife(String abbr, float value) {
        this.abbr = abbr;
        this.value = value;
    }

//    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
//    public String getAbbr() { return abbr; }
//
//    public String getValue() { return value; }
//    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
//    public void setAbbr(String abbr) { this.abbr = abbr; }
//
//    public void setValue(String value) { this.value = value; }
}
