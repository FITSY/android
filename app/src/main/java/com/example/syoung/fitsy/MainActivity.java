package com.example.syoung.fitsy;

import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.example.syoung.fitsy.course.CourseFragment;
import com.example.syoung.fitsy.main.MainFragment;
import com.example.syoung.fitsy.history.HistoryFragment;
import com.example.syoung.fitsy.statistics.StatisticsFragment;
import com.example.syoung.fitsy.myinfo.MyInformationFragment;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment navigationDrawerFragment;
    private CharSequence title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getString(R.string.title_section1);

        navigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = null;

        switch (position){
            case 0:
                objFragment = MainFragment.getInstance();
                title = getString(R.string.title_section1);
                break;
            case 1:
                objFragment = StatisticsFragment.getInstance();
                title = getString(R.string.title_section2);
                break;
            case 2:
                objFragment = HistoryFragment.getInstance();
                title = getString(R.string.title_section3);
                break;
            case 3:
                objFragment = CourseFragment.getInstance();
                title = getString(R.string.title_section4);
                break;
            case 4:
                objFragment = MyInformationFragment.getInstance();
                title = getString(R.string.title_section5);
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, objFragment).commit();
        setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
