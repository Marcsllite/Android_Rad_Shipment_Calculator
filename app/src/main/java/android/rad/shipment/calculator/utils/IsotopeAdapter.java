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
public class IsotopeAdapter extends ArrayAdapter<Isotope> {
    // View lookup cache
    private static class ViewHolder {
        TextView txtViewName;
        TextView txtViewInfo;
    }


    public IsotopeAdapter(Context context, ArrayList<Isotope> isotopes) {
        super(context, R.layout.isotope_item, isotopes);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        Isotope isotope = getItem(position);  // Get the data item for this position
        ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.isotope_item, parent, false);

            viewHolder.txtViewName = convertView.findViewById(R.id.txtViewName);
            viewHolder.txtViewInfo = convertView.findViewById(R.id.txtViewInfo);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // \u00B5 = µ
        String info = ((int)isotope.get_A0()) + "\u00B5Ci";

        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.txtViewName.setText(isotope.get_Name().toUpperCase());
        viewHolder.txtViewInfo.setText(info);
        // Return the completed view to render on screen
        return convertView;
    }
}