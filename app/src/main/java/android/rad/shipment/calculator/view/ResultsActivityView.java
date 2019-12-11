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
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ResultsActivityView extends BaseActivity<ResultsPresenter> {
    // Declaring variables
    private DetailedIsotopeAdapter detailedIsotopeAdapter;
//    private ProgressBar loading;
    private NonScrollListView nonScrollListView;
    private View backBtn;
    private TextView txtViewD0;
    private TextView txtViewMass;
    private TextView txtViewNature;
    private TextView txtViewState;
    private TextView txtViewForm;
    private TextView txtViewLicExempt;
    private TextView txtViewClass;
    private TextView txtViewActivityLim;
    private TextView txtViewActivityCon;
    private TextView txtViewLicPer;

    @NonNull @Override
    protected ResultsPresenter createPresenter(@NonNull Context context) {
        return new ResultsPresenter(this,  new AppTaskExecutor(this), new ShipmentCalculatorDataSource(this));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.results_layout);  // showing the reference page

        // getting all the views from the reference page that need to be programmed
        backBtn = findViewById(R.id.imgViewBack);
//        loading = findViewById(R.id.loading);
        nonScrollListView = findViewById(R.id.nonScrollListView);
        txtViewD0 = findViewById(R.id.txtViewD0);
        txtViewMass = findViewById(R.id.txtViewMass);
        txtViewNature = findViewById(R.id.txtViewNature);
        txtViewState = findViewById(R.id.txtViewState);
        txtViewForm = findViewById(R.id.txtViewForm);
        txtViewLicExempt = findViewById(R.id.txtViewLicExempt);
        txtViewClass = findViewById(R.id.txtViewClass);
        txtViewActivityLim = findViewById(R.id.txtViewActivityLim);
        txtViewActivityCon = findViewById(R.id.txtViewActivityCon);
        txtViewLicPer = findViewById(R.id.txtViewLicPer);

        // linking the isotopeAdapter to the Shipment's arraylist of isotopes
        detailedIsotopeAdapter = new DetailedIsotopeAdapter(this, BaseActivity.getShipment().getIsotopes());
        nonScrollListView.setAdapter(detailedIsotopeAdapter);

//        hideLoading();  // initializing the loading progress bar to be hidden

        // creating custom click listeners
        OnResultsButtonsClicked onResultsButtonsClicked = new OnResultsButtonsClicked();

        // adding custom onClick listeners to the views
        backBtn.setOnClickListener(onResultsButtonsClicked);

        mPresenter.calculateShipment(); // calculating the shipment and adding the data to the results page
    }

    public void setTxtViewD0(String value) {
        txtViewD0.setText(value);
    }

    public void setTxtViewMass(String value) {
        txtViewMass.setText(value);
    }

    public void setTxtViewNature(String value) {
        txtViewNature.setText(value);
    }

    public void setTxtViewState(String value) {
        txtViewState.setText(value);
    }

    public void setTxtViewForm(String value) {
        txtViewForm.setText(value);
    }

    public void setTxtViewLicExempt(String value) {
        txtViewLicExempt.setText(value);
    }

    public void setTxtViewClass(String value) {
        txtViewClass.setText(value);
    }

    public void setTxtViewActivityLim(String value) {
        txtViewActivityLim.setText(value);
    }

    public void setTxtViewActivityCon(String value) {
        txtViewActivityCon.setText(value);
    }

    public void setTxtViewLicPer(String value) {
        txtViewLicPer.setText(value);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

//    /**
//     * Helper function to show the loading progress bar on the shipment page
//     */
//    public void showLoading() { loading.setVisibility(View.VISIBLE); }
//
//    /**
//     * Helper function to hide the loading progress bar on the shipment page
//     */
//    public void hideLoading() { loading.setVisibility(View.INVISIBLE); }

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
