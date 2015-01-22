package epitech.epioid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import epitech.epioid.R;
import epitech.epioid.model.NvDrawerItem;

/**
 * Created by Gnitzsh on 22/01/2015.
 */
public class NavigationDrawerListAdapter extends ArrayAdapter<NvDrawerItem> {
    private Context mContext;
    private List<NvDrawerItem> mValues;

    public NavigationDrawerListAdapter(Context context, List<NvDrawerItem> objects) {
        super(context, R.layout.item_navdrawer_list,  objects);
        mContext = context;
        mValues = objects;
    }

    static class ViewHolder {
        private ImageView   iv_icon;
        private TextView    tv_itemTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_navdrawer_list, parent, false);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.navdrawer_item_icon);
            viewHolder.tv_itemTitle = (TextView) convertView.findViewById(R.id.navdrawer_item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_itemTitle.setText(mValues.get(position).getmLabel());
        viewHolder.iv_icon.setImageResource(mValues.get(position).getmIcon());
        return (convertView);
    }
}
