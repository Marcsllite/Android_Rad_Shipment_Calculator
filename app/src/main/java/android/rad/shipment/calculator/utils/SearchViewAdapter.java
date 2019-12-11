package android.rad.shipment.calculator.utils;

import android.content.Context;
import android.rad.shipment.calculator.R;
import android.rad.shipment.calculator.database.tables.Isotopes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author
 *
 * Adapter to take an Isotopes and map it's data to a View to be shown in
 * the listView
 */
public class SearchViewAdapter extends ArrayAdapter<Isotopes> {
    // View lookup cache
    private static class ViewHolder {
        TextView txtViewName;
    }

    public SearchViewAdapter(Context context, List<Isotopes> isotopes) {
        super(context, R.layout.search_item, isotopes);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        Isotopes isotope = getItem(position);  // Get the data item for this position
        ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.search_item, parent, false);

            viewHolder.txtViewName = convertView.findViewById(R.id.txtViewName);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        assert isotope != null;
        String name = isotope.getName().toUpperCase() + " (" + isotope.getAbbr() + ")";
        
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.txtViewName.setText(name);
        // Return the completed view to render on screen
        return convertView;
    }
}