package com.parg.bartian.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parg.bartian.R;
import com.parg.bartian.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setTitle("Real Time Departure");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(), "MainFragmentTag")
                    .commitNow();
        } else {
            Fragment mainFragment = getSupportFragmentManager().findFragmentByTag("MainFragmentTag");
            if(mainFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, mainFragment)
                        .commitNow();
            }
        }
    }
}
