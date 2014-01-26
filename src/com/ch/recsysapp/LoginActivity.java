package com.ch.recsysapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * 用户登录界面
 * 
 * @author adj
 * 
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		final Activity thisActivity = this;
		final TextView userNameText = (TextView) this
				.findViewById(R.id.login_user_input);
		final TextView passWordTest = (TextView) this
				.findViewById(R.id.login_password_input);
		Button cancel;
		cancel = (Button) findViewById(R.id.login_submit_button);
		cancel.setOnClickListener(new OnClickListener() {
			/*
			 * 登录按钮事件处理
			 */
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(thisActivity)
						.setTitle("输入")
						.setItems(
								new String[] {
										userNameText.getText().toString(),
										passWordTest.getText().toString() },
								null).setNegativeButton("确定", null).show();
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

}
