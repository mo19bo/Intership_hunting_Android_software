package adt;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

public class Project {
	
    public Project() {
		super();
	}

	private String projectname;
	   private String play_roleString;
	      private  int begin_years;
	      private int begin_month;
	      private int end_year;
	      private int end_month;
	      private String description;
	      private String idstString;
   public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getPlay_roleString() {
		return play_roleString;
	}
	public void setPlay_roleString(String play_roleString) {
		this.play_roleString = play_roleString;
	}
	public int getBegin_years() {
		return begin_years;
	}
	public void setBegin_years(int begin_years) {
		this.begin_years = begin_years;
	}
	public int getBegin_month() {
		return begin_month;
	}
	public void setBegin_month(int begin_month) {
		this.begin_month = begin_month;
	}
	public int getEnd_year() {
		return end_year;
	}
	public void setEnd_year(int end_year) {
		this.end_year = end_year;
	}
	public int getEnd_month() {
		return end_month;
	}
	public void setEnd_month(int end_month) {
		this.end_month = end_month;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

   public Project(String projectname, String play_roleString, int begin_years,
		int begin_month, int end_year, int end_month, String description) {
	super();
	this.projectname = projectname;
	this.play_roleString = play_roleString;
	this.begin_years = begin_years;
	this.begin_month = begin_month;
	this.end_year = end_year;
	this.end_month = end_month;
	this.description = description;
}
public static List<Project> parseJsonArray(String jsonStr) {
	// TODO Auto-generated method stub
	List<Project> ProjectList = new ArrayList<Project>();
	try {
		JSONArray internItems = new JSONArray(jsonStr);
		for (int i = 0; i <  internItems.length(); i++) {
			JSONObject jsonObj = internItems.getJSONObject(i);

			Project Projectitem = new Project();
			 
			Projectitem.setIdstString(jsonObj.getString("sid"));
			Projectitem.setProjectname(jsonObj.getString("name"));
			Projectitem.setPlay_roleString(jsonObj.getString("play_role"));
			Projectitem.setBegin_years(jsonObj.getInt("begin_year"));
			Projectitem.setBegin_month(jsonObj.getInt("begin_month"));
			Projectitem.setEnd_year(jsonObj.getInt("end_year"));
			Projectitem.setEnd_month(jsonObj.getInt("end_month"));
			Projectitem.setDescription(jsonObj.getString("description"));
			
            
            
			ProjectList.add(Projectitem);
		}
		return ProjectList;
	} catch (JSONException e) {
		e.printStackTrace();
		return null;
	}
}
public String getIdstString() {
	return idstString;
}
public void setIdstString(String idstString) {
	this.idstString = idstString;
}

   
   
}
