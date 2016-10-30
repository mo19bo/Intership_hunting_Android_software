package adt;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

public class Internship {

	public Internship() {
		super();
	}

	private String companyString;
	public String getCompanyString() {
		return companyString;
	}
	public void setCompanyString(String companyString) {
		this.companyString = companyString;
	}
	public String getOccupationString() {
		return occupationString;
	}
	public void setOccupationString(String occupationString) {
		this.occupationString = occupationString;
	}
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	private String occupationString;
	private int starttime;
	private int  endtime;
	private int startmonthtime;
	private String idString;
	public Internship(String companyString, String occupationString,
			int starttime, int endtime, int startmonthtime, int endmonthtime) {
		super();
		this.companyString = companyString;
		this.occupationString = occupationString;
		this.starttime = starttime;
		this.endtime = endtime;
		this.startmonthtime = startmonthtime;
		this.endmonthtime = endmonthtime;
	}
	private int endmonthtime;
	
	public int getStartmonthtime() {
		return startmonthtime;
	}
	public void setStartmonthtime(int startmonthtime) {
		this.startmonthtime = startmonthtime;
	}
	public int getEndmonthtime() {
		return endmonthtime;
	}
	public void setEndmonthtime(int endmonthtime) {
		this.endmonthtime = endmonthtime;
	}
	public static List<Internship> parseJsonArray(String jsonStr) {
		// TODO Auto-generated method stub
		List<Internship>  internshipList = new ArrayList<Internship>();
		try {
			JSONArray internItems = new JSONArray(jsonStr);
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Internship internship = new Internship();
				internship.setIdString(jsonObj.getString("sid"));
				internship.setCompanyString(jsonObj.getString("name"));
				internship.setOccupationString(jsonObj.getString("play_role"));
				internship.setStarttime(jsonObj.getInt("begin_year"));
				internship.setStartmonthtime(jsonObj.getInt("begin_month"));
				internship.setEndtime(jsonObj.getInt("end_year"));
				internship.setEndmonthtime(jsonObj.getInt("end_month"));
                
                
				internshipList.add(internship);
			}
			return internshipList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	
	
}
