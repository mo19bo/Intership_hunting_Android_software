package adt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intern implements Serializable{
	private static String URL="http://192.168.0.17:80/1/";
	public String getCom_logo_url() {
		return com_logo_url;
	}
	public void setCom_logo_url(String com_logo_url) {
		this.com_logo_url = com_logo_url;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPub_dateString() {
		return pub_dateString;
	}
	public void setPub_dateString(String pub_dateString) {
		this.pub_dateString = pub_dateString;
	}
	public String getCom_nameString() {
		return com_nameString;
	}
	public void setCom_nameString(String com_nameString) {
		this.com_nameString = com_nameString;
	}
	public String getCom_departmentString() {
		return com_departmentString;
	}
	public void setCom_departmentString(String com_departmentString) {
		this.com_departmentString = com_departmentString;
	}
	public String getCityString() {
		return cityString;
	}
	public void setCityString(String cityString) {
		this.cityString = cityString;
	}
	public String getDayweek() {
		return dayweek;
	}
	public void setDayweek(String dayweek) {
		this.dayweek = dayweek;
	}
	public String getSalaryString() {
		return salaryString;
	}
	public void setSalaryString(String salaryString) {
		this.salaryString = salaryString;
	}
	
	private String intern_idString;
	private String job;
	private String pub_dateString;
	private String com_nameString;
	private String com_departmentString;
	private String cityString;
	private String dayweek;
	private String salaryString;
	private String jobattractionString;
	private String com_logo_url;
	private String com_idString;
	private String deliver_email;
	private String description_job;
	private String work_addressString;
	public Intern(String job, String pub_dateString, String com_nameString,
			String com_departmentString, String cityString, String dayweek,
			String salaryString, String jobattractionString,
			String com_logo_url, String com_idString, String deliver_email,
			String description_job) {
		super();
		this.job = job;
		this.pub_dateString = pub_dateString;
		this.com_nameString = com_nameString;
		this.com_departmentString = com_departmentString;
		this.cityString = cityString;
		this.dayweek = dayweek;
		this.salaryString = salaryString;
		this.jobattractionString = jobattractionString;
		this.com_logo_url = com_logo_url;
		this.com_idString = com_idString;
		this.deliver_email = deliver_email;
		this.description_job = description_job;
	}
	public String getJobattractionString() {
		return jobattractionString;
	}
	public void setJobattractionString(String jobattractionString) {
		this.jobattractionString = jobattractionString;
	}
	public String getCom_idString() {
		return com_idString;
	}
	public void setCom_idString(String com_idString) {
		this.com_idString = com_idString;
	}
	public String getDeliver_email() {
		return deliver_email;
	}
	public void setDeliver_email(String deliver_email) {
		this.deliver_email = deliver_email;
	}
	public String getDescription_job() {
		return description_job;
	}
	public void setDescription_job(String description_job) {
		this.description_job = description_job;
	}
	
	
	public Intern() {
		// TODO Auto-generated constructor stub
	}
	public static List<Intern> parseJsonArray(String jsonStr) {
		
		
		List<Intern> driveItemsList = new ArrayList<Intern>();
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");  
		  
		;
		try {
			JSONObject jsonObj_data= new JSONObject(jsonStr);
			JSONArray  internItems=jsonObj_data.getJSONArray("data");
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Intern internitem = new Intern();
				internitem.setDayweek(jsonObj.getString("dayperweek"));
				internitem.setIntern_idString(jsonObj.getString("sid"));
				internitem.setCom_logo_url(new staticurl().getURL()+jsonObj.getString("logo"));
				internitem.setCom_idString(jsonObj.getString("com_id"));
				internitem.setCom_nameString(jsonObj.getString("com_name"));
				 
				if(jsonObj.get("add_time")!=null){
				 Long time=new Long(jsonObj.getInt("add_time"));
				 String d = sdf.format(time*1000);
				 
			      
			
				internitem.setPub_dateString(d+"发布");
				
				}
				internitem.setCom_departmentString(jsonObj.getString("department"));
				internitem.setCityString(jsonObj.getString("city"));
				internitem.setJob(jsonObj.getString("name"));
				internitem.setSalaryString(jsonObj.getInt("minsalary")+"-"+jsonObj.getInt("maxsalary")+"元/天");
			    internitem.setJobattractionString(jsonObj.getString("attraction"));
                internitem.setDeliver_email(jsonObj.getString("deliver_email"));
                internitem.setDescription_job(jsonObj.getString("info"));
                internitem.setWork_addressString(jsonObj.getString("address"));
                
				driveItemsList.add(internitem);
			}
			return driveItemsList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
public static List<Intern> parseJsonArray2(String jsonStr) {
		
		
		List<Intern> driveItemsList2 = new ArrayList<Intern>();
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");  
		  
		;
		try {
			JSONObject jsonObj_data= new JSONObject(jsonStr);
			JSONArray  internItems=jsonObj_data.getJSONArray("data");
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Intern internitem = new Intern();
				internitem.setDayweek(jsonObj.getString("dayperweek"));
				internitem.setIntern_idString(jsonObj.getString("sid"));
				internitem.setCom_logo_url( new staticurl().getURL()+jsonObj.getString("logo"));
				internitem.setCom_idString(jsonObj.getString("com_id"));
				internitem.setCom_nameString(jsonObj.getString("com_name"));
				 
				Long time=new Long(jsonObj.getInt("add_time"));
				 String d = sdf.format(time*1000);
				 
			      
			
				internitem.setPub_dateString(d+"发布");
				
				
				internitem.setCom_departmentString(jsonObj.getString("department"));
				internitem.setCityString(jsonObj.getString("city"));
				internitem.setJob(jsonObj.getString("name"));
				internitem.setSalaryString(jsonObj.getInt("minsalary")+"-"+jsonObj.getInt("maxsalary")+"元/天");
			    internitem.setJobattractionString(jsonObj.getString("attraction"));
                internitem.setDeliver_email(jsonObj.getString("deliver_email"));
                internitem.setDescription_job(jsonObj.getString("info"));
                internitem.setWork_addressString(jsonObj.getString("address"));
                
				driveItemsList2.add(internitem);
			}
			return driveItemsList2;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	private String resultString;
	public String getIntern_idString() {
		return intern_idString;
	}
	public void setIntern_idString(String intern_idString) {
		this.intern_idString = intern_idString;
	}
	public String getWork_addressString() {
		return work_addressString;
	}
	public void setWork_addressString(String work_addressString) {
		this.work_addressString = work_addressString;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	/**
	 * @param args
	 */
	

}
