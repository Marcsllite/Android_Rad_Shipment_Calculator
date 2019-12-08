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
import android.rad.shipment.calculator.database.dao.ShortLongDao;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.database.tables.ShortLong;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ShipmentCalculatorDataSource implements IShipmentCalculatorDataSource {

    ShipmentCalculatorLocalDB mShipmentCalculatorDB;
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
    private final ShortLongDao mShortLongDao;

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
        mShortLongDao = mShipmentCalculatorDB.shortLongDao();
    }

    public ShipmentCalculatorLocalDB getInstance() { return mShipmentCalculatorDB; }

    @Override public List<Isotopes> getAllValidIsos() { return mIsotopesDao.loadAllIsotopes(); }

    @Override public LiveData<List<Isotopes>> searchIsotope(String searchText) { return mIsotopesDao.searchIsotope(searchText); }

    @Override public String getAbbr(String name) { return mIsotopesDao.loadIsotopeAbbr(name); }

    @Override public String getName(String abbr) { return mIsotopesDao.loadIsotopeName(abbr); }

    @Override public Isotopes getNameAndAbbr(String abbr) { return mIsotopesDao.loadIsotopeNameAndAbbr(abbr); }

    @Override public List<ShortLong> getAllShortLong() { return mShortLongDao.loadAllShortLong(); }

    @Override public String getShortLong(String name) { return mShortLongDao.loadShortLongAbbr(name); }

    @Override public float getA1(String abbr) { return mA1Dao.loadA1Value(abbr); }

    @Override public float getA2(String abbr) { return mA2Dao.loadA2Value(abbr); }

    @Override public float getDecayConstant(String abbr) { return mDecayConstantDao.loadDecayConstantValue(abbr); }

    @Override public float getExemptConcentration(String abbr) { return mExemptConcentrationDao.loadExemptConcentrationValue(abbr); }

    @Override public float getExemptLimit(String abbr) { return mExemptLimitDao.loadExemptLimitValue(abbr); }

    @Override public float getHalfLife(String abbr) { return mHalfLifeDao.loadHalfLifeValue(abbr); }

    @Override public float getIALimitedMultiplier(String state, String form) { return mIALimitedLimitDao.loadIALimitedLimitValue(state, form); }

    @Override public float getIAPackageLimit(String state, String form) { return mIAPackageLmitDao.loadIAPackageLimitValue(state, form); }

    @Override public float getLicensingLimit(String abbr) { return mLicensingLimitDao.loadLicensingLimitValue(abbr); }

    @Override public float getLimitedLimit(String state, String form) { return mLimitedLimitDao.loadLimitedLimitValue(state, form); }

    @Override public float getReportableQuantity(String abbr) { return mReportableQuantityDao.loadReportableQuantityValue(abbr); }
}
