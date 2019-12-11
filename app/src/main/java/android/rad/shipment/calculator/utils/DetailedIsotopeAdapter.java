package android.rad.shipment.calculator.utils;

import android.content.Context;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.model.Isotope;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author
 *
 * Adapter to take an Isotope and map it's data to a View to be shown in
 * the listView
 */
public class DetailedIsotopeAdapter extends ArrayAdapter<Isotope> {
    // View lookup cache
    private static class ViewHolder {
        TextView txtViewName;
        TextView txtViewA0;
        TextView txtViewAToday;
        TextView txtViewHalfLife;
        TextView txtViewDecayConst;
        TextView txtViewDPM;
        TextView txtViewClass;
        TextView txtViewRQ;
        TextView txtViewLicLimPer;
        TextView txtViewActivityPer;
    }


    public DetailedIsotopeAdapter(Context context, ArrayList<Isotope> isotopes) {
        super(context, R.layout.detailed_isotope_item, isotopes);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        Isotope isotope = getItem(position);  // Get the data item for this position
        ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.detailed_isotope_item, parent, false);

            viewHolder.txtViewName = convertView.findViewById(R.id.txtViewName);
            viewHolder.txtViewA0 = convertView.findViewById(R.id.txtViewA0);
            viewHolder.txtViewAToday = convertView.findViewById(R.id.txtViewAToday);
            viewHolder.txtViewHalfLife = convertView.findViewById(R.id.txtViewHalfLife);
            viewHolder.txtViewDecayConst = convertView.findViewById(R.id.txtViewDecayConst);
            viewHolder.txtViewDPM = convertView.findViewById(R.id.txtViewDPM);
            viewHolder.txtViewClass = convertView.findViewById(R.id.txtViewClass);
            viewHolder.txtViewRQ = convertView.findViewById(R.id.txtViewRQ);
            viewHolder.txtViewLicLimPer = convertView.findViewById(R.id.txtViewLicLimPer);
            viewHolder.txtViewActivityPer = convertView.findViewById(R.id.txtViewActivityPer);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        assert isotope != null;
        String A0 = isotope.get_A0() + " \u00B5Ci",
                AToday = isotope.get_AToday() + " \u00B5Ci",
                HalfLife = isotope.get_HalfLife() + " days";

        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.txtViewName.setText(isotope.get_Name().toUpperCase());
        viewHolder.txtViewA0.setText(A0);
        viewHolder.txtViewAToday.setText(AToday);
        viewHolder.txtViewHalfLife.setText(HalfLife);
        viewHolder.txtViewDecayConst.setText(Float.toString(isotope.get_DecayConst()));
        viewHolder.txtViewDPM.setText(Float.toString(isotope.get_DPM()));
        viewHolder.txtViewClass.setText(isotope.get_StringIsotopeClass());
        viewHolder.txtViewRQ.setText(Boolean.toString(isotope.get_RQ()));
        viewHolder.txtViewLicLimPer.setText(Float.toString(isotope.get_LicLimPer()));
        viewHolder.txtViewActivityPer.setText(Float.toString(isotope.get_ActivityPer()));
        // Return the completed view to render on screen
        return convertView;
    }
}