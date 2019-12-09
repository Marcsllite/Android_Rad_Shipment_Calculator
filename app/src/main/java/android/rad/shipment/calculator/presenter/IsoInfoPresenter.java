package android.rad.shipment.calculator.presenter;

import android.rad.shipment.calculator.base.BasePresenter;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.rad.shipment.calculator.task.AppTask;
import android.rad.shipment.calculator.task.TaskExecutor;
import android.rad.shipment.calculator.view.IsoInfoDialogueView;

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

    public void initViews(String abbr) { mTaskExecutor.async(new FetchIsotopeInfoTask(abbr)); }

    /*//////////////////////////////////////// LISTENERS /////////////////////////////////////////*/

    /**
     * Listener function that is called when the add button is clicked
     */
    public void onBtnOkayClicked() { mView.leaveActivity(); }

    /*////////////////////////////////////////// TASKS ///////////////////////////////////////////*/

    private class FetchIsotopeInfoTask implements AppTask<Void> {
        private final String mAbbr;
        private boolean isShortLong;
        private String _isoName, 
                _A1, 
                _A2,
                _DecayConst, 
                _ExemptCon, 
                _ExemptLim, 
                _HalfLife,
                _LicLim, 
                _RQ,
                _A1Short,
                _A2Short,
                _DecayConstShort,
                _ExemptConShort,
                _ExemptLimShort,
                _HalfLifeShort,
                _LicLimShort,
                _RQShort,
                _A1Long,
                _A2Long,
                _DecayConstLong,
                _ExemptConLong,
                _ExemptLimLong,
                _HalfLifeLong,
                _LicLimLong,
                _RQLong;

        public FetchIsotopeInfoTask(String abbr) {
            mAbbr = abbr;
            isShortLong = false;
        }

        @Override
        public Void execute() {
            _isoName = mShipmentCalculatorDB.getName(mAbbr) + " (" + mAbbr + ")";
            
            for(ShortLong sl: mShipmentCalculatorDB.getAllShortLong()) {
                if(sl.getAbbr().equalsIgnoreCase(mAbbr)) isShortLong = true;
            }

            if(isShortLong) {
                _A1Short = Float.toString(mShipmentCalculatorDB.getA1(mAbbr + "(short)"));
                _A2Short = Float.toString(mShipmentCalculatorDB.getA2(mAbbr + "(short)"));
                _DecayConstShort = Float.toString(mShipmentCalculatorDB.getDecayConstant(mAbbr + "(short)"));
                _ExemptConShort = Float.toString(mShipmentCalculatorDB.getExemptConcentration(mAbbr + "(short)"));
                _ExemptLimShort = Float.toString(mShipmentCalculatorDB.getExemptLimit(mAbbr + "(short)"));
                _HalfLifeShort = Float.toString(mShipmentCalculatorDB.getHalfLife(mAbbr + "(short)"));
                _LicLimShort = Float.toString(mShipmentCalculatorDB.getLicensingLimit(mAbbr + "(short)"));
                _RQShort = Float.toString(mShipmentCalculatorDB.getReportableQuantity(mAbbr + "(short)"));

                _A1Long = Float.toString(mShipmentCalculatorDB.getA1(mAbbr + "(long)"));
                _A2Long = Float.toString(mShipmentCalculatorDB.getA2(mAbbr + "(long)"));
                _DecayConstLong = Float.toString(mShipmentCalculatorDB.getDecayConstant(mAbbr + "(long)"));
                _ExemptConLong = Float.toString(mShipmentCalculatorDB.getExemptConcentration(mAbbr + "(long)"));
                _ExemptLimLong = Float.toString(mShipmentCalculatorDB.getExemptLimit(mAbbr + "(long)"));
                _HalfLifeLong = Float.toString(mShipmentCalculatorDB.getHalfLife(mAbbr + "(long)"));
                _LicLimLong = Float.toString(mShipmentCalculatorDB.getLicensingLimit(mAbbr + "(long)"));
                _RQLong = Float.toString(mShipmentCalculatorDB.getReportableQuantity(mAbbr + "(long)"));
            } else {
                _A1 = Float.toString(mShipmentCalculatorDB.getA1(mAbbr));
                _A2 = Float.toString(mShipmentCalculatorDB.getA2(mAbbr));
                _DecayConst = Float.toString(mShipmentCalculatorDB.getDecayConstant(mAbbr));
                _ExemptCon = Float.toString(mShipmentCalculatorDB.getExemptConcentration(mAbbr));
                _ExemptLim = Float.toString(mShipmentCalculatorDB.getExemptLimit(mAbbr));
                _HalfLife = Float.toString(mShipmentCalculatorDB.getHalfLife(mAbbr));
                _LicLim = Float.toString(mShipmentCalculatorDB.getLicensingLimit(mAbbr));
                _RQ = Float.toString(mShipmentCalculatorDB.getReportableQuantity(mAbbr));
            }
            
            return null;
        }

        @Override
        public void onPostExecute(@Nullable Void result) {
            // setting values
            if(isShortLong) {
                mView.enableShortLong();
                
                mView.setTxtViewA1Short(_A1Short);
                mView.setTxtViewA2Short(_A2Short);
                mView.setTxtViewDecayConstShort(_DecayConstShort);
                mView.setTxtViewExemptConShort(_ExemptConShort);
                mView.setTxtViewExemptLimShort(_ExemptLimShort);
                mView.setTxtViewHalfLifeShort(_HalfLifeShort);
                mView.setTxtViewLicLimShort(_LicLimShort);
                mView.setTxtViewRQShort(_RQShort);

                mView.setTxtViewA1Long(_A1Long);
                mView.setTxtViewA2Long(_A2Long);
                mView.setTxtViewDecayConstLong(_DecayConstLong);
                mView.setTxtViewExemptConLong(_ExemptConLong);
                mView.setTxtViewExemptLimLong(_ExemptLimLong);
                mView.setTxtViewHalfLifeLong(_HalfLifeLong);
                mView.setTxtViewLicLimLong(_LicLimLong);
                mView.setTxtViewRQLong(_RQLong);
            } else {
                mView.disableShortLong();
                mView.setTxtViewA1(_A1);
                mView.setTxtViewA2(_A2);
                mView.setTxtViewDecayConst(_DecayConst);
                mView.setTxtViewExemptCon(_ExemptCon);
                mView.setTxtViewExemptLim(_ExemptLim);
                mView.setTxtViewHalfLife(_HalfLife);
                mView.setTxtViewLicLim(_LicLim);
                mView.setTxtViewRQ(_RQ);
            }
        }
    }
}
