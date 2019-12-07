package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.view.MenuActivityView;
import android.rad.shipment.calculator.view.ReferenceActivityView;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MenuPresenter extends BasePresenter {

    private final MenuActivityView mView;  // connection to the menu activity view

    /**
     * Constructor to make a menu presenter attached to the given menu activity view
     *
     * @param view the menu activity view that this presenter will be affecting
     */
    public MenuPresenter(@NonNull final MenuActivityView view) { mView = view; }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView.showToast("Menu onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.showToast("Menu onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.showToast("Menu onPause");
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mView.showToast("Menu onSaveInstanceState");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.showToast("Menu onDestroy");
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mView.showToast("Menu onActivityResult");
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mView.showToast("Menu onRequestPermissionsResult");
    }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/
    /**
     * Listener function that is called when the logo on the menu page is clicked
     */
    public void onLogoClicked() {
        mView.launchBrowser(mView.getString(R.string.umlURL));
    }

    /**
     * Listener function that is called when the Shipment textView on the menu page is clicked
     */
    public void onShipmentButtonClicked() {
        mView.launchActivity(mView.getApplicationContext(), ShipmentActivityView.class);
    }

    /**
     * Listener function that is called when the Reference textView on the menu page is clicked
     */
    public void onReferenceButtonClicked() {
        mView.launchActivity(mView.getApplicationContext(), ReferenceActivityView.class);
    }
}
