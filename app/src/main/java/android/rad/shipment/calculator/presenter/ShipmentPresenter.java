package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.AddActivityView;
import android.rad.shipment.calculator.view.EditActivityView;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShipmentPresenter extends BasePresenter {
    private final ShipmentActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;
    private boolean activityStarted = false;

    public ShipmentPresenter(@NonNull final ShipmentActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    public void onMenuButtonClicked() { mView.leaveActivity(); }

    public void onAddButtonClicked() {
        mView.launchActivity(mView.getApplicationContext(), AddActivityView.class);
    }

    public void onListChanged(){
        if(ShipmentActivityView.getIsotopeAdapter().isEmpty()) mView.disableCalculateButton();
        else mView.enableCalculateButton();
    }

    public void onShipmentIsotopeClicked(int index){
        Intent intent = new Intent(mView.getApplicationContext(), EditActivityView.class);
        intent.putExtra("index", index);

        mView.startActivity(intent);
    }

    public void onCalculateButtonClicked() {
        mView.showToast("User clicked the calculate button");
    }

    private class FetchA1Task implements AppTask<Float> {

        private final String mAbbr;

        public FetchA1Task(String abbr) { mAbbr = abbr; }

        @Override
        public Float execute() { return mShipmentCalculatorDB.getA1(mAbbr); }

        @Override
        public void onPostExecute(@Nullable Float result) {

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
        }
    }
    private class FetchShortLongTask implements AppTask<List<ShortLong>> {
        @Override
        public List<ShortLong> execute() {
            return mShipmentCalculatorDB.getAllShortLong();
        }

        @Override
        public void onPostExecute(@Nullable List<ShortLong> result) {

        }
    }

}
