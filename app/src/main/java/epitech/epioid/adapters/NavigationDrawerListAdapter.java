package epitech.epioid.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import epitech.epioid.R;

/**
 * Created by Ganitzsh on 22/01/2015.
 */
public class NavigationDrawerListAdapter extends ArrayAdapter<String> {
    public NavigationDrawerListAdapter(Context context, List<String> objects) {
        super(context, R.layout.item_navdrawer_list,  objects);
    }
}
