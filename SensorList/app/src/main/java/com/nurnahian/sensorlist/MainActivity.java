package com.nurnahian.sensorlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView sensorListTextView;
    List<Sensor> senserList;
    private SensorManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorListTextView = findViewById(R.id.textView);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senserList = sm.getSensorList(Sensor.TYPE_ALL);

        for (int i=1;i<senserList.size();i++){
            sensorListTextView.append(senserList.get(i).getName());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}