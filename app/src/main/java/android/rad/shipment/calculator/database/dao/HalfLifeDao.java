package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface HalfLifeDao {
    @Query("SELECT VALUE FROM HalfLife WHERE abbr LIKE :abbr")
    float loadHalfLifeValue(String abbr);
}
