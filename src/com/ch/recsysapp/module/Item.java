package com.ch.recsysapp.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.ch.recsysapp.MainActivity;
import com.ch.recsysapp.util.JsonHelper;

public class Item implements Serializable{

	private String id;
	private String name;
	private String summary;
	private String imageUri;
	private Bitmap bitmap;

	/*
	 * �޲ι���
	 */
	public Item() {
		setName("�������");
		setSummary("����");
		setImageUri("asd");
	}

	/*
	 * json�ַ�������
	 */
	public Item(String Json) {
		try {
			Map result = JsonHelper.toMap(Json);
			setName((String)result.get("name"));
			setSummary((String)result.get("summary"));
			setImageUri((String)result.get("imageUri"));
			setId((String)result.get("id"));
		} catch (JSONException e) {
			setName("json�ַ�����ʽ����com.ch.recsysapp.http.Item");
    		setSummary("��˹�ټٰ�");
    		setImageUri("12312321");
			e.printStackTrace();
			
		}
		
	}
	/*
	 * ��������
	 */
	public Item(String id, String name, String summary, String imageUri) {
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.imageUri = imageUri;
	}
	/*
	 * ���̻߳�ȡͼƬ
	 */
	public void startGetImage(Handler handler) {
		new Thread(new GetImageThread(handler)).start();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	/*
	 * ��ȡͼƬ�߳���
	 */
	public class GetImageThread implements Runnable {
		private Handler handler;
		public GetImageThread(Handler handler) {
			this.handler = handler;
		}

		@Override
		public void run() {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(imageUri);
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					byte[] data = EntityUtils.toByteArray(httpResponse
							.getEntity());
					Bitmap bmp = BitmapFactory
							.decodeByteArray(data, 0, data.length);
					Item.this.setBitmap(bmp);
					// ��ȡһ��Message��������whatΪ1
					Message msg = Message.obtain();
					msg.obj = Item.this;
					msg.what = MainActivity.Item_IS_OK;
					System.out.println("run����id��"+ id);
					// ���������Ϣ����Ϣ������
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				System.out.println("com.ch.recsysapp.module.item");
				e.printStackTrace();
			}
		}
	}

}
