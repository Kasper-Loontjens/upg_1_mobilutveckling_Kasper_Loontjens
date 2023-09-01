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

public class GyroActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mGyroSensor;
    SensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);


    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView gyroTextX = findViewById(R.id.gyroTextX);
        TextView gyroTextY = findViewById(R.id.gyroTextY);
        TextView gyroTextZ = findViewById(R.id.gyroTextZ);

        ImageView imageArrowGyro = findViewById(R.id.imageArrowGyro);


        // Obtain references to the SensorManager and the Gyroscope sensor
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mGyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Implement a listener to receive updates
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                Log.d("bob","Gyro AAH");


                //Rotates picture changes text corresponding gyroscope movement

                gyroTextX.setText("X: " + String.valueOf(event.values[0]));
                imageArrowGyro.setRotationX((event.values[0]) * -10);

                gyroTextY.setText("Y: " + String.valueOf(event.values[1]));
                imageArrowGyro.setRotationY((event.values[1]) * 10);

                gyroTextZ.setText("Z: " + String.valueOf(event.values[2]));
                imageArrowGyro.setRotation((event.values[2]) * -10);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(
                listener, mGyroSensor, SensorManager.SENSOR_DELAY_UI
        );

        // Changes activity to Main activity
        Button mainMenuBtn = findViewById(R.id.mainMenuBtn);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mA = new Intent(GyroActivity.this, MainActivity.class);
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