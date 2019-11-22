package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ExemptLimitDao {
    @Query("SELECT VALUE FROM ExemptLimit WHERE abbr LIKE :abbr")
    float loadExemptLimitValue(String abbr);
}
