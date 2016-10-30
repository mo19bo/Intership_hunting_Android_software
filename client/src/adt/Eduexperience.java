package adt;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

public class Eduexperience {

	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
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
	private String schoolname;
	public Eduexperience(String schoolname, String major, String degree,
			int starttime, int endtime) {
		super();
		this.schoolname = schoolname;
		this.major = major;
		this.degree = degree;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	public Eduexperience() {
		
	}
	public static List<Eduexperience> parseJsonArray(String jsonStr) {
		List<Eduexperience> EduexperienceList = new ArrayList<Eduexperience>();
		try {
			JSONArray internItems = new JSONArray(jsonStr);
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Eduexperience Eduexperienceitem = new Eduexperience();
				
				Eduexperienceitem.setIdString(jsonObj.getString("sid"));
				Eduexperienceitem.setSchoolname(jsonObj.getString("school"));
				Eduexperienceitem.setDegree(jsonObj.getString("degree"));
				Eduexperienceitem.setMajor(jsonObj.getString("major"));
				Eduexperienceitem.setStarttime(jsonObj.getInt("begin_year"));
				Eduexperienceitem.setEndtime(jsonObj.getInt("end_year"));
				
                
                
                EduexperienceList.add(Eduexperienceitem);
			}
			return EduexperienceList;
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
	private String major;
	private String degree;
	private int starttime;
	private int endtime;
	private String idString;

}
