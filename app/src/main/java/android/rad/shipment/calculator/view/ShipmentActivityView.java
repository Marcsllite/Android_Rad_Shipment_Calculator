package android.rad.shipment.calculator.view;

import android.content.Context;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.presenter.ShipmentPresenter;
import android.rad.shipment.calculator.view.interfaces.ShipmentActivityViewInterface;

import androidx.annotation.NonNull;

public class ShipmentActivityView extends BaseActivity<ShipmentPresenter> implements ShipmentActivityViewInterface {
    @NonNull @Override
    protected ShipmentPresenter createPresenter(@NonNull Context context) {
        return null;
    }
}