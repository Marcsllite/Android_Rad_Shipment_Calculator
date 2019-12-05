package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.model.Isotope;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.EditActivityView;
import android.text.Editable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EditPresenter extends BasePresenter {
    private final EditActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;
    private boolean isValidIso;
    private List<ShortLong> shortLongs;

    public EditPresenter(@NonNull final EditActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
        isValidIso = false;
        mTaskExecutor.async(new FetchShortLongTask());
    }

    public void onEditTextChanged(Editable editable) {
        mView.showToast("User changed the Isotope Name");
        mTaskExecutor.async(new FetchIsotopeInfoTask(editable.toString()));
        if(isValidIso) {

        }
    }

    public void onChckBoxSameMassClicked() {
        mView.showToast("User clicked the consistent mass checkbox");
    }

    public void onChckBoxSameNSFClicked() {
        mView.showToast("User clicked the the consistent nature, state, form checkbox");
    }

    public void onBtnCancelClicked() {
        mView.showToast("User clicked the cancel button");
        mView.leaveActivity();
    }

    public void onBtnDeleteClicked() {
        mView.showToast("User clicked the delete button");
        BaseActivity.getShipment().getIsotopes().remove(mView.getIndex());
        mView.leaveActivity();
    }

    public void onBtnEditClicked() {
        mView.showToast("User clicked the edit button");
        if(isFormFilled()){
            Isotope isotope = new Isotope(mView.getIsoName(), mView.getInitialActivity(), mView.getMass(), mView.getNature(), mView.getState(), mView.getForm());  // creating a new isotope
            BaseActivity.getShipment().updateIsotope(mView.getIndex(), isotope);
        }
    }

    private boolean isFormFilled(){
        return false;
    }

    private class FetchIsotopeInfoTask implements AppTask<Boolean> {

        private final String mAbbr;

        public FetchIsotopeInfoTask(String abbr) { mAbbr = abbr; }

        @Override
        public Boolean execute() {
            return mShipmentCalculatorDB.getNameAndAbbr(mAbbr) == null;
        }

        @Override
        public void onPostExecute(@Nullable Boolean result) {
            isValidIso = result;
        }
    }

    private class FetchShortLongTask implements AppTask<List<ShortLong>> {
        @Override
        public List<ShortLong> execute() {
            return mShipmentCalculatorDB.getAllShortLong();
        }

        @Override
        public void onPostExecute(@Nullable List<ShortLong> result) {
           shortLongs = result;
        }
    }
}
