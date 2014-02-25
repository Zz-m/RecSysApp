package com.ch.recsysapp;

import com.ch.recsysapp.module.Item;
import com.ch.recsysapp.module.ItemList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	private Context context;
	private ItemList itemList;
	private View view;
	private LayoutInflater mInflater;
	

	public MyListAdapter(Context context, ItemList itemList) {
		this.context = context;
		this.itemList = itemList;
		mInflater = LayoutInflater.from(context);
		view = mInflater.inflate(R.layout.activity_main_lay1_itemsyle, null);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		Item item = itemList.get(position);
//		ImageView imageView = (ImageView) itemView
//				.findViewById(R.id.activity_main_img1);
//		System.out.println("asdasdasd输出错误qian");
//		if (item.getBitmap() != null && imageView != null) {
//			System.out.println("bitmap y已经不为空");
//			imageView.setImageBitmap(item.getBitmap());
//		}
//		return itemView;
		if(convertView == null)
        {
            convertView = mInflater.inflate(R.layout.activity_main_lay1_itemsyle, null);
        }
        
        ImageView img = (ImageView)convertView.findViewById(R.id.activity_main_img1) ;
        TextView title = (TextView)convertView.findViewById(R.id.activity_main_title1);
        TextView summary = (TextView)convertView.findViewById(R.id.activity_main_info1);
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