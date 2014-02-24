package com.ch.recsysapp;

import java.util.List;

import com.ch.recsysapp.http.Item;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyListAdapter extends BaseAdapter {
	private Activity context;
	private List<Item> list;

	public MyListAdapter(Activity context, List<Item> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View itemView = inflater.inflate(R.layout.activity_main_lay1, null);
		Item info = list.get(position);
		//TextView textView = (TextView) itemView.findViewById(R.id.list_text);
		ImageView imageView = (ImageView) itemView
				.findViewById(R.id.activity_main_lay1_listview);
		//textView.setText(info.getBitmap());
		imageView.setImageBitmap(info.getBitmap());
		return itemView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}