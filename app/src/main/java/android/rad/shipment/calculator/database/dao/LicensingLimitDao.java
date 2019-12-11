package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.LicensingLimit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LicensingLimitDao {
    @Query("SELECT COUNT(*) FROM LicensingLimit")
    int count();

    @Query("SELECT VALUE FROM LicensingLimit WHERE abbr LIKE :abbr")
    float loadLicensingLimitValue(String abbr);

    @Insert
    void insertAll(LicensingLimit... licensingLimits);
}
