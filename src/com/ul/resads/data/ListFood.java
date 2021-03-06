package com.ul.resads.data;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.ul.resads.util.RESTClient;


public class ListFood {
	private List<Food>foodList;

	public ListFood() {
		super();
		foodList=new ArrayList<Food>();
	}
	
	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}



	public void excuteURL(String url){
		String result=RESTClient.callRESTService(url);
		JSONArray jsonArray = null;
		try {
			jsonArray=new JSONArray(result);
		} catch (JSONException e) {
			Log.e("JSON Parser", e.toString());
		}
		for(int i=0;i<jsonArray.length();i++){
			try {
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				String imageUrl = "http://phone-price.info/res_ads"+jsonObject.getString("res_food_image");
				Food food=new Food(jsonObject.getInt("res_id"),imageUrl, jsonObject.getString("resd_food_name"), jsonObject.getInt("resd_id"));
				foodList.add(food);

				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
