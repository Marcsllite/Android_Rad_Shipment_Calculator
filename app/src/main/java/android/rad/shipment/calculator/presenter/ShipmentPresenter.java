package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.AddDialogueView;
import android.rad.shipment.calculator.view.DateDialogueView;
import android.rad.shipment.calculator.view.EditDialogueView;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ShipmentPresenter extends BasePresenter {
    private final ShipmentActivityView mView;  // connection to the reference activity view
    private final TaskExecutor mTaskExecutor;  // runs tasks in the background
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;  // data connection to the database

    /**
     * Constructor to make an add presenter attached to the given add dialogue view
     *
     * @param view the add dialogue view that this presenter will be affecting
     * @param taskExecutor the taskExecutor to run background tasks (like database queries)
     * @param db the data connection to the database
     */
    public ShipmentPresenter(@NonNull final ShipmentActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView.showToast("Ship onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.hideLoading();
    }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/
    /**
     * Listener function that is called when the menu button is clicked 
     */
    public void onMenuButtonClicked() { mView.leaveActivity(); }

    /**
     * Listener function that is called when the add button is clicked 
     */
    public void onAddButtonClicked() {
        mView.showLoading();
        mView.launchActivity(mView.getApplicationContext(), AddDialogueView.class);
    }

    /**
     * Listener function that is called when the calculate button is clicked 
     */
    public void onCalculateButtonClicked() {
        mView.showLoading();
        if(BaseActivity.getShipment().get_ReferenceDate() == null ) mView.launchActivity(mView.getApplicationContext(), DateDialogueView.class);
        else mTaskExecutor.async(new CalculateTask());
    }

    /**
     * Listener function that is called when the list is modified
     */
    public void onListChanged(){
        if(BaseActivity.getShipment().isEmpty()) mView.disableCalculateButton();
        else mView.enableCalculateButton();
    }

    /**
     * Listener function that is called when a list item is clicked
     * 
     * @param index the index of the item that was clicked
     */
    public void onShipmentIsotopeClicked(int index){
        Intent intent = new Intent(mView.getApplicationContext(), EditDialogueView.class);
        intent.putExtra("index", index);

        mView.startActivity(intent);
    }

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
           return null;
        }

        @Override
        public void onPostExecute(Void result) {
            mView.hideLoading();
            mView.showToast("Done Calculating");
        }
    }
}
