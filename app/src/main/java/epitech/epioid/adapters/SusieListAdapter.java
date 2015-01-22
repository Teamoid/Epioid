package epitech.epioid.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date begin = null;
        Date end = null;

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_susie_list, parent, false);
        TextView susieClassName = (TextView) rowView.findViewById(R.id.susie_class_name);
        TextView susieDate = (TextView) rowView.findViewById(R.id.susie_date);
        TextView susieName = (TextView) rowView.findViewById(R.id.susie_name);
        TextView susieRegistered = (TextView) rowView.findViewById(R.id.susie_registered);

        susieRegistered.setTypeface(font);

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy à HH:mm");

        if (((SusiePlanning.SusiePlanningItem)values.get(position)).event_registered != null)
            susieRegistered.setText(context.getResources().getString(R.string.susie_registered));

        try {
            begin = input.parse(((SusiePlanning.SusiePlanningItem)values.get(position)).start);
            end = input.parse(((SusiePlanning.SusiePlanningItem)values.get(position)).end);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        susieClassName.setText(((SusiePlanning.SusiePlanningItem)values.get(position)).title);
        susieDate.setText("Du " + output.format(begin)
        + " au " + output.format(end));
        susieName.setText("Intervenant : " + ((SusiePlanning.SusiePlanningItem)values.get(position)).maker.login);

        return rowView;
    }

    public EpitechItem getItem(int position){
        return values.get(position);
    }

}
