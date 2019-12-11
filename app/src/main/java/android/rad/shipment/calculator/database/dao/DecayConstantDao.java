package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.DecayConstant;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DecayConstantDao {
    @Query("SELECT COUNT(*) FROM DecayConstant")
    int count();

    @Query("SELECT VALUE FROM DecayConstant WHERE abbr LIKE :abbr")
    float loadDecayConstantValue(String abbr);

    @Insert
    void insertAll(DecayConstant... decayConstants);
}
