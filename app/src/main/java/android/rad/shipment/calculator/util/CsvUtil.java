package android.rad.shipment.calculator.util;

import android.content.Context;
import android.rad.shipment.calculator.R;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.RawRes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to read the database data from the csv files located in the res folder
 * NOTE: does not skip first line of csv file which contains the column names
 *       this must be removed before using this class
 */
public class CsvUtil {
    private static final String TAG = "CsvUtil";
    private static List<Pair<String, String>> valid_isotopes = new ArrayList<>();

    /**
     * Helper function to initialize the valid_isotopes array with the
     * values from valid_isotopes.csv
     *
     * @param context the application context to open the csv file as an input stream
     */
    private static void initValidIsotopes(Context context){
        InputStream inputStream = context.getResources().openRawResource(R.raw.valid_isotopes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;

            reader.readLine(); // skipping the first line of the csv file

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                // adding the abbreviation and full name of the isotope to the
                // valid isotopes array. the abbreviation will be used as the
                // key since the other csv files use that as the key as well
                valid_isotopes.add(new Pair<>(splitLine[1], splitLine[0]));
            }
        }
        catch (Exception ex) { Log.v(TAG, ex.getMessage()); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(TAG, e.getMessage()); }
        }
    }

    /**
     * Helper function to return a list of the name pairs for the valid isotopes
     *      first in the pair is the abbreviated name of the isotope
     *      second in the pair is the full isotope name
     *
     * @param context the application context to open the csv file as an input stream
     * @return the list of the name pairs (abbreviated, full name) of all the valid isotopes
     */
     public static List<Pair<String, String>> getValidIsotopes(Context context) {
        if(valid_isotopes.isEmpty()){
            initValidIsotopes(context);
        }
        return valid_isotopes;
    }

    /**
     * Helper function to get a value of the given isotope from the corresponding csv file
     *
     * @param file the resource id of the csv file to get the value from
     * @param abbr the abbreviated name of the isotope to search for
     * @param context the application context to open the csv file as an input stream
     * @return the value for the given isotope from the corresponding csv file
     */
    public static Float getValue(@RawRes int file, String abbr, Context context) {
        float ret = context.getResources().getInteger(R.integer.error);

        InputStream inputStream = context.getResources().openRawResource(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        if(file == R.raw.atomic_number){
            abbr = abbr.substring(0, abbr.indexOf('-'));
        }

        try {
            String line;
            String[] splitLine;

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                if(abbr.equals(splitLine[0])){
                    ret = Float.parseFloat(splitLine[1]);
                    break;
                }
            }
        }
        catch (Exception ex) { Log.v(TAG, ex.getMessage()); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(TAG, e.getMessage()); }
        }

        return ret;
    }

    /**
     * Helper function to check if the given isotope has a short and long halflife
     *
     * @param file the resource id of the csv file to get the value from
     * @param name the name of the isotope to search for
     * @param context the application context to open the csv file as an input stream
     * @return true if the isotope is in short_long.csv o.w false
     */
    public static boolean isShortLong(@RawRes int file, String name, Context context) {
        InputStream inputStream = context.getResources().openRawResource(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                if(line.contains(name)){
                    return true;
                }
            }
        }
        catch (Exception ex) { Log.v(TAG, ex.getMessage()); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(TAG, e.getMessage()); }
        }

        return false;
    }

}
