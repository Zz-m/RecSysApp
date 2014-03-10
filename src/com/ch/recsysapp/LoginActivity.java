package com.ch.recsysapp;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
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

	TextView myTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		String s = "没有账号？注册";
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
				ObjectAnimator oa = ObjectAnimator.ofFloat(cancel, "alpha", 0f,
						1f);
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
		myTextView = (TextView) this.findViewById(R.id.register_link2);
		// 创建一个 SpannableString对象
		SpannableString sp = new SpannableString(s);
		// 设置超链接
		sp.setSpan(new URLSpan("http://www.google.com"), s.length() - 2,
				s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// //设置高亮样式一
		// sp.setSpan(new BackgroundColorSpan(Color.RED), 17
		// ,19,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// //设置高亮样式二
		// sp.setSpan(new ForegroundColorSpan(888),0,
		// s.length()-3,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		// //设置斜体
		sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0,
				s.length() - 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		// SpannableString对象设置给TextView
		myTextView.setText(sp);
		// 设置TextView可点击
		myTextView.setMovementMethod(LinkMovementMethod.getInstance());
	}

}
