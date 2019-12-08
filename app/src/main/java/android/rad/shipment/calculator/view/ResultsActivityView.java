package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.ResultsPresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.rad.shipment.calculator.utils.DetailedIsotopeAdapter;
import android.rad.shipment.calculator.utils.NonScrollListView;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

public class ResultsActivityView extends BaseActivity<ResultsPresenter> {
    // Declaring variables
    private DetailedIsotopeAdapter detailedIsotopeAdapter;
    private ProgressBar loading;
    private NonScrollListView nonScrollListView;
    private View backBtn;

    @NonNull @Override
    protected ResultsPresenter createPresenter(@NonNull Context context) {
        return new ResultsPresenter(this,  new AppTaskExecutor(this), new ShipmentCalculatorDataSource(this));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reference_layout);  // showing the reference page

        // getting all the views from the reference page that need to be programmed
        backBtn = findViewById(R.id.imgViewBack);
        loading = findViewById(R.id.loading);
//        nonScrollListView = findViewById(R.id.nonScrollListView);

        // linking the isotopeAdapter to the Shipment's arraylist of isotopes
        detailedIsotopeAdapter = new DetailedIsotopeAdapter(this, BaseActivity.getShipment().getIsotopes());
        nonScrollListView.setAdapter(detailedIsotopeAdapter);

        // creating custom click listeners
        OnResultsButtonsClicked onResultsButtonsClicked = new OnResultsButtonsClicked();

        // adding custom onClick listeners to the views
        backBtn.setOnClickListener(onResultsButtonsClicked);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    /**
     * Helper function to show the loading progress bar on the shipment page
     */
    public void showLoading() { loading.setVisibility(View.VISIBLE); }

    /**
     * Helper function to hide the loading progress bar on the shipment page
     */
    public void hideLoading() { loading.setVisibility(View.INVISIBLE); }

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom View.onClickListener to call the correct results function when any clickable view on
     * the results page is interacted with
     */
    private class OnResultsButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null && view.getId() == R.id.imgViewBack) mPresenter.onMenuButtonClicked();
        }
    }
}
