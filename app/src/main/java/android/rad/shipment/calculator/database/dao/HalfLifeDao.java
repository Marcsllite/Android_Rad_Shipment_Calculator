package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.HalfLife;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface HalfLifeDao {
    @Query("SELECT COUNT(*) FROM HalfLife")
    int count();

    @Query("SELECT VALUE FROM HalfLife WHERE abbr LIKE :abbr")
    float loadHalfLifeValue(String abbr);

    @Insert
    void insertAll(HalfLife... halfLives);
}
