package com.pinecone.technology.mcommerce.learning.android.chapter04;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleEventActivity extends Activity {
	private TextView textview1;
	private Button button1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c04_simple_event);

		textview1 = (TextView) findViewById(R.id.textview1);
		button1 = (Button) findViewById(R.id.button01);
		/*
		 * 增加事件响应
		 */
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Toast提示控件
				Toast.makeText(SimpleEventActivity.this, "你已经点击了按钮！",
						Toast.LENGTH_LONG).show();
				// 将TextView的文字发生改变
				textview1.setText("一个按钮的点击事件!");
			}
		});
	}
}
