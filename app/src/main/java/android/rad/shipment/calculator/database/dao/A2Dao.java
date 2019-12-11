package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.A2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface A2Dao {
    @Query("SELECT COUNT(*) FROM A2")
    int count();

    @Query("SELECT VALUE FROM A2 WHERE abbr LIKE :abbr")
    float loadA2Value(String abbr);

    @Insert
    void insertAll(A2... a2s);
}
