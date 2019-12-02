package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.view.MenuActivityView;
import android.rad.shipment.calculator.view.ReferenceActivityView;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.NonNull;

public class MenuPresenter extends BasePresenter {

    private final MenuActivityView mView;

    public MenuPresenter(@NonNull final MenuActivityView view) {
        mView = view;
    }

    public void onLogoClicked() {
        mView.showToast("User clicked the UMass logo");
    }

    public void onShipmentButtonClicked() {
        mView.launchActivity(mView.getApplicationContext(), ShipmentActivityView.class);
    }

    public void onReferenceButtonClicked() {
        mView.launchActivity(mView.getApplicationContext(), ReferenceActivityView.class);
    }
}
