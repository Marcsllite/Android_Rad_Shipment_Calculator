package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.model.Isotope;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.utils.Conversions;
import android.rad.shipment.calculator.view.AddDialogueView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddPresenter extends BasePresenter {
    private final AddDialogueView mView;  // connection to the reference activity view
    private final TaskExecutor mTaskExecutor;  // runs tasks in the background
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;  // data connection to the database
    private boolean isValidIso;  // true if isotope name is valid, otherwise false

    /**
     * Constructor to make an add presenter attached to the given add dialogue view
     *
     * @param view the add dialogue view that this presenter will be affecting
     * @param taskExecutor the taskExecutor to run background tasks (like database queries)
     * @param db the data connection to the database
     */
    public AddPresenter(@NonNull final AddDialogueView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    /**
     * Helper function to initialize the consistent mass checkbox based on whether it was checked previously
     *
     * @return true if the mass checkbox should be checked, otherwise false
     */
    public boolean initChckBoxSameMass() {
        // if the checkBox was ever checked when adding an isotope to the shipment
        if(BaseActivity.getShipment().getMassConsistent()) {
            // get the value from the isotope where the checkBox was last checked
            mView.getEditTxtMass().setText(Float.toString(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentMassIndex()).get_Mass()));

            // make sure the units are in microCuries (unit value gets saved to in Isotope object)
            mView.getSpinnerMassUnits_SI().setSelection(mView.getResources().getInteger(R.integer.baseIndex));
            // setting the form index based on hte previous isotope's value
            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentMassIndex()).get_MassUnit()) {
                case "grams":
                    mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.gramsIndex));
                    break;
                case "liters":
                    mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.litersIndex));
                    break;
            }
            return true;  // returning tru to note that checkBox should be selected
        } else { return false; }
    }

    /**
     * Helper function to initialize the consistent nature/state/form checkbox based on
     * whether it was checked previously
     *
     * @return true if the nature/state/form checkBox should be checked, otherwise false
     */
    public boolean initChckBoxSameNSF() {
        // if the checkBox was ever checked while adding an isotope to the shipment
        if(BaseActivity.getShipment().getNSFConsistent()) {
            // declaring variables to hold the previous isotope's spinner index values
            int natureIndex = 0, stateIndex = 0, formIndex = 0;

            // setting the nature index based on hte previous isotope's value
            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentNSFIndex()).get_Nature()) {
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

            // setting the state index based on hte previous isotope's value
            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentNSFIndex()).get_State()) {
                case "Solid":
                    stateIndex = 0;
                    break;
                case "Liquid":
                    stateIndex = 1;
                    break;
                case "Gas":
                    stateIndex = 2;
                    break;
            }

            // setting the form index based on hte previous isotope's value
            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentNSFIndex()).get_Form()) {
                case "Special":
                    formIndex = 0;
                    break;
                case "Normal":
                    formIndex = 1;
                    break;
            }

            // setting current isotope's value to match the previous isotope's
            mView.getSpinnerNature().setSelection(natureIndex);
            mView.getSpinnerState().setSelection(stateIndex);
            mView.getSpinnerForm().setSelection(formIndex);
            return true;  // returning true to note that checkBox should be selected
        } else { return false; }
    }

    /**
     * Helper function to convert the initial activity to microCuries before adding to Isotope object
     *
     * @param value the initial activity from the initial activity editText
     * @return the converted microCurie value of the given initial activity
     */
    private float convertToMicroCurie(float value) {
        // checking if the user selected becquerels or curies
        switch(mView.getSpinnerA0Units_Name().getSelectedItemPosition()) {
            case 0:  // user selected becquerel, converting to microCuries
                return Conversions.baseToMicro(
                        Conversions.BqToCi(
                                convertToBase(value, 
                                        mView.getSpinnerA0Units_SI().getSelectedItemPosition())));
            case 1:  // user selected curie, converting to microCuries
                return Conversions.baseToMicro(
                        convertToBase(value,
                                mView.getSpinnerA0Units_SI().getSelectedItemPosition()));
            default:
                return value;
        }
    }

    /**
     * Helper function to convert given value to its base unit
     *
     * @param value the value to be converted
     * @param index the index in the SI unit spinner that indicated the value's current unit
     * @return the converted value to its base unit
     */
    private float convertToBase(float value, int index) {
        switch(index) {
            case 0:  // user selected yotta
                return Conversions.YottaToBase(value);
            case 1:  // user selected zetta
                return Conversions.ZettaToBase(value);
            case 2:  // user selected exa
                return Conversions.ExaToBase(value);
            case 3:  // user selected peta
                return Conversions.PetaToBase(value);
            case 4:  // user selected tera
                return Conversions.TeraToBase(value);
            case 5:  // user selected giga
                return Conversions.GigaToBase(value);
            case 6:  // user selected mega
                return Conversions.MegaToBase(value);
            case 7:  // user selected kilo
                return Conversions.KiloToBase(value);
            case 8:  // user selected hecto
                return Conversions.HectoToBase(value);
            case 9:  // user selected deka
                return Conversions.DekaToBase(value);
            case 10:  // user selected base
                return value;
            case 11:  // user selected deci
                return Conversions.DeciToBase(value);
            case 12:  // user selected centi
                return Conversions.CentiToBase(value);
            case 13:  // user selected milli
                return Conversions.MilliToBase(value);
            case 14:  // user selected micro
                return Conversions.MicroToBase(value);
            case 15:  // user selected nano
                return Conversions.NanoToBase(value);
            case 16:  // user selected pico
                return Conversions.PicoToBase(value);
            case 17:  // user selected femto
                return Conversions.FemtoToBase(value);
            case 18:  // user selected atto
                return Conversions.AttoToBase(value);
            case 19:  // user selected zepto
                return Conversions.ZeptoToBase(value);
            case 20:  // user selected yocto
                return Conversions.YoctoToBase(value);
            default:
                return value;
        }
    }

    /**
     * Helper function to check if the form has been filled out correctly
     *
     * @return true if the form has been correctly filled, otherwise false
     */
    private boolean isValidForm() {
        int errors = 0;

        // Checking all parts of the form
        if(!isValidIso) {
            mView.setError(mView.getEditTxtIsoName(), "Invalid Isotope");
            errors++;
        } else { mView.setError(mView.getEditTxtIsoName(),null); }

        if(mView.showAdditionalInfoError()) errors++;

        if(mView.getEditTxtA0().getText().toString().equals("")) {
            mView.setError(mView.getEditTxtA0(),"Invalid Initial Activity");
            errors++;
        } else { mView.setError(mView.getEditTxtA0(),null); }

        if(mView.getEditTxtMass().getText().toString().equals("")) {
            mView.setError(mView.getEditTxtMass(),"Invalid Mass");
            errors++;
        } else { mView.setError(mView.getEditTxtMass(),null); }

        return errors == 0;
    }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the text in the isotope name editText is changed
     *
     * @param newText the new string in the isotope name editText
     */
    public void onNameTextChanged(String newText) {
        mTaskExecutor.async(new FetchIsotopeInfoTask(newText));
        mTaskExecutor.async(new FetchShortLongTask(newText));
    }

    /**
     * Listener function that is called when the text in the initial activity editText is changed
     *
     * @param newText the new string in the initial activity editText
     */
    public void onA0TextChanged(String newText) { if(newText.equals("0")) mView.getEditTxtA0().setText(""); }

    /**
     * Listener function that is called when any of the radio buttons are clicked
     */
    public void onRadioBtnClicked() { mView.showAdditionalInfoError(); }

    /**
     * Listener function that is called when the text in the mass editText is changed
     *
     * @param newText the new string in the mass editText
     */
    public void onMassTextChanged(String newText) {
        mView.getChckBoxSameMass().setChecked(false);
        if(newText.equals("0")) mView.getEditTxtMass().setText("");
    }

    /**
     * Listener function that is called when an si unit for the mass is selected from the spinner
     */
    public void onMassUnitSISelected(){ mView.getChckBoxSameMass().setChecked(false); }

    /**
     * Listener function that is called when a name unit for the mass is selected from the spinner
     *
     * @param index the index of the name that was selected from the spinner
     */
    public void onMassUnitNameSelected(int index){
        mView.getChckBoxSameMass().setChecked(false);

        switch(index) {
            case 0:  // user selected grams
                mView.getSpinnerState().setSelection(mView.getResources().getInteger(R.integer.solidIndex));
                mView.getChckBoxSameNSF().setChecked(false);
                break;
            case 1:  // user selected liters
                mView.getSpinnerState().setSelection(mView.getResources().getInteger(R.integer.liquidIndex));
                mView.getChckBoxSameNSF().setChecked(false);
                break;
        }
    }

    /**
     * Listener function that is called when a nature is selected from the nature spinner
     */
    public void onNatureSelected(){ mView.getChckBoxSameNSF().setChecked(false); }

    /**
     * Listener function that is called when a state is selected from the state spinner
     *
     * @param index the index of the state that was selected from the spinner
     */
    public void onStateSelected(int index){
        mView.getChckBoxSameNSF().setChecked(false);

        switch(index) {
            case 0: case 2:  // user selected solid or gas
                mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.gramsIndex));
                mView.getChckBoxSameMass().setChecked(false);
                break;
            case 1:  // user selected liquid
                mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.litersIndex));
                mView.getChckBoxSameMass().setChecked(false);
                break;
        }
    }

    /**
     * Listener function that is called when a form is selected from the form spinner
     */
    public void onFormSelected(){ mView.getChckBoxSameNSF().setChecked(false); }

    /**
     * Listener function that is called when the consistent mass checkBox is clicked
     */
    public void onChckBoxSameMassClicked() {
        if(mView.getChckBoxSameMass().isChecked() && BaseActivity.getShipment().getMassConsistent()) {
            mView.getEditTxtMass().setText(Float.toString(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentMassIndex()).get_Mass()));
            mView.getSpinnerMassUnits_SI().setSelection(mView.getResources().getInteger(R.integer.baseIndex));
            switch(mView.getMassUnit()){
                case "grams":
                    mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.gramsIndex));
                    break;
                case "liters":
                    mView.getSpinnerMassUnits_Name().setSelection(mView.getResources().getInteger(R.integer.litersIndex));
                    break;
            }
        }
    }

    /**
     * Listener function that is called when the consistent nature/state/form checkBox is clicked
     */
    public void onChckBoxSameNSFClicked() {
        if(mView.getChckBoxSameNSF().isChecked() && BaseActivity.getShipment().getNSFConsistent()) {
            int natureIndex = 0, stateIndex = 0, formIndex = 0;

            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentNSFIndex()).get_Nature()) {
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

            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentNSFIndex()).get_State()) {
                case "Solid":
                    stateIndex = 0;
                    break;
                case "Liquid":
                    stateIndex = 1;
                    break;
                case "Gas":
                    stateIndex = 2;
                    break;
            }

            switch(BaseActivity.getShipment().get(BaseActivity.getShipment().getConsistentNSFIndex()).get_Form()) {
                case "Special":
                    formIndex = 0;
                    break;
                case "Normal":
                    formIndex = 1;
                    break;
            }

            mView.getSpinnerNature().setSelection(natureIndex);
            mView.getSpinnerState().setSelection(stateIndex);
            mView.getSpinnerForm().setSelection(formIndex);
        }
    }

    /**
     * Listener function that is called when the cancel button is clicked
     */
    public void onBtnCancelClicked() { mView.leaveActivity(); }

    /**
     * Listener function that is called when the add button is clicked
     */
    public void onBtnAddClicked() {
        if(isValidForm()) {
            Isotope isotope = new Isotope(mView.getName(),
                    convertToMicroCurie(mView.getInitialActivity()),
                    convertToBase(mView.getMass(), mView.getSpinnerMassUnits_SI().getSelectedItemPosition()),
                    mView.getMassUnit(),
                    mView.getNature(),
                    mView.getState(),
                    mView.getForm());  // creating a new isotope

            if(mView.isShortLongEnabled())  isotope.set_ShortLong(mView.getShortLong());  // creating a new isotope
            else if(mView.isLungAbsEnabled()) isotope.set_LungAbs(mView.getLungAbs());  // creating a new isotope

            if(!BaseActivity.getShipment().isInShipment(isotope)) {
                if(mView.getChckBoxSameMass().isChecked() && !BaseActivity.getShipment().getMassConsistent()) {
                    BaseActivity.getShipment().setMassConsistent();
                    BaseActivity.getShipment().setConsistentMassIndex(BaseActivity.getShipment().getSize());
                }
                if(mView.getChckBoxSameNSF().isChecked() && !BaseActivity.getShipment().getNSFConsistent()) {
                    BaseActivity.getShipment().setNSFConsistent();
                    BaseActivity.getShipment().setConsistentNSFIndex(BaseActivity.getShipment().getSize());
                }
                BaseActivity.getShipment().addIsotopes(isotope);
                mView.leaveActivity();
            } else { mView.setError(mView.getEditTxtIsoName(), "Isotope already in Shipment"); }
        }
    }

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/

    /**
     * Custom task that checks if the isotope is valid and if it is whether or not the
     * lung absorption additional info section needs to be enabled
     */
    private class FetchIsotopeInfoTask implements AppTask<Boolean> {
        private String mAbbr;  // variable to hold the abbreviation of the isotope

        /**
         * Constructor for the fetch isotope info task
         *
         * @param abbr the abbreviation of the isotope
         */
        FetchIsotopeInfoTask(String abbr) { mAbbr = abbr; }

        @Override
        public Boolean execute() {
            mAbbr = mShipmentCalculatorDB.getAbbr(mAbbr);  // getting the abbreviation from the database
            return mAbbr != null;  // returning if the abbreviation was found
        }

        @Override
        public void onPostExecute(Boolean result) {
            isValidIso = result;  // saving whether or not the isotope was found in the database table

            // checking if the lung absorption section needs to be enabled for this isotope
            // NOTE: only certain uranium values required this (at the moment every uranium in the 230's does)
            if(result && mAbbr.contains("U-23")) mView.enableLungAbs(true);
            else mView.enableLungAbs(false);
        }
    }

    /**
     * Custom task that retrieves the short/long table contents and checks if the given isotope
     * is in it. If it is it enabled the short/long additional info section
     */
    private class FetchShortLongTask implements AppTask<List<ShortLong>> {
        private final String mAbbr;  // variable to hold the abbreviation of the isotope

        /**
         * Constructor for the fetch short/long lived task
         *
         * @param abbr the abbreviation of the isotope
         */
        FetchShortLongTask(String abbr) { mAbbr = abbr; }

        @Override
        public List<ShortLong> execute() { return mShipmentCalculatorDB.getAllShortLong(); }

        @Override
        public void onPostExecute(List<ShortLong> result) {
            mView.enableShortLong(false);  // starting off by disabling the short/long lived section

            // re-enabling the short/long lived section only if it's name is found in the shortLong database table
            for(ShortLong sl: result) {
               if(sl.getName().equalsIgnoreCase(mAbbr) || sl.getAbbr().equalsIgnoreCase(mAbbr)) mView.enableShortLong(true);
           }
        }
    }
}
