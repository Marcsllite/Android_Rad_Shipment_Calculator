package android.rad.shipment.calculator.database.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"name", "abbr"})
public class ShortLong {
    @NonNull
    private String name;
    @NonNull
    private String abbr;

    public ShortLong(@NonNull String name, @NonNull String abbr) {
        this.name = name;
        this.abbr = abbr;
    }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    @NonNull
    public String getName() { return name; }

    @NonNull
    public String getAbbr() { return abbr; }
    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    public void setName(@NonNull String name) { this.name = name; }

    public void setAbbr(@NonNull String abbr) { this.abbr = abbr; }
}
