package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.presenter.ReferencePresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.rad.shipment.calculator.utils.SearchViewAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

public class ReferenceActivityView extends BaseActivity<ReferencePresenter> {
    // Declaring variables
    private View menuBtn;
    private SearchView searchView;
    private ListView listView;

    @NonNull @Override
    protected ReferencePresenter createPresenter(@NonNull Context context) {
        // creating the connection to the database
        ShipmentCalculatorDataSource db = new ShipmentCalculatorDataSource(this);

        // initializing the database in the background if it hasn't already been initialized
        new initDBAsyncTask(this, db.getInstance()).execute();

        // returning a presenter where all the business logic for this activity will reside
        return new ReferencePresenter(this,  new AppTaskExecutor(this), db);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reference_layout);  // showing the reference page

        // getting all the views from the reference page that need to be programmed
        menuBtn = findViewById(R.id.imgViewMenuBtn);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        listView.setEmptyView(findViewById(R.id.txtViewNoResults));

        // creating custom listeners
        OnSearchIsotopeClicked onSearchIsotopeClicked = new OnSearchIsotopeClicked();
        OnReferenceQueryListener onReferenceQueryListener = new OnReferenceQueryListener();
        OnReferenceButtonsClicked onReferenceButtonsClicked = new OnReferenceButtonsClicked();

        // adding custom onClick listeners to the views
        menuBtn.setOnClickListener(onReferenceButtonsClicked);
        searchView.setOnQueryTextListener(onReferenceQueryListener);
        listView.setOnItemClickListener(onSearchIsotopeClicked);
    }

    public void setListViewAdapter(SearchViewAdapter searchViewAdapter) { listView.setAdapter(searchViewAdapter); }

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom AdapterView.onItemClickListener to call the presenter function when an isotope in the
     * reference page's list view is clicked
     */
    private class OnSearchIsotopeClicked implements AdapterView.OnItemClickListener {
        @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // passing the abbreviation of the clicked list item
            mPresenter.onSearchIsotopeClicked(((Isotopes)adapterView.getAdapter().getItem(i)).getAbbr());
        }
    }

    private class OnReferenceQueryListener implements SearchView.OnQueryTextListener {
        /**
         * Called when the user submits the query. This could be due to a key press on the
         * keyboard or due to pressing a submit button.
         * The listener can override the standard behavior by returning true
         * to indicate that it has handled the submit request. Otherwise return false to
         * let the SearchView handle the submission by launching any associated intent.
         *
         * @param query the query text that is to be submitted
         * @return true if the query has been handled by the listener, false to let the
         * SearchView perform the default action.
         */
        @Override
        public boolean onQueryTextSubmit(String query) {
            mPresenter.onReferenceQuery(query);
            return true;
        }

        /**
         * Called when the query text is changed by the user.
         *
         * @param newText the new content of the query text field.
         * @return false if the SearchView should perform the default action of showing any
         * suggestions if available, true if the action was handled by the listener.
         */
        @Override
        public boolean onQueryTextChange(String newText) {
            mPresenter.onReferenceQuery(newText);
            return true;
        }
    }

    /**
     * Custom View.onClickListener to call the correct presenter function when any clickable view on
     * the reference page is interacted with
     */
    private class OnReferenceButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null) {
                switch (view.getId()) {
                    case R.id.imgViewMenuBtn:
                        mPresenter.onMenuButtonClicked();
                        break;
                }
            }
        }
    }
}
