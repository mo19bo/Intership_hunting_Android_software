package adt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

public class DeliverResume implements Serializable{

/**
 * @return the delivercomname
 */
public String getDelivercomname() {
	return delivercomname;
}
/**
 * @param delivercomname the delivercomname to set
 */
public void setDelivercomname(String delivercomname) {
	this.delivercomname = delivercomname;
}
/**
 * @return the deliverjob
 */
public String getDeliverjob() {
	return deliverjob;
}
/**
 * @param deliverjob the deliverjob to set
 */
public void setDeliverjob(String deliverjob) {
	this.deliverjob = deliverjob;
}
/**
 * @return the usingResume
 */
public String getUsingResume() {
	return usingResume;
}
/**
 * @param usingResume the usingResume to set
 */
public void setUsingResume(String usingResume) {
	this.usingResume = usingResume;
}
/**
 * @return the delivertime
 */
public String getDelivertime() {
	return delivertime;
}
/**
 * @param delivertime the delivertime to set
 */
public void setDelivertime(String delivertime) {
	this.delivertime = delivertime;
}
/**
 * @return the delivertag
 */
public int getDelivertag() {
	return delivertag;
}
/**
 * @param delivertag the delivertag to set
 */
public void setDelivertag(int delivertag) {
	this.delivertag = delivertag;
}
private String delivercomname;
private String deliverjob;
private String usingResume;
private String delivertime;
private int delivertag;
private String intern_ID;
public String getIntern_ID() {
	return intern_ID;
}
public void setIntern_ID(String intern_ID) {
	this.intern_ID = intern_ID;
}
public DeliverResume(String intern_ID,String delivercomname, String deliverjob,
		String usingResume, String delivertime, int delivertag) {
	super();
	this.intern_ID=intern_ID;
	this.delivercomname = delivercomname;
	this.deliverjob = deliverjob;
	this.usingResume = usingResume;
	this.delivertime = delivertime;
	this.delivertag = delivertag;
}
public DeliverResume() {
	super();
}
private String interview_time;
public String getInterview_time() {
	return interview_time;
}
public void setInterview_time(String interview_time) {
	this.interview_time = interview_time;
}
public String getInterview_place() {
	return interview_place;
}
public void setInterview_place(String interview_place) {
	this.interview_place = interview_place;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getContact_tel() {
	return contact_tel;
}
public void setContact_tel(String contact_tel) {
	this.contact_tel = contact_tel;
}
public String getCompany_grade() {
	return company_grade;
}
public void setCompany_grade(String company_grade) {
	this.company_grade = company_grade;
}
public String getCompany_comments() {
	return company_comments;
}
public void setCompany_comments(String company_comments) {
	this.company_comments = company_comments;
}
public String getStudents_comment() {
	return students_comment;
}
public void setStudents_comment(String students_comment) {
	this.students_comment = students_comment;
}
public String getReject_time() {
	return reject_time;
}
public void setReject_time(String reject_time) {
	this.reject_time = reject_time;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
private String  interview_place="";
private String  contact="";
private String contact_tel="";
private String company_grade="";
private String company_comments="";
private String students_comment="";
private String reject_time="";
private String content="";


public static List<DeliverResume> parseJsonArray(String responseStr) {
	// TODO Auto-generated method stub
	List<DeliverResume> DeliverResumeList = new ArrayList<DeliverResume>();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	try {
		JSONObject jsonObj_data= new JSONObject(responseStr);
		String jsonObj_result=jsonObj_data.getString("result");
		if("failed".equals(jsonObj_result)){
			
			return  null;
		}
		JSONArray  DeliverResumedatalist=jsonObj_data.getJSONArray("data");
		for (int i = 0; i <  DeliverResumedatalist.length(); i++) {
			JSONObject jsonObj = DeliverResumedatalist.getJSONObject(i);

			DeliverResume DeliverResumeitem = new DeliverResume();
			
			DeliverResumeitem.setDeliverjob(jsonObj.getString("job"));
			DeliverResumeitem.setDelivercomname(jsonObj.getString("com_name"));
			
			if(!jsonObj.getString("deliver_time").equals("null")){
				
				 Long time=new Long(jsonObj.getInt("deliver_time"));
				 String d = sdf.format(time*1000);
				 DeliverResumeitem.setDelivertime(d);
			}
			
			
			DeliverResumeitem.setUsingResume("在线简历");
			DeliverResumeitem.setDelivertag(jsonObj.getInt("status_tag"));
			DeliverResumeitem.setIntern_ID(jsonObj.getString("intern_id"));
			
			if(!jsonObj.getString("interview_time").equals("null")){
				
				 Long time=new Long(jsonObj.getInt("interview_time"));
				 String d = sdf.format(time*1000);
				 DeliverResumeitem.setInterview_time(d);
			}
			 if(!jsonObj.getString("interview_place").equals("null")){		 
				 DeliverResumeitem.setInterview_place(jsonObj.getString("interview_place"));
			 }
			 if(!jsonObj.getString("contact").equals("null")){		 
				 DeliverResumeitem.setContact(jsonObj.getString("contact"));
			 }
			 if(!jsonObj.get("contact_tel").equals("null")){		 
				 DeliverResumeitem.setContact_tel(jsonObj.getString("contact_tel"));
			 }
			 if(!jsonObj.get("content").equals("null")){		 
				 DeliverResumeitem.setContent(jsonObj.getString("content"));
			 }
			
            
			DeliverResumeList.add(DeliverResumeitem);
		}
		return DeliverResumeList;
	} catch (JSONException e) {
		e.printStackTrace();
		return null;
	}
}

}
