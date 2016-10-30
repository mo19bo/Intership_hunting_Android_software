package adt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.MyStatus;

public class Resume {
    public MyStatus getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(MyStatus myStatus) {
		this.myStatus = myStatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIntern_type() {
		return intern_type;
	}

	public void setIntern_type(String intern_type) {
		this.intern_type = intern_type;
	}

	public String getExceptedjob() {
		return exceptedjob;
	}

	public void setExceptedjob(String exceptedjob) {
		this.exceptedjob = exceptedjob;
	}

	public String getExceptedjob_category() {
		return exceptedjob_category;
	}

	public void setExceptedjob_category(String exceptedjob_category) {
		this.exceptedjob_category = exceptedjob_category;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String string) {
		this.salary = string;
	}

	public int getDate_length() {
		return date_length;
	}

	public void setDate_length(int date_length) {
		this.date_length = date_length;
	}

	public int getDayperweeks() {
		return dayperweeks;
	}

	public void setDayperweeks(int dayperweeks) {
		this.dayperweeks = dayperweeks;
	}

	public String getReport_time() {
		return report_time;
	}

	public void setReport_time(String report_time) {
		this.report_time = report_time;
	}

	private MyStatus myStatus;
    private String city;
    private String intern_type;
    private String exceptedjob;
    private String exceptedjob_category;
    private String salary;
    private int date_length;
    private int dayperweeks;
    private String report_time;
   
	public Resume() {
		// TODO Auto-generated constructor stub
	}

	public static Resume parseJsonArray(String responseStr) {
		// TODO Auto-generated method stub
		try {
		
		JSONObject jsonObj = new JSONObject(responseStr);
		Resume resume = new Resume();
		
		resume.setCity(jsonObj.getString("city"));
		
		resume.setDate_length(jsonObj.getInt("date_length"));
		
		resume.setExceptedjob_category(jsonObj.getString("job_category"));
		resume.setExceptedjob(jsonObj.getString("job"));
		resume.setDayperweeks(jsonObj.getInt("dayperweek"));
		resume.setReport_time(jsonObj.getString("report_time"));
		String report_time=jsonObj.getString("report_time").toString();
		if(report_time.equals("null")) 
			return null;
		resume.setSalary(jsonObj.getString("salary"));
		if(jsonObj.getString("salary").equals("null"))
			return null;
		
		
			return resume;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
		
	}

}
