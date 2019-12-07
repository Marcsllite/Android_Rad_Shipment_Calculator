package android.rad.shipment.calculator.model;

import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Shipment {
    // Declaring variables
    private final ArrayList<Isotope> isotopes;
    private final int defaultInt = R.integer.defaultInt;
    private boolean isMassConsistent, isNSFConsistent;      // variables to know if the user wants
                                                            // the same mass and nature/state/form
                                                            // values as the last added isotope
    private int consistentMassIndex;                        // variable to hold the index of the last
                                                            // added isotope where the consistent mass
                                                            // checkbox was checked
    private int consistentNSFIndex;                         // variable to hold the index of the last
                                                            // added isotope where the consistent 
                                                            // nature/state/form checkbox was checked

    /*/////////////////////////////////////////////////// SHIPMENT ///////////////////////////////////////////////////*/
    /**
     * Constructs an empty Shipment object
     */
    public Shipment() { 
        this.isotopes = new ArrayList<>(); 
        isMassConsistent = false;
        isNSFConsistent = false;
        consistentMassIndex = defaultInt;
        consistentNSFIndex = defaultInt;
    }

    /**
     * Constructs an Shipment object with the given isotopes
     *
     * @param isotopes the isotopes in the shipment
     */
    Shipment(Isotope... isotopes) {
        this.isotopes = new ArrayList<>();
        isMassConsistent = false;
        isNSFConsistent = false;
        consistentMassIndex = defaultInt;
        consistentNSFIndex = defaultInt;
        
        addIsotopes(isotopes);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    /**
     * Helper function to reset if the user clicked both the consistent mass or consistent nature/state/form
     * checkboxes
     */
    private void resetConsistentAll() {
        isMassConsistent = false;
        isNSFConsistent = false;
        consistentMassIndex = defaultInt;
        consistentNSFIndex = defaultInt;
    }

    /**
     * Helper function to reset if the user clicked the consistent mass checkbox
     */
    private void resetConsistentMass() {
        isMassConsistent = false;
        consistentMassIndex = defaultInt;
    }

    /**
     * Helper function to reset if the user clicked the consistent nature/state/form checkbox
     */
    private void resetConsistentNSF() {
        isNSFConsistent = false;
        consistentNSFIndex = defaultInt;
    }

    /**
     * Helper function to figure out if isotope is empty
     *
     * @return is the shipment does not have any isotopes in it
     */
    public boolean isEmpty() { return isotopes.isEmpty(); }

    /**
     * Helper function to get the number of isotopes in the shipment
     *
     * @return the number of isotopes in the shipment
     */
    public int getSize() { return isotopes.size(); }

    /**
     * Helper function to get an isotope in the shipment from the given index
     *
     * @param index the index of the isotope to get
     * @return the isotope located at that index
     */
    @Nullable public Isotope get(int index) { return (index < 0 || index >= isotopes.size())? null : isotopes.get(index);}

    /**
     * Helper function to remove a given isotope from the shipment
     *
     * @param isotope the isotope to remove from the shipment
     */
    public void removeIsotope(Isotope isotope) {
        if(isotope != null) {
            isotopes.remove(isotope);

            if(isotopes.indexOf(isotope) == consistentMassIndex) resetConsistentMass();

            if(isotopes.indexOf(isotope) == consistentNSFIndex) resetConsistentNSF();

            // resetting the checkbox booleans if there are no more isotopes in the shipment
            if(this.isotopes.size() == 0) resetConsistentAll();

            ShipmentActivityView.getIsotopeAdapter().notifyDataSetChanged();
        }
    }

    /**
     * Helper function to remove an isotope at the given index from the shipment
     *
     * @param index the index of the isotope to remove
     */
    public void removeIsotope(int index) {
        // making sure index is valid
        if(index >= 0 && index < isotopes.size()) {
            isotopes.remove(isotopes.get(index));

            // resetting the is consistent variables if needed
            if(index == consistentMassIndex) resetConsistentMass();
            if(index == consistentNSFIndex) resetConsistentNSF();
            if(this.isotopes.size() == 0) resetConsistentAll();

            ShipmentActivityView.getIsotopeAdapter().notifyDataSetChanged();
        }
    }

    /**
     * Helper function to remove all the isotopes in the shipment
     */
    public void removeAll() {
        this.isotopes.clear();
        resetConsistentAll();
        ShipmentActivityView.getIsotopeAdapter().notifyDataSetChanged();
    }

    /**
     * Helper function to replace an isotope in the shipment with a new one
     *
     * @param oldIsotope the isotope to be replaced
     * @param newIsotope the new isotope to replace the old one
     */
    public void updateIsotope(Isotope oldIsotope, Isotope newIsotope) {
        if(oldIsotope != null && newIsotope != null) {
            if(isInShipment(oldIsotope)) {
                isotopes.set(isotopes.indexOf(oldIsotope), newIsotope);
                ShipmentActivityView.getIsotopeAdapter().notifyDataSetChanged();
            }
        }
    }

    /**
     * Helper function to replace an isotope in the shipment with a new one
     *
     * @param index the index of the isotope to be replaced
     * @param newIsotope the new isotope to replace the old one
     */
    public void updateIsotope(int index, Isotope newIsotope) {
        if (index >= 0 && newIsotope != null) {
            isotopes.set(index, newIsotope);
            ShipmentActivityView.getIsotopeAdapter().notifyDataSetChanged();
        }
    }

    /**
     * Helper function to check if a given isotope is in the shipment
     *
     * @param isotope the isotope to check for
     * @return true if the isotope is in the shipment otherwise false
     */
    public boolean isInShipment(Isotope isotope) { return this.isotopes.contains(isotope); }

    /*///////////////////////////////////////// GETTERS //////////////////////////////////////////*/
    /**
     * Getter function to get the isotopes in this shipment
     *
     * @return the isotopes in this shipment
     */
    public ArrayList<Isotope> getIsotopes() { return isotopes; }

    /**
     * Getter function to get if the upcoming isotopes will have the same mass as the isotope at
     * consistentMassIndex
     *
     * @return whether the upcoming isotopes will share the same mass as the last one added
     */
    public boolean getMassConsistent() { return isMassConsistent; }

    /**
     * Getter function to get if the upcoming isotopes will have the same nature/state/form as the 
     * isotope at at consistentNSFIndex
     *
     * @return whether the upcoming isotopes will share the same nature/state/form as 
     *                     the last one added
     */
    public boolean getNSFConsistent() { return isNSFConsistent; }

    /**
     * Getter function to get the index of the isotope where the consistent mass is located
     *
     * @return the index of the isotope where the consistent mass is located
     */
    public int getConsistentMassIndex() { return consistentMassIndex; }

    /**
     * Getter function to get the index of the isotope where the consistent nature/state/form is located
     *
     * @return the index of the isotope where the consistent nature/state/form is located
     */
    public int getConsistentNSFIndex() { return consistentNSFIndex; }

    /*///////////////////////////////////////// SETTERS //////////////////////////////////////////*/
    /**
     * Setter function to set the isotopes in the shipment
     *
     * @param isotopes the isotopes to be put into the shipment object
     */
    public void addIsotopes(Isotope... isotopes) {
        if(isotopes != null)
            for (Isotope isotope : isotopes){
                if(isotope != null) {
                    this.isotopes.add(isotope);  // adding the isotope to the list
                    ShipmentActivityView.getIsotopeAdapter().notifyDataSetChanged();
                }
            }
    }

    /**
     * Setter function to set that the upcoming isotopes will have the same mass as the isotope at
     * consistentMassIndex
     */
    public void setMassConsistent() { isMassConsistent = true; }

    /**
     * Setter function to set that the upcoming isotopes will have the same nature/state/form as the
     * isotope at at consistentNSFIndex
     */
    public void setNSFConsistent() { isNSFConsistent = true; }

    /**
     * Setter function to set the index of the isotope where the consistent mass is located
     *
     * @param index the index of the isotope where the consistent mass is located
     */
    public void setConsistentMassIndex(int index) { 
        if(index >= 0 && index <= isotopes.size() && consistentMassIndex == R.integer.defaultInt) consistentMassIndex = index;
    }

    /**
     * Setter function to set the index of the isotope where the consistent nature/state/form is located
     *
     * @param index the index of the isotope where the consistent nature/state/form is located
     */
    public void setConsistentNSFIndex(int index) {
        if(index >= 0 && index <= isotopes.size() && consistentNSFIndex == R.integer.defaultInt) consistentNSFIndex = index;
    }
}
