package com.example.arsan_irianto.katalogfilm;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arsan_irianto.katalogfilm.adapters.SlidingTabAdapter;
import com.example.arsan_irianto.katalogfilm.fragments.NowPlayingFragment;
import com.example.arsan_irianto.katalogfilm.fragments.SearchFilmFragment;
import com.example.arsan_irianto.katalogfilm.fragments.UpcomingFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int NOTIFICATION_ID = 1;
    private final int NOTIF_ID_REPEATING = 101;

    private NotificationBroadcastReceiver notificationBroadcastReceiver;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SlidingTabAdapter slidingTabAdapter;

    private TabLayout.Tab nowPlaying;
    private TabLayout.Tab upComing;
    private TabLayout.Tab searchFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Cast and initiate Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Cast and Initiate Tab
        initTabBar();

        notificationBroadcastReceiver = new NotificationBroadcastReceiver();
        appNotification();

    }

    private void appNotification() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String repeatTimeTime = "06:00";

        notificationBroadcastReceiver.setRepeatingAlarm(this, repeatTimeTime);
    }

    private void initTabBar() {
        //getSupportActionBar().setElevation(0);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);

        slidingTabAdapter = new SlidingTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(slidingTabAdapter);

        // Set Tabs item
        nowPlaying = tabLayout.newTab();
        upComing = tabLayout.newTab();
        searchFilm = tabLayout.newTab();

        // Labeling tabs item
        nowPlaying.setText(R.string.tab_now_playing);
        upComing.setText(R.string.tab_upcoming);
        searchFilm.setText(R.string.tab_search);

        // Set tabs index
        tabLayout.addTab(nowPlaying, 0);
        tabLayout.addTab(upComing, 1);
        tabLayout.addTab(searchFilm, 2);

        //set tab selector color
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));

        //set the indicator
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));

        // switch the fragment when the current fragment was slided.
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // switch the fragment when the tab item was clicked
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(0);
            fragment = new NowPlayingFragment();
        } else if (id == R.id.nav_now_playing) {
            viewPager.setCurrentItem(0);
            fragment = new NowPlayingFragment();
        } else if (id == R.id.nav_upcoming) {
            viewPager.setCurrentItem(1);
            fragment = new UpcomingFragment();
        } else if (id == R.id.nav_search) {
            viewPager.setCurrentItem(2);
            fragment = new SearchFilmFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
