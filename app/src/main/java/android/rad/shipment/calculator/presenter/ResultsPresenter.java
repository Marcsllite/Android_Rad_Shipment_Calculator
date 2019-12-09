package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.model.Isotope;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.ResultsActivityView;

import androidx.annotation.NonNull;

public class ResultsPresenter extends BasePresenter {

    private final ResultsActivityView mView;  // connection to the reference activity view
    private final TaskExecutor mTaskExecutor;  // runs tasks in the background
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;  // data connection to the database

    /**
     * Constructor to make a reference presenter attached to the given reference activity view
     *
     * @param view the reference activity view that this presenter will be affecting
     * @param taskExecutor the taskExecutor to run background tasks (like database queries)
     * @param db the data connection to the database
     */
    public ResultsPresenter(@NonNull final ResultsActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    public void calculateShipment() {
//        mView.showLoading();
        mTaskExecutor.async(new CalculateTask());
    }
    
    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the menu button is clicked
     */
    public void onMenuButtonClicked() { mView.leaveActivity(); }

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/

    /**
     * Custom task that calculates the classification of the shipment
     */
    private class CalculateTask implements AppTask<Void> {

        private String sClass, MASS, NATURE, STATE, FORM;

        /**
         * Constructor for the calculate task
         */
        CalculateTask() { }

        @Override
        public Void execute() {
            // finding classification of isotopes in shipment
            for (Isotope iso: BaseActivity.getShipment().getIsotopes()) {
                if (iso.exemptClass(mShipmentCalculatorDB.getExemptLimit(iso.get_DBName()),
                        mShipmentCalculatorDB.getExemptConcentration(iso.get_DBName()))) {
                    iso.set_IsotopeClass(0);
                } else {
                    if (iso.limitedClass(mShipmentCalculatorDB.getA1(iso.get_DBName()),
                            mShipmentCalculatorDB.getA2(iso.get_DBName()),
                                    mShipmentCalculatorDB.getIALimitedMultiplier(iso.get_State(), iso.get_Form()),
                                            mShipmentCalculatorDB.getLimitedLimit(iso.get_State(), iso.get_Form())))
                        iso.set_IsotopeClass(1);
                    else if (iso.typeAClass(mShipmentCalculatorDB.getA1(iso.get_DBName()),
                            mShipmentCalculatorDB.getA2(iso.get_DBName())))
                        iso.set_IsotopeClass(2);
                    else if (iso.HRCQClass(mShipmentCalculatorDB.getA1(iso.get_DBName()),
                            mShipmentCalculatorDB.getA2(iso.get_DBName())))
                        iso.set_IsotopeClass(8);
                    else if (iso.typeBClass(mShipmentCalculatorDB.getA1(iso.get_DBName()),
                            mShipmentCalculatorDB.getA2(iso.get_DBName())))
                        iso.set_IsotopeClass(4);
                }

                iso.set_RQFrac((float) (iso.get_AToday()/(mShipmentCalculatorDB.getReportableQuantity(iso.get_DBName())* 1000000.0))); // saving each isotope's RQFrac
                iso.set_ExemptLimit(mShipmentCalculatorDB.getExemptLimit(iso.get_DBName()));// saving each isotope's exempt limit
                iso.set_ExemptConcentration(mShipmentCalculatorDB.getExemptConcentration(iso.get_DBName()));// saving each isotope's exempt concetraion
                iso.set_LicensingLimit(mShipmentCalculatorDB.getLicensingLimit(iso.get_DBName()));// saving each isotope's licensing limit

                MASS = Float.toString(iso.get_Mass());
                        NATURE = iso.get_Nature();
                        STATE = iso.get_State();
                                FORM = iso.get_Form();

            }

            // finding the classification of the shipment
            sClass = BaseActivity.getShipment().findClass();

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
//            mView.hideLoading();
            mView.setTxtViewD0(BaseActivity.getShipment().get_D0());
            mView.setTxtViewMass(MASS);
            mView.setTxtViewNature(NATURE);
            mView.setTxtViewState(STATE);
            mView.setTxtViewForm(FORM);

            mView.setTxtViewLicExempt(BaseActivity.getShipment().get_isLicenseExempt());
            mView.setTxtViewClass(sClass);
        }
    }
}
