package android.rad.shipment.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Shipment {
    // Declaring variables
    private final List<Isotope> isotopes;

    /*/////////////////////////////////////////////////// SHIPMENT ///////////////////////////////////////////////////*/
    /**
     * Constructs an empty Shipment object
     */
    Shipment() { this.isotopes = new ArrayList<Isotope>(); }

    /**
     * Constructs an Shipment object with the given isotopes
     *
     * @param isotopes the isotopes in the shipment
     */
    Shipment(Isotope... isotopes) {
        this.isotopes = new ArrayList<Isotope>();
        setIsotopes(isotopes);
    }

    /*/////////////////////////////////////////////////// GETTERS ////////////////////////////////////////////////////*/
    /**
     * Getter function to get the isotopes in this shipment
     *
     * @return the isotopes in this shipment
     */
    public List<Isotope> getIsotopes() { return isotopes; }

    /*/////////////////////////////////////////////////// SETTERS ////////////////////////////////////////////////////*/
    /**
     * Setter function to set the isotopes in the shipment
     *
     * @param isotopes the isotopes to be put into the shipment object
     */
    public void setIsotopes(Isotope... isotopes) {
        if(isotopes != null)
            for (Isotope isotope : isotopes){
                if(isotope != null)this.isotopes.add(isotope);  // adding the isotope to the list
            }
    }
}
