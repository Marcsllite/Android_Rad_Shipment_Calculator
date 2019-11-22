package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface A1Dao {
    @Query("SELECT VALUE FROM A1 WHERE abbr LIKE :abbr")
    float loadA1Value(String abbr);
}
