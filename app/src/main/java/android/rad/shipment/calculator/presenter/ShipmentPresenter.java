package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.view.AddDialogueView;
import android.rad.shipment.calculator.view.EditDialogueView;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShipmentPresenter extends BasePresenter {
    private final ShipmentActivityView mView;  // connection to the reference activity view

    /**
     * Constructor to make a shipment presenter attached to the given shipment activity view
     *
     * @param view the shipment activity view that this presenter will be affecting
     */
    public ShipmentPresenter(@NonNull final ShipmentActivityView view){ mView = view; }

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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mView.showToast("Ship onSaveInstanceState");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.showToast("Ship onDestroy");
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mView.showToast("Ship onActivityResult");
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mView.showToast("Ship onRequestPermissionsResult");
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
}
