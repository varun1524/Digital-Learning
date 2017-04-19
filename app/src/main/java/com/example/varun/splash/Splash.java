package com.example.varun.splash;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.varun.digitallearning.ListUser;
import com.example.varun.digitallearning.R;

/**
 * Created by varun on 07/08/14.
 */

public class Splash extends Activity {

    MediaPlayer ourSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        //SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openMyActivity = new Intent(Splash.this, ListUser.class);
                    startActivity(openMyActivity);
                    
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
