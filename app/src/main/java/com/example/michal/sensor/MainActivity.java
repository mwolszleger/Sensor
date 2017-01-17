package com.example.michal.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SensorManager sensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double total = Math.sqrt(x * x + y * y + z * z);
//                Log.e("X",String.valueOf(x));
//                Log.e("Y",String.valueOf(y));
//                Log.e("Z",String.valueOf(z));
                if(Math.abs(x)>Math.abs(y))
                {
                    if (x > 2)
                        ((TextView) findViewById(R.id.textView)).setText("LEWO");
                    else if (x < -2)
                        ((TextView) findViewById(R.id.textView)).setText("PRAWO");
                    else
                        ((TextView) findViewById(R.id.textView)).setText("");

                }
                else
                {
                    if (y > 2)
                        ((TextView) findViewById(R.id.textView)).setText("GÓRA");
                    else if (y < -2)
                        ((TextView) findViewById(R.id.textView)).setText("DÓŁ");
                    else
                        ((TextView) findViewById(R.id.textView)).setText("");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
}
