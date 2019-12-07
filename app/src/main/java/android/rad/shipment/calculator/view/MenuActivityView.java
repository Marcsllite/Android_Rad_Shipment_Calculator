package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.presenter.MenuPresenter;
import android.view.View;

import androidx.annotation.NonNull;

public class MenuActivityView extends BaseActivity<MenuPresenter> {
    @NonNull @Override
    protected MenuPresenter createPresenter(@NonNull Context context) { return new MenuPresenter(this); }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_layout);  // showing the menu page

        // getting all the views from the menu page that need to be programmed
        final View logo = findViewById(R.id.imgViewColorLogo);
        final View shipmentBtn = findViewById(R.id.btnShipment);
        final View referenceBtn = findViewById(R.id.btnReference);

        // creating custom onClick listener
        OnMenuButtonsClicked onMenuButtonsClicked = new OnMenuButtonsClicked();

        // adding custom on click listener to views
        logo.setOnClickListener(onMenuButtonsClicked);
        referenceBtn.setOnClickListener(onMenuButtonsClicked);
        shipmentBtn.setOnClickListener(onMenuButtonsClicked);
    }

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom View.onClickListener to call the correct presenter function when
     * any clickable view on the menu page is interacted with
     */
    private class OnMenuButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null) {
                switch (view.getId()) {
                    case R.id.imgViewColorLogo:
                        mPresenter.onLogoClicked();
                        break;
                    case R.id.btnShipment:
                        mPresenter.onShipmentButtonClicked();
                        break;
                    case R.id.btnReference:
                        mPresenter.onReferenceButtonClicked();
                        break;
                }
            }
        }
    }
}
