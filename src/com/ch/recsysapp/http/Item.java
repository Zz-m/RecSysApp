package com.ch.recsysapp.http;

import java.io.Serializable;
import java.util.Map;

import org.json.JSONException;

import com.ch.recsysapp.util.JsonHelper;

public class Item implements Serializable{

	private String id;
	private String name;
	private String summary;
	private String imageUri;

	public Item() {
		setName("ÍøÂç´íÎó");
		setSummary("´íÎó");
		setImageUri("asd");
	}

	public Item(String Json) {
		try {
			Map result = JsonHelper.toMap(Json);
			setName((String)result.get("name"));
			setSummary((String)result.get("summary"));
			setImageUri((String)result.get("imageUri"));
			setId((String)result.get("id"));
		} catch (JSONException e) {
			setName("json×Ö·û´®¸ñÊ½´íÎó£¬com.ch.recsysapp.http.Item");
    		setSummary("°¢Ë¹¶Ù¼Ù°¡");
    		setImageUri("12312321");
			e.printStackTrace();
			
		}
		
	}

	public Item(String name, String summary, String imageUri) {
		this.name = name;
		this.summary = summary;
		this.imageUri = imageUri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

}
