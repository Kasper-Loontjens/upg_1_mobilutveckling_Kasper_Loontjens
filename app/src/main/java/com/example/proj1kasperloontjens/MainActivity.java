package com.example.proj1kasperloontjens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager mSensorManager;
    Sensor mNewSensor;
    SensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Changes activity to gyro activity
        Button gyroButton = findViewById(R.id.gyroButton);
        gyroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gA = new Intent(MainActivity.this, GyroActivity.class);
                startActivity(gA);
            }
        });

        // Changes activity to accel activity
        Button accelButton = findViewById(R.id.accelButton);
        accelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent accA = new Intent(MainActivity.this, AccelActivity.class);
                startActivity(accA);
            }
        });

        //Activates or removes a fragment
        Button btnOther = findViewById(R.id.otherButton);
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.FragmentOther);
                if(fragment != null){
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }else {
                    fm.beginTransaction().add(R.id.FragmentOther , FragmentOther.class, null).commit();
                }
            }
        });


        // Obtain references to the SensorManager and the sensor
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mNewSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        // Implement a listener to receive updates
       listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                TextView fragtext1 = findViewById(R.id.fragmentText1);
                Log.d("bob","Temp AAH");


                // If fragment is viewable its ui is updated to show temperature
                if (fragtext1 != null){
                    fragtext1.setText("Temperature: "+ String.valueOf(event.values[0]) + " C.");
                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(
                listener, mNewSensor, SensorManager.SENSOR_DELAY_UI
        );


    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(listener);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}