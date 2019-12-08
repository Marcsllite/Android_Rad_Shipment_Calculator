package android.rad.shipment.calculator.presenter;

import android.content.Intent;
import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.utils.SearchViewAdapter;
import android.rad.shipment.calculator.view.EditDialogueView;
import android.rad.shipment.calculator.view.ReferenceActivityView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
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

    /**
     * Listener function that is called when a list item is clicked
     *
     * @param index the index of the item that was clicked
     */
    public void onSearchIsotopeClicked(int index){
        Intent intent = new Intent(mView.getApplicationContext(), EditDialogueView.class);
        intent.putExtra("index", index);

        mView.startActivity(intent);
    }

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/
    private class FetchIsotopeInfoTask implements AppTask<LiveData<List<Isotopes>>> {
        private final String mQuery;

        public FetchIsotopeInfoTask(String query) { mQuery = (query == null || "".equals(query))? "" : "%"+query+"%"; }

        @Override
        public LiveData<List<Isotopes>> execute() { return mShipmentCalculatorDB.searchIsotope(mQuery); }

        @Override
        public void onPostExecute(@Nullable LiveData<List<Isotopes>> result) {
            assert result != null;
            result.observe(mView, new Observer<List<Isotopes>>() {
                        @Override public void onChanged(@Nullable List<Isotopes> isotopes) {
                            if (isotopes == null) return;

                            if(isotopes.size() > 0) {
                                SearchViewAdapter adapter = new SearchViewAdapter(mView.getApplicationContext(), isotopes);
                                mView.setListViewAdapter(adapter);
                            } else { mView.setListViewAdapter(null); }
                        }
                    }
            );
        }
    }
}
