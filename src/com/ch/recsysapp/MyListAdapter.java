package com.ch.recsysapp;

import com.ch.recsysapp.module.Item;
import com.ch.recsysapp.module.ItemList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyListAdapter extends BaseAdapter {
	private View itemView;
	private ItemList itemList;

	public MyListAdapter(View itemView, ItemList itemList) {
		this.itemView = itemView;
		this.itemList = itemList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = itemList.get(position);
		ImageView imageView = (ImageView) itemView
				.findViewById(R.id.activity_main_img1);
		System.out.println("asdasdasd输出错误qian");
		if (item.getBitmap() != null && imageView != null) {
			System.out.println("bitmap y已经不为空");
			imageView.setImageBitmap(item.getBitmap());
		}
		return itemView;
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