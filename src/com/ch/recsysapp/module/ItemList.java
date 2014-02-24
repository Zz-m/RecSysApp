package com.ch.recsysapp.module;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

	/**
	 * @param args
	 */
	private List<Item> list = new ArrayList<Item>();

	public ItemList(){}
	public ItemList(List<Item> list){
		this.list = list;
	}
	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}

	public void add(Item item) {
		list.add(item);
	}
	public void add(int location, Item item) {
		list.add(location, item);
	}
	public void delete(int location) {
		list.remove(location);
	}
	public int size() {
		return list.size();
	}
	public Item get(int location) {
		return list.get(location);
	}

}
