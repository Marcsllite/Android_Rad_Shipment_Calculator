package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.ReportableQuantity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ReportableQuantityDao {
    @Query("SELECT COUNT(*) FROM ReportableQuantity")
    int count();

    @Query("SELECT VALUE FROM ReportableQuantity WHERE abbr LIKE :abbr")
    float loadReportableQuantityValue(String abbr);

    @Insert
    void insertAll(ReportableQuantity... reportableQuantities);
}
