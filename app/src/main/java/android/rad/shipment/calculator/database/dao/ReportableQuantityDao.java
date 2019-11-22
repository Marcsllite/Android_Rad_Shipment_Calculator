package android.rad.shipment.calculator.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ReportableQuantityDao {
    @Query("SELECT VALUE FROM ReportableQuantity WHERE abbr LIKE :abbr")
    float loadReportableQuantityValue(String abbr);
}
