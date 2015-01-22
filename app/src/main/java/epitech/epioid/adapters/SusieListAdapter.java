package epitech.epioid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Information;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;

/**
 * Created by mato_t on 22/01/15.
 */
public class SusieListAdapter extends ArrayAdapter<EpitechItem> {

    private final Context context;
    private final List<EpitechItem> values;

    public SusieListAdapter(Context context, List<EpitechItem> values) {
        super(context, R.layout.item_susie_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_susie_list, parent, false);
        TextView susieClassName = (TextView) rowView.findViewById(R.id.susie_class_name);
        TextView susieStart = (TextView) rowView.findViewById(R.id.susie_start);
        TextView susieEnd = (TextView) rowView.findViewById(R.id.susie_end);
        TextView susieName = (TextView) rowView.findViewById(R.id.susie_name);

        susieClassName.setText(((SusiePlanning.SusiePlanningItem)values.get(position)).title);
        susieStart.setText(((SusiePlanning.SusiePlanningItem)values.get(position)).start);
        susieEnd.setText(((SusiePlanning.SusiePlanningItem)values.get(position)).end);
        susieName.setText(((SusiePlanning.SusiePlanningItem)values.get(position)).owner.login);

        return rowView;
    }

}
