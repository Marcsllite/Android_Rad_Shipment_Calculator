package android.rad.shipment.calculator.model;

import java.util.ArrayList;

public class Shipment {
    // Declaring variables
    private final ArrayList<Isotope> isotopes;

    /*/////////////////////////////////////////////////// SHIPMENT ///////////////////////////////////////////////////*/
    /**
     * Constructs an empty Shipment object
     */
    public Shipment() { this.isotopes = new ArrayList<Isotope>(); }

    /**
     * Constructs an Shipment object with the given isotopes
     *
     * @param isotopes the isotopes in the shipment
     */
    Shipment(Isotope... isotopes) {
        this.isotopes = new ArrayList<Isotope>();
        addIsotopes(isotopes);
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/
    /**
     * Helper function to remove a given isotope from the shipment
     *
     * @param isotope the isotope to remove from the shipment
     */
    public void removeIsotope(Isotope isotope) { if(isotope != null) this.isotopes.remove(isotope); }

    /**
     * Helper function to remove all the isotopes in the shipment
     */
    public void removeAll() { this.isotopes.clear(); }

    /**
     * Helper function to replace an isotope in the shipment with a new one
     *
     * @param oldIsotope the isotope to be replaced
     * @param newIsotope the new isotope to replace the old one
     */
    public void updateIsotope(Isotope oldIsotope, Isotope newIsotope) {
        if(oldIsotope != null && newIsotope != null) {
            if(isInShipment(oldIsotope)) isotopes.set(isotopes.indexOf(oldIsotope), newIsotope);
        }
    }

    /**
     * Helper function to replace an isotope in the shipment with a new one
     *
     * @param index the index of the isotope to be replaced
     * @param newIsotope the new isotope to replace the old one
     */
    public void updateIsotope(int index, Isotope newIsotope) {
        if (index >= 0 && newIsotope != null) isotopes.set(index, newIsotope);
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

    /*///////////////////////////////////////// SETTERS //////////////////////////////////////////*/
    /**
     * Setter function to set the isotopes in the shipment
     *
     * @param isotopes the isotopes to be put into the shipment object
     */
    public void addIsotopes(Isotope... isotopes) {
        if(isotopes != null)
            for (Isotope isotope : isotopes){
                if(isotope != null)this.isotopes.add(isotope);  // adding the isotope to the list
            }
    }
}
