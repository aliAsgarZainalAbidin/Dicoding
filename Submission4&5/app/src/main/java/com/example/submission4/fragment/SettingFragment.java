package com.example.submission4.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.submission4.NotificationService;
import com.example.submission4.R;
import com.google.firebase.messaging.FirebaseMessaging;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_setting, container, false);
        final Switch switchRelease = view.findViewById(R.id.switchRelease);
        final Switch switchDaily = view.findViewById(R.id.switchDaily);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean stateRelease = sharedPreferences.getBoolean(getString(R.string.state_release), false);
        boolean stateDaily = sharedPreferences.getBoolean(getString(R.string.state_daily), false);
        if (stateRelease){
            switchRelease.setChecked(true);
        } else {
            switchRelease.setChecked(false);
        }

        if (stateDaily){
            switchDaily.setChecked(true);
            FirebaseMessaging.getInstance().subscribeToTopic("test3");
        } else {
            switchDaily.setChecked(false);
            FirebaseMessaging.getInstance().unsubscribeFromTopic("test3");
        }

        switchRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    switchRelease.setChecked(true);
                    editor.putBoolean(getString(R.string.state_release), true);
                    editor.apply();
                    Intent intent = new Intent(getContext(), NotificationService.class);
                    getContext().startService(intent);
                } else {
                    switchRelease.setChecked(false);
                    editor.putBoolean(getString(R.string.state_release), false);
                    editor.apply();
                    Intent intent = new Intent(getContext(), NotificationService.class);
                    getContext().stopService(intent);
                }
            }
        });

        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    switchDaily.setChecked(true);
                    editor.putBoolean(getString(R.string.state_daily), true);
                    editor.apply();
                    FirebaseMessaging.getInstance().subscribeToTopic("test3");
                } else {
                    switchDaily.setChecked(false);
                    editor.putBoolean(getString(R.string.state_daily), false);
                    editor.apply();
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("test3");
                }
            }
        });

        return  view;
    }



}
