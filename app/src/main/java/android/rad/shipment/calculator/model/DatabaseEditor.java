package android.rad.shipment.calculator.model;

import android.rad.shipment.calculator.R;

import java.security.InvalidParameterException;

public class DatabaseEditor {

    public DatabaseEditor() {
        //TODO: connect ot database

        //TODO: make sure database is populated
    }

    /*/////////////////////////////////////////////////// GETTERS ////////////////////////////////////////////////////*/

    /**
     * Getter function to get the given isotope's A1 value 
     * from the A1 table in the database
     *
     * @param name the name of the isotope
     * @return the A1 value of that isotope
     */
    public float getA1(String name) {
        float ret = (float) R.integer.defaultInt;

        // TODO: Finish getA1

        return ret;
    }

    /**
     * Getter function to get the given isotope's A2 value 
     * from the A2 table in the database
     *
     * @param name the name of the isotope
     * @return the A2 value of that isotope
     */
    public float getA2(String name) {
        float ret = (float)R.integer.defaultInt;

        // TODO: Finish getA2

        return ret;
    }

    /**
     * Getter function to get the given isotope's Decay Constant value 
     * from the Decay Constant table in the database
     *
     * @param name the name of the isotope
     * @return the Decay Constant value of that isotope
     */
    public float getDecayConstant(String name) {
        float ret = (float)R.integer.defaultInt;

        // TODO: Finish getDecayConstant

        return ret;
    }

    /**
     * Getter function to get the given isotope's Exempt Concentration value 
     * from the Exempt Concentration table in the database
     *
     * @param name the name of the isotope
     * @return the Exempt Concentration value of that isotope
     */
    public float getExemptConcentration(String name) {
        float ret = (float)R.integer.defaultInt;

        // TODO: Finish getExemptConcentration

        return ret;
    }

    /**
     * Getter function to get the given isotope's Exempt Limit value 
     * from the Exempt Limit table in the database
     *
     * @param name the name of the isotope
     * @return the Exempt Limit value of that isotope
     */
    public float getExemptLimit(String name) {
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getExemptLimit

        return ret;
    }

    /**
     * Getter function to get the given isotope's Half Life value 
     * from the Half Life table in the database
     *
     * @param name the name of the isotope
     * @return the Half Life value of that isotope
     */
    public float getHalfLife(String name) {
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getHalfLife

        return ret;
    }

    /**
     * Getter function to get the given isotope's Instruments/Articles Limited Multiplier value 
     * from the Instruments/Articles Limited Limit Multiplier table in the database
     *
     * @param state the state of the instrument/article
     * @param form the form of the instrument/article
     * @return the Instruments/Articles Limited Multiplier value of that isotope
     */
    public float getIALimitedMultiplier(String state, String form) throws RuntimeException {
        if(state == null || "".equals(state)) throw new InvalidParameterException("state cannot be null or empty string");
        if(form == null || "".equals(form)) throw new InvalidParameterException("form cannot be null or empty string");
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getIALimitedMultiplier

        return ret;
    }

    /**
     * Getter function to get the given isotope's Instruments/Articles Package Limit value 
     * from the Instruments/Articles Package Limit table in the database
     *
     * @param state the state of the instrument/article
     * @param form the form of the instrument/article
     * @return the Instruments/Articles Package Limit value of that isotope
     */
    public float getIAPackageLimit(String state, String form) throws RuntimeException {
        if(state == null || "".equals(state)) throw new InvalidParameterException("state cannot be null or empty string");
        if(form == null || "".equals(form)) throw new InvalidParameterException("form cannot be null or empty string");
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getIAPackageLimit

        return ret;
    }

    /**
     * Getter function to get the given isotope's License Limit value 
     * from the Info table in the database
     *
     * @param name the name of the isotope
     * @return the License Limit value of that isotope
     */
    public float getLicenseLimit(String name) {
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getLicenseLimit

        return ret;
    }

    /**
     * Getter function to get the given isotope's Limited Limit value 
     * from the Limited Limit table in the database
     *
     * @param state the state of the limited isotope
     * @param form the form of the limited isotope
     * @return the Limited Limit value of that isotope
     */
    public float getLimitedLimit(String state, String form) throws RuntimeException {
        if(state == null || "".equals(state)) throw new InvalidParameterException("state cannot be null or empty string");
        if(form == null || "".equals(form)) throw new InvalidParameterException("form cannot be null or empty string");
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getLimitedLimit

        return ret;
    }

    /**
     * Getter function to get the given isotope's Reportable Quantity value
     * from the Reportable Quantity table in the database
     *
     * @param name the name of the isotope
     * @return the Reportable Quantity value of that isotope
     */
    public float getReportableQuantity(String name) throws RuntimeException {
        float ret = (float)R.integer.defaultInt;

        //TODO: Finish getReportableQuantity

        return ret;
    }

}
