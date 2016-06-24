package com.google.mcommerce.sample.android.chapter08.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class GyroscopeActivity extends Activity implements SensorEventListener {
	private SensorManager mgr;
	private Sensor gyro;
	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c08_sensor_gravity);

		mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

		gyro = mgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

		text = (TextView) findViewById(R.id.text);
	}

	@Override
	protected void onResume() {
		mgr.registerListener(this, gyro, SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}

	@Override
	protected void onPause() {
		mgr.unregisterListener(this, gyro);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// ignore
	}

	public void onSensorChanged(SensorEvent event) {
		String msg = "0: " + event.values[0] + "\n" + "1: " + event.values[1]
				+ "\n" + "2: " + event.values[2] + "\n";
		text.setText(msg);
		text.invalidate();
	}
}