package android.rad.shipment.calculator.model;

import android.rad.shipment.calculator.R;

public class Isotope {
    // Declaring Variables
    private int defaultVal = R.integer.defaultInt;
    private String _Name;                   // The name of the isotope
    private float _A0;                      // Initial Activity (microCuries) of isotope
    private float _Mass;                    // the mass of the isotope (grams for solids, liters for liquids)
    private String _Nature;                 // the nature (regular, special) of the isotope
    private String _State;                  // the state (solid, liquid, gas) of the isotope
    private String _Form;                   // the form (normal, special) of the isotope
    private String _LongShort;              // for isotopes with different half lives
    private char _LungAbs;                  // the lung absorption speed if the isotope is Uranium (f = fast, m = medium, s = slow)

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
    public Isotope(String name, float A0, float mass, String nature, String state, String form) {
        // Saving values
        _Name = name;
        _A0 = A0;
        _Mass = mass;
        _Nature = nature;
        _State = state;
        _Form = form;

        _LungAbs = '\0';
        _LongShort = "";
        _IsotopeClass = defaultVal;
    }

    /**
     * Constructs an Isotope object with the given name and initial activity
     *
     * @param name the name of the isotope
     * @param A0 the initial activity (microCi) of the isotope
     * @param mass the mass (grams/liters) of the isotope
     * @param nature the nature (special, regular) of the isotope
     * @param state the state (solid, liquid, gas) of the isotope
     * @param form the form (special, normal) of the isotope
     * @param longShort whether this is the isotope with the longer or shorter halflife (only for isotopes with multiple halflives)
     */
    public Isotope(String name, float A0, float mass, String nature, String state, String form, String longShort) {
        // Saving values
        _Name = name;
        _A0 = A0;
        _Mass = mass;
        _Nature = nature;
        _State = state;
        _Form = form;

        _LungAbs = '\0';
        _LongShort = longShort;
        _IsotopeClass = defaultVal;
    }

    /**
     * Constructs an Isotope object with the given name and initial activity
     *
     * @param name the name of the isotope
     * @param A0 the initial activity (microCi) of the isotope
     * @param mass the mass (grams/liters) of the isotope
     * @param nature the nature (special, regular) of the isotope
     * @param state the state (solid, liquid, gas) of the isotope
     * @param form the form (special, normal) of the isotope
     * @param lungAbs the lung absorption rate of the isotope 
     *                  f = fast, m = medium, s = slow
     */
    public Isotope(String name, float A0, float mass, String nature, String state, String form, char lungAbs) {
        // Saving values
        _Name = name;
        _A0 = A0;
        _Mass = mass;
        _Nature = nature;
        _State = state;
        _Form = form;

        _LungAbs = lungAbs;
        _LongShort = "";
        _IsotopeClass = defaultVal;
    }

    /**
     * Copy Constructor
     *
     * @param newIso the isotope to copy all the information from
     */
    public Isotope(Isotope newIso) {
        _Name = newIso._Name;                 
        _A0 = newIso._A0;
        _Mass = newIso._Mass;
        _Nature = newIso._Nature;
        _State = newIso._State;
        _Form = newIso._Form;
        _LongShort = newIso._LongShort;
        _LungAbs = newIso._LungAbs;
        _IsotopeClass = newIso._IsotopeClass;
    } 
    
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((_Name == null) ? 0 : _Name.hashCode()) +
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
                other._A0 == this._A0 &&
                other._Mass == this._Mass &&
                other._Nature.equals(this._Nature) &&
                other._State.equals(this._State) &&
                other._Form.equals(this._Form);
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
     * Getter function to get this isotope's mass in grams for solids and liters for liquids
     *
     * @return the mass of this isotope in (grams/liters)
     */
    public float get_Mass() { return _Mass; }
    
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
    public String get_LongShort() { return _LongShort; }

    /**
     * Getter function to get this isotope's lung absorption rate (slow/medium/fast)
     *
     * @return this isotope's lung absorption rate
     */
    public char get_LungAbs() { return _LungAbs; }

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
}
