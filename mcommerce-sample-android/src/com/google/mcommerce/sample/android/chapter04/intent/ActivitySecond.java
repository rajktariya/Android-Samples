package com.google.mcommerce.sample.android.chapter04.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.mcommerce.sample.android.R;

public class ActivitySecond extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.c04_activity_second_layout);
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		String value1 = extras.getString("Value1");
		String value2 = extras.getString("Value2");
		if (value1 != null && value2 != null) {
			EditText text1 = (EditText) findViewById(R.id.input1);
			EditText text2 = (EditText) findViewById(R.id.input2);
			text1.setText(value1);
			text2.setText(value2);
		}
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		Intent data = new Intent();
		// Return some hard-coded values
		data.putExtra("returnKey1", "Swinging on a star. ");
		data.putExtra("returnKey2", "You could be better then you are. ");
		setResult(RESULT_OK, data);
		super.finish();
	}
}
