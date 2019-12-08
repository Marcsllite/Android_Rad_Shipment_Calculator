package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.utils.SearchViewAdapter;
import android.rad.shipment.calculator.view.ReferenceActivityView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public class ReferencePresenter  extends BasePresenter {

    private final ReferenceActivityView mView;  // connection to the reference activity view
    private final TaskExecutor mTaskExecutor;  // runs tasks in the background
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;  // data connection to the database

    /**
     * Constructor to make a reference presenter attached to the given reference activity view
     *
     * @param view the reference activity view that this presenter will be affecting
     * @param taskExecutor the taskExecutor to run background tasks (like database queries)
     * @param db the data connection to the database
     */
    public ReferencePresenter(@NonNull final ReferenceActivityView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the menu button is clicked
     */
    public void onMenuButtonClicked() { mView.leaveActivity(); }
    
    public void onReferenceQuery(String query) { mTaskExecutor.async(new FetchIsotopeInfoTask(query));}

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/
    private class FetchIsotopeInfoTask implements AppTask<Void> {
        private final String mQuery;

        public FetchIsotopeInfoTask(String query) { mQuery = query; }

        @Override
        public Void execute() {
            mShipmentCalculatorDB.searchIsotope(mQuery)
                .observe(mView.getApplicationContext(), new Observer<ArrayList<Isotopes>>() {
                    @Override public void onChanged(@Nullable ArrayList<Isotopes> isotopes) {
                        if (isotopes == null) return;

                        SearchViewAdapter adapter = new SearchViewAdapter(mView.getApplicationContext(), isotopes);

                        listView.setAdapter(adapter);
                    }
                }
            );
            return null;
        }

        @Override
        public void onPostExecute(@Nullable Void result) {
            mView.showToast("Done Searching");
        }
    }
}
