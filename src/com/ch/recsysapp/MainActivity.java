package com.ch.recsysapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Ö÷½çÃæ
 * 
 * @author adj
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);

		final Activity thisActivity = this;

		boolean flag = false;
		if (flag) {
			flag = false;
			startActivity(intent);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		// super.onCreateOptionsMenu(menu);

		return true;
	}

}
