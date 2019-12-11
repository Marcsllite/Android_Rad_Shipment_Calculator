package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.A1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface A1Dao {
    @Query("SELECT COUNT(*) FROM A1")
    int count();

    @Query("SELECT VALUE FROM A1 WHERE abbr LIKE :abbr")
    float loadA1Value(String abbr);

    @Insert
    void insertAll(A1... a1s);
}
