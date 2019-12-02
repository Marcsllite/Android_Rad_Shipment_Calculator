package android.rad.shipment.calculator.database;

import android.content.Context;
import android.rad.shipment.calculator.database.dao.A1Dao;
import android.rad.shipment.calculator.database.dao.A2Dao;
import android.rad.shipment.calculator.database.dao.DecayConstantDao;
import android.rad.shipment.calculator.database.dao.ExemptConcentrationDao;
import android.rad.shipment.calculator.database.dao.ExemptLimitDao;
import android.rad.shipment.calculator.database.dao.HalfLifeDao;
import android.rad.shipment.calculator.database.dao.IALimitedLimitDao;
import android.rad.shipment.calculator.database.dao.IAPackageLimitDao;
import android.rad.shipment.calculator.database.dao.IsotopesDao;
import android.rad.shipment.calculator.database.dao.LicensingLimitDao;
import android.rad.shipment.calculator.database.dao.LimitedLimitDao;
import android.rad.shipment.calculator.database.dao.ReportableQuantityDao;
import android.rad.shipment.calculator.database.tables.A1;
import android.rad.shipment.calculator.database.tables.A2;
import android.rad.shipment.calculator.database.tables.DecayConstant;
import android.rad.shipment.calculator.database.tables.ExemptConcentration;
import android.rad.shipment.calculator.database.tables.ExemptLimit;
import android.rad.shipment.calculator.database.tables.HalfLife;
import android.rad.shipment.calculator.database.tables.IALimitedLimit;
import android.rad.shipment.calculator.database.tables.IAPackageLimit;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.database.tables.LicensingLimit;
import android.rad.shipment.calculator.database.tables.LimitedLimit;
import android.rad.shipment.calculator.database.tables.ReportableQuantity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Isotopes.class, A1.class, A2.class, DecayConstant.class,
        ExemptConcentration.class, ExemptLimit.class, HalfLife.class, IALimitedLimit.class,
        IAPackageLimit.class, LicensingLimit.class, LimitedLimit.class, ReportableQuantity.class},
        version = 1, exportSchema = false)
public abstract class ShipmentCalculatorLocalDB extends RoomDatabase {
    private static volatile ShipmentCalculatorLocalDB INSTANCE;

    public abstract IsotopesDao isotopesDao();
    public abstract A1Dao a1Dao();
    public abstract A2Dao a2Dao();
    public abstract DecayConstantDao decayConstantDao();
    public abstract ExemptConcentrationDao exemptConcentrationDao();
    public abstract ExemptLimitDao exemptLimitDao();
    public abstract HalfLifeDao halfLifeDao();
    public abstract IALimitedLimitDao iaLimitedLimitDao();
    public abstract IAPackageLimitDao iaPackageLimitDao();
    public abstract LicensingLimitDao licensingLimitDao();
    public abstract LimitedLimitDao limitedLimitDao();
    public abstract ReportableQuantityDao reportableQuantityDao();
    
    public static ShipmentCalculatorLocalDB getInstance(Context context){
        if(INSTANCE == null) {
            synchronized (ShipmentCalculatorLocalDB.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShipmentCalculatorLocalDB.class, "ShipmentCalculatorLocal.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
