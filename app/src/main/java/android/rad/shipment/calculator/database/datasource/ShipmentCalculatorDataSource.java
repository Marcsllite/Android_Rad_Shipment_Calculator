package android.rad.shipment.calculator.database.datasource;


import android.content.Context;
import android.rad.shipment.calculator.database.ShipmentCalculatorLocalDB;
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

public class ShipmentCalculatorDataSource implements IShipmentCalculatorDataSource {

    private final ShipmentCalculatorLocalDB mShipmentCalculatorDB;
    private final A1Dao mA1Dao;
    private final A2Dao mA2Dao;
    private final DecayConstantDao mDecayConstantDao;
    private final ExemptConcentrationDao mExemptConcentrationDao;
    private final ExemptLimitDao mExemptLimitDao;
    private final HalfLifeDao mHalfLifeDao;
    private final IALimitedLimitDao mIALimitedLimitDao;
    private final IAPackageLimitDao mIAPackageLmitDao;
    private final IsotopesDao mIsotopesDao;
    private final LicensingLimitDao mLicensingLimitDao;
    private final LimitedLimitDao mLimitedLimitDao;
    private final ReportableQuantityDao mReportableQuantityDao;

    public ShipmentCalculatorDataSource(Context context) {
        mShipmentCalculatorDB = ShipmentCalculatorLocalDB.getInstance(context);
        mA1Dao = mShipmentCalculatorDB.a1Dao();
        mA2Dao = mShipmentCalculatorDB.a2Dao();
        mDecayConstantDao = mShipmentCalculatorDB.decayConstantDao();
        mExemptConcentrationDao = mShipmentCalculatorDB.exemptConcentrationDao();
        mExemptLimitDao = mShipmentCalculatorDB.exemptLimitDao();
        mHalfLifeDao = mShipmentCalculatorDB.halfLifeDao();
        mIALimitedLimitDao = mShipmentCalculatorDB.iaLimitedLimitDao();
        mIAPackageLmitDao = mShipmentCalculatorDB.iaPackageLimitDao();
        mIsotopesDao = mShipmentCalculatorDB.isotopesDao();
        mLicensingLimitDao = mShipmentCalculatorDB.licensingLimitDao();
        mLimitedLimitDao = mShipmentCalculatorDB.limitedLimitDao();
        mReportableQuantityDao = mShipmentCalculatorDB.reportableQuantityDao();
    }


}
