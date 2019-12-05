package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.IAPackageLimit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface IAPackageLimitDao {
    @Query("SELECT COUNT(*) FROM IAPackageLimit")
    int count();

    @Query("SELECT VALUE FROM IAPackageLimit WHERE state LIKE :state AND form LIKE :form")
    float loadIAPackageLimitValue(String state, String form);

    @Insert
    void insertAll(IAPackageLimit... iaPackageLimits);
}
