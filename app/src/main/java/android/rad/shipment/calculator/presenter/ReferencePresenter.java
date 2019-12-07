package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.ReferenceActivityView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ReferencePresenter  extends BasePresenter {

    private final ReferenceActivityView mView;  // connection to the reference activity view
    private final TaskExecutor mTaskExecutor;  // runs tasks in the background
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;  // data connection to the database

    /**
     * Constructor to make a reference presenter attached to the given reference activity view
     *
     * @param view the reference activity view that this presenter will be affecting
     * @param taskExecutor the taskExecutor to run background tasks (like database queries)
     * @param db the data connection to the database
     */
    public ReferencePresenter(@NonNull final ReferenceActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView.showToast("Ref onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.showToast("Ref onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.showToast("Ref onPause");
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mView.showToast("Ref onSaveInstanceState");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.showToast("Ref onDestroy");
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mView.showToast("Ref onActivityResult");
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mView.showToast("Ref onRequestPermissionsResult");
    }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the menu button is clicked
     */
    public void onMenuButtonClicked() { mView.leaveActivity(); }

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/
    private class FetchA1Task implements AppTask<Float> {

        private final String mAbbr;

        public FetchA1Task(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() { return mShipmentCalculatorDB.getA1(mAbbr); }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " A1: " + result);
        }
    }
    private class FetchA2Task implements AppTask<Float> {

        private final String mAbbr;

        public FetchA2Task(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getA2(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " A2: " + result);
        }
    }
    private class FetchDecayConstantTask implements AppTask<Float> {

        private final String mAbbr;

        public FetchDecayConstantTask(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getDecayConstant(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " Decay Constant: " + result);
        }
    }
    private class FetchExemptConcentrationTask implements AppTask<Float> {

        private final String mAbbr;

        public FetchExemptConcentrationTask(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getExemptConcentration(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " Exempt Concentration: " + result);
        }
    }
    private class FetchExemptLimitTask implements AppTask<Float> {

        private final String mAbbr;

        public FetchExemptLimitTask(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getExemptLimit(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " Exempt Limit: " + result);
        }
    }
    private class FetchHalfLifeTask implements AppTask<Float> {

        private final String mAbbr;

        public FetchHalfLifeTask(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getHalfLife(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " Half Life: " + result);
        }
    }
    private class FetchIALimitedLimitTask implements AppTask<Float> {

        private final String mState, mForm;

        public FetchIALimitedLimitTask(String state, String form) {
            mState = state;
            mForm = form;
        }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getIALimitedMultiplier(mState, mForm);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mState + ", " + mForm + ": " + result);
        }
    }
    private class FetchIAPackageLimitTask implements AppTask<Float> {

        private final String mState, mForm;

        public FetchIAPackageLimitTask(String state, String form) {
            mState = state;
            mForm = form;
        }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getIAPackageLimit(mState, mForm);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mState + ", " + mForm + ": " + result);
        }
    }
    private class FetchIsotopeInfoTask implements AppTask<Isotopes> {

        private final String mAbbr;

        public FetchIsotopeInfoTask(String abbr) { mAbbr = abbr; }

        @Override
        public Isotopes execute() {
            return mShipmentCalculatorDB.getNameAndAbbr(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Isotopes result) {
//            assert result != null;
//            System.out.println(result.getName() + ", " + result.getAbbr());
        }
    }
    private class FetchLicensingLimitTask implements AppTask<Float> {

        private final String mAbbr;

        public FetchLicensingLimitTask(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getLicensingLimit(mAbbr);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mAbbr + " Licensing Limit: " + result);
        }
    }
    private class FetchLimitedLimitTask implements AppTask<Float> {

        private final String mState, mForm;

        public FetchLimitedLimitTask(String state, String form) {
            mState = state;
            mForm = form;
        }

        @Override
        public Float execute() {
            return mShipmentCalculatorDB.getLimitedLimit(mState, mForm);
        }

        @Override
        public void onPostExecute(@Nullable Float result) {
//            System.out.println(mState + ", " + mForm + ": " + result);
        }
    }
    private class FetchShortLongTask implements AppTask<List<ShortLong>> {
        @Override
        public List<ShortLong> execute() {
            return mShipmentCalculatorDB.getAllShortLong();
        }

        @Override
        public void onPostExecute(@Nullable List<ShortLong> result) {
            System.out.println("Short Long:");
            assert result != null;
            for(ShortLong sl: result) {
                System.out.println(sl.getName() + ", " + sl.getAbbr());
            }
            System.out.println("End Short Long:");
        }
    }
}
