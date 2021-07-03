package com.example.temp_sensor_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class TempService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Sensor humiditySensor;
    private Sensor magneticSensor;
    private Sensor pressureSensor;
    private Sensor lightSensor;
    IntentFilter intentfilter;
    float batteryTemp;
    int batteryLevel;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("in Services","inside");

        sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);


        //Ambient sensor
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){
            tempSensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            Log.v("in Services","condition");

        }

        //Humidity Sensor
        if(sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)!=null){
            humiditySensor=sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        }



        //Magnetic Sensor
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!=null){
            magneticSensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }


        //Pressure Sensor
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)!=null){
            pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        }

        //Light Sensor
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!=null){
            lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,humiditySensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,magneticSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);


    }



        @Override

        // execution of the service will
        // stop on calling this method
        public void onDestroy() {
            super.onDestroy();
            sensorManager.unregisterListener(this);
            // stopping the process
         //   player.stop();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //textView.setText("Ambient temp: "+sensorEvent.values[0]+" C");
// TODO Auto-generated method stub
        SharedPreferences preferences = getSharedPreferences("SENSORS_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            //detect the shake and do your work here
            editor.putString("Temp",String.valueOf(sensorEvent.values[0]));
            editor.apply();




        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            //detect the shake and do your work here
            editor.putString("Humidity",String.valueOf(sensorEvent.values[0]));
            editor.apply();

        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            //detect the shake and do your work here
            editor.putString("Magnetic",String.valueOf(sensorEvent.values[0]));
            editor.apply();
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            //detect the shake and do your work here
            editor.putString("Pressure",String.valueOf(sensorEvent.values[0]));
            editor.apply();

        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            //detect the shake and do your work here
            editor.putString("Light",String.valueOf(sensorEvent.values[0]));
            editor.apply();

        }



    }



    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    }
