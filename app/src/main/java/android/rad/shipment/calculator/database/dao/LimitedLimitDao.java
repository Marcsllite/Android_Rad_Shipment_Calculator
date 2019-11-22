package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface LimitedLimitDao {
    @Query("SELECT VALUE FROM LimitedLimit WHERE state LIKE :state AND form LIKE :form")
    float loadLimitedLimitValue(String state, String form);
}
