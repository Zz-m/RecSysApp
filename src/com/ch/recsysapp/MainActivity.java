package com.ch.recsysapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ch.recsysapp.module.Item;
import com.ch.recsysapp.module.ItemList;
import com.ch.recsysapp.util.GetPostUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ������
 * 
 * @author adj
 * 
 */
public class MainActivity extends Activity {

	private ViewPager viewPager;// ҳ������
	private ImageView imageView;// ����ͼƬ
	private TextView textView1, textView2, textView3;
	private List<View> views;// Tabҳ���б�
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	private View view1, view2, view3;// ����ҳ��

	private static String path = "http://192.168.1.105:8080/RecSysServer/servlet/GetList";// ������servlet uri
	private static final int TXT_IS_FINISH = 1;// �ı��������
	//private static final int IMG_IS_FINISH = 2;// ����ͼƬ�������
	public static final int Item_IS_OK = 3;// ����Item���
	private String response;// ����������json����
	private Map<String, Bitmap> imgMap = new HashMap<String, Bitmap>();// ͼƬmap
	private ItemList itemList= new ItemList();//listView ��ʾ��
	/*
	 * handler,ͨ����ɺ�ˢ�½���
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			if (msg.what == TXT_IS_FINISH) {
				if (response != null && !response.equals("")) {
					String[] result = response.split("&-&");
					
					for (int i = 0; i < result.length; i++) {
						Item item = new Item(result[i]);
						item.startGetImage(handler);
						}
				}
			}
			if (msg.what == Item_IS_OK) {
				Item item = (Item) msg.obj;
				itemList.add(item);
				System.out.println("��ȡͼƬ��" + itemList.get(0).getBitmap().getHeight());
				
				refreshViewPager();
				
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * �����Ƿ���ε�½���ж��Ƿ���ת��½���棬���Ż�
		 */
		// Intent intent = new Intent();
		// intent.setClass(MainActivity.this, LoginActivity.class);
		// startActivity(intent);

		new Thread(new GetTxtThread()).start();
		setContentView(R.layout.activity_main);
		InitImageView();
		InitTextView();
		InitViewPager();

	}
	/*
	 * ���ݼ�����ɺ���ˢ��ҳ��
	 */
	
	private void refreshViewPager() {
		System.out.println("asddddddddddddddddddddd    refresh!!!!!");
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.activity_main_lay1, null);
		view2 = inflater.inflate(R.layout.activity_main_lay2, null);
		view3 = inflater.inflate(R.layout.activity_main_lay3, null);
	
		/*
		 * ��ʼ����ǩ1
		 */
		ListView lv1 = (ListView) view1
				.findViewById(R.id.activity_main_lay1_listview);
		MyListAdapter adapter = new MyListAdapter(MainActivity.this, itemList);
		
		lv1.setAdapter(adapter);
		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, DetailActivity.class);
				startActivity(intent);
				// ��������õ����ľ���position ���������ѡ������ĸ��С�����������Ϳ�����������¼����в����ˡ�
				
				//InitViewPager();
			}
		});
	
		/*
		 * ��ʼ����ǩ2
		 */
		ListView lv2 = (ListView) view2
				.findViewById(R.id.activity_main_lay2_listview);
		SimpleAdapter adapter2 = new SimpleAdapter(MainActivity.this, getData(),
				R.layout.activity_main_lay2_itemsyle, new String[] { "title",
						"info", "img" }, new int[] { R.id.activity_main_title2,
						R.id.activity_main_info2, R.id.activity_main_img2 });
		lv2.setAdapter(adapter2);
		/*
		 * ��ʼ����ǩ3
		 */
		ListView lv3 = (ListView) view3
				.findViewById(R.id.activity_main_lay3_listview);
		SimpleAdapter adapter3 = new SimpleAdapter(MainActivity.this, getData(),
				R.layout.activity_main_lay3_itemsyle, new String[] { "title",
						"info", "img" }, new int[] { R.id.activity_main_title3,
						R.id.activity_main_info3, R.id.activity_main_img3 });
		lv3.setAdapter(adapter3);

		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	
	}
	/**
	 * ��ʼ��������3����ǩ����
	 */
	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.activity_main_lay1, null);
		view2 = inflater.inflate(R.layout.activity_main_lay2, null);
		view3 = inflater.inflate(R.layout.activity_main_lay3, null);
		/*
		 * ��ʼ����ǩ1
		 */
		ListView lv1 = (ListView) view1
				.findViewById(R.id.activity_main_lay1_listview);
		MyListAdapter adapter = new MyListAdapter(MainActivity.this, itemList);		
		lv1.setAdapter(adapter);
		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, DetailActivity.class);
				startActivity(intent);
				// ��������õ����ľ���position ���������ѡ������ĸ��С�����������Ϳ�����������¼����в����ˡ�
				
				//InitViewPager();
			}
		});
		/*
		 * ��ʼ����ǩ2
		 */
		ListView lv2 = (ListView) view2
				.findViewById(R.id.activity_main_lay2_listview);
		SimpleAdapter adapter2 = new SimpleAdapter(this, getData(),
				R.layout.activity_main_lay2_itemsyle, new String[] { "title",
						"info", "img" }, new int[] { R.id.activity_main_title2,
						R.id.activity_main_info2, R.id.activity_main_img2 });
		lv2.setAdapter(adapter2);
		/*
		 * ��ʼ����ǩ3
		 */
		ListView lv3 = (ListView) view3
				.findViewById(R.id.activity_main_lay3_listview);
		SimpleAdapter adapter3 = new SimpleAdapter(this, getData(),
				R.layout.activity_main_lay3_itemsyle, new String[] { "title",
						"info", "img" }, new int[] { R.id.activity_main_title3,
						R.id.activity_main_info3, R.id.activity_main_img3 });
		lv3.setAdapter(adapter3);

		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * ��ȡ���ݣ��ṩ��simpleadapter
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		for (int i = 0; i < 7; i++) {
			map = new HashMap<String, Object>();
			map.put("title", "���� Rango (2011)");
			map.put("info",
					"  ���꣨Լ���ᡤ���� Johnny Depp ��������һֻ���ݡ����̵����棬��ס�������������Ҭ�����ļ�������");
			map.put("img", R.drawable.cat);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "�������ܶ�Ա Wall��E (2008)");
			map.put("info",
					"  ��Ԫ2700�꣬���������߶ȷ�չ��ȴ����Ⱦ������������������ʹ�õ��������������ס�������˱��ȳ����ɴ��뿪���磬����һ�������ޱߵ�����֮�á�");
			map.put("img", R.drawable.cat2);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "����Ů Father And Daughter (2001)");
			map.put("info",
					"  ������ů�İ������״���Ů��һ���ﵥ�������Ǵ����ּ�С·������ݵأ����ϸ��£�����ƽ���ĺ��ߡ� ���ױ���Ů����������С����Ů��");
			map.put("img", R.drawable.cat3);
			list.add(map);

		}
		return list;
	}

	/**
	 * ��ȡ���ݣ��ṩ��simpleadapter1
	 * Ŀǰ������
	 * @return
	 */
	private List<Map<String, Object>> getDataTest() {
		Item item = new Item();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (response != null && !response.equals("")) {
			String[] result = response.split("&-&");
			
			for (int i = 0; i < result.length; i++) {
				item = new Item(result[i]);
				map = new HashMap<String, Object>();
				map.put("title", item.getName());
				System.out.println("getDataTest����id��"+ item.getId());
				map.put("info", imgMap.size());
				map.put("img", R.drawable.cat);
				list.add(map);
				}

		} else {

			map = new HashMap<String, Object>();
			map.put("title", "���� Rango (2011)");
			map.put("info",
					"  ���꣨Լ���ᡤ���� Johnny Depp ��������һֻ���ݡ����̵����棬��ס�������������Ҭ�����ļ�������");
			map.put("img", R.drawable.cat);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "�������ܶ�Ա Wall��E (2008)");
			map.put("info",
					"  ��Ԫ2700�꣬���������߶ȷ�չ��ȴ����Ⱦ������������������ʹ�õ��������������ס�������˱��ȳ����ɴ��뿪���磬����һ�������ޱߵ�����֮�á�");
			map.put("img", R.drawable.cat2);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "����Ů Father And Daughter (2001)");
			map.put("info",
					"  ������ů�İ������״���Ů��һ���ﵥ�������Ǵ����ּ�С·������ݵأ����ϸ��£�����ƽ���ĺ��ߡ� ���ױ���Ů����������С����Ů��");
			map.put("img", R.drawable.cat3);
			list.add(map);

		}
		return list;
	}

	/**
	 * ��ʼ��������3��tabͷ�����ʾ���֣��趨OnClickListener
	 */

	private void InitTextView() {
		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		textView3 = (TextView) findViewById(R.id.text3);

		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
	}

	/**
	 * ��ʼ���������������ҳ������ʱ������ĺ���Ҳ������Ч������������Ҫ����һЩ���� 3
	 */

	private void InitImageView() {
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.cursor_main).getWidth();// ��ȡͼƬ���
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
		offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
	}

	/**
	 * 
	 * ͷ��������
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}

	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	/*
	 * ��ȡjson response�߳�
	 */
	public class GetTxtThread implements Runnable {

		@Override
		public void run() {
			response = GetPostUtil.sendGet(path, null);
			// ��ȡһ��Message��������whatΪ1
			Message msg = Message.obtain();
			msg.obj = response;
			msg.what = TXT_IS_FINISH;
			// ���������Ϣ����Ϣ������
			handler.sendMessage(msg);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
		int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
			/*
			 * ���ַ����������һ�֣����滹��һ�֣���Ȼ����Ƚ��鷳 Animation animation = null; switch
			 * (arg0) { case 0: if (currIndex == 1) { animation = new
			 * TranslateAnimation(one, 0, 0, 0); } else if (currIndex == 2) {
			 * animation = new TranslateAnimation(two, 0, 0, 0); } break; case
			 * 1: if (currIndex == 0) { animation = new
			 * TranslateAnimation(offset, one, 0, 0); } else if (currIndex == 2)
			 * { animation = new TranslateAnimation(two, one, 0, 0); } break;
			 * case 2: if (currIndex == 0) { animation = new
			 * TranslateAnimation(offset, two, 0, 0); } else if (currIndex == 1)
			 * { animation = new TranslateAnimation(one, two, 0, 0); } break;
			 * 
			 * }
			 */
			Animation animation = new TranslateAnimation(one * currIndex, one
					* arg0, 0, 0);// ��Ȼ����Ƚϼ�ֻ࣬��һ�д��롣
			currIndex = arg0;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);
			imageView.startAnimation(animation);
			Toast.makeText(MainActivity.this,
					"��ѡ����" + viewPager.getCurrentItem() + "ҳ��",
					Toast.LENGTH_SHORT).show();
		}

	}
}
