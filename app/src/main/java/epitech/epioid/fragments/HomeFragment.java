package epitech.epioid.fragments;

import epitech.epioid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class HomeFragment extends Fragment {

    private TextView    tv_login;
    private TextView    tv_fullname;
    private TextView    tv_gpa;
    private TextView    tv_semester;
    private TextView    tv_credit;
    private TextView    tv_promo;
    private TextView    tv_history;
    private ImageView   iv_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_view, container, false);
    }

    public TextView getTv_history() {
        return tv_history;
    }

    public void setTv_history(TextView tv_history) {
        this.tv_history = tv_history;
    }

    public TextView getTv_login() {
        return tv_login;
    }

    public void setTv_login(TextView tv_login) {
        this.tv_login = tv_login;
    }

    public TextView getTv_fullname() {
        return tv_fullname;
    }

    public void setTv_fullname(TextView tv_fullname) {
        this.tv_fullname = tv_fullname;
    }

    public TextView getTv_gpa() {
        return tv_gpa;
    }

    public void setTv_gpa(TextView tv_gpa) {
        this.tv_gpa = tv_gpa;
    }

    public TextView getTv_semester() {
        return tv_semester;
    }

    public void setTv_semester(TextView tv_semester) {
        this.tv_semester = tv_semester;
    }

    public TextView getTv_credit() {
        return tv_credit;
    }

    public void setTv_credit(TextView tv_credit) {
        this.tv_credit = tv_credit;
    }

    public TextView getTv_promo() {
        return tv_promo;
    }

    public void setTv_promo(TextView tv_promo) {
        this.tv_promo = tv_promo;
    }

    public ImageView getIv_image() {
        return iv_image;
    }

    public void setIv_image(ImageView iv_image) {
        this.iv_image = iv_image;
    }
}
