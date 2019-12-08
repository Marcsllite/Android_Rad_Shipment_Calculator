package android.rad.shipment.calculator.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.ShipmentPresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.rad.shipment.calculator.utils.IsotopeAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

public class ShipmentActivityView extends BaseActivity<ShipmentPresenter> {
    // Declaring variables
    private static IsotopeAdapter isotopeAdapter;
    private ProgressBar loading;
    private View menuBtn;
    private View addBtn;
    private View calculateBtn;

    @NonNull @Override
    protected ShipmentPresenter createPresenter(@NonNull Context context) {
        // creating the connection to the database
        ShipmentCalculatorDataSource db = new ShipmentCalculatorDataSource(this);

        // initializing the database in the background if it hasn't already been initialized
        new initDBAsyncTask(this, db.getInstance()).execute();

        // returning a presenter where all the business logic for this activity will reside
        return new ShipmentPresenter(this, new AppTaskExecutor(this), db);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shipment_layout);  // showing the shipment page

        // getting all the views from the shipment page that need to be programmed
        loading = findViewById(R.id.loading);
        menuBtn = findViewById(R.id.imgViewMenuBtn);
        addBtn = findViewById(R.id.imgViewAddBtn);
        ListView listView = findViewById(R.id.listView);
        calculateBtn = findViewById(R.id.btnCalculate);

        listView.setEmptyView(findViewById(R.id.txtViewEmptyShipment));  // showing textField on empty list

        // linking the isotopeAdapter to the Shipment's arraylist of isotopes
        isotopeAdapter = new IsotopeAdapter(this, BaseActivity.getShipment().getIsotopes());
        listView.setAdapter(isotopeAdapter);

        disableCalculateButton();  // initializing the calculate button to be disabled
        hideLoading();  // initializing the loading progress bar to be hidden

        // creating custom Change and Click listeners
        OnListChanged onListChanged = new OnListChanged();
        OnShipmentButtonsClicked onShipmentButtonsClicked = new OnShipmentButtonsClicked();
        AdapterView.OnItemClickListener onItemClickListener = new OnShipmentIsotopeClicked();

        isotopeAdapter.registerDataSetObserver(onListChanged);  // added custom dataset observer to isotopeAdapter

        // adding custom onClick listeners to the views
        menuBtn.setOnClickListener(onShipmentButtonsClicked);
        addBtn.setOnClickListener(onShipmentButtonsClicked);
        listView.setOnItemClickListener(onItemClickListener);
        calculateBtn.setOnClickListener(onShipmentButtonsClicked);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    /**
     * Helper function to enable the calculate button on the shipment page
     */
    public void enableCalculateButton() {
        calculateBtn.setEnabled(true);
        calculateBtn.setVisibility(View.VISIBLE);
    }

    /**
     * Helper function to disable the calculate button on the shipment page
     */
    public void disableCalculateButton() {
        calculateBtn.setEnabled(false);
        calculateBtn.setVisibility(View.INVISIBLE);
    }

    /**
     * Helper function to show the loading progress bar on the shipment page
     */
    public void showLoading() { loading.setVisibility(View.VISIBLE); }

    /**
     * Helper function to hide the loading progress bar on the shipment page
     */
    public void hideLoading() { loading.setVisibility(View.INVISIBLE); }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/

    /**
     * Getter function to get the isotopeAdapter linked to the ListView on the shipment page
     *
     * @return the isotopeAdapter linked to the ListView on the shipment page
     */
    public static IsotopeAdapter getIsotopeAdapter() { return isotopeAdapter; }

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom dataSetObserver to call the presenter function when the list has been changed
     */
    private class OnListChanged extends DataSetObserver {
        @Override public void onChanged() { mPresenter.onListChanged(); }
    }

    /**
     * Custom AdapterView.onItemClickListener to call the presenter function when an isotope in the
     * shipment page's list view is clicked
     */
    private class OnShipmentIsotopeClicked implements AdapterView.OnItemClickListener {
        @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mPresenter.onShipmentIsotopeClicked(i);  // passing the index of the clicked list item
        }
    }

    /**
     * Custom View.onClickListener to call the correct presenter function when any clickable view on
     * the shipment page is interacted with
     */
    private class OnShipmentButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null) {
                switch (view.getId()) {
                    case R.id.imgViewMenuBtn:
                        mPresenter.onMenuButtonClicked();
                        break;
                    case R.id.imgViewAddBtn:
                        mPresenter.onAddButtonClicked();
                        break;
                    case R.id.btnCalculate:
                        mPresenter.onCalculateButtonClicked();
                        break;
                }
            }
        }
    }
}