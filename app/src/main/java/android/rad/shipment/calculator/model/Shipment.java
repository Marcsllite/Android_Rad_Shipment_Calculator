package android.rad.shipment.calculator.model;

import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.view.ShipmentActivityView;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;

public class Shipment {
    // Declaring variables
    private final ArrayList<Isotope> isotopes;
    private final int defaultInt = R.integer.defaultInt;
    private Date _D0;        // the reference date for the shipment
    private boolean isRQ;
    private boolean isMassConsistent, isNSFConsistent;      // variables to know if the user wants
                                                            // the same mass and nature/state/form
                                                            // values as the last added isotope
    private int consistentMassIndex;        // variable to hold the index of the last
                                            // added isotope where the consistent mass
                                            // checkbox was checked
    private int consistentNSFIndex;     // variable to hold the index of the last
                                        // added isotope where the consistent
                                        // nature/state/form checkbox was checked
    private int _ShipmentClass;     // Classification of isotope as an integer
                                    // (0 = Exempt,
                                    // 1 = Excepted,
                                    // 2 = Type A,
                                    // 4 = Type B,
                                    // 8 = Type B: Highway Route Control)

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

        _ShipmentClass = defaultInt;
        _D0 = null;
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

        _ShipmentClass = defaultInt;
        _D0 = null;
    }

    @Override public int hashCode() {
        final int prime = 31;
        int result = prime;

        for (Isotope iso: isotopes) { result += iso.hashCode(); }

        return result;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Shipment other = (Shipment) obj;

        if(other.isotopes.size() == this.isotopes.size()) {
            for (int i = 0; i < other.isotopes.size(); i++) {
                if(!other.isotopes.get(i).equals(this.isotopes.get(i))) return false;
            }
            return true;
        }
        return false;
    }

    /*///////////////////////////////////////// HELPERS //////////////////////////////////////////*/

    public String findClass() {
        int hClass = 0;
        float pLimit = 0, pCon = 0, aCon = 0, pAToday = 0, pRQ = 0,
                aFracEx = 0, aFracLim = 0, aFracA = 0, aFracB = 0,
                aFracHRCQ = 0, lFrac = 0, pLicLimit = 0, typeALim = 0;
        String retVal;

        // Getting highest Classification between all of the isotopes in the shipment
        for (Isotope iso: isotopes) {
            if (iso.get_IsotopeClass() > hClass)
                hClass = iso.get_IsotopeClass();
        }

        // Getting shipment limit percentage and shipment concentration and shipment licencing exemption
        for (Isotope iso: isotopes) {
            pLimit += iso.get_ActivityPer();  // adding each isotipe's percentage of the activity limit
            pCon += iter->second.CToday * 37000;  // adding each isotope's concentration today (in Bq)
            pAToday += iso.get_AToday() * 37000;  // adding each isotope's activity today (in Bq)
            lFrac += (iso.get_AToday() * 37000) / ((iter->second.licLim * 1000) * 37000);  // isotope activity in Bq / (1000 * licensing limit) in Bq
        }

        // calculating package limits for Exempt, Excepted, Type A Classification
        for (Isotope iso: isotopes) {
            iter->second.aCon = (iter->second.CToday * 37000) / pCon;  // fraction of activity concentration of isotope (Bq)
            iter->second.aFrac = (iter->second.Atoday * 37000) / pAToday;  // fraction of activity of isotope (Bq)
            aCon += iter->second.aCon / iter->second.exCon;  // fraction of activity concentration of isotope (Bq) / Exempt concentration limit (Bq/g)
            aFracEx += iter->second.aFrac / iter->second.exLim;  // fraction of activity of isotope (Bq) / Exempt Activity limit (Bq)
            aFracLim += iter->second.aFrac / (iter->second.ALim * pow(10.0, 12.0));  // package activity limit for Excepted Classification
            typeALim += (iter->second.Atoday * 37000) / (iter->second.ALim * 1e+12); // converting Activity today to Bq then dividing by A1 or A2 converted to Bq
            pRQ += iter->second.RQFrac;  // sum(individual nuclide activity (in microCi) / individual nuclide reportable quantity (in microCi))
        }

        if (pRQ < 1) {  // finding out if package is a reportable quantity
            isRQ = false;
        } else {
            isRQ = true;
        }

        aCon = 1 / aCon;  // Exempt Activity Concentration Limit for Package
        aFracEx = 1 / aFracEx;  // Exempt activity limit for Package
        aFracLim = 1 / aFracLim;  // Excepted activity limit for Package

        if (pLimit > 1 || typeALim > 1) {  // if package limit or typeALim is greater than 1 then move to next classification
            if (hClass == 0)
                hClass = 1;
            else {
                hClass = hClass << 1;
            }
        }

        if (lFrac > 1) {  // checking if shipment is exempt from licensing
            retVal = "Shipment exempt from licensing?: No\n";
        } else {
            retVal = "Shipment exempt from licensing?: Yes\n";
        }

        if (isRQ) {
            retVal.append("Shipemnt Reportable Quantity?: Yes\n");
        } else {
            retVal.append("Shipemnt Reportable Quantity?: No\n");
        }

        switch (hClass) {
            case 0:
                _ShipmentClass = 0;  // saving the shipment class
                retVal.append("Shipment Classification: Exempt:\n\tShipment Activity Limit: ");
                retVal.append(std::to_string(aFracEx));
                retVal.append(" Bq (Actual Activity: " + std::to_string(pAToday) + ")\n\tShipment Activity Concentration Limit: ");
                retVal.append(std::to_string(aCon));
                retVal.append(" Bq/g (Actual Concentraion: " + std::to_string(pCon) + ")\n\tShipment License Percentage: ");
                retVal.append(std::to_string(lFrac * 100) + "%\n");
                return retVal;
            break;
            case 1:
                _ShipmentClass = 1;  // saving the shipment class
                retVal.append("Shipment Classification: Excepted:\n\tShipment Activity Limit: ");
                retVal.append(std::to_string(aFracLim));
                retVal.append(" Bq (Actual Activity: " + std::to_string(pAToday) + ")\n\tShipment License Percentage: ");
                retVal.append(std::to_string(lFrac * 100) + "%\n");
                return retVal;
            break;
            case 2:
                _ShipmentClass = 2;  // saving the shipment class
                retVal.append("Shipment Classification: Type A:\n\tShipment Activity: ");
                retVal.append(std::to_string(pAToday));
                retVal.append(")\n\tShipment License Percentage: ");
                retVal.append(std::to_string(lFrac * 100) + "%\n");
                return retVal;
            break;
            case 4:
                _ShipmentClass = 4;  // saving the shipment class
                retVal.append("Shipment Classification: Tyep B:\n\tShipment Activity: ");
                retVal.append(std::to_string(pAToday));
                retVal.append(")\n\tShipment License Percentage: ");
                retVal.append(std::to_string(lFrac * 100) + "%\n");
                return retVal;
            break;
            case 8:
                _ShipmentClass = 8;  // saving the shipment class
                retVal.append("Shipment Classification: Type B: Highway Route Control:\n\tShipment Activity: ");
                retVal.append(std::to_string(pAToday));
                retVal.append(")\n\tShipment License Percentage: ");
                retVal.append(std::to_string(lFrac * 100) + "%\n");
                return retVal;
            break;
            default:
                return "FindClass: Error finding Shipment Classification.\n";
            break;
        }
    }

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

    /**
     * Getter function to get this shipment's classification as an integer
     *  0 = Exempt Classification
     *  1 = Excepted/Limited Classification
     *  2 = Type A Classification
     *  4 = Type B Classification
     *  8 = Type B: Highway Route Control Classification
     *
     * @return the classification of this shipment as an integer
     */
    public int get_ShipmentClass() { return _ShipmentClass; }

    /**
     * Getter function to get this shipment's reference date
     *
     * @return the reference date for the shipment
     */
    public Date get_ReferenceDate() { return _D0; }

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

    /**
     * Setter function to set this shipment's classification as an integer
     *  0 = Exempt Classification
     *  1 = Excepted/Limited Classification
     *  2 = Type A Classification
     *  4 = Type B Classification
     *  8 = Type B: Highway Route Control Classification
     *
     * @param shipmentClass the new classification of this shipment as an integer
     */
    public void set_ShipmentClass(int shipmentClass) { _ShipmentClass = shipmentClass; }

    /**
     * Setter function to set this shipment's reference date
     *
     * @param D0 the new reference date for the shipment
     */
    public void set_ReferenceDate(Date D0) { _D0 = D0; }
}
