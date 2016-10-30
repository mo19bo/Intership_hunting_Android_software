package adt;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class advertisement {

	public advertisement() {
		// TODO Auto-generated constructor stub
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	private String target;
	private String img_url;
	//private static String URL="http://192.168.0.17:80/1/";
	public static List<advertisement> parseJsonArray(String responseStr) {
		// TODO Auto-generated method stub
		 List<advertisement> adList=new ArrayList<advertisement>();
		try {
			JSONObject jsonObj_data= new JSONObject(responseStr);
			JSONArray  adItems=jsonObj_data.getJSONArray("data");
			for (int i = 0; i <  adItems.length(); i++) {
				JSONObject jsonObj = adItems.getJSONObject(i);

				advertisement advertisementitem = new advertisement();
				advertisementitem.setImg_url(new staticurl().getURL()+jsonObj.getString("img_url"));
				advertisementitem.setTarget(jsonObj.getString("target"));
				
				adList.add(advertisementitem);
			}
			return adList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
