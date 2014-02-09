package com.ch.recsysapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * �û���¼����
 * 
 * @author adj
 * 
 */
public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Button play;
		play = (Button) findViewById(R.id.detail_play_button);
		play.setOnClickListener(new OnClickListener() {
			/*
			 * ��¼��ť�¼�����
			 */
			@Override
			public void onClick(View v) {
				Toast.makeText(DetailActivity.this, "����", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}
