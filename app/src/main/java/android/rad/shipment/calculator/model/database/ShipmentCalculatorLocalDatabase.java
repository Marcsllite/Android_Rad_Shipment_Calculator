package android.rad.shipment.calculator.model.database;

import android.rad.shipment.calculator.model.database.dao.A1Dao;
import android.rad.shipment.calculator.model.database.dao.A2Dao;
import android.rad.shipment.calculator.model.database.dao.DecayConstantDao;
import android.rad.shipment.calculator.model.database.dao.ExemptConcentrationDao;
import android.rad.shipment.calculator.model.database.dao.ExemptLimitDao;
import android.rad.shipment.calculator.model.database.dao.HalfLifeDao;
import android.rad.shipment.calculator.model.database.dao.IALimitedLimitDao;
import android.rad.shipment.calculator.model.database.dao.IAPackageLimitDao;
import android.rad.shipment.calculator.model.database.dao.IsotopesDao;
import android.rad.shipment.calculator.model.database.dao.LicensingLimitDao;
import android.rad.shipment.calculator.model.database.dao.LimitedLimitDao;
import android.rad.shipment.calculator.model.database.dao.ReportableQuantityDao;
import android.rad.shipment.calculator.model.database.tables.A1;
import android.rad.shipment.calculator.model.database.tables.A2;
import android.rad.shipment.calculator.model.database.tables.DecayConstant;
import android.rad.shipment.calculator.model.database.tables.ExemptConcentration;
import android.rad.shipment.calculator.model.database.tables.ExemptLimit;
import android.rad.shipment.calculator.model.database.tables.HalfLife;
import android.rad.shipment.calculator.model.database.tables.IALimiteLimit;
import android.rad.shipment.calculator.model.database.tables.IAPackageLimit;
import android.rad.shipment.calculator.model.database.tables.Isotopes;
import android.rad.shipment.calculator.model.database.tables.LicensingLimit;
import android.rad.shipment.calculator.model.database.tables.LimitedLimit;
import android.rad.shipment.calculator.model.database.tables.ReportableQuantity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Isotopes.class, A1.class, A2.class, DecayConstant.class,
        ExemptConcentration.class, ExemptLimit.class, HalfLife.class, IALimiteLimit.class,
        IAPackageLimit.class, LicensingLimit.class, LimitedLimit.class, ReportableQuantity.class},
        version = 1)
public abstract class ShipmentCalculatorLocalDatabase extends RoomDatabase {
    public IsotopesDao isotopesDao;
    public A1Dao a1Dao;
    public A2Dao a2Dao;
    public DecayConstantDao decayConstantDao;
    public ExemptConcentrationDao exemptConcentrationDao;
    public ExemptLimitDao exemptLimitDao;
    public HalfLifeDao halfLifeDao;
    public IALimitedLimitDao iaLimitedLimitDao;
    public IAPackageLimitDao iaPackageLimitDao;
    public LicensingLimitDao licensingLimitDao;
    public LimitedLimitDao limitedLimitDao;
    public ReportableQuantityDao reportableQuantityDao;
}
