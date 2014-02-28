package com.ch.recsysapp.module;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.ch.recsysapp.MainActivity;
import com.ch.recsysapp.http.CustomerHttpClient;
import com.ch.recsysapp.util.JsonHelper;

/**
 * 
 * @author adj
 * 
 */
public class Item {

	private String id;
	private String name;
	private String summary;
	private String imageUri;
	private Bitmap bitmap;

	/*
	 * 无参构造 测试完修改
	 */
	public Item() {
		setName("网络错误");
		setSummary("错误");
		setImageUri("asd");
	}

	/*
	 * json字符串构造
	 */
	@SuppressWarnings("rawtypes")
	public Item(String Json) {
		try {
			Map result = JsonHelper.toMap(Json);
			setName((String) result.get("name"));
			setSummary((String) result.get("summary"));
			setImageUri((String) result.get("imageUri"));
			setId((String) result.get("id"));
		} catch (JSONException e) {
			setName("json字符串格式错误，com.ch.recsysapp.http.Item");// 测试完修改
			setSummary("阿斯顿假啊");
			setImageUri("12312321");
			e.printStackTrace();

		}

	}

	/*
	 * 参数构造
	 */
	public Item(String id, String name, String summary, String imageUri) {
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.imageUri = imageUri;
	}

	/*
	 * 新线程获取图片
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
	 * 读取图片线程类 有空了线程池优化
	 */
	public class GetImageThread implements Runnable {
		private Handler handler;

		public GetImageThread(Handler handler) {
			// 获取MainActivity handler
			this.handler = handler;
		}

		@Override
		public void run() {
			HttpClient httpClient = CustomerHttpClient.getHttpClient();// 使用单例Client,超时等内部设置
			HttpGet httpGet = new HttpGet(imageUri);
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					byte[] data = EntityUtils.toByteArray(httpResponse
							.getEntity());
					Bitmap bmp = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					Item.this.setBitmap(bmp);
					// 获取一个Message对象，设置what为Item_IS_OK
					Message msg = Message.obtain();
					msg.obj = Item.this;
					msg.what = MainActivity.ITEM_IS_OK;
					System.out.println("run方法id：" + id);
					// 发送这个消息到消息队列中
					handler.sendMessage(msg);
				}
			} catch (Exception e) {// 图像获取失败依然传不完整item，mainactivity刷新的时候重新获取图片
				Message msg = Message.obtain();
				msg.obj = Item.this;
				msg.what = MainActivity.ITEM_IS_OK;
				System.out.println("run方法id：" + id);
				handler.sendMessage(msg);
				System.out.println("com.ch.recsysapp.module.item");
				e.printStackTrace();
			}
		}
	}

}
