package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DecayConstantDao {
    @Query("SELECT VALUE FROM DecayConstant WHERE abbr LIKE :abbr")
    float loadDecayConstantValue(String abbr);
}
