package com.example.heckyeahvince.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean wasRunning = false;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = wasRunning;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = wasRunning;
    }

    public void onClickStart(View view) {
        isRunning = true;
    }

    public void onClickStop(View view) {
        isRunning = false;
    }

    public void onClickReset(View view) {
        isRunning = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeDisplay = (TextView)findViewById(R.id.displayTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hrs = seconds/3600;
                int mins = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hrs, mins, secs);
                timeDisplay.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRunning", isRunning);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
