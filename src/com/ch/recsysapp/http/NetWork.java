package com.ch.recsysapp.http;

import org.json.JSONException;

import com.ch.recsysapp.util.GetPostUtil;
import com.ch.recsysapp.util.HttpConnection;
import com.ch.recsysapp.util.JsonHelper;

public class NetWork {
	public static Item getItem(String strURL)  {
		String result = GetPostUtil.sendGet(strURL, null);
		Item item = new Item(result);
		return item;
	}

	public static Item getItem() {
		Item item = new Item();
		return item;
	}
}
