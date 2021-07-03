package com.example.temp_sensor_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ShowDataSensors extends AppCompatActivity {

    private Button paramsbutton;
    private String Temp,Humidity , Magnetic,Pressure,Light;
    private TextView text_temp,text_humidity,text_magnetic,text_pressure,text_light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_sensors);

        paramsbutton = (Button) findViewById(R.id.paramsbutton);
        SharedPreferences prfs = getSharedPreferences("SENSORS_DATA", Context.MODE_PRIVATE);
        text_temp=findViewById(R.id.text_temp);
        text_humidity=findViewById(R.id.text_humidity);
        text_light=findViewById(R.id.text_light);
        text_magnetic=findViewById(R.id.text_magnetic);
        text_pressure=findViewById(R.id.text_pressure);


        setDataSensors(prfs);
        text_temp.setText(Temp);
        text_humidity.setText(Humidity);
        text_magnetic.setText(Magnetic);
        text_pressure.setText(Pressure);
        text_light.setText(Light);

        paramsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataSensors(prfs);
                Log.v("Temp",Temp);
                Log.v("Light",Light);
                Log.v("Pressure",Pressure);
                Log.v("Magnetic",Magnetic);
                Log.v("Humidity",Humidity);

                text_temp.setText(Temp);
                text_humidity.setText(Humidity);
                text_magnetic.setText(Magnetic);
                text_pressure.setText(Pressure);
                text_light.setText(Light);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void setDataSensors(SharedPreferences prfs){
        Temp = prfs.getString("Temp", "");
        Humidity = prfs.getString("Humidity", "");
        Magnetic = prfs.getString("Magnetic", "");
        Pressure = prfs.getString("Pressure", "");
        Light = prfs.getString("Light", "");


    }
}