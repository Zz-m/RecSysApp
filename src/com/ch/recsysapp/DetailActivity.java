package com.ch.recsysapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 推荐内容详细界面
 * 
 * @author adj
 * 
 */
public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		//TextView tv = (TextView)findViewById(R.id.detail_title);
		//tv.setText("某偶离线阿斯顿");
		Button play;
		play = (Button) findViewById(R.id.detail_play_button);
		play.setOnClickListener(new OnClickListener() {
			/*
			 * 登录按钮事件处理
			 */
			@Override
			public void onClick(View v) {
				Toast.makeText(DetailActivity.this, "播放", Toast.LENGTH_SHORT)
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
