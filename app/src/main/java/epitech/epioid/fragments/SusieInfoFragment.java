package epitech.epioid.fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Information;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;

public class SusieInfoFragment extends Fragment {

    private SusiePlanning.SusiePlanningItem susie;
    private TextView title;
    private TextView date;
    private TextView name;
    private TextView nb_place;
    private TextView id_room;
    private TextView registered;
    private Button registerButton;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Date begin = null;
        Date end = null;
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy à HH:mm");

        try {
            begin = input.parse(susie.start);
            end = input.parse(susie.end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        View rootView = inflater.inflate(R.layout.fragment_susie_info, container, false);

        title = (TextView) rootView.findViewById(R.id.susie_class_name);
        date = (TextView) rootView.findViewById(R.id.susie_date);
        name = (TextView) rootView.findViewById(R.id.susie_name);
        nb_place = (TextView) rootView.findViewById(R.id.nb_place);
        id_room = (TextView) rootView.findViewById(R.id.id_room);
        registered = (TextView) rootView.findViewById(R.id.susie_registered);
        registerButton = (Button) rootView.findViewById(R.id.button);

        title.setText(susie.title);
        date.setText("Du " + output.format(begin)
                + " au " + output.format(end));
        name.setText("Intervenant : " + susie.maker.login);
        nb_place.setText(susie.nb_place + " place(s) restante(s)");
        id_room.setText("Pas de salle assignée");
        if (susie.type_room != null)
            id_room.setText("Salle : " + susie.type_room);
        if (susie.event_registered != null) {
            registered.setText(getActivity().getResources().getString(R.string.susie_registered));
            registerButton.setText("UNREGISTER");
            registerButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //UNREGISTER
                }

            });
        }
        else {
            registered.setText(getActivity().getResources().getString(R.string.default_susie_registered));
            registerButton.setText("REGISTER");
            registerButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //REGISTER
                }

            });
        }
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        registered.setTypeface(font);

        return rootView;
    }

    public void setSusie(SusiePlanning.SusiePlanningItem value) {
        this.susie = value;
    }
}