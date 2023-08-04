package com.nurnahian.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private ImageView imageView;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor,magnetometerSensor;

    private float [] lastAccelerometer = new float[3];
    private float [] lastmagnetometer= new float[3];
    private float [] rotationMatrix = new float[9];
    private float [] orientation = new float[3];
    boolean isLastAccelerometerArray = false;
    boolean isLastmagnetometterArray = false;
    long lastUpdateTime = 0;
    float currentDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor == accelerometerSensor){
            System.arraycopy(sensorEvent.values,0,lastAccelerometer,0,sensorEvent.values.length);
            isLastAccelerometerArray = true;

        }
        else if(sensorEvent.sensor== magnetometerSensor){
            System.arraycopy(sensorEvent.values,0,lastmagnetometer,0,sensorEvent.values.length);
            isLastmagnetometterArray=true;
        }
        if ( isLastAccelerometerArray && isLastmagnetometterArray && System.currentTimeMillis()-lastUpdateTime>250){

            SensorManager.getRotationMatrix(rotationMatrix,null,lastAccelerometer,lastmagnetometer);
            SensorManager.getOrientation(rotationMatrix,orientation);

            float azimuthInRadians = orientation[0];
            float azimuthInDegree = (float)Math.toDegrees(azimuthInRadians);

            RotateAnimation rotateAnimation = new RotateAnimation(currentDegree,-azimuthInDegree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);

            imageView.startAnimation(rotateAnimation);

            currentDegree = -azimuthInDegree;
            lastUpdateTime = System.currentTimeMillis();

            int x = (int) azimuthInDegree;
            textView.setText((x+360)%360 + " ° ");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,magnetometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,accelerometerSensor);
        sensorManager.unregisterListener(this,magnetometerSensor);
    }
}