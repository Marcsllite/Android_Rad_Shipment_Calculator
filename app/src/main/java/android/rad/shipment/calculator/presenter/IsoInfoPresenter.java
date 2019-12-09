package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.IsoInfoDialogueView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IsoInfoPresenter extends BasePresenter {
    private final IsoInfoDialogueView mView;  // connection to the isotope info activity view
    private final TaskExecutor mTaskExecutor;  // runs tasks in the background
    private final ShipmentCalculatorDataSource mShipmentCalculatorDB;  // data connection to the database

    /**
     * Constructor to make a isotope info presenter attached to the given isotope info dialogue view
     *
     * @param view the isotope info dialogue view that this presenter will be affecting
     * @param taskExecutor the taskExecutor to run background tasks (like database queries)
     * @param db the data connection to the database
     */
    public IsoInfoPresenter(@NonNull final IsoInfoDialogueView view, @NonNull final TaskExecutor taskExecutor, ShipmentCalculatorDataSource db){
        mTaskExecutor = taskExecutor;
        mView = view;
        mShipmentCalculatorDB = db;
    }

    public void initViews(String abbr) { }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the add button is clicked
     */
    public void onBtnOkayClicked() { mView.leaveActivity(); }

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/

    private class FetchIsotopeInfoTask implements AppTask<List<ShortLong>> {
        private final String mAbbr;
        private boolean isShortLong;
        private String _isoName, _A1Short, _A1Long, _A2Short, _A2Long, _DecayConst, _ExemptCon, _ExemptLim, _HalfLifeShort,
                        _HalfLifeLong, _LicLim, _RQ;

        public FetchIsotopeInfoTask(String abbr) {
            mAbbr = abbr;
            isShortLong = false;
        }

        @Override
        public List<ShortLong> execute() {
            for(ShortLong sl: mShipmentCalculatorDB.getAllShortLong()) {
                if(sl.getAbbr().equalsIgnoreCase(mAbbr)) isShortLong = true;
            }

            if(isShortLong) {
                _A1Short = Float.toString(mShipmentCalculatorDB.getA1(mAbbr + "(short)"));
                _A1Long = Float.toString(mShipmentCalculatorDB.getA1(mAbbr + "(long)"));
                _A2Short = Float.toString(mShipmentCalculatorDB.getA2(mAbbr + "(short)"));
                _A2Long = Float.toString(mShipmentCalculatorDB.getA2(mAbbr + "(long)"));
                _HalfLifeShort = Float.toString(mShipmentCalculatorDB.getHalfLife(mAbbr + "(short)"));
                _HalfLifeLong = Float.toString(mShipmentCalculatorDB.getHalfLife(mAbbr + "(long)"));
            } else {
                _A1Short = Float.toString(mShipmentCalculatorDB.getA1(mAbbr));
                _A2Short = Float.toString(mShipmentCalculatorDB.getA2(mAbbr));
                _HalfLifeShort = Float.toString(mShipmentCalculatorDB.getHalfLife(mAbbr));
                _A1Long = null;
                _A2Long = null;
                _HalfLifeLong = null;
            }

            _isoName = mShipmentCalculatorDB.getName(mAbbr) + " (" + mAbbr + ")";
            _DecayConst = Float.toString(mShipmentCalculatorDB.getDecayConstant(mAbbr));
            _ExemptCon = Float.toString(mShipmentCalculatorDB.getExemptConcentration(mAbbr));
            _ExemptLim = Float.toString();
            _LicLim = ;
            _RQ = ;
            return mShipmentCalculatorDB.getAllShortLong();
        }

        @Override
        public void onPostExecute(@Nullable List<ShortLong> result) {
            // setting values
            mView.setTxtViewName(_isoName);
            mView.setTxtViewA1(_A1);
            mView.setTxtViewA2(_A2);
            mView.setTxtViewDecayConst(_DecayConst);
            mView.setTxtViewExemptCon(_ExemptCon);
            mView.setTxtViewExemptLim(_ExemptLim);
            mView.setTxtViewHalfLifeShort(_HalfLifeShort);
            mView.setTxtViewLicLim(_LicLim);
            mView.setTxtViewRQ(_RQ);

            if(isShortLong) mView.disableShortLong();
            else {
                mView.enableShortLong();
                mView.setTxtViewHalfLifeLong(_HalfLifeLong);
            }
        }
    }
}
