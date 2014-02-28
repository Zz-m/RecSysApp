package com.ch.recsysapp;

import com.ch.recsysapp.module.Item;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 推荐内容详细界面
 * 
 * @author adj
 * 
 */
public class DetailActivity extends Activity {

	private Item item;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	/**
	 * 初始化
	 */
	private void init() {
		System.out.println("aDetailActivity starttttttttttttttt");
		TextView tv = (TextView) findViewById(R.id.detail_title);
		TextView tv2 = (TextView) findViewById(R.id.detail_content);
		iv = (ImageView) findViewById(R.id.detail_poster);
		Bundle bundle = this.getIntent().getExtras();
		item = new Item(bundle.getString("id"), bundle.getString("name"),
				bundle.getString("summary"), bundle.getString("imageUri"));
		item.startGetImage(handler);
		tv.setText(item.getName());
		tv2.setText(item.getSummary());
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

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			if (msg.what == MainActivity.ITEM_IS_OK) {
				item = (Item) msg.obj;
				iv.setImageBitmap(item.getBitmap());
			}

		}

	};
}
