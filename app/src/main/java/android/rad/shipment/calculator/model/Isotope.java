package android.rad.shipment.calculator.model;

import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.base.BaseActivity;
import android.rad.shipment.calculator.utils.Conversions;

import java.util.Calendar;
import java.util.Date;

public class Isotope {
    // Declaring Variables
    transient private int defaultVal = R.integer.defaultInt;
    transient private final float DPM = (float)2.22e+6;
    private String _Name;                   // The name of the isotope
    private String _DBName;                 // The database search name of the isotope (including short/long or lung absorption)
    private float _A0;                      // Initial Activity (microCuries) of isotope
    private float _AToday;                  // Activity of the isotope today (microCuries)
    private float _Mass;                    // the mass of the isotope (grams for solids, liters for liquids)
    private String _MassUnit;               // the mass unit for the isotope (grams or liter)
    private String _Nature;                 // the nature (regular, special) of the isotope
    private String _State;                  // the state (solid, liquid, gas) of the isotope
    private String _Form;                   // the form (normal, special) of the isotope
    private String _ShortLong;              // for isotopes with different half lives
    private String _LungAbs;                // the lung absorption speed if the isotope is Uranium (f = fast, m = medium, s = slow)
    private float _HalfLife;                //
    private float _DecayConst;              //
    private float _DPM;                     //
    private boolean _RQ;                    //
    private float _LicLimPer;               //
    private float _ActivityPer;             //
    private float _ConcentrationPer;        //
    private float _ActivityCon;
    private float _ActivityFrac;
    private float _AValue;
    private float _RQFrac;
    private float _ExemptLimit;
    private float _ExemptConcentration;
    private float _LicensingLimit;

    private int _IsotopeClass;              // Classification of isotope as an integer
                                            // (0 = Exempt, 1 = Excepted, 2 = Type A, 4 = Type B, 8 = Type B: Highway Route Control)

    /*/////////////////////////////////////////////////// ISOTOPE ////////////////////////////////////////////////////*/
    /**
     * Constructs an Isotope object with the given name and initial activity
     *
     * @param name the name of the isotope
     * @param A0 the initial activity (microCi) of the isotope
     * @param mass the mass (grams/liters) of the isotope
     * @param nature the nature (special, regular) of the isotope
     * @param state the state (solid, liquid, gas) of the isotope
     * @param form the form (special, normal) of the isotope
     */
    public Isotope(String name, float A0, float mass, String massUnit, String nature, String state, String form) {
        // Saving values
        _Name = name;
        _DBName = name;
        _A0 = A0;
        _Mass = mass;
        _MassUnit = massUnit;
        _Nature = nature;
        _State = state;
        _Form = form;

        _LungAbs = "";
        _ShortLong = "";
        _AToday = defaultVal;
        _HalfLife = defaultVal;
        _DecayConst = defaultVal;
        _DPM = defaultVal;
        _RQ = false;
        _LicLimPer = defaultVal;
        _ActivityPer = defaultVal;
        _IsotopeClass = defaultVal;
    }

    /**
     * Copy Constructor
     *
     * @param newIso the isotope to copy all the information from
     */
    public Isotope(Isotope newIso) {
        _Name = newIso._Name;
        _DBName = newIso._DBName;
        _A0 = newIso._A0;
        _Mass = newIso._Mass;
        _MassUnit = newIso._MassUnit;
        _Nature = newIso._Nature;
        _State = newIso._State;
        _Form = newIso._Form;

        _LungAbs = newIso._LungAbs;
        _ShortLong = newIso._ShortLong;
        _AToday = newIso._AToday;
        _HalfLife = newIso._HalfLife;
        _DecayConst = newIso._DecayConst;
        _DPM = newIso._DPM;
        _RQ = newIso._RQ;
        _LicLimPer = newIso._LicLimPer;
        _ActivityPer = newIso._ActivityPer;
        _IsotopeClass = newIso._IsotopeClass;
    } 
    
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((_Name == null) ? 0 : _Name.hashCode()) +
                                ((_DBName == null) ? 0 : _DBName.hashCode()) +
                                ((_MassUnit == null) ? 0 : _MassUnit.hashCode()) +
                                ((_Nature == null) ? 0 : _Nature.hashCode()) +
                                ((_State == null) ? 0 : _State.hashCode()) +
                                ((_Form == null) ? 0 : _Form.hashCode()) +
                                ((int)_A0) + ((int)_Mass);
        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Isotope other = (Isotope) obj;

        return other._Name.equals(this._Name) &&
                other._DBName.equals(this._DBName) &&
                other._A0 == this._A0 &&
                other._Mass == this._Mass &&
                other._MassUnit == this._MassUnit &&
                other._Nature.equals(this._Nature) &&
                other._State.equals(this._State) &&
                other._Form.equals(this._Form);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    /**
     * Helper function to get this isotope's decay date
     *
     * @return the decay date of this isotope
     */
    public Date getDecayDate(float decayConst) {
        float multiplier = DPM / 500;
        Date decayDate = BaseActivity.getShipment().get_ReferenceDate();
        Calendar c = Calendar.getInstance();
        c.setTime(decayDate);
        c.add(Calendar.DATE, (int) (Math.log(get_A0() * multiplier) / decayConst));
        decayDate = c.getTime();

        return decayDate;
    }

    /**
     * Helper function to get this isotope's current Activity in microCuries
     *
     * @return the current activity of this isotope
     */
    public float get_AToday() {
        Date now =  new Date();
        long diff = BaseActivity.getShipment().get_ReferenceDate().getTime() - now.getTime();

        long days = diff / (24 * 60 * 60 * 1000);

        return get_A0() * (float)Math.exp(-get_HalfLife() * days);
    }

    /**
     * Helper function to get this isotope's decays per minute
     *
     * @return this isotope's decays per minute
     */
    public float get_DPM() throws IllegalStateException { return get_AToday() * DPM; }

    public boolean exemptClass(float exemptLim, float exemptCon) {
        // Declaring variabels
        float convertedA, percent = 0;

        // cout << "\n### Testing for exempt classification ###\n";

        //converting microCurie to Bq
        convertedA = Conversions.CiToBq(Conversions.MicroToBase(get_AToday()));

        // checking if isotope exceeds limit
        if (convertedA > exemptLim) return false;
        else {
            _ActivityPer = convertedA / exemptLim;

            percent = (convertedA / _Mass);

            if (percent > exemptCon) return false;
            else {
                _ConcentrationPer = convertedA / _Mass;
                return true;
            }
        }
    }

    public boolean limitedClass(float A1, float A2, float IALimLim, float LimLim) {
        // Declaring variables
        float limit, multiplier, A, convertedA;

        // converting A0 to TBq
        convertedA = Conversions.baseToTera(Conversions.CiToBq(Conversions.MicroToBase(get_AToday())));

        // Getting A1 or A2 based on form
        if ("Special".equals(_Form)) A = A1;
        else A = A2;

        _AValue = A;

        // Getting limit multiplier based on nature
        if ("Regular".equals(_Nature)) multiplier = LimLim;
        else multiplier = IALimLim;


        // calculating limit and saving it
        if ("Regular".equals(_Nature) && "Liquid".equals(_State) && "Tritiated".equals(_Form))
            limit = multiplier;
        else limit = A * multiplier;

//        isos[name].limLim = limit;

        // checking if isotope exceeds limit
        if (convertedA > limit) return false;
        else {
            _ActivityPer = convertedA / limit;
           return true;
        }
    }

    public boolean typeAClass(float A1, float A2) {
        // Declaring variables
        float A, convertedA;

        // Getting A1 or A2 based on form
        if ("Special".equals(_Form)) A = A1;
        else A = A2;

        _AValue = A;

        // converting A0 to TBq
        convertedA = Conversions.baseToTera(Conversions.CiToBq(Conversions.MicroToBase(get_AToday())));

        // Checking if isotope is type A
        if (convertedA > A) return false;
        else {
//            isos[name].limPer = convertedA / A;
            return true;
        }
    }

    public boolean typeBClass(float A1, float A2) {
        // Declaring variables
        float A, convertedA;

        // Getting A1 or A2 based on form
        if ("Special".equals(_Form)) A = A1;
        else A = A2;

        _AValue = A;

        // converting A0 to TBq
        convertedA = Conversions.baseToTera(Conversions.CiToBq(Conversions.MicroToBase(get_AToday())));

        // Checking if isotope is type B
        return !(convertedA <= A);
    }

    public boolean HRCQClass(float A1, float A2) {
        // Declaring variables
        float A, convertedA, percent,
                hrcqLim = 1000; // hrcqLim is in TBq

        // Getting A1 or A2 based on form
        if ("Special".equals(_Form)) A = A1;
        else A = A2;

        _AValue = A;

        // Getting HRCQ limit (whichever is the least of (3000 * A1 or A2) and (1000 TBq))
        if ((3000 * A) < hrcqLim) {
            hrcqLim = 3000 * A;
        }

        // saving HRCQ limit
//        isos[name].hrcqLim = hrcqLim;

        // converting A0 to TBq
        convertedA = Conversions.baseToTera(Conversions.CiToBq(Conversions.MicroToBase(get_AToday())));

        // Checking if isotope is a highway route control quantity
        percent = convertedA / hrcqLim;  // percentage of the hrcq limit
        // Activity is under limit
        return !(convertedA <= hrcqLim);
    }

    /*/////////////////////////////////////////////////// SETTERS ////////////////////////////////////////////////////*/
    /**
     * Setter function to set this isotope's name
     *
     * @param name the new name of the isotope
     */
    public void set_Name(String name) { _Name = name; }

    /**
     * Setter function to set this isotope's name
     *
     * @param dbName the new name of the isotope
     */
    public void set_DBName(String dbName) { _DBName = dbName; }

    /**
     * Setter function to set this isotope's initial activity in microCi
     *
     * @param A0 the new initial activity of the isotope in microCi
     */
    public void set_A0(float A0) { _A0 = A0; }

    /**
     * Setter function to set this isotope's mass in grams for solids and liters for liquids
     *
     * @param mass the new mass of the isotope 
     */
    public void set_Mass(float mass) { _Mass = mass; }

    /**
     * Setter function to set this isotope's mass unit
     *
     * @param massUnit the new mass unig (grams/liters) of this isotope
     */
    public void set_MassUnit(String massUnit) { _MassUnit = massUnit; }

    /**
     * Setter function to set this isotope's nature (regular/special)
     *
     * @param nature the new nature of this isotope
     */
    public void set_Nature(String nature) { _Nature = nature; }

    /**
     * Setter function to set this isotope's state (solid/liquid/gas)
     *
     * @param state the new state of this isotope
     */
    public void set_State(String state) { _State = state; }

    /**
     * Setter function to set this isotope's form (normal/special)
     *
     * @param form the new form of this isotope
     */
    public void set_Form(String form) { _Form = form; }

    /**
     * Setter function to set if this is the isotope with the longer half life or the shorter one
     *
     * @param shortLong if this is the isotope with the longer half life or the shorter one
     */
    public void set_ShortLong(String shortLong) { _ShortLong = shortLong; }

    /**
     * Setter function to set this isotope's lung absorption rate (slow/medium/fast)
     *
     * @param lungAbs the new lung absorption rate for this isotope (slow/medium/fast)
     */
    public void set_LungAbs(String lungAbs) { _LungAbs = lungAbs; }

    public void set_HalfLife(float halflife) { _HalfLife = halflife; }

    public void set_DecayConst(float decayConst) { _DecayConst = decayConst; }

    public void set_DPM(float dpm) { _DPM = dpm; }

    public void set_RQ(boolean rq) { _RQ = rq; }

    public void set_LicLimPer(float licLimPer) { _LicLimPer = licLimPer; }

    public void set_ActivityPer(float activityPer) { _ActivityPer = activityPer; }
    
    /**
     * Setter function to set this isotope's classification as an integer
     *  0 = Exempt Classification
     *  1 = Excepted/Limited Classification
     *  2 = Type A Classification
     *  4 = Type B Classification
     *  8 = Type B: Highway Route Control Classification
     *
     * @param isotopeClass the new classification of this isotope as an integer
     */
    public void set_IsotopeClass(int isotopeClass) { _IsotopeClass = isotopeClass; }
    
    /*/////////////////////////////////////////////////// GETTERS ////////////////////////////////////////////////////*/

    /**
     * Getter function to get this isotope's name
     *
     * @return the name of this isotope
     */
    public String get_Name() { return _Name; }

    /**
     * Getter function to get this isotope's database search name
     *
     * @return the database search name of this isotope
     */
    public String get_DBName() { return _DBName; }

    /**
     * Getter function to get this isotope's initial activity in microCi
     * 
     * @return the initial activity of this isotope in microCi
     */
    public float get_A0() { return _A0; }

    /**
     * Getter function to get this isotope's mass in grams for solids and liters for liquids
     *
     * @return the mass of this isotope in (grams/liters)
     */
    public float get_Mass() { return _Mass; }

    /**
     * Getter function to get this isotope's mass unit
     *
     * @return the mass unit of this isotope either grams or liters
     */
    public String get_MassUnit() { return _MassUnit; }
    
    /**
     * Getter function to get this isotope's nature (regular/special)
     *
     * @return the nature of this isotope
     */
    public String get_Nature() { return _Nature; }

    /**
     * Getter function to get this isotope's state (solid/liquid/gas)
     *
     * @return the state of this isotope
     */
    public String get_State() { return _State; }

    /**
     * Getter function to get this isotope's form (normal/special)
     *
     * @return the form of this isotope
     */
    public String get_Form() { return _Form; }

    /**
     * Getter function to get if this is the isotope with the longer half life or the shorter one
     *
     * @return if this is the isotope with the longer half life or the shorter one
     */
    public String get_ShortLong() { return _ShortLong; }

    /**
     * Getter function to get this isotope's lung absorption rate (slow/medium/fast)
     *
     * @return this isotope's lung absorption rate
     */
    public String get_LungAbs() { return _LungAbs; }

    public float get_HalfLife() { return _HalfLife; }

    public float get_DecayConst() { return _DecayConst; }

    public boolean get_RQ() { return _RQ; }

    public float get_LicLimPer() { return _LicLimPer; }

    public float get_ActivityPer() { return _ActivityPer; }
    
    /**
     * Getter function to get this isotope's classification as an integer
     *  0 = Exempt Classification
     *  1 = Excepted/Limited Classification
     *  2 = Type A Classification
     *  4 = Type B Classification
     *  8 = Type B: Highway Route Control Classification
     *
     * @return the classification of this isotope as an integer
     */
    public int get_IsotopeClass() { return _IsotopeClass; }

    /**
     * Getter function to get this isotope's classification as a string
     *  0 = Exempt Classification
     *  1 = Excepted/Limited Classification
     *  2 = Type A Classification
     *  4 = Type B Classification
     *  8 = Type B: Highway Route Control Classification
     *
     * @return the classification of this isotope as a string
     */
    public String get_StringIsotopeClass() {
        switch (_IsotopeClass){
            case 0: return "Exempt";
            case 1: return "Excepted/Limited";
            case 2: return "Type A";
            case 4: return "Type B";
            case 8: return "Type B: Highway Route Control";
            default: return "Invalid Classification";
        }
    }

    public float get_ConcentrationPer() {
        return _ConcentrationPer;
    }

    public void set_ConcentrationPer(float _ConcentrationPer) {
        this._ConcentrationPer = _ConcentrationPer;
    }

    public float get_ActivityCon() {
        return _ActivityCon;
    }

    public void set_ActivityCon(float _ActivityCon) {
        this._ActivityCon = _ActivityCon;
    }

    public float get_ActivityFrac() {
        return _ActivityFrac;
    }

    public void set_ActivityFrac(float _ActivityFrac) {
        this._ActivityFrac = _ActivityFrac;
    }

    public float get_AValue() {
        return _AValue;
    }

    public float get_RQFrac() {
        return _RQFrac;
    }

    public void set_RQFrac(float _RQFrac) {
        this._RQFrac = _RQFrac;
    }

    public float get_ExemptLimit() {
        return _ExemptLimit;
    }

    public void set_ExemptLimit(float _ExemptLimit) {
        this._ExemptLimit = _ExemptLimit;
    }

    public float get_ExemptConcentration() {
        return _ExemptConcentration;
    }

    public void set_ExemptConcentration(float _ExemptConcentration) {
        this._ExemptConcentration = _ExemptConcentration;
    }

    public float get_LicensingLimit() {
        return _LicensingLimit;
    }

    public void set_LicensingLimit(float _LicensingLimit) {
        this._LicensingLimit = _LicensingLimit;
    }
}
