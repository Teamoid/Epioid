package epitech.epioid.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import epitech.epioid.API.Epitech;
import epitech.epioid.R;
import epitech.epioid.adapters.NavigationDrawerListAdapter;
import epitech.epioid.fragments.ActivitiesToComeFragment;
import epitech.epioid.fragments.CalendarFragment;
import epitech.epioid.fragments.HomeFragment;
import epitech.epioid.fragments.SusiesListFragment;
import epitech.epioid.interfaces.IEpiFragment;
import epitech.epioid.model.NvDrawerItem;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout            mDrawerLayout;
    private ListView                mDrawerListView;
    private ActionBarDrawerToggle   mDrawerToggle;
    private List<NvDrawerItem>      mDrawerItemList;

    private ProgressBar             mProgressBar;
    private FrameLayout             mFrameLayout;

    private IEpiFragment            mCurrentFragment;

    private String                  mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.main_view_progress);
        mFrameLayout = (FrameLayout) findViewById(R.id.content_frame);

        mDrawerItemList = new ArrayList<>();
        mDrawerItemList.add(new NvDrawerItem(R.drawable.ic_action_person, "Accueil", new HomeFragment()));
        mDrawerItemList.add(new NvDrawerItem(R.drawable.ic_action_group, "Susies", new SusiesListFragment()));
        mDrawerItemList.add(new NvDrawerItem(R.drawable.ic_action_event, "Planning", new CalendarFragment()));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setAdapter(new NavigationDrawerListAdapter(this, mDrawerItemList));
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                null,
                R.string.default_credits,
                R.string.default_credits
        ) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        IEpiFragment homeFragment = new HomeFragment();
        mCurrentFragment = homeFragment;
        showProgress(true);
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, (Fragment) homeFragment).commit();
        getSupportActionBar().setTitle(mDrawerItemList.get(0).getmLabel());
        showProgress(false);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            showProgress(true);
            selectItem(position);
            showProgress(false);
        }
    }

    private void selectItem(int position) {
        Epitech.client.cancelRequests(this, false);
        mCurrentFragment = mDrawerItemList.get(position).getmFragment();
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, (Fragment) mDrawerItemList.get(position).getmFragment()).commit();
        mTitle = mDrawerItemList.get(position).getmLabel();
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mCurrentFragment.refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mFrameLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mFrameLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mFrameLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mFrameLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}