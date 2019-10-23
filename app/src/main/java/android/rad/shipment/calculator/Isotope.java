package android.rad.shipment.calculator;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Isotope {
    // Declaring Variables
    private static final Logger LOGR = Logger.getLogger(Isotope.class.getName());  // getting logger
    private final float defaultVal = (float)R.integer.defaultInt;  // default float value for uninitialized variables
//    private final DatabaseEditor dbEditor = Main.getDBEditor();  // getting the database editor from the main class
    private String _Name;                    // The name of the isotope
    private float _A0;                      // Initial Activity (microCi) of isotope
    private float _ConcentrationToday;      // Today's concentration (microCi/gram or microCi/liter) of isotope
    private float _DecaysPerMinute;         // Decays per minute of isotope
    private float _ActivityConcentration;   // Activity Concentration (Bq) of isotope (Activity of isotope / sum of entire package concentration)
    private float _ActivityFraction;        // Activity Fraction (Bq) of isotope (Activity fraction of isotope / sum of entire package activity)
    private float _LimitedQuanMultiplier;   // Limited Quantities limit multiplier  (see 173.425_Table 4)
    private float _ReportableQuanFraction;  // Fraction of Reportable Quantity of isotope (Activity Today (microCi) / Reportable Quantity (microCuries))
    private float _LimitPercentage;         // Activity Percentage of isotope limit (activity of isotope / activity limit of isotope)
    private float _ConcentrationPercentage;  // Activity concentration of isotope (activity concentration of isotope / activity concentration limit of isotope)
    private float _LicensePercentage;       // Percentage of licensing limit (activity of isotope / license_limit from Info table in database)
    private float _ALimit;                  // A1 value (TBq) of isotope if Special form, A2 value (TBq) of isotope if Normal Form
    private float _HRCQLimit;               // Highway Route Control Limit (either 1000 * TBq or 3000 * A1/A2 whichever is the lowest number)
    private boolean _isFissile;              // true if isotope is fissile
    private boolean _isReportableQuan;      // true if isotope is a reportable quantity
    private boolean _isLong;                // for isotopes with different half lives, true if longer half life else false
    private int _isotopeClass;          // Classification of isotope as an integer
                                                                // (0 = Exempt, 1 = Excepted, 2 = Type A, 4 = Type B, 8 = Type B: Highway Route Control)
    // Constant values from database
    private float _A1;                  // A1 (TBq) of isotope (from Info table in database)
    private float _A2;                  // A2 (TBq) of isotope (from Info table in database)
    private float _DecayConstant;       // Decay Constant (1 / halflife(days)) of isotope (from Info table in database)
    private float _ExemptConcentration; // Exempt Concentration (Bq/gram) of isotope (from Info table in database)
    private float _ExemptLimit;         // Exempt Limit (Bq)of isotope (from Info table in database)
    private float _HalfLife;            // Halflife (days) of isotope (from Info table in database)
    private float _IALimitedMultiplier; // Instruments/Articles multiplier (see 173.425_Table 4) of isotope (from Info table in database)
    private float _LicenseLimit;        // Licensing Limit (microCi) of isotope (from Info table in database)
    private float _LimitedLimit;        // limited Limit (TBq) of isotope (from Info table in database)
    private float _ReportableQuan;      // Reportable Quantity (Ci) of isotope (from Info table in database)

    /*/////////////////////////////////////////////////// ISOTOPE ////////////////////////////////////////////////////*/
    /**
     * Constructs an Isotope object with the given name and initial activity
     *
     * @param name the name of the isotope
     * @param A0 the initial activity (microCi) of the isotope
     * @param state the state (solid, liquid, gas) of the isotope
     * @param form the form (special, normal) of the isotope
     */
    Isotope(String name, float A0, String state, String form) throws RuntimeException {
//        try {
            // getting the values from the database
//            _A1 = dbEditor.getA1(name);
//            _A2 = dbEditor.getA2(name);
//            _DecayConstant = dbEditor.getDecayConstant(name);
//            _ExemptConcentration = dbEditor.getExemptConcentration(name);
//            _ExemptLimit = dbEditor.getExemptLimit(name);
//            _HalfLife = dbEditor.getHalfLife(name);
//            _IALimitedMultiplier = dbEditor.getIALimitedMultiplier(state, form);
//            _LicenseLimit = dbEditor.getLicenseLimit(name);
//            _LimitedLimit = dbEditor.getLimitedLimit(state, form);
//            _ReportableQuan = dbEditor.getReportableQuantity(name);
//        } catch (InvalidParameterException e) {
//            // TODO: figure out android logging
//            // LOGR.log(Level.SEVERE, "Failed to create isotope named " + name + ". Error: ", e);
//            throw new RuntimeException("Failed to create isotope named " + name);
//        }

        // Saving isotope name
        _Name = name;

        // making sure initial activity is valid
        if(A0 <= 0) throw new InvalidParameterException("Initial Activity of isotope cannot be less than or equal to 0");

        _A0 = A0;
        _ConcentrationToday = defaultVal;
        _DecaysPerMinute = defaultVal;
        _ActivityConcentration = defaultVal;
        _ActivityFraction = defaultVal;
        _LimitedQuanMultiplier = defaultVal;
        _ReportableQuanFraction = defaultVal;
        _LimitPercentage = defaultVal;
        _ConcentrationPercentage = defaultVal;
        _LicensePercentage = defaultVal;
        _ALimit = defaultVal;
        _HRCQLimit = defaultVal;
        _isFissile = false;
        _isReportableQuan = false;
        _isLong = false;
        _isotopeClass = (int)defaultVal;
    }

    /*/////////////////////////////////////////////////// HELPERS ////////////////////////////////////////////////////*/
    /**
     * Helper function to get this isotope's decay date
     *
     * @return the decay date of this isotope
     */
    public LocalDateTime getDecayDate() {
        // TODO: finish getDecayDate function
        return null;
    }

    /**
     * Helper function to get this isotope's current Activity
     *
     * @return the current activity of this isotope
     */
    public float getActivity() {
        // TODO: finish getActivity function
        return defaultVal;
    }

    /**
     * Helper function to get the date this isotope will be exempt from licensing
     *
     * @return the date this isotope will be exempt from licensing
     */
    public LocalDateTime getDateForExemptLicensing() {
        // TODO: finish getDateForExemptLicensing function
        return null;
    }

    /**
     * Helper function to get the date this isotope will be exempt from shipping
     *
     * @return the date this isotope will be exempt from shipping
     */
    public LocalDateTime getDateForExemptShipping() {
        // TODO: finish getDateForExemptShipping function
        return null;
    }

    /*/////////////////////////////////////////////////// GETTERS ////////////////////////////////////////////////////*/

    /**
     * Getter function to get this isotope's name
     *
     * @return the name of this isotope
     */
    public String get_Name() { return _Name; }

    /**
     * Getter function to get this isotope's initial activity in microCi
     * 
     * @return the initial activity of this isotope in microCi
     */
    public float get_A0() { return _A0; }

    /**
     * Getter function to get this isotope's current concentration value (as of today)
     * in microCi/gram or microCi/liter
     *
     * @return the current concentration as of today of this isotope in microCi/gram or microCi/liter
     */
    public float get_ConcentrationToday() { return _ConcentrationToday; }

    /**
     * Getter function to get this isotope's decays per minute
     *
     * @return the decays per minute of this isotope
     */
    public float get_DecaysPerMinute() { return _DecaysPerMinute; }

    /**
     * Getter function to get this isotope's activity concentration in (Bq)
     * Activity Concentration = Activity of isotope / sum of entire package concentration
     *
     * @return the activity concentration of this isotope
     */
    public float get_ActivityConcentration() { return _ActivityConcentration; }

    /**
     * Getter function to get this isotope's initial activity fraction in Bq
     * Activity Fraction = Activity fraction of isotope / sum of entire package activity
     *
     * @return the activity fraction of this isotope
     */
    public float get_ActivityFraction() { return _ActivityFraction; }

    /**
     * Getter function to get this isotope's limited quantity multiplier
     * from 173.425_Table 4
     *
     * @return the limited quantity multiplier of this isotope
     */
    public float get_LimitedQuanMultiplier() { return _LimitedQuanMultiplier; }

    /**
     * Getter function to get this isotope's reportable quantity fraction
     * Reportable Quantity Fraction = Activity Today (microCi) / Reportable Quantity (microCuries)
     *
     * @return the reportable quantity fraction of this isotope
     */
    public float get_ReportableQuanFraction() { return _ReportableQuanFraction; }

    /**
     * Getter function to get this isotope's activity percentage of the limit
     * Limit Percentage = (activity of isotope / activity limit of isotope)
     *
     * @return the activity percentage of this isotope
     */
    public float get_LimitPercentage() { return _LimitPercentage; }

    /**
     * Getter function to get this isotope's activity concentration of the limit
     * Concentration Percentage = activity concentration of isotope / activity concentration limit of isotope
     *
     * @return the activity concentration percentage of this isotope
     */
    public float get_ConcentrationPercentage() { return _ConcentrationPercentage; }

    /**
     * Getter function to get this isotope's percentage of licensing limit
     * License percentage activity of isotope / license_limit (from Info table in database)
     *
     * @return the percentage of licensing limit of this isotope
     */
    public float get_LicensePercentage() { return _LicensePercentage; }

    /**
     * Getter function to get this isotope's A1 or A2 value (TBq)
     * For Special Form Isotopes: A1 value (TBq)
     * For Normal Form Isotopes: A2 value (TBq)
     *
     * @return the A1 or A2 value (TBq) of this isotope
     */
    public float get_ALimit() { return _ALimit; }

    /**
     * Getter function to get this isotope's Highway Route Control Quantity Limit
     * Highway Route Control Quantity Limit = 1000 * TBq
     *                                            or
     *                                       3000 * A1/A2
     *                                 (whichever is the lowest number)
     *
     * @return the Highways Route Control Quantity Limit of this isotope
     */
    public float get_HRCQLimit() { return _HRCQLimit; }

    /**
     * Getter function to get whether this isotope is fissile or not
     *
     * @return true if isotope is fissile, else false
     */
    public boolean get_isFissile() { return _isFissile; }

    /**
     * Getter function to get whether this isotope is a reportable quantity or not
     *
     * @return true if isotope is a reportable quantity, else false
     */
    public boolean get_isReportableQuan() { return _isReportableQuan; }

    /**
     * Getter function to get whether this isotope has different half lives
     * and this is the isotope with the longer half life
     *
     * @return true if this has the longer half life, else false
     */
    public boolean get_isLong() { return _isLong; }

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
    public int get_isotopeClass() { return _isotopeClass; }

    /**
     * Getter function to get this isotope's A1 value (from Info table in database)
     *
     * @return the A1 value of this isotope (from Info table in database)
     */
    public float get_A1() { return _A1; }

    /**
     * Getter function to get this isotope's A2 value (from Info table in database)
     *
     * @return the A2 value of this isotope (from Info table in database)
     */
    public float get_A2() { return _A2; }

    /**
     * Getter function to get this isotope's decay constant (from Info table in database)
     *
     * @return the decay constant of this isotope (from Info table in database)
     */
    public float get_DecayConstant() { return _DecayConstant; }

    /**
     * Getter function to get this isotope's exempt concentration (from Info table in database)
     *
     * @return the exempt concentration of this isotope (from Info table in database)
     */
    public float get_ExemptConcentration() { return _ExemptConcentration; }

    /**
     * Getter function to get this isotope's exempt limit (from Info table in database)
     *
     * @return the exempt limit of this isotope (from Info table in database)
     */
    public float get_ExemptLimit() { return _ExemptLimit; }

    /**
     * Getter function to get this isotope's halflife (from Info table in database)
     *
     * @return the halflife of this isotope (from Info table in database)
     */
    public float get_HalfLife() { return _HalfLife; }

    /**
     * Getter function to get this isotope's instruments/articles limited limit (from Info table in database)
     *
     * @return the instruments/articles limited limit of this isotope (from Info table in database)
     */
    public float get_IALimitedMultiplier() { return _IALimitedMultiplier; }

    /**
     * Getter function to get this isotope's license limit (from Info table in database)
     *
     * @return the license limit of this isotope (from Info table in database)
     */
    public float get_LicenseLimit() { return _LicenseLimit; }

    /**
     * Getter function to get this isotope's limited limit (from Info table in database)
     *
     * @return the limited limit of this isotope (from Info table in database)
     */
    public float get_LimitedLimit() { return _LimitedLimit; }

    /**
     * Getter function to get this isotope's reportable quantity limit (from Info table in database)
     *
     * @return the reportable quantity limit of this isotope (from Info table in database)
     */
    public float get_ReportableQuan() { return _ReportableQuan; }
    
    /*/////////////////////////////////////////////////// SETTERS ////////////////////////////////////////////////////*/

    /**
     * Setter function to set this isotope's name
     *
     * @param name the new name of this isotope
     */
    public void set_Name(String name) { _Name = name; }

    /**
     * Setter function to set this isotope's initial activity in microCi
     *
     * @param A0 the new initial activity of this isotope in microCi
     */
    public void set_A0(float A0) { _A0 = A0; }

    /**
     * Setter function to set this isotope's current concentration value (as of today)
     * in microCi/gram or microCi/liter
     *
     * @param CToday the NEW current concentration as of today of this isotope in microCi/gram or microCi/liter
     */
    public void set_ConcentrationToday(float CToday) { _ConcentrationToday = CToday; }

    /**
     * Setter function to set this isotope's decays per minute
     *
     * @param dpm the new decays per minute of this isotope
     */
    public void set_DecaysPerMinute(float dpm) { _DecaysPerMinute = dpm; }

    /**
     * Setter function to set this isotope's activity concentration in (Bq)
     * Activity Concentration = Activity of isotope / sum of entire package concentration
     *
     * @param ACon the new activity concentration of this isotope
     */
    public void set_ActivityConcentration(float ACon) { _ActivityConcentration = ACon; }

    /**
     * Setter function to set this isotope's initial activity fraction in Bq
     * Activity Fraction = Activity fraction of isotope / sum of entire package activity
     *
     * @param Afrac the new activity fraction of this isotope
     */
    public void set_ActivityFraction(float Afrac ) { _ActivityFraction = Afrac; }

    /**
     * Setter function to set this isotope's limited quantity multiplier
     * from 173.425_Table 4
     *
     * @param LimMult the new limited quantity multiplier of this isotope
     */
    public void set_LimitedQuanMultiplier(float LimMult) { _LimitedQuanMultiplier = LimMult; }

    /**
     * Setter function to set this isotope's reportable quantity fraction
     * Reportable Quantity Fraction = Activity Today (microCi) / Reportable Quantity (microCuries)
     *
     * @param RQFrac the new reportable quantity fraction of this isotope
     */
    public void set_ReportableQuanFraction(float RQFrac) { _ReportableQuanFraction = RQFrac; }

    /**
     * Setter function to set this isotope's activity percentage of the limit
     * Limit Percentage = (activity of isotope / activity limit of isotope)
     *
     * @param LimPer the new activity percentage of this isotope
     */
    public void set_LimitPercentage(float LimPer) { _LimitPercentage = LimPer; }

    /**
     * Setter function to set this isotope's activity concentration of the limit
     * Concentration Percentage = activity concentration of isotope / activity concentration limit of isotope
     *
     * @param ConPer the new activity concentration percentage of this isotope
     */
    public void set_ConcentrationPercentage(float ConPer) { _ConcentrationPercentage = ConPer; }

    /**
     * Setter function to set this isotope's percentage of licensing limit
     * License percentage activity of isotope / license_limit (from Info table in database)
     *
     * @param LicPer the new percentage of licensing limit of this isotope
     */
    public void set_LicensePercentage(float LicPer) { _LicensePercentage = LicPer; }

    /**
     * Setter function to set this isotope's A1 or A2 value (TBq)
     * For Special Form Isotopes: A1 value (TBq)
     * For Normal Form Isotopes: A2 value (TBq)
     *
     * @param ALim the new A1 or A2 value (TBq) of this isotope
     */
    public void set_ALimit(float ALim) { _ALimit = ALim; }

    /**
     * Setter function to set this isotope's Highway Route Control Quantity Limit
     * Highway Route Control Quantity Limit = 1000 * TBq
     *                                            or
     *                                       3000 * A1/A2
     *                                 (whichever is the lowest number)
     *
     * @param HRCQLim the new Highways Route Control Quantity Limit of this isotope
     */
    public void set_HRCQLimit(float HRCQLim) { _HRCQLimit = HRCQLim; }

    /**
     * Setter function to set whether this isotope is fissile or not
     *
     * @param isFissile whether this isotope is fissile or not
     */
    public void set_isFissile(boolean isFissile) { _isFissile = isFissile; }

    /**
     * Setter function to set whether this isotope is a reportable quantity or not
     *
     * @param isRQ whether this isotope is a reportable quantity or not
     */
    public void set_isReportableQuan(boolean isRQ) { _isReportableQuan = isRQ; }

    /**
     * Setter function to set whether this isotope has different half lives
     * and this is the isotope with the longer half life
     *
     * @param isLong whether this is the isotope with the longer half life
     */
    public void set_isLong(boolean isLong) { _isLong = isLong; }

    /**
     * Setter function to set this isotope's classification as an integer
     *  0 = Exempt Classification
     *  1 = Excepted/Limited Classification
     *  2 = Type A Classification
     *  4 = Type B Classification
     *  8 = Type B: Highway Route Control Classification
     *
     * @param isoClass the new classification of this isotope as an integer
     */
    public void set_isotopeClass(int isoClass) { _isotopeClass = isoClass; }

    /**
     * Setter function to set this isotope's A1 value (from Info table in database)
     *
     * @param A1 the new A1 value of this isotope (from Info table in database)
     */
    public void set_A1(float A1) { _A1 = A1; }

    /**
     * Setter function to set this isotope's A2 value (from Info table in database)
     *
     * @param A2 the new A2 value of this isotope (from Info table in database)
     */
    public void set_A2(float A2) { _A2 = A2; }

    /**
     * Setter function to set this isotope's decay constant (from Info table in database)
     *
     * @param decayCon he new decay constant of this isotope (from Info table in database)
     */
    public void set_DecayConstant(float decayCon) { _DecayConstant = decayCon; }

    /**
     * Setter function to set this isotope's exempt concentration (from Info table in database)
     *
     * @param ExemptCon the new exempt concentration of this isotope (from Info table in database)
     */
    public void set_ExemptConcentration(float ExemptCon) { _ExemptConcentration = ExemptCon; }

    /**
     * Setter function to set this isotope's exempt limit (from Info table in database)
     *
     * @param ExemptLim the new exempt limit of this isotope (from Info table in database)
     */
    public void set_ExemptLimit(float ExemptLim) { _ExemptLimit = ExemptLim; }

    /**
     * Setter function to set this isotope's halflife (from Info table in database)
     *
     * @param halfLife the new halflife of this isotope (from Info table in database)
     */
    public void set_HalfLife(float halfLife) { _HalfLife = halfLife; }

    /**
     * Setter function to set this isotope's instruments/articles limited limit (from Info table in database)
     *
     * @param IAMult the new instruments/articles limited limit of this isotope (from Info table in database)
     */
    public void set_IALimitedMultiplier(float IAMult) { _IALimitedMultiplier = IAMult; }

    /**
     * Setter function to set this isotope's license limit (from Info table in database)
     *
     * @param LicLim the new license limit of this isotope (from Info table in database)
     */
    public void set_LicenseLimit(float LicLim) { _LicenseLimit = LicLim; }

    /**
     * Setter function to set this isotope's limited limit (from Info table in database)
     *
     * @param LimLim the new limited limit of this isotope (from Info table in database)
     */
    public void set_LimitedLimit(float LimLim) { _LimitedLimit = LimLim; }

    /**
     * Setter function to set this isotope's reportable quantity limit (from Info table in database)
     *
     * @param RQ the new reportable quantity limit of this isotope (from Info table in database)
     */
    public void set_ReportableQuan(float RQ) { _ReportableQuan = RQ; }
}
