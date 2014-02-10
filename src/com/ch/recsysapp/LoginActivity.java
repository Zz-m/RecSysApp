package com.ch.recsysapp;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
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
		final Button cancel;
		cancel = (Button) findViewById(R.id.login_submit_button);
		cancel.setOnClickListener(new OnClickListener() {
			/*
			 * 登录按钮事件处理
			 */
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				/*
				 * 提交按钮点击动画，重做
				 */
				ObjectAnimator oa=ObjectAnimator.ofFloat(cancel, "alpha", 0f, 1f);
				oa.setDuration(300);
				oa.start();
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
