package android.rad.shipment.calculator.database.dao;

import android.rad.shipment.calculator.database.tables.Isotopes;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface IsotopesDao {
    @Query("SELECT COUNT(*) FROM Isotopes")
    int count();

    @Query("SELECT * FROM Isotopes WHERE name LIKE :searchText or abbr LIKE :searchText")
    LiveData<List<Isotopes>> searchIsotope(String searchText);

    @Query("SELECT name FROM Isotopes WHERE abbr LIKE :abbr OR name LIKE :abbr")
    String loadIsotopeName(String abbr);

    @Query("SELECT abbr FROM Isotopes WHERE name LIKE :name OR abbr LIKE :name")
    String loadIsotopeAbbr(String name);

    @Query("SELECT name, abbr FROM Isotopes WHERE abbr LIKE :abbr OR name LIKE :abbr")
    Isotopes loadIsotopeNameAndAbbr(String abbr);

    @Query("SELECT name, abbr FROM Isotopes")
    List<Isotopes> loadAllIsotopes();

    @Insert
    void insertAll(Isotopes... isotopes);
}
