package android.rad.shipment.calculator.database.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(foreignKeys = @ForeignKey(entity = Isotopes.class,
//                                    parentColumns = "abbr",
//                                    childColumns = "abbr"))
@Entity
public class ExemptConcentration {
    @NonNull
    @PrimaryKey
    private String abbr;
    @NonNull
    private float value;

    public ExemptConcentration(@NonNull String abbr, float value) {
        this.abbr = abbr;
        this.value = value;
    }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    @NonNull
    public String getAbbr() { return abbr; }

    public float getValue() { return value; }
    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    public void setAbbr(@NonNull String abbr) { this.abbr = abbr; }

    public void setValue(float value) { this.value = value; }
}
