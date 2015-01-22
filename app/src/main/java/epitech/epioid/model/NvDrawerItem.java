package epitech.epioid.model;

import android.support.v4.app.Fragment;

/**
 * Created by Ganitzsh on 22/01/2015.
 */
public class NvDrawerItem {
    private int         mIcon;
    private String      mLabel;
    private Fragment    mFragment;

    public NvDrawerItem(int icon, String label, Fragment fragment) {
        mIcon = icon;
        mLabel = label;
        mFragment = fragment;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getmLabel() {
        return mLabel;
    }

    public void setmLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }
}
