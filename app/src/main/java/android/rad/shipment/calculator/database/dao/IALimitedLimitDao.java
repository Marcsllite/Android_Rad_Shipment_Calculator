package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface IALimitedLimitDao {
    @Query("SELECT VALUE FROM IALimitedLimit WHERE state LIKE :state AND form LIKE :form")
    float loadIALimitedLimitValue(String state, String form);
}
