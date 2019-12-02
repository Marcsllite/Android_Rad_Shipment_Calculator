package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.NonNull;

public class ShipmentPresenter extends BasePresenter {
    private final ShipmentActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;

    public ShipmentPresenter(@NonNull final ShipmentActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    public void onMenuButtonClicked() { mView.leaveActivity(); }

    public void onAddButtonClicked() { mView.showToast("User clicked the add button"); }

}
