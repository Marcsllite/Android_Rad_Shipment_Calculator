package android.rad.shipment.calculator.database.datasource;

import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.database.tables.ShortLong;

import java.util.List;

/**
 * Access point for managing user data.
 */
public interface IShipmentCalculatorDataSource {

    // Isotopes getters
    List<Isotopes> getAllValidIsos();

    String getAbbr(String name);

    String getName(String abbr);

    Isotopes getNameAndAbbr(String abbr);

    // Short/Long getters
    List<ShortLong> getAllShortLong();

    String getShortLong(String name);

    // Isotope Info getters
    float getA1(String abbr);

    float getA2(String abbr);

    float getDecayConstant(String abbr);

    float getExemptConcentration(String abbr);

    float getExemptLimit(String abbr);

    float getHalfLife(String abbr);

    float getIALimitedMultiplier(String state, String form);

    float getIAPackageLimit(String state, String form);

    float getLicensingLimit(String abbr);

    float getLimitedLimit(String state, String form);

    float getReportableQuantity(String abbr);
}