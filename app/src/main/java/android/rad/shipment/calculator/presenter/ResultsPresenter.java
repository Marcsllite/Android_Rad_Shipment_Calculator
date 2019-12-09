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
        /**
         * Constructor for the calculate task
         */
        CalculateTask() { }

        @Override
        public Void execute() {
            // finding classification of isotopes in shipment
            boolean lim;
            for (Isotope iso: BaseActivity.getShipment().getIsotopes()) {
                if (iso.exemptClass()) {
                    iso.set_IsotopeClass(0);
                }
                else {
                    if (lim = iso.limitedClass())
                        iso.set_IsotopeClass(1);
                    else if (iso.typeAClass() && !lim)
                        iso.set_IsotopeClass(2);
                    else if (iso.HRCQClass())
                        iso.set_IsotopeClass(8);
                    else if (iso.typeBClass())
                        iso.set_IsotopeClass(4);
                }
            }

            // finding the classification of the shipment
            sClassOut = findClass();

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
//            mView.hideLoading();
            mView.showToast("Done Calculating");
        }
    }
}
