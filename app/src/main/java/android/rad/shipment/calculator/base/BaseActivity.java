package android.rad.shipment.calculator.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.rad.shipment.calculator.model.Shipment;
import android.rad.shipment.calculator.utils.CSVData;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class  BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {

    protected Presenter mPresenter;
    private static Shipment mShipment;

    @NonNull
    protected abstract Presenter createPresenter(@NonNull final Context context);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter(this);
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void launchBrowser(String url) {
        // making sure url is proper
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.resolveActivity(getPackageManager());
        startActivity(intent);
    }

    public void launchActivity(Context context, Class goTo) {
        Intent intent = new Intent(context, goTo);
        startActivity(intent);
    }

    public synchronized static Shipment getShipment() {
        if (mShipment == null) mShipment = new Shipment();
        return mShipment;
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void leaveActivity() { finish(); }

    /**
     * @author https://stackoverflow.com/users/7241363/mcastro
     * link: https://stackoverflow.com/questions/44167111/android-room-simple-select-query-cannot-access-database-on-the-main-thread
     */
    protected static class initDBAsyncTask extends AsyncTask<Void, Void, Void> {

        //Prevent leak
        private WeakReference<Activity> weakActivity;
        private IsotopesDao isotopesDao;
        private A1Dao a1Dao;
        private A2Dao a2Dao;
        private DecayConstantDao decayConstantDao;
        private ExemptConcentrationDao exemptConcentrationDao;
        private ExemptLimitDao exemptLimitDao;
        private HalfLifeDao halfLifeDao;
        private IALimitedLimitDao iaLimitedLimitDao;
        private IAPackageLimitDao iaPackageLimitDao;
        private LicensingLimitDao licensingLimitDao;
        private LimitedLimitDao limitedLimitDao;
        private ReportableQuantityDao reportableQuantityDao;
        private ShortLongDao shortLongDao;

        public initDBAsyncTask(Activity activity, ShipmentCalculatorLocalDB db) {
            weakActivity = new WeakReference<>(activity);
            isotopesDao = db.isotopesDao();
            a1Dao = db.a1Dao();
            a2Dao = db.a2Dao();
            decayConstantDao = db.decayConstantDao();
            exemptConcentrationDao = db.exemptConcentrationDao();
            exemptLimitDao = db.exemptLimitDao();
            halfLifeDao = db.halfLifeDao();
            iaLimitedLimitDao = db.iaLimitedLimitDao();
            iaPackageLimitDao = db.iaPackageLimitDao();
            licensingLimitDao = db.licensingLimitDao();
            limitedLimitDao = db.limitedLimitDao();
            reportableQuantityDao = db.reportableQuantityDao();
            shortLongDao = db.shortLongDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(a1Dao.count() == 0) a1Dao.insertAll(CSVData.getA1Data(weakActivity.get()));
        
            if(a2Dao.count() == 0) a2Dao.insertAll(CSVData.getA2Data(weakActivity.get())); 
               
            if(decayConstantDao.count() == 0) decayConstantDao.insertAll(CSVData.getDecayConstantData(weakActivity.get()));
        
            if(exemptConcentrationDao.count() == 0)  exemptConcentrationDao.insertAll(CSVData.getExemptConcentrationData(weakActivity.get()));
        
            if(exemptLimitDao.count() == 0)  exemptLimitDao.insertAll(CSVData.getExemptLimitData(weakActivity.get()));
        
            if(halfLifeDao.count() == 0)  halfLifeDao.insertAll(CSVData.getHalfLifeData(weakActivity.get())); 
               
            if(iaLimitedLimitDao.count() == 0)  iaLimitedLimitDao.insertAll(CSVData.getIALimitedLimitData(weakActivity.get())); 
               
            if(iaPackageLimitDao.count() == 0)  iaPackageLimitDao.insertAll(CSVData.getIAPackageLimitData(weakActivity.get())); 
                
            if(isotopesDao.count() == 0)  isotopesDao.insertAll(CSVData.getIsotopesData(weakActivity.get())); 
               
            if(licensingLimitDao.count() == 0)  licensingLimitDao.insertAll(CSVData.getLicensingLimitData(weakActivity.get())); 
                
            if(limitedLimitDao.count() == 0)  limitedLimitDao.insertAll(CSVData.getLimitedLimitData(weakActivity.get()));
        
            if(reportableQuantityDao.count() == 0)  reportableQuantityDao.insertAll(CSVData.getReportableQuantityData(weakActivity.get())); 
                
            if(shortLongDao.count() == 0)  shortLongDao.insertAll(CSVData.getShortLongData(weakActivity.get())); 
            
            return null;
        }

        @Override protected void onPostExecute(Void result) {
        }
    }
}
