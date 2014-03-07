package com.ch.recsysapp.adapter;

import com.ch.recsysapp.R;
import com.ch.recsysapp.R.id;
import com.ch.recsysapp.R.layout;
import com.ch.recsysapp.module.ItemList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author adj listView adapter
 * 
 */
public class MyListAdapter extends BaseAdapter {
	private Context context;
	private ItemList itemList;
	private LayoutInflater mInflater;

	public MyListAdapter(Context context, ItemList itemList) {
		this.context = context;
		this.itemList = itemList;
	}

	/**
	 * 返回每个item样式
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(
					R.layout.activity_main_lay1_itemsyle, null);
		}
		ImageView img = (ImageView) convertView
				.findViewById(R.id.activity_main_img1);
		TextView title = (TextView) convertView
				.findViewById(R.id.activity_main_title1);
		TextView summary = (TextView) convertView
				.findViewById(R.id.activity_main_info1);
		img.setImageBitmap(itemList.get(position).getBitmap());
		title.setText(itemList.get(position).getName());
		summary.setText(itemList.get(position).getSummary());
		return convertView;
	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}