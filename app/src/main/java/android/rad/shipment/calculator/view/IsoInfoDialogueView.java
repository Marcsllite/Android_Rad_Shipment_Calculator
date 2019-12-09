package android.rad.shipment.calculator.view;

import android.content.Context;
import android.os.Bundle;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.database.datasource.ShipmentCalculatorDataSource;
import android.rad.shipment.calculator.presenter.IsoInfoPresenter;
import android.rad.shipment.calculator.task.AppTaskExecutor;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class IsoInfoDialogueView extends BaseActivity<IsoInfoPresenter> {
    // Declaring variables
    private TextView txtViewName;
    private LinearLayout linearLayoutNormal;
    private TextView txtViewA1;
    private TextView txtViewA2;
    private TextView txtViewDecayConst;
    private TextView txtViewExemptCon;
    private TextView txtViewExemptLim;
    private TextView txtViewHalfLife;
    private TextView txtViewLicLim;
    private TextView txtViewRQ;
    
    private LinearLayout linearLayoutShortHalfLife;
    private TextView txtViewA1Short;
    private TextView txtViewA2Short;
    private TextView txtViewDecayConstShort;
    private TextView txtViewExemptConShort;
    private TextView txtViewExemptLimShort;
    private TextView txtViewHalfLifeShort;
    private TextView txtViewLicLimShort;
    private TextView txtViewRQShort;
    
    private LinearLayout linearLayoutLongHalfLife;
    private TextView txtViewA1Long;
    private TextView txtViewA2Long;
    private TextView txtViewDecayConstLong;
    private TextView txtViewExemptConLong;
    private TextView txtViewExemptLimLong;
    private TextView txtViewHalfLifeLong;
    private TextView txtViewLicLimLong;
    private TextView txtViewRQLong;
    
    private View btnOkay;
    private String _abbr;

    @NonNull @Override
    protected IsoInfoPresenter createPresenter(@NonNull Context context) { 
        return new IsoInfoPresenter(this,  new AppTaskExecutor(this), new ShipmentCalculatorDataSource(this)); 
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.isoInfoTitle);  // setting the title for the isotope info dialogue
        setContentView(R.layout.iso_info_dialogue);  // showing the isotope info dialogue

        // getting the abbr of selected isotope in list (passed through launching intent)
        _abbr = getIntent().getStringExtra("index");

        // getting all the views from the add dialogue that need to be programmed
        txtViewName = findViewById(R.id.txtViewName);

        linearLayoutNormal = findViewById(R.id.linearLayoutNormal);
        txtViewA1 = findViewById(R.id.txtViewA1);
        txtViewA2 = findViewById(R.id.txtViewA2);
        txtViewDecayConst = findViewById(R.id.txtViewDecayConst);
        txtViewExemptCon = findViewById(R.id.txtViewExemptCon);
        txtViewExemptLim = findViewById(R.id.txtViewExemptLim);
        txtViewHalfLife = findViewById(R.id.txtViewHalfLife);
        txtViewLicLim = findViewById(R.id.txtViewLicLim);
        txtViewRQ = findViewById(R.id.txtViewRQ);

        linearLayoutShortHalfLife = findViewById(R.id.linearLayoutShortHalfLife);
        txtViewA1Short = findViewById(R.id.txtViewA1Short);
        txtViewA2Short = findViewById(R.id.txtViewA2Short);
        txtViewDecayConstShort = findViewById(R.id.txtViewDecayConstShort);
        txtViewExemptConShort = findViewById(R.id.txtViewExemptConShort);
        txtViewExemptLimShort = findViewById(R.id.txtViewExemptLimShort);
        txtViewHalfLifeShort = findViewById(R.id.txtViewHalfLifeShort);
        txtViewLicLimShort = findViewById(R.id.txtViewLicLimShort);
        txtViewRQShort = findViewById(R.id.txtViewRQShort);

        linearLayoutLongHalfLife = findViewById(R.id.linearLayoutLongHalfLife);
        txtViewA1Long = findViewById(R.id.txtViewA1Long);
        txtViewA2Long = findViewById(R.id.txtViewA2Long);
        txtViewDecayConstLong = findViewById(R.id.txtViewDecayConstLong);
        txtViewExemptConLong = findViewById(R.id.txtViewExemptConLong);
        txtViewExemptLimLong = findViewById(R.id.txtViewExemptLimLong);
        txtViewHalfLifeLong = findViewById(R.id.txtViewHalfLifeLong);
        txtViewLicLimLong = findViewById(R.id.txtViewLicLimLong);
        txtViewRQLong = findViewById(R.id.txtViewRQLong);
        
        btnOkay = findViewById(R.id.btnOkay);

        mPresenter.initViews(_abbr); // Initializing all the views

        // creating custom listeners
        OnIsoInfoButtonsClicked onIsoInfoButtonsClicked = new OnIsoInfoButtonsClicked();

        // adding custom listeners to the views
        btnOkay.setOnClickListener(onIsoInfoButtonsClicked);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/
    public void enableShortLong() { 
        linearLayoutNormal.setVisibility(View.GONE);
        linearLayoutShortHalfLife.setVisibility(View.VISIBLE);
        linearLayoutLongHalfLife.setVisibility(View.VISIBLE);
    } 
    
    public void disableShortLong() {
        linearLayoutNormal.setVisibility(View.VISIBLE);
        linearLayoutShortHalfLife.setVisibility(View.GONE);
        linearLayoutLongHalfLife.setVisibility(View.GONE);
    }

    /*///////////////////////////////////////// SETTERS //////////////////////////////////////////*/
    public void setTxtViewName(String name) { txtViewName.setText(name); }
    
    public void setTxtViewA1(String A1) { txtViewA1.setText(A1); }
    
    public void setTxtViewA2(String A2) { txtViewA2.setText(A2); }
    
    public void setTxtViewDecayConst(String decayConst) { txtViewDecayConst.setText(decayConst); }
    
    public void setTxtViewExemptCon(String ExemptCon) { txtViewExemptCon.setText(ExemptCon); }
    
    public void setTxtViewExemptLim(String ExemptLim) { txtViewExemptLim.setText(ExemptLim); }
    
    public void setTxtViewHalfLife(String halfLife) { txtViewHalfLife.setText(halfLife); }
    
    public void setTxtViewLicLim(String licLim) { txtViewLicLim.setText(licLim); }
    
    public void setTxtViewRQ(String rq) { txtViewRQ.setText(rq); }
    
    // Short

    public void setTxtViewA1Short(String A1) { txtViewA1Short.setText(A1); }

    public void setTxtViewA2Short(String A2) { txtViewA2Short.setText(A2); }

    public void setTxtViewDecayConstShort(String decayConst) { txtViewDecayConstShort.setText(decayConst); }

    public void setTxtViewExemptConShort(String ExemptCon) { txtViewExemptConShort.setText(ExemptCon); }

    public void setTxtViewExemptLimShort(String ExemptLim) { txtViewExemptLimShort.setText(ExemptLim); }

    public void setTxtViewHalfLifeShort(String halfLife) { txtViewHalfLifeShort.setText(halfLife); }

    public void setTxtViewLicLimShort(String licLim) { txtViewLicLimShort.setText(licLim); }

    public void setTxtViewRQShort(String rq) { txtViewRQShort.setText(rq); }
    
    // Long

    public void setTxtViewA1Long(String A1) { txtViewA1Long.setText(A1); }

    public void setTxtViewA2Long(String A2) { txtViewA2Long.setText(A2); }

    public void setTxtViewDecayConstLong(String decayConst) { txtViewDecayConstLong.setText(decayConst); }

    public void setTxtViewExemptConLong(String ExemptCon) { txtViewExemptConLong.setText(ExemptCon); }

    public void setTxtViewExemptLimLong(String ExemptLim) { txtViewExemptLimLong.setText(ExemptLim); }

    public void setTxtViewHalfLifeLong(String halfLife) { txtViewHalfLifeLong.setText(halfLife); }

    public void setTxtViewLicLimLong(String licLim) { txtViewLicLimLong.setText(licLim); }

    public void setTxtViewRQLong(String rq) { txtViewRQLong.setText(rq); }
    
    /*/////////////////////////////////////// LISTENERS //////////////////////////////////////////*/

    /**
     * Custom View.onClickListener to call the correct presenter function when any clickable view on
     * the isotope info dialogue is interacted with
     */
    private class OnIsoInfoButtonsClicked implements View.OnClickListener {
        @Override public void onClick(final View view) {
            if(view != null && view.getId() == R.id.btnOkay) mPresenter.onBtnOkayClicked();
        }
    }
}
