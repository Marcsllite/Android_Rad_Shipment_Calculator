package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.presenter.DatePresenter;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import java.util.Date;

public class DateDialogueView extends BaseActivity<DatePresenter> {
    // Declaring variables
    private DatePicker datePicker;
    
    @NonNull @Override
    protected DatePresenter createPresenter(@NonNull Context context) {
        return new DatePresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.dateTitle);  // setting the title for the date dialogue
        setContentView(R.layout.initial_date_dialog);  // showing the date dialogue

        // getting all the views from the add dialogue that need to be programmed
        datePicker = findViewById(R.id.datePicker);
        View btnCancel = findViewById(R.id.btnCancel);
        View btnOkay = findViewById(R.id.btnOkay);

        // creating custom listeners
        OnDateButtonsClicked onDateButtonsClicked = new OnDateButtonsClicked();

        // adding custom listeners to the views
        btnCancel.setOnClickListener(onDateButtonsClicked);
        btnOkay.setOnClickListener(onDateButtonsClicked);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    public Date getDate() { return new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());}

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom View.onClickListener to call the correct presenter function when any clickable view on
     * the date dialogue is interacted with
     */
    private class OnDateButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null) {
                switch (view.getId()) {
                   case R.id.btnCancel:
                        mPresenter.onBtnCancelClicked();
                        break;
                    case R.id.btnOkay:
                        mPresenter.onBtnOkayClicked();
                }
            }
        }
    }
}
