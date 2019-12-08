package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.ReferencePresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;

public class ReferenceActivityView extends BaseActivity<ReferencePresenter> {
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
        final View menuBtn = findViewById(R.id.imgViewMenuBtn);
        final SearchView searchView = findViewById(R.id.searchView);

        // creating custom listeners
        OnReferenceQueryListener onReferenceQueryListener = new OnReferenceQueryListener();
        OnReferenceButtonsClicked onReferenceButtonsClicked = new OnReferenceButtonsClicked();

        // adding custom onClick listeners to the views
        menuBtn.setOnClickListener(onReferenceButtonsClicked);
        searchView.setOnQueryTextListener(onReferenceQueryListener);
    }

    public initListView

    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

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
