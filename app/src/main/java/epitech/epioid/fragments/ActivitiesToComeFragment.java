package epitech.epioid.fragments;

import epitech.epioid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class ActivitiesToComeFragment extends Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activities_view, container, false);
    }
}
