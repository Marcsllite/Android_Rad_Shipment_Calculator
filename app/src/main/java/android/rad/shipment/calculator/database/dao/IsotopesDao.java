package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface IsotopesDao {
    @Query("SELECT name FROM Isotopes WHERE abbr LIKE :abbr")
    String loadIsotopeName(String abbr);

    @Query("SELECT abbr FROM Isotopes WHERE name LIKE :name")
    String loadIsotopeAbbr(String name);
}
