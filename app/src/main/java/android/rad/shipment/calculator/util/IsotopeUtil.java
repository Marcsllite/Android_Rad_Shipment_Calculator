package android.rad.shipment.calculator.util;

import android.content.Context;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.model.Isotope;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for Isotopes
 */
public class IsotopeUtil {
    private static final String TAG = "IsotopeUtil";

    /**
     * Helper function to get a list of all the valid isotopes
     * and their data from the internal csv files
     *
     * @param context the application context to open the csv file as an input stream
     * @return A list of all the valid isotopes and their information from the csv files
     */
    public static List<Isotope> getIsotopes(Context context) {
        List<Isotope> isotopes = new ArrayList<>();
        List<Pair<String, String>> validIsotopes = CsvUtil.getValidIsotopes(context);

        for (Pair<String, String> pair: validIsotopes) {
            boolean isShortLong = CsvUtil.isShortLong(R.raw.short_long, pair.second, context);
            
            if(isShortLong) {
                isotopes.add(createIso(pair.second, pair.first + "(short)", context));
                isotopes.add(createIso(pair.second, pair.first + "(long)", context));
            } else {
                isotopes.add(createIso(pair.second, pair.first, context));
            }
        }

        return isotopes;
    }

    /**
     * Helper function to create an isotope using the given names and values from the csv files
     *
     * @param name the name of the isotope to be created
     * @param abbr the abbreviated name of the isotope to be created
     * @param context the application context to open the csv file as an input stream
     * @return an isotope with all the information from the csv files
     */
    private static Isotope createIso(String name, String abbr, Context context) {
        Isotope ret = new Isotope();

        ret.setNumber(CsvUtil.getValue(R.raw.atomic_number, abbr, context).intValue());
        ret.setName(name);
        ret.setAbbr(abbr);
        ret.setA1(CsvUtil.getValue(R.raw.a1, abbr, context));
        ret.setA2(CsvUtil.getValue(R.raw.a2, abbr, context));
        ret.setDecayConst(CsvUtil.getValue(R.raw.decay_constant, abbr, context));
        ret.setExemptCon(CsvUtil.getValue(R.raw.exempt_concentration, abbr, context));
        ret.setExemptLim(CsvUtil.getValue(R.raw.exempt_limit, abbr, context));
        ret.setHalfLife(CsvUtil.getValue(R.raw.half_life, abbr, context));
        ret.setLicLim(CsvUtil.getValue(R.raw.licensing_limit, abbr, context));
        ret.setReportQ(CsvUtil.getValue(R.raw.reportable_quantity, abbr, context));
        
        return ret;
    }
}
