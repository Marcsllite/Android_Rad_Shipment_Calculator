package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.ReferencePresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.view.View;

import androidx.annotation.NonNull;

public class ReferenceActivityView extends BaseActivity<ReferencePresenter> {

    @NonNull @Override
    protected ReferencePresenter createPresenter(@NonNull Context context) {
        // creating the connection ot the database
        ShipmentCalculatorDataSource db = new ShipmentCalculatorDataSource(this);

        // initializing the database in the background if it hasn't already been created
        new initDBAsyncTask(this, db.getInstance()).execute();

        // returning a presenter where all the business logic for this activity will reside
        return new ReferencePresenter(this,  new AppTaskExecutor(this), db);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reference_layout);

        final View menuBtn = findViewById(R.id.imgViewMenuBtn);
        final View searchView = findViewById(R.id.searchView);

        OnReferenceButtonsClicked onReferenceButtonsClicked = new OnReferenceButtonsClicked();

        menuBtn.setOnClickListener(onReferenceButtonsClicked);
    }

    private class OnReferenceButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.imgViewMenuBtn:
                    mPresenter.onMenuButtonClicked();
                    break;
            }
        }
    }
}
