package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.ExemptLimit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExemptLimitDao {
    @Query("SELECT COUNT(*) FROM ExemptLimit")
    int count();

    @Query("SELECT VALUE FROM ExemptLimit WHERE abbr LIKE :abbr")
    float loadExemptLimitValue(String abbr);

    @Insert
    void insertAll(ExemptLimit... exemptLimits);
}
