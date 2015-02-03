package epitech.epioid.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Planning;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;

/**
 * Created by mato_t on 03/02/15.
 */
public class CalendarAdapter extends ArrayAdapter<EpitechItem> {

    private final Context context;
    private final List<EpitechItem> values;

    public CalendarAdapter(Context context, List<EpitechItem> values) {
        super(context, R.layout.item_calendar_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date begin = null;
        Date end = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_calendar_list, parent, false);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");

        TextView className = (TextView) rowView.findViewById(R.id.class_name);
        TextView classDate = (TextView) rowView.findViewById(R.id.class_date);
        TextView classRoom = (TextView) rowView.findViewById(R.id.class_room);
        TextView classRegistered = (TextView) rowView.findViewById(R.id.class_is_registered);

        classRegistered.setTypeface(font);

        if (((Planning.PlanningItem)values.get(position)).event_registered != null)
            classRegistered.setText(context.getResources().getString(R.string.susie_registered));

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm");

        try {
            begin = input.parse(((Planning.PlanningItem)values.get(position)).start);
            end = input.parse(((Planning.PlanningItem)values.get(position)).end);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        className.setText(((Planning.PlanningItem)values.get(position)).acti_title);
        classDate.setText("De " + output.format(begin)
                + " à " + output.format(end));
        classRoom.setText(((Planning.PlanningItem)values.get(position)).room.code);

        return rowView;
    }

    public EpitechItem getItem(int position){
        return values.get(position);
    }
}
