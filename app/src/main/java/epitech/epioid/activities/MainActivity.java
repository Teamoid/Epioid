package epitech.epioid.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import epitech.epioid.R;
import epitech.epioid.adapters.NavigationDrawerListAdapter;
import epitech.epioid.fragments.HomeFragment;
import epitech.epioid.fragments.SusiesFragment;
import epitech.epioid.model.NvDrawerItem;

public class MainActivity extends ActionBarActivity {

    private HomeFragment            mHomeFragment;

    private DrawerLayout            mDrawerLayout;
    private ListView                mDrawerListView;
    private ActionBarDrawerToggle   mDrawerToggle;
    private List<NvDrawerItem>      mDrawerItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerItemList = new ArrayList<>();
        mDrawerItemList.add(new NvDrawerItem(R.drawable.ic_action_person, "Accueil", new HomeFragment()));
        mDrawerItemList.add(new NvDrawerItem(R.drawable.ic_action_person, "Susies", new SusiesFragment()));

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
                getSupportActionBar().setTitle("It's closed");
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("It's opened");
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mHomeFragment = new HomeFragment();
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, mHomeFragment).commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, mDrawerItemList.get(position).getmFragment()).commit();
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
        return super.onOptionsItemSelected(item);
    }
}