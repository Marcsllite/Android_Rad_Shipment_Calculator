package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.EditPresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class EditDialogueView extends BaseActivity<EditPresenter> {
    // Declaring variables
    private View editTxtIsoName;
    private View editTxtInitialActivity;
    private Spinner spinnerA0Units_SI;
    private Spinner spinnerA0Units_Name;
    private View radioGrpShortLong;
    private RadioButton radioBtnShortLived;
    private RadioButton radioBtnLongLived;
    private View radioGrpLungAbs;
    private RadioButton radioBtnSlowLungAbs;
    private RadioButton radioBtnMediumLungAbs;
    private RadioButton radioBtnFastLungAbs;
    private View editTxtMass;
    private Spinner spinnerMassUnits_SI;
    private Spinner spinnerMassUnits_Name;
    private View chckBoxSameMass;
    private Spinner spinnerNature;
    private Spinner spinnerState;
    private Spinner spinnerForm;
    private View chckBoxSameNSF;
    private boolean isShortLongEnabled;
    private boolean isLungAbsEnabled;
    private int _index;

    @NonNull @Override
    protected EditPresenter createPresenter(@NonNull Context context) {
        return new EditPresenter(this,  new AppTaskExecutor(this),new ShipmentCalculatorDataSource(this));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle(R.string.editTitle);  // setting the title for the edit dialogue
        setContentView(R.layout.edit_iso_dialogue);  // showing the edit dialogue

        // getting index of selected isotope in list (passed through launching intent)
        _index = getIntent().getIntExtra("index", getResources().getInteger(R.integer.defaultInt));

        // getting all the views from the add dialogue that need to be programmed
        editTxtIsoName = findViewById(R.id.editTxtIsoName);
        editTxtInitialActivity = findViewById(R.id.editTxtInitialActivity);
        spinnerA0Units_SI = findViewById(R.id.spinnerA0Units_SI);
        spinnerA0Units_Name = findViewById(R.id.spinnerA0Units_Name);
        radioGrpShortLong = findViewById(R.id.radioGrpShortLong);
        radioBtnShortLived = findViewById(R.id.radioBtnShortLived);
        radioBtnLongLived = findViewById(R.id.radioBtnLongLived);
        radioGrpLungAbs = findViewById(R.id.radioGrpLungAbs);
        radioBtnSlowLungAbs = findViewById(R.id.radioBtnSlowLungAbs);
        radioBtnMediumLungAbs = findViewById(R.id.radioBtnMediumLungAbs);
        radioBtnFastLungAbs = findViewById(R.id.radioBtnFastLungAbs);
        editTxtMass = findViewById(R.id.editTxtMass);
        spinnerMassUnits_SI = findViewById(R.id.spinnerMassUnits_SI);
        spinnerMassUnits_Name = findViewById(R.id.spinnerMassUnits_Name);
        chckBoxSameMass = findViewById(R.id.chckBoxSameMass);
        spinnerNature = findViewById(R.id.spinnerNature);
        spinnerState = findViewById(R.id.spinnerState);
        spinnerForm = findViewById(R.id.spinnerForm);
        chckBoxSameNSF = findViewById(R.id.chckBoxSameNSF);
        View btnCancel = findViewById(R.id.btnCancel);
        View btnDelete = findViewById(R.id.btnDelete);
        View btnEdit = findViewById(R.id.btnEdit);

        // initializing views
        setupSpinners();
        ((EditText) editTxtIsoName).setText(BaseActivity.getShipment().get(_index).get_Name());  // get name
        ((EditText) editTxtInitialActivity).setText(Float.toString(BaseActivity.getShipment().get(_index).get_A0()));  // get initial activity
        if("".equals(BaseActivity.getShipment().get(_index).get_ShortLong())) {  // get short long
            switch(BaseActivity.getShipment().get(_index).get_ShortLong()) {
                case "Short Lived":
                    radioBtnShortLived.toggle();
                    break;
                case "Long Lived":
                    radioBtnLongLived.toggle();
                    break;
            }
        }
        if("".equals(BaseActivity.getShipment().get(_index).get_LungAbs())) {  // get lung abs
            switch(BaseActivity.getShipment().get(_index).get_LungAbs()) {
                case "Slow Lung Absorption":
                    radioBtnSlowLungAbs.toggle();
                    break;
                case "Medium Lung Absorption":
                    radioBtnMediumLungAbs.toggle();
                    break;
                case "Fast Lung Absorption":
                    radioBtnFastLungAbs.toggle();
                    break;
            }
        }
        ((EditText) editTxtMass).setText(Float.toString(BaseActivity.getShipment().get(_index).get_Mass()));  // get mass

        // creating custom listeners
        OnSpinnerItemSelected onSpinnerItemSelected = new OnSpinnerItemSelected();
        OnNameTextChanged onNameTextChanged = new OnNameTextChanged();
        OnA0TextChanged onA0TextChanged = new OnA0TextChanged();
        OnMassTextChanged onMassTextChanged = new OnMassTextChanged();
        OnEditButtonsClicked onEditButtonsClicked = new OnEditButtonsClicked();

        // adding custom listeners to the views
        spinnerA0Units_SI.setOnItemSelectedListener(onSpinnerItemSelected);
        spinnerMassUnits_Name.setOnItemSelectedListener(onSpinnerItemSelected);
        spinnerA0Units_SI.setOnItemSelectedListener(onSpinnerItemSelected);
        spinnerA0Units_Name.setOnItemSelectedListener(onSpinnerItemSelected);
        ((EditText) editTxtIsoName).addTextChangedListener(onNameTextChanged);
        ((EditText) editTxtInitialActivity).addTextChangedListener(onA0TextChanged);
        ((EditText) editTxtMass).addTextChangedListener(onMassTextChanged);
        chckBoxSameMass.setOnClickListener(onEditButtonsClicked);
        chckBoxSameNSF.setOnClickListener(onEditButtonsClicked);
        btnCancel.setOnClickListener(onEditButtonsClicked);
        btnDelete.setOnClickListener(onEditButtonsClicked);
        btnEdit.setOnClickListener(onEditButtonsClicked);
        radioBtnShortLived.setOnClickListener(onEditButtonsClicked);
        radioBtnLongLived.setOnClickListener(onEditButtonsClicked);
        radioBtnSlowLungAbs.setOnClickListener(onEditButtonsClicked);
        radioBtnMediumLungAbs.setOnClickListener(onEditButtonsClicked);
        radioBtnFastLungAbs.setOnClickListener(onEditButtonsClicked);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    /**
     * Helper function to setup the spinners on the edit dialogue with options and set the default value
     */
    private void setupSpinners() {
        // creating the arrayAdapters from the resources arrays
        ArrayAdapter<CharSequence> UnitsAdapter_SI = ArrayAdapter.createFromResource(this, R.array.Units_SI, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> A0Adapter_Name = ArrayAdapter.createFromResource(this, R.array.AOUnits_Name, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> MassAdapter_Name = ArrayAdapter.createFromResource(this, R.array.MassUnits_Name, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> NatureAdapter = ArrayAdapter.createFromResource(this, R.array.NatureUnits, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> StateAdapter = ArrayAdapter.createFromResource(this, R.array.StateUnits, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> FormAdapter = ArrayAdapter.createFromResource(this, R.array.FormUnits, android.R.layout.simple_spinner_item);

        // setting how the spinners will look when the dropdown menu is enabled
        UnitsAdapter_SI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        A0Adapter_Name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MassAdapter_Name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FormAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // linking the adapters to the spinners
        spinnerA0Units_SI.setAdapter(UnitsAdapter_SI);
        spinnerA0Units_Name.setAdapter(A0Adapter_Name);
        spinnerMassUnits_SI.setAdapter(UnitsAdapter_SI);
        spinnerMassUnits_Name.setAdapter(MassAdapter_Name);
        spinnerNature.setAdapter(NatureAdapter);
        spinnerState.setAdapter(StateAdapter);
        spinnerForm.setAdapter(FormAdapter);

        // setting the default selection for each spinner
        spinnerA0Units_SI.setSelection(getResources().getInteger(R.integer.microIndex));
        spinnerA0Units_Name.setSelection(getResources().getInteger(R.integer.curieIndex));
        spinnerMassUnits_SI.setSelection(getResources().getInteger(R.integer.baseIndex));
        switch(BaseActivity.getShipment().get(_index).get_MassUnit()){
            case "grams":
                spinnerMassUnits_Name.setSelection(getResources().getInteger(R.integer.gramsIndex));
                break;
            case "liters":
                spinnerMassUnits_Name.setSelection(getResources().getInteger(R.integer.litersIndex));
                break;
        }
        switch(BaseActivity.getShipment().get(_index).get_Nature()) {
            case "Regular":
                spinnerNature.setSelection(getResources().getInteger(R.integer.regularIndex));
                break;
            case "Instrument":
                spinnerNature.setSelection(getResources().getInteger(R.integer.instrumentIndex));
                break;
            case "Article":
                spinnerNature.setSelection(getResources().getInteger(R.integer.articleIndex));
                break;
        }

        switch(BaseActivity.getShipment().get(_index).get_State()) {
            case "Solid":
                spinnerState.setSelection(getResources().getInteger(R.integer.solidIndex));
                break;
            case "Liquid":
                spinnerState.setSelection(getResources().getInteger(R.integer.liquidIndex));
                break;
            case "Gas":
                spinnerState.setSelection(getResources().getInteger(R.integer.gasIndex));
                break;
        }

        switch(BaseActivity.getShipment().get(_index).get_Form()) {
            case "Special":
                spinnerForm.setSelection(getResources().getInteger(R.integer.specialIndex));
                break;
            case "Normal":
                spinnerForm.setSelection(getResources().getInteger(R.integer.normalIndex));
                break;
        }
    }

    /**
     * Helper function to get whether the short/long lived additional info section is enabled
     *
     * @return whether the short/long lived additional info section is enabled
     */
    public boolean isShortLongEnabled() { return isShortLongEnabled; }

    /**
     * Helper function to get whether the lung absorption additional info section is enabled
     *
     * @return whether the lung absorption additional info section is enabled
     */
    public boolean isLungAbsEnabled() { return isLungAbsEnabled; }

    /**
     * Helper function to show or hide the additional info section asking about the isotope's halflife
     * NOTE: this section should only be enabled if the isotope has two halflives and a distinction
     *       between which one the user wants is needed
     *
     * @param isEnabled true if the radio buttons asking about the isotopes half life should be shown
     *                  otherwise false
     */
    public void enableShortLong(boolean isEnabled){
        isShortLongEnabled = isEnabled;  // setting variable to know if short/long lived radio buttons are enabled

        // resetting the values of the radio buttons
        radioBtnShortLived.setChecked(false);
        radioBtnLongLived.setChecked(false);

        // resetting any previous errors
        findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        if(!isEnabled) {
            // removing the radio buttons and the top line describing the additional info section
            radioGrpShortLong.setVisibility(View.GONE);
            findViewById(R.id.linearLayoutEditInfoTop).setVisibility(View.GONE);
        }
        else {
            // showing the radio buttons and the top line describing the additional info section
            radioGrpShortLong.setVisibility(View.VISIBLE);
            findViewById(R.id.linearLayoutEditInfoTop).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Helper function to show or hide the additional info section asking about the isotope's lung absorption
     * NOTE: this section should only be enabled if the isotope has different values depending on the
     *       lung absorption rate
     *
     * @param isEnabled true if the radio buttons asking about the isotopes lung absorption should be shown
     *                  otherwise false
     */
    public void enableLungAbs(boolean isEnabled){
        isLungAbsEnabled = isEnabled;  // setting variable to know if the lung absorption radio buttons are enabled

        // resetting the values of the radio buttons
        radioBtnSlowLungAbs.setChecked(false);
        radioBtnMediumLungAbs.setChecked(false);
        radioBtnFastLungAbs.setChecked(false);

        // resetting any previous errors
        findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        if(!isEnabled) {
            // removing the radio buttons and the top line describing the additional info section
            radioGrpLungAbs.setVisibility(View.GONE);
            findViewById(R.id.linearLayoutEditInfoTop).setVisibility(View.GONE);
        } else {
            // showing the radio buttons and the top line describing the additional info section
            radioGrpLungAbs.setVisibility(View.VISIBLE);
            findViewById(R.id.linearLayoutEditInfoTop).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Helper function to figure out made an error on the additional info section and display that an error was made
     *
     * @return true if an error was made otherwise false
     */
    public boolean showAdditionalInfoError(){
        // checking if the short/long radio buttons are enabled
        if(isShortLongEnabled) {
            // if none of the radio buttons are selected, show error and return true
            if (((RadioGroup)radioGrpShortLong).getCheckedRadioButtonId() == -1) {
                findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.invalidAdditionalInfo));
                return true;
            } else {  // else remove any errors and return false
                findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                return false;
            }
        } else if(isLungAbsEnabled) {  // checking if the lung absorption radio buttons are enabled
            // if none of the radio buttons are selected, show error and return true
            if (((RadioGroup)radioGrpLungAbs).getCheckedRadioButtonId() == -1) {
                findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.invalidAdditionalInfo));
                return true;
            } else {  // else remove any errors and return false
                findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                return false;
            }
        } else {  // if neither are enabled then remove any errors and return false
            findViewById(R.id.linearLayoutEditInfoMiddle).setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
            return false;
        }
    }

    /**
     * Helper function to set an error message on an editText from the edit dialogue
     *
     * @param editTxt the edit text to show the error on
     * @param error the error message to be shown or null to remove an error message
     */
    public void setError(EditText editTxt, String error) {
        if(editTxt.equals(editTxtIsoName)) {
            ((EditText) editTxtIsoName).setError(error);
        } else if (editTxt.equals(editTxtInitialActivity)) {
            ((EditText) editTxtInitialActivity).setError(error);
        } else if (editTxt.equals(editTxtMass)) {
            ((EditText) editTxtMass).setError(error);
        }
    }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/

    /**
     * Getter function to get the index of the Isotope that is currently being edited
     *
     * @return the index of the isotope currently being edited
     */
    public int getIndex() { return _index; }

    /**
     * Getter function to get the isotope name editText
     *
     * @return the isotope name editText
     */
    public EditText getEditTxtIsoName() { return ((EditText) editTxtIsoName);}

    /**
     * Getter function to get the initial activity edit text
     *
     * @return the initial activity edit text
     */
    public EditText getEditTxtA0() { return ((EditText) editTxtInitialActivity);}

    /**
     * Getter function to get the initial activity SI unit spinner
     *
     * @return the initial activity SI unit spinner
     */
    public Spinner getSpinnerA0Units_SI() { return spinnerA0Units_SI;}

    /**
     * Getter function to get the initial activity name unit spinner
     *
     * @return the initial activity name unit spinner
     */
    public Spinner getSpinnerA0Units_Name() { return spinnerA0Units_Name;}

    /**
     * Getter function to get the mass editText
     *
     * @return the mass editText
     */
    public EditText getEditTxtMass() { return ((EditText) editTxtMass);}

    /**
     * Getter function to get the consistent mass checkBox
     *
     * @return the consistent mass checkBox
     */
    public CheckBox getChckBoxSameMass() { return ((CheckBox) chckBoxSameMass);}

    /**
     * Getter function to get the mass SI unit spinner
     *
     * @return the mass SI unit spinner
     */
    public Spinner getSpinnerMassUnits_SI() { return spinnerMassUnits_SI;}

    /**
     * Getter function to get the mass name unit spinner
     *
     * @return the mass name unit spinner
     */
    public Spinner getSpinnerMassUnits_Name() { return spinnerMassUnits_Name;}

    /**
     * Getter function to get the nature spinner
     *
     * @return the nature spinner
     */
    public Spinner getSpinnerNature() { return spinnerNature;}

    /**
     * Getter function to get the state spinner
     *
     * @return the state spinner
     */
    public Spinner getSpinnerState() { return spinnerState;}

    /**
     * Getter function to get the form spinner
     *
     * @return the form spinner
     */
    public Spinner getSpinnerForm() { return spinnerForm;}

    /**
     * Getter function to get the consistent nature/state/form checkBox
     *
     * @return the consistent nature/state/form checkBox
     */
    public CheckBox getChckBoxSameNSF() { return ((CheckBox) chckBoxSameNSF);}

    /**
     * Getter function to get the isotope's name from the isotope name edit text
     *
     * @return the isotope's name from the isotope name edit text
     */
    public String getName() { return ((EditText) editTxtIsoName).getText().toString(); }

    /**
     * Getter function to get the isotope's initial activity from the initial activity editText
     *
     * @return the isotope's initial activity
     */
    public float getInitialActivity() { return Float.parseFloat(((EditText) editTxtInitialActivity).getText().toString()); }

    /**
     * Getter function to get whether the isotope is short or long lived from the additional info section
     *
     * @return the whether the isotope is short or long lived from the additional info section
     */
    public String getShortLong() {
        if(isShortLongEnabled) {
            switch(((RadioGroup) radioGrpShortLong).getCheckedRadioButtonId()) {
                case R.id.radioBtnShortLived: return radioBtnShortLived.getText().toString();
                case R.id.radioBtnLongLived: return radioBtnLongLived.getText().toString();
                default: return "";
            }
        } else return "";
    }

    /**
     * Getter function to get the isotope's lung absorption from the additional info section
     *
     * @return the isotope's lung absorption from the additional info section
     */
    public String getLungAbs() {
        if(isLungAbsEnabled) {
            switch(((RadioGroup) radioGrpLungAbs).getCheckedRadioButtonId()) {
                case R.id.radioBtnSlowLungAbs: return radioBtnSlowLungAbs.getText().toString();
                case R.id.radioBtnMediumLungAbs: return radioBtnMediumLungAbs.getText().toString();
                case R.id.radioBtnFastLungAbs: return radioBtnFastLungAbs.getText().toString();
                default: return "";
            }
        } else return "";
    }

    /**
     * Getter function to get the isotope's mass from the mass editText
     *
     * @return the isotope's mass
     */
    public float getMass() { return Float.parseFloat(((EditText) editTxtMass).getText().toString()); }

    /**
     * Getter function to get the isotope's mass unit name from the mass unit name spinner
     *
     * @return the isotope's mass unit (grams or liters)
     */
    public String getMassUnit() { return  spinnerMassUnits_Name.getSelectedItem().toString(); }

    /**
     * Getter function to get the isotope's nature from the nature spinner
     *
     * @return the isotope's nature
     */
    public String getNature() { return  spinnerNature.getSelectedItem().toString(); }

    /**
     * Getter function to get the isotope's state from the state spinner
     *
     * @return the isotope's state
     */
    public String getState() { return  spinnerState.getSelectedItem().toString(); }

    /**
     * Getter function to get the isotope's form from the form spinner
     *
     * @return the isotope's form
     */
    public String getForm() { return  spinnerForm.getSelectedItem().toString(); }

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom AdapterView.onItemSelectedListener to call the correct presenter function when any spinner
     * is selected
     */
    private class OnSpinnerItemSelected implements AdapterView.OnItemSelectedListener {
        @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // passing the index of the selected item to the presenter
            if(view != null) {
                switch (view.getId()) {
                    case R.id.spinnerMassUnits_SI:
                        mPresenter.onMassUnitSISelected();
                        break;
                    case R.id.spinnerMassUnits_Name:
                        mPresenter.onMassUnitNameSelected(i);
                        break;
                    case R.id.spinnerNature:
                        mPresenter.onNatureSelected();
                        break;
                    case R.id.spinnerState:
                        mPresenter.onStateSelected(i);
                        break;
                    case R.id.spinnerForm:
                        mPresenter.onFormSelected();
                        break;
                }
            }
        }

        @Override public void onNothingSelected(AdapterView<?> adapterView) { }
    }

    /**
     * Custom TextWatcher for the isotope name editText
     */
    private class OnNameTextChanged implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override public void afterTextChanged(Editable editable) { if(!"".equals(editable.toString())) mPresenter.onNameTextChanged(editable.toString()); }
    }

    /**
     * Custom TextWatcher for the initial activity editText
     */
    private class OnA0TextChanged implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override public void afterTextChanged(Editable editable) { if(!"".equals(editable.toString())) mPresenter.onA0TextChanged(editable.toString()); }
    }

    /**
     * Custom TextWatcher for the mass editText
     */
    private class OnMassTextChanged implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override public void afterTextChanged(Editable editable) { if(!"".equals(editable.toString())) mPresenter.onMassTextChanged(editable.toString()); }
    }

    /**
     * Custom View.onClickListener to call the correct presenter function when any clickable view on
     * the add dialogue is interacted with
     */
    private class OnEditButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null) {
                switch (view.getId()) {
                    case R.id.chckBoxSameMass:
                        mPresenter.onChckBoxSameMassClicked();
                        break;
                    case R.id.chckBoxSameNSF:
                        mPresenter.onChckBoxSameNSFClicked();
                        break;
                    case R.id.btnCancel:
                        mPresenter.onBtnCancelClicked();
                        break;
                    case R.id.btnDelete:
                        mPresenter.onBtnDeleteClicked();
                        break;
                    case R.id.btnEdit:
                        mPresenter.onBtnEditClicked();
                        break;
                    case R.id.radioBtnShortLived:
                    case R.id.radioBtnLongLived:
                    case R.id.radioBtnSlowLungAbs:
                    case R.id.radioBtnMediumLungAbs:
                    case R.id.radioBtnFastLungAbs:
                        mPresenter.onRadioBtnClicked();
                        break;
                }
            }
        }
    }
}
