package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.ExemptConcentration;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExemptConcentrationDao {
    @Query("SELECT COUNT(*) FROM Exemptconcentration")
    int count();

    @Query("SELECT VALUE FROM ExemptConcentration WHERE abbr LIKE :abbr")
    float loadExemptConcentrationValue(String abbr);

    @Insert
    void insertAll(ExemptConcentration... exemptConcentrations);
}
