package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface IAPackageLimitDao {
    @Query("SELECT VALUE FROM IAPackageLimit WHERE state LIKE :state AND form LIKE :form")
    float loadIAPackageLimitValue(String state, String form);
}
