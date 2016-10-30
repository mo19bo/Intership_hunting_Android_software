package adt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

public class Company implements Serializable{
	private static String URL="http://192.168.0.17:80/1/";

	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_scale() {
		return com_scale;
	}
	public void setCom_scale(String com_scale) {
		this.com_scale = com_scale;
	}
	public String getCom_industry() {
		return com_industry;
	}
	public void setCom_industry(String com_industry) {
		this.com_industry = com_industry;
	}
	public String getCom_home_page() {
		return com_home_page;
	}
	public void setCom_home_page(String com_home_page) {
		this.com_home_page = com_home_page;
	}
	public String getCom_place() {
		return com_place;
	}
	public void setCom_place(String com_place) {
		this.com_place = com_place;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	
	public Company() {
		super();
	}
	private String com_id;
	public Company(String com_id, String com_scale, String com_industry,
			String com_home_page, String com_place, String logo_url) {
		super();
		this.com_id = com_id;
		this.com_scale = com_scale;
		this.com_industry = com_industry;
		this.com_home_page = com_home_page;
		this.com_place = com_place;
		this.logo_url = logo_url;
	}
	private String com_scale;
	private String  com_industry;
	private String  com_home_page;
	private String com_place;
	private String logo_url;
	private String com_nameString;
	private String red_heart_idString;
	public static  List<Company> parseJsonArray(String responseStr,int flag) {
		// TODO Auto-generated method stub

		List<Company> CompanyList = new ArrayList<Company>();
		try {
			JSONObject jsonObj_data= new JSONObject(responseStr);
			JSONArray  internItems=jsonObj_data.getJSONArray("data");
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Company Companyitem = new Company();
				if(flag!=2){
					Companyitem.setRed_heart_idString(jsonObj.getString("red_heart_sid"));
					
				}
				
				Companyitem.setCom_id(jsonObj.getString("sid"));
				Companyitem.setCom_nameString(jsonObj.getString("name"));
				Companyitem.setCom_industry(jsonObj.getString("industry"));	
				Companyitem.setCom_scale(jsonObj.getString("scale"));
				Companyitem.setCom_place(jsonObj.getString("place"));
				Companyitem.setCom_home_page(jsonObj.getString("home_page"));
				Companyitem.setLogo_url(new staticurl().getURL()+jsonObj.getString("logo_url"));
				CompanyList.add(Companyitem);
			}
			return CompanyList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getCom_nameString() {
		return com_nameString;
	}
	public void setCom_nameString(String com_nameString) {
		this.com_nameString = com_nameString;
	}
	public String getRed_heart_idString() {
		return red_heart_idString;
	}
	public void setRed_heart_idString(String red_heart_idString) {
		this.red_heart_idString = red_heart_idString;
	}
	
	
	
	
}
