package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.model.Isotope;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.AddActivityView;
import android.rad.shipment.calculator.view.ShipmentActivityView;
import android.text.Editable;

import java.util.List;

import androidx.annotation.NonNull;

public class AddPresenter extends BasePresenter {
    private final AddActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;
    private boolean isValidIso;
    private List<ShortLong> shortLongs;
    private int indexA0UnitSI, indexA0Name, indexMassUnitSI, indexMassName, indexNature, indexState, indexForm;
    private float microCIA0, gramsMass, litersMass;
    private final String[] SIUnits, A0Name, MassName, Nature, State, Form;

    public AddPresenter(@NonNull final AddActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
        SIUnits = mView.getResources().getStringArray(R.array.Units_SI);
        A0Name = mView.getResources().getStringArray(R.array.AOUnits_Name);
        MassName = mView.getResources().getStringArray(R.array.MassUnits_Name);
        Nature = mView.getResources().getStringArray(R.array.NatureUnits);
        State = mView.getResources().getStringArray(R.array.StateUnits);
        Form = mView.getResources().getStringArray(R.array.FormUnits);
    }

    public void onAddTextChanged(Editable editable) {
        mTaskExecutor.async(new FetchIsotopeInfoTask(editable.toString()));
        mTaskExecutor.async(new FetchShortLongTask(editable.toString()));
    }

    public void onA0TextChanged(Editable editable) {
        if(editable.toString().equals("0") && editable.length() == 1) mView.getEditTxtA0().setText("");
    }

    public void onA0UnitSISelected(int index){ indexA0UnitSI = index; }

    public void onA0UnitNameSelected(int index){ indexA0Name = index; }

    public void onMassUnitSISelected(int index){ indexMassUnitSI = index; }

    public void onMassUnitNameSelected(int index){ indexMassUnitSI = index; }

    public void onNatureSelected(int index){
        indexNature = index;
        mView.getChckBoxSameNSF().setChecked(false);
    }

    public void onStateSelected(int index){
        indexState = index;
        mView.getChckBoxSameNSF().setChecked(false);

        switch(index) {
            case 0: case 2:
                mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.gramsIndex));
                break;
            case 1:
                mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.litersIndex));
                break;
        }
    }

    public void onFormSelected(int index){
        indexForm = index;
        mView.getChckBoxSameNSF().setChecked(false);
    }

    public void onChckBoxSameMassClicked() {
        mView.showToast("User clicked the consistent mass checkbox");
        if(mView.getChckBoxSameMass().isChecked()) {
            if (BaseActivity.getShipment().getIsotopes().size() > 0) {
                mView.getEditTxtMass().setText(Float.toString(ShipmentActivityView.getIsotopeAdapter().getItem(0).get_Mass()));
                mView.getSpinnerMassUnits_SI().setSelection(mView.getResources().getInteger(R.integer.microIndex));
                mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.curieIndex));
            }
        }
    }

    public boolean initChckBoxSameNSF() {
        return BaseActivity.getShipment().getIsotopes().size() > 0;
    }

    public void onChckBoxSameNSFClicked() {
        mView.showToast("User clicked the the consistent nature, state, form checkbox");
        if(mView.getChckBoxSameNSF().isChecked()) {
            if (BaseActivity.getShipment().getIsotopes().size() > 0) {
                int natureIndex = 0, stateIndex = 0, formIndex = 0;

                switch(BaseActivity.getShipment().getIsotopes().get(0).get_Nature()) {
                    case "Regular":
                        natureIndex = 0;
                        break;
                    case "Instrument":
                        natureIndex = 1;
                        break;
                    case "Article":
                        natureIndex = 2;
                        break;
                }

                switch(BaseActivity.getShipment().getIsotopes().get(0).get_State()) {
                    case "Solid":
                        natureIndex = 0;
                        break;
                    case "Liquid":
                        natureIndex = 1;
                        break;
                    case "Gas":
                        natureIndex = 2;
                        break;
                }

                switch(BaseActivity.getShipment().getIsotopes().get(0).get_Form()) {
                    case "Special":
                        natureIndex = 0;
                        break;
                    case "Normal":
                        natureIndex = 1;
                        break;
                }

                mView.getSpinnerNature().setSelection(natureIndex);
                mView.getSpinnerState().setSelection(stateIndex);
                mView.getSpinnerForm().setSelection(formIndex);
            }
        }
    }

    public void onBtnCancelClicked() {
        mView.showToast("User clicked the cancel button");
        mView.leaveActivity();
    }

    public void onBtnAddClicked() {
        if(isValidForm()) {
            Isotope isotope = new Isotope(mView.getIsoName(), convertToMicroCurie(mView.getInitialActivity()), convertToBase(mView.getMass()), mView.getNature(), mView.getState(), mView.getForm());  // creating a new isotope
            if(!BaseActivity.getShipment().isInShipment(isotope)) {
                ShipmentActivityView.getIsotopeAdapter().add(isotope);
                mView.leaveActivity();
            } else {
                mView.setError(mView.getEditTxtIsoName(), "Isotope already in Shipment");
            }
        }
    }

    private float convertToMicroCurie(float value) {
        float ret = value;
        
        return ret;
    }

    private float convertToBase(float value) {
        float ret = value;
        return ret;
    }

    private boolean isValidForm() {
        int errors = 0;

        // Checking all parts of the form
        if(!isValidIso) {
            mView.setError(mView.getEditTxtIsoName(), "Invalid Isotope");
            errors++;
        } else {
            if(--errors < 0) errors = 0;
            mView.setError(mView.getEditTxtIsoName(),null);
        }

        if(mView.getEditTxtA0().getText().toString().equals("")) {
            mView.setError(mView.getEditTxtA0(),"Invalid Initial Activity");
            errors++;
        } else {
            if(--errors < 0) errors = 0;
            mView.setError(mView.getEditTxtA0(),null);
        }

        if(mView.getEditTxtMass().getText().toString().equals("") && !mView.getChckBoxSameMass().isChecked()) {
            mView.setError(mView.getEditTxtMass(),"Invalid Mass");
            errors++;
        } else {
            if(--errors < 0) errors = 0;
            mView.setError(mView.getEditTxtMass(),null);
        }

        return errors == 0;
    }

    private class FetchIsotopeInfoTask implements AppTask<Boolean> {

        private String mAbbr;

        public FetchIsotopeInfoTask(String abbr) { mAbbr = abbr; }

        @Override
        public Boolean execute() {
            mAbbr = mShipmentCalculatorDB.getAbbr(mAbbr);
            return mAbbr != null;
        }

        @Override
        public void onPostExecute(Boolean result) {
            isValidIso = result;
            if(result && mAbbr.contains("U-23")) mView.enableLungAbs(true);
            else mView.enableLungAbs(false);
        }
    }

    private class FetchShortLongTask implements AppTask<List<ShortLong>> {
        private final String mAbbr;

        public FetchShortLongTask(String abbr) { mAbbr = abbr; }

        @Override
        public List<ShortLong> execute() {
            return mShipmentCalculatorDB.getAllShortLong();
        }

        @Override
        public void onPostExecute(List<ShortLong> result) {
            mView.enableShortLong(false);
           for(ShortLong sl: result) {
               if(sl.getName().equalsIgnoreCase(mAbbr) || sl.getAbbr().equalsIgnoreCase(mAbbr)) mView.enableShortLong(true);
           }
        }
    }
}
