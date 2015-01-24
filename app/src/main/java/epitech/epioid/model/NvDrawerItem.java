package epitech.epioid.model;
import epitech.epioid.interfaces.IEpiFragment;

/**
 * Created by Ganitzsh on 22/01/2015.
 */
public class NvDrawerItem {
    private int             mIcon;
    private String          mLabel;
    private IEpiFragment    mFragment;

    public NvDrawerItem(int icon, String label, IEpiFragment fragment) {
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

    public IEpiFragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(IEpiFragment mFragment) {
        this.mFragment = mFragment;
    }
}
