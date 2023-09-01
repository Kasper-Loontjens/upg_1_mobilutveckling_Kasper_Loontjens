package com.example.proj1kasperloontjens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AccelActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mAccelSensor;
    SensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView accelTextX = findViewById(R.id.accelTextX);
        TextView accelTextY = findViewById(R.id.accelTextY);
        TextView accelTextZ = findViewById(R.id.accelTextZ);

        ImageView imageArrowGyro = findViewById(R.id.imageArrowGyro);

        // Obtain references to the SensorManager and the Acceleration sensor
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Implement a listener to receive updates
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                accelTextX.setText("X: " + String.valueOf(event.values[0]));

                accelTextY.setText("Y: " + String.valueOf(event.values[1] - 9.81f));

                accelTextZ.setText("Z: " + String.valueOf(event.values[2]));

                Log.d("bob","Accel AAH");


                // if phone is shaking alert shows up
                if (event.values[0] > 10f || event.values[1] > 20f || event.values[2] > 10f ){
                    Toast.makeText(AccelActivity.this, "Whoa slow down!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(
                listener, mAccelSensor, SensorManager.SENSOR_DELAY_UI
        );

        // Changes activity to Main activity
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mA = new Intent(AccelActivity.this, MainActivity.class);
                startActivity(mA);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(listener);

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