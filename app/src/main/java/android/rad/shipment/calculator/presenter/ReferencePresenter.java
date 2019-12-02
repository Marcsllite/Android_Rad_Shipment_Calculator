package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.ReferenceActivityView;

import androidx.annotation.NonNull;

public class ReferencePresenter  extends BasePresenter {

    private final ReferenceActivityView mView;
    private final TaskExecutor mTaskExecutor;
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;

    public ReferencePresenter(@NonNull final ReferenceActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    public void onMenuButtonClicked() { mView.leaveActivity(); }
}
