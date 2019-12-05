package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.IALimitedLimit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface IALimitedLimitDao {
    @Query("SELECT COUNT(*) FROM IAlimitedlimit")
    int count();

    @Query("SELECT VALUE FROM IALimitedLimit WHERE state LIKE :state AND form LIKE :form")
    float loadIALimitedLimitValue(String state, String form);

    @Insert
    void insertAll(IALimitedLimit... iaLimitedLimits);
}
