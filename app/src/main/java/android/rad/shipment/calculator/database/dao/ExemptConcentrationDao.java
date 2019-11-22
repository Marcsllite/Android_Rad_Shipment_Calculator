package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ExemptConcentrationDao {
    @Query("SELECT VALUE FROM ExemptConcentration WHERE abbr LIKE :abbr")
    float loadExemptConcentrationValue(String abbr);
}
