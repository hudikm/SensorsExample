package fri.uniza.sk.sensorsexample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor accSensor;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //Get system sensor manager

        final List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL); //Get all available sensors

        for (Sensor sensor : sensorList) {
            Log.d("SensorList", "Type: " + sensor.getStringType() + " |-> Name: " + sensor.getName()); //Print all available sensors in your device
        }

        //Get default sensors
       accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Register listener for sensors
        mSensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                Log.d("ACCELEROMETER sensor", "X:" + event.values[0]);
                Log.d("ACCELEROMETER sensor", "Y:" + event.values[1]);
                Log.d("ACCELEROMETER sensor", "Z:" + event.values[2]);
                break;
            case Sensor.TYPE_LIGHT:
                Log.d("Light sensor", "Value[lux]:" + event.values[0]);
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
