package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.view.DateDialogueView;

import androidx.annotation.NonNull;

public class DatePresenter extends BasePresenter {
    private final DateDialogueView mView;  // connection to the reference activity view
    private boolean isValidIso;  // true if isotope name is valid, otherwise false

    /**
     * Constructor to make an date presenter attached to the given date dialogue view
     *
     * @param view the date dialogue view that this presenter will be affecting
     */
    public DatePresenter(@NonNull final DateDialogueView view){ mView = view; }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the cancel button is clicked
     */
    public void onBtnCancelClicked() { mView.leaveActivity(); }

    /**
     * Listener function that is called when the add button is clicked
     */
    public void onBtnOkayClicked() {
        BaseActivity.getShipment().set_ReferenceDate(mView.getDate());
        mView.leaveActivity();
    }
}
