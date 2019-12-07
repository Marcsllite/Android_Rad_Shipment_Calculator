package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.view.AddDialogueView;
import android.rad.shipment.calculator.view.EditDialogueView;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.NonNull;

public class ShipmentPresenter extends BasePresenter {
    private final ShipmentActivityView mView;  // connection to the reference activity view

    /**
     * Constructor to make a shipment presenter attached to the given shipment activity view
     *
     * @param view the shipment activity view that this presenter will be affecting
     */
    public ShipmentPresenter(@NonNull final ShipmentActivityView view){ mView = view; }

    
    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/
    /**
     * Listener function that is called when the menu button is clicked 
     */
    public void onMenuButtonClicked() { mView.leaveActivity(); }

    /**
     * Listener function that is called when the add button is clicked 
     */
    public void onAddButtonClicked() { mView.launchActivity(mView.getApplicationContext(), AddDialogueView.class); }

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
