package android.rad.shipment.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class contains static util methods
 */
public class Utils {

    /**
     * Private constructor in order to prevent accidental instantiation
     */
    private Utils() {}

    /**
     * Convert Unix timestamp to a human readable representation of date
     */
    public static String convertToHumanReadableDate(String timestamp) {
        SimpleDateFormat fmtOut = new SimpleDateFormat("", Locale.US);
        return fmtOut.format(new Date(Long.valueOf(timestamp)));
    }

}
