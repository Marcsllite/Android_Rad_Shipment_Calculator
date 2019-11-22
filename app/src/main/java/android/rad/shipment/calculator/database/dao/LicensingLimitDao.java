package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface LicensingLimitDao {
    @Query("SELECT VALUE FROM LicensingLimit WHERE abbr LIKE :abbr")
    float loadLicensingLimitValue(String abbr);
}
