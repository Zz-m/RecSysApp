package com.ch.recsysapp.module;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author adj
 * 
 */
public class ItemList {

	/**
	 * 
	 * @param args
	 */
	private List<Item> list = new ArrayList<Item>();

	public ItemList() {
	}

	public ItemList(List<Item> list) {
		this.list = list;
	}

	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}

	/*
	 * 添加元素时按照id排序 ,id存在的时候更新
	 */
	public void add(Item item) {
		if (item.getId() == null || item.getId() == "" || list.size() == 0) {
			list.add(item);
		} else {
			try {
				for (int i = 0; i < list.size(); i++) {
					if (Integer.parseInt(list.get(i).getId()) == Integer
							.parseInt(item.getId())) {
						list.add(i, item);
						list.remove(i + 1);
						break;
					} else if (Integer.parseInt(list.get(i).getId()) > Integer
							.parseInt(item.getId()) || i == list.size() - 1) {
						if (i == list.size() - 1
								&& Integer.parseInt(list.get(i).getId()) < Integer
										.parseInt(item.getId())) {
							list.add(i + 1, item);
							break;
						}
						list.add(i, item);
						break;
					} else {
						continue;
					}
				}
			} catch (NumberFormatException e) {
				list.add(item);
				e.printStackTrace();
			}
		}
	}

	public void add(int location, Item item) {
		list.add(location, item);
	}

	public void remove(int location) {
		list.remove(location);
	}

	public int size() {
		return list.size();
	}

	public Item get(int location) {
		return list.get(location);
	}

	public void clean() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; j < list.size() - i; j++) {

			}
		}
	}

}
