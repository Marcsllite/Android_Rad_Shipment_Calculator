package android.rad.shipment.calculator.model;

/**
 * Isotope JavaBean class to hold the isotopes from the FireStore Database
 */
public class Isotope {
    private int mNumber;        // the atomic number of the isotope
    private String mName;       // the name of the isotope
    private String mAbbr;       // the abbreviated name of the isotope
    private float mA1;          // the isotope's A1 value
    private float mA2;          // the isotope's A2 value
    private float mDecayCon;    // the isotope's decay constant
    private float mExemptCon;   // the isotope's exempt concentration
    private float mExemptLim;   // the isotope's exempt limit
    private float mHalfLife;    // the isotope's half life
    private float mLicLim;      // the isotope's licensing limit
    private float mReportQ;     // the isotope's reportable quantity

    /*/////////////////////////////////////////////////// ISOTOPE ////////////////////////////////////////////////////*/

    public Isotope() {} // Empty constructor for Firebase's serialization

    /**
     * Constructs an Isotope object with the given name and initial activity
     *
     * @param name      the name of the isotope
     * @param abbr      the abbreviated name of the isotope
     * @param a1        the isotope's a1 value
     * @param a2        the isotope's a2 value
     * @param decayCon  the isotope's decay constant
     * @param exemptCon the isotope's exempt concentration
     * @param exemptLim the isotope's exempt limit
     * @param halfLife  the isotope's half life
     * @param licLim    the isotope's licensing limit
     * @param reportQ   the isotope's reportable quantity
     */
    public Isotope(int number, String name, String abbr, float a1, float a2, float decayCon, float exemptCon,
                   float exemptLim, float halfLife, float licLim, float reportQ) {
        mNumber = number;
        mName = name;
        mAbbr = abbr;
        mA1 = a1;
        mA2 = a2;
        mDecayCon = decayCon;
        mExemptCon = exemptCon;
        mExemptLim = exemptLim;
        mHalfLife = halfLife;
        mLicLim = licLim;
        mReportQ = reportQ;
    }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/

    /**
     * Getter function to get this isotope's atomic number
     *
     * @return the atomic number of this isotope
     */
    public int getNumber() { return mNumber; }

    /**
     * Getter function to get this isotope's name
     *
     * @return the name of this isotope
     */
    public String getName() { return mName; }

    /**
     * Getter function to get this isotope's abbreviated name
     *
     * @return the abbreviated name of this isotope
     */
    public String getAbbr() { return mAbbr; }

    /**
     * Getter function to get this isotope's A1 value
     *
     * @return the isotope's A1 value
     */
    public float getA1() { return mA1; }

    /**
     * Getter function to get this isotope's A2 value
     *
     * @return the isotope's A2 value
     */
    public float getA2() { return mA2; }

    /**
     * Getter function to get this isotope's decay constant
     *
     * @return the isotope's decay constant
     */
    public float getDecayConst() { return mDecayCon; }

    /**
     * Getter function to get this isotope's exempt concentration
     *
     * @return the isotope's exempt concentration
     */
    public float getExemptCon() { return mExemptCon; }

    /**
     * Getter function to get this isotope's exempt limit
     *
     * @return the isotope's exempt limit
     */
    public float getExemptLim() { return mExemptLim; }

    /**
     * Getter function to get this isotope's halflife
     *
     * @return the isotope's halflife
     */
    public float getHalfLife() { return mHalfLife; }

    /**
     * Getter function to get this isotope's licensing limit
     *
     * @return the isotope's licensing limit
     */
    public float getLicLim() { return mLicLim; }

    /**
     * Getter function to get this isotope's reportable quantity
     *
     * @return the isotope's reportable quantity
     */
    public float getReportQ() { return mReportQ; }

    /*///////////////////////////////////////// SETTERS //////////////////////////////////////////*/

    /**
     * Setter function to set this isotope's atomic number
     *
     * @param number the new atomic number of the isotope
     */
    public void setNumber(int number) { mNumber = number; }

    /**
     * Setter function to set this isotope's name
     *
     * @param name the new name of the isotope
     */
    public void setName(String name) { mName = name; }

    /**
     * Setter function to set this isotope's abbreviated name
     *
     * @param abbr the new abbreviation of the the isotope
     */
    public void setAbbr(String abbr) { mAbbr = abbr; }

    /**
     * Setter function to set this isotope's A1 value
     *
     * @param a1 the new a1 value of the isotope
     */
    public void setA1(float a1) { mA1 = a1; }

    /**
     * Setter function to set this isotope's A2 value
     *
     * @param a2 the new a2 value of the isotope
     */
    public void setA2(float a2) { mA2 = a2; }

    /**
     * Setter function to set this isotope's decay constant
     *
     * @param decayCon the new decay constant of the isopope
     */
    public void setDecayConst(float decayCon) { mDecayCon = decayCon; }

    /**
     * Setter function to set this isotope's exempt concentration
     *
     * @param exemptCon the new exempt concentration of the isotope
     */
    public void setExemptCon(float exemptCon) { mExemptCon = exemptCon; }

    /**
     * Setter function to set this isotope's exempt limit
     *
     * @param exemptLim the new exempt limit of the isotope
     */
    public void setExemptLim(float exemptLim) { mExemptLim = exemptLim; }

    /**
     * Setter function to set this isotope's halflife
     *
     * @param halfLife the new halflife of the isotope
     */
    public void setHalfLife(float halfLife) { mHalfLife = halfLife; }

    /**
     * Setter function to set this isotope's licensing limit
     *
     * @param licLim the new licensing limit of the isotope
     */
    public void setLicLim(float licLim) { mLicLim = licLim; }

    /**
     * Setter function to set this isotope's reportable quantity
     *
     * @param reportQ the new reportable quantity of the isotope
     */
    public void setReportQ(float reportQ) { mReportQ = reportQ; }


}

