package com.ch.recsysapp.http;

import com.ch.recsysapp.module.Item;
import com.ch.recsysapp.util.GetPostUtil;

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
