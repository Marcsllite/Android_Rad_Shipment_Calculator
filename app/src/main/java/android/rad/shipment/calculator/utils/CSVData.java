package android.rad.shipment.calculator.utils;

import android.content.Context;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.database.tables.A1;
import android.rad.shipment.calculator.database.tables.A2;
import android.rad.shipment.calculator.database.tables.DecayConstant;
import android.rad.shipment.calculator.database.tables.ExemptConcentration;
import android.rad.shipment.calculator.database.tables.ExemptLimit;
import android.rad.shipment.calculator.database.tables.HalfLife;
import android.rad.shipment.calculator.database.tables.IALimitedLimit;
import android.rad.shipment.calculator.database.tables.IAPackageLimit;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.rad.shipment.calculator.database.tables.LicensingLimit;
import android.rad.shipment.calculator.database.tables.LimitedLimit;
import android.rad.shipment.calculator.database.tables.ReportableQuantity;
import android.rad.shipment.calculator.database.tables.ShortLong;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVData {
    public static A1[] getA1Data(Context context) {
        ArrayList<A1> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.a1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new A1(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new A1[0]);
    }

    public static A2[] getA2Data(Context context) {
        ArrayList<A2> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.a2);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new A2(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new A2[0]);
    }

    public static DecayConstant[] getDecayConstantData(Context context) {
        ArrayList<DecayConstant> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.decay_constant);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new DecayConstant(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new DecayConstant[0]);
    }

    public static ExemptConcentration[] getExemptConcentrationData(Context context) {
        ArrayList<ExemptConcentration> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.exempt_concentration);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new ExemptConcentration(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new ExemptConcentration[0]);
    }

    public static ExemptLimit[] getExemptLimitData(Context context) {
        ArrayList<ExemptLimit> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.exempt_limit);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new ExemptLimit(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new ExemptLimit[0]);
    }

    public static HalfLife[] getHalfLifeData(Context context) {
        ArrayList<HalfLife> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.half_life);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new HalfLife(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new HalfLife[0]);
    }

    public static IALimitedLimit[] getIALimitedLimitData(Context context) {
        ArrayList<IALimitedLimit> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.ia_limited_limit);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new IALimitedLimit(splitLine[0], splitLine[1], Float.parseFloat(splitLine[2])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new IALimitedLimit[0]);
    }

    public static IAPackageLimit[] getIAPackageLimitData(Context context) {
        ArrayList<IAPackageLimit> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.ia_package_limit);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new IAPackageLimit(splitLine[0], splitLine[1], Float.parseFloat(splitLine[2])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new IAPackageLimit[0]);
    }

    public static Isotopes[] getIsotopesData(Context context) {
        ArrayList<Isotopes> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.valid_isotopes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new Isotopes(splitLine[0], splitLine[1]));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new Isotopes[0]);
    }

    public static LicensingLimit[] getLicensingLimitData(Context context) {
        ArrayList<LicensingLimit> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.licensing_limit);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new LicensingLimit(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new LicensingLimit[0]);
    }

    public static LimitedLimit[] getLimitedLimitData(Context context) {
        ArrayList<LimitedLimit> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.limited_limit);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new LimitedLimit(splitLine[0], splitLine[1], Float.parseFloat(splitLine[2])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new LimitedLimit[0]);
    }

    public static ReportableQuantity[] getReportableQuantityData(Context context) {
        ArrayList<ReportableQuantity> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.reportable_quantity);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new ReportableQuantity(splitLine[0], Float.parseFloat(splitLine[1])));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new ReportableQuantity[0]);
    }

    public static ShortLong[] getShortLongData(Context context) {
        ArrayList<ShortLong> ret = null;

        InputStream inputStream = context.getResources().openRawResource(R.raw.short_long);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            String[] splitLine;
            ret = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");  // splitting comma separated values

                ret.add(new ShortLong(splitLine[0], splitLine[1]));
            }
        }
        catch (Exception ex) { Log.v(ex.getMessage(), "message"); }
        finally {
            try { inputStream.close(); }
            catch (IOException e) { Log.v(e.getMessage(), "message"); }
        }

        return (ret == null)? null : ret.toArray(new ShortLong[0]);
    }
}
