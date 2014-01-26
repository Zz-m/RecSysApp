package com.ch.recsysapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private TextView textView1, textView2, textView3;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private View view1, view2, view3;// 各个页卡

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);

		setContentView(R.layout.activity_main);
		InitImageView();
		InitTextView();
		InitViewPager();

	}

	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.activity_main_lay1, null);
		view2 = inflater.inflate(R.layout.activity_main_lay2, null);
		view3 = inflater.inflate(R.layout.activity_main_lay3, null);
		/* vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */

		ListView lv1 = (ListView) view1
				.findViewById(R.id.activity_main_lay1_listview);
		SimpleAdapter adapter1 = new SimpleAdapter(this, getData(),
				R.layout.activity_main_lay1_listsyle, new String[] { "title",
						"info", "img" }, new int[] { R.id.activity_main_title,
						R.id.activity_main_info, R.id.activity_main_img });
		lv1.setAdapter(adapter1);
		/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */

		/*
		 * vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv2014-1-26vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		 */

		ListView lv2 = (ListView) view2
				.findViewById(R.id.activity_main_lay2_listview);
		SimpleAdapter adapter2 = new SimpleAdapter(this, getData2(),
				R.layout.activity_main_lay2_listsyle, new String[] { "title1",
						"info1", "img1", "title2", "info2", "img2", "title3",
						"info3", "img3" }, new int[] {
						R.id.activity_main_lay2_title1,
						R.id.activity_main_lay2_info1,
						R.id.activity_main_lay2_img1,
						R.id.activity_main_lay2_title2,
						R.id.activity_main_lay2_info2,
						R.id.activity_main_lay2_img2,
						R.id.activity_main_lay2_title3,
						R.id.activity_main_lay2_info3,
						R.id.activity_main_lay2_img3 });
		lv2.setAdapter(adapter2);
		/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */
		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * 获取数据，提供给simpleadapter
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 7; i++) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "兰戈 Rango (2011)");
		map.put("info", "  兰戈（约翰尼·德普 Johnny Depp 配音）是一只干瘦、翠绿的蜥蜴，他住在鱼缸里，蓝天白云椰子树的假相让他");
		map.put("img", R.drawable.cat);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "机器人总动员 Wall·E (2008)");
		map.put("info", "  公元2700年，人类文明高度发展，却因污染和生活垃圾大量增加使得地球不再适于人类居住。地球人被迫乘坐飞船离开故乡，进行一次漫长无边的宇宙之旅。");
		map.put("img", R.drawable.cat2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "父与女 Father And Daughter (2001)");
		map.put("info",
				"  秋日温暖的傍晚，父亲带着女儿一起骑单车，他们穿过林间小路，骑过草地，骑上高坡，来到平静的湖边。 父亲抱抱女儿，登上了小船。女儿");
		map.put("img", R.drawable.cat3);
		list.add(map);
		
			
		}

		return list;
	}

	/**
	 * 获取数据，提供给simpleadapter2
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getData2() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title1", "兰戈 Rango (2011)");
		map.put("info1", "  兰戈（约翰尼·德普 Johnny Depp 配音）是一只干瘦、翠绿的蜥蜴，他住在鱼缸里，蓝天白云椰子树的假相让他");
		map.put("img1", R.drawable.cat);
		map.put("title2", "机器人总动员 Wall·E (2008)");
		map.put("info2",
				"  公元2700年，人类文明高度发展，却因污染和生活垃圾大量增加使得地球不再适于人类居住。地球人被迫乘坐飞船离开故乡，进行一次漫长无边的宇宙之旅。");
		map.put("img2", R.drawable.cat2);
		map.put("title3", "父与女 Father And Daughter (2001)");
		map.put("info3", "  秋日温暖的傍晚，父亲带着女儿一起骑单车，他们穿过林间小路，骑过草地，骑上高坡，来到平静的湖边。 父亲抱抱女儿，登上了小船。女儿");
		map.put("img3", R.drawable.cat3);
		list.add(map);
		
			
		}

		return list;
	}

	/**
	 * 初始化头标
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
	 * 2 * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据 3
	 */

	private void InitImageView() {
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.cursor_main).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// 设置动画初始位置
	}

	// <img src="http://img.my.csdn.net/uploads/201211/10/1352554452_1685.jpg"
	// alt="">
	/**
	 * 
	 * 头标点击监听 3
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

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
			/*
			 * 两种方法，这个是一种，下面还有一种，显然这个比较麻烦 Animation animation = null; switch
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
					* arg0, 0, 0);// 显然这个比较简洁，只有一行代码。
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			imageView.startAnimation(animation);
			Toast.makeText(MainActivity.this,
					"您选择了" + viewPager.getCurrentItem() + "页卡",
					Toast.LENGTH_SHORT).show();
		}

	}
}
