package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.LimitedLimit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LimitedLimitDao {
    @Query("SELECT COUNT(*) FROM LimitedLimit")
    int count();

    @Query("SELECT VALUE FROM LimitedLimit WHERE state LIKE :state AND form LIKE :form")
    float loadLimitedLimitValue(String state, String form);

    @Insert
    void insertAll(LimitedLimit... limitedLimits);
}
