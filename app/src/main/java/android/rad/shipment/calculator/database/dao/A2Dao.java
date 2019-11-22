package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface A2Dao {
    @Query("SELECT VALUE FROM A2 WHERE abbr LIKE :abbr")
    float loadA2Value(String abbr);
}
