package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.ShipmentPresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.rad.shipment.calculator.view.interfaces.ShipmentActivityViewInterface;
import android.view.View;

import androidx.annotation.NonNull;

public class ShipmentActivityView extends BaseActivity<ShipmentPresenter> implements ShipmentActivityViewInterface {
    @NonNull @Override
    protected ShipmentPresenter createPresenter(@NonNull Context context) {
        return new ShipmentPresenter(this,  new AppTaskExecutor(this), new ShipmentCalculatorDataSource(this));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* setting the layout to show on the screen (most important)
         *
         * R = Resource Class (generated by the build system)
         * R.layout.activity_main = Resource object that represents the layout
         *     - getting the activity_main.xml file (similar to loading fxml file)
         *
         * takes the reference_layout layout and sets it to be the CurrentView (what is currently displayed on the screen)
         */
        setContentView(R.layout.shipment_layout);

        final View menuBtn = findViewById(R.id.imgViewMenuBtn);
        final View addBtn = findViewById(R.id.imgViewAddBtn);

        OnShipmentButtonsClicked onShipmentButtonsClicked = new OnShipmentButtonsClicked();

        menuBtn.setOnClickListener(onShipmentButtonsClicked);
        addBtn.setOnClickListener(onShipmentButtonsClicked);
    }

    private class OnShipmentButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.imgViewMenuBtn:
                    mPresenter.onMenuButtonClicked();
                    break;
                case R.id.imgViewAddBtn:
                    mPresenter.onAddButtonClicked();
                    break;
            }
        }
    }
}