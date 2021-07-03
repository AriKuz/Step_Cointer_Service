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

    private Button startServices,stopServices;
    float tempParemeter;

    SensorsDataReciever receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new SensorsDataReciever();
        registerReceiver(receiver, new IntentFilter("GET_SIGNAL_STRENGTH"));  //<----Register


        startServices=findViewById(R.id.startServices);
        stopServices=findViewById(R.id.stopServices);
        startServices.setOnClickListener( this );
        stopServices.setOnClickListener( this );
        Log.v("hhhhhhhhh","sdasdasd");


    }

    class SensorsDataReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals("GET_SIGNAL_STRENGTH")) {
                String level = intent.getStringExtra("LEVEL_DATA");
                Log.v("hello sensorreciver","");
                // Show it in GraphView
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

        // process to be performed
        // if start button is clicked
        if(view == startServices){
            Log.v("Start Services","Start");
           // getDataFromService(true);

            // starting the service
           startService(new Intent( this, TempService.class ) );
            Intent i = new Intent(this, ShowDataSensors.class);
            startActivity(i);


        }

        // process to be performed
        // if stop button is clicked
        else if (view == stopServices){
            Log.v("Stop Services","Stop");
            //getDataFromService(false);

            // stopping the service
            stopService(new Intent( this, TempService.class ) );


        }
    }





}