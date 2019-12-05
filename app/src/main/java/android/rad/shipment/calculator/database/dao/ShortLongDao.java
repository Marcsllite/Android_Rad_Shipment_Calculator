package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.ShortLong;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ShortLongDao {
    @Query("SELECT COUNT(*) FROM ShortLong")
    int count();

    @Query("SELECT name FROM ShortLong WHERE abbr LIKE :abbr OR name LIKE :abbr")
    String loadShortLongName(String abbr);

    @Query("SELECT abbr FROM ShortLong WHERE name LIKE :name OR abbr LIKE :name")
    String loadShortLongAbbr(String name);

    @Query("SELECT name, abbr FROM ShortLong")
    List<ShortLong> loadAllShortLong();

    @Insert
    void insertAll(ShortLong... shortLongs);
}
