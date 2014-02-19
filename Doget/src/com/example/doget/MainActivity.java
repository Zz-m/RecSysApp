package com.example.doget;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author jiaruihua jrhhybh@163.com
 * @version 1.0
 */
public class MainActivity extends Activity {
	Button get, post;
	TextView show;
	private String TOURL = "http://192.168.1.105:8080/RecSysServer/servlet/GetList";
	// �����������Ӧ���ַ���
	String response;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				// ����show�����ʾ��������Ӧ
				show.setText(response);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		get = (Button) findViewById(R.id.get);
		post = (Button) findViewById(R.id.post);
		show = (TextView) findViewById(R.id.show);
		get.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						response = GetPostUtil.sendGet(TOURL, "str=get");
						// ������Ϣ֪ͨUI�̸߳���UI���
						handler.sendEmptyMessage(0x123);
					}
				}.start();
			}
		});
		post.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						response = GetPostUtil.sendPost(TOURL, "str=post");
					}
				}.start();
				// ������Ϣ֪ͨUI�̸߳���UI���
				handler.sendEmptyMessage(0x123);
			}
		});
	}
}