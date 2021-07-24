package com.example.temp_sensor_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mapService;

    SensorsDataReciever receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new SensorsDataReciever();
        registerReceiver(receiver, new IntentFilter("GET_SIGNAL_STRENGTH"));  //<----Register

        mapService=findViewById(R.id.stepsService);
        mapService.setOnClickListener( this );
    }

    class SensorsDataReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals("GET_SIGNAL_STRENGTH")) {
                String level = intent.getStringExtra("LEVEL_DATA");
            }
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        unregisterReceiver(receiver);           //<-- Unregister to avoid memoryleak
    }

    public void onClick(View view) {
            Log.v("StepCounter Services","Steps");
            Intent i = new Intent(this, StepsCounter.class);
            startActivity(i);

    }
}