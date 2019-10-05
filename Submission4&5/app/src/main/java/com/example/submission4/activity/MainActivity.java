package com.example.submission4.activity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.submission4.NotificationService;
import com.example.submission4.R;
import com.example.submission4.ReleaseTodayService;
import com.example.submission4.db.FavoriteHelper;
import com.example.submission4.fragment.FavoriteFragment;
import com.example.submission4.fragment.MovieFragment;
import com.example.submission4.fragment.TvFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private ViewPager viewPager;
    private FavoriteHelper favoriteHelper;
    private int jobId = 10;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_movie:
                    fragment = new MovieFragment();
                    setupFragment(fragment);
                    return true;
                case R.id.nav_show:
                    fragment = new TvFragment();
                    setupFragment(fragment);
                    return true;
                case R.id.nav_fav:
                    fragment = new FavoriteFragment();
                    setupFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.viewpager);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fullScreen();
        if (savedInstanceState == null){
            Fragment fragment = new MovieFragment();
            setupFragment(fragment);
        }
        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

    }

    public static class DataObserver extends ContentObserver {
        final Context context;
        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        fullScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
        Log.i(TAG,"onDestroy");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onStart() {
            super.onStart();
        Log.i(TAG,"onStart");
        fullScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
        fullScreen();
    }

    private void fullScreen(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void setupFragment (Fragment fragment){
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.container_fragment, fragment, fragment.getClass().getSimpleName())
            .commit();
    }
}
