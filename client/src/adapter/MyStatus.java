package adapter;

import java.net.URL;
import java.util.Date;

import android.R;
import android.app.Application;
import android.net.Uri;

public class MyStatus extends Application {
	

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String string) {
		this.sex = string;
	}

	private Boolean status = false;
	private String username = "未输入";
	private String urlString = "http://192.168.0.17:80/1/";
	private String phonenumber = "未输入";
	private String  sex = "男";
	private Date curtime;
    private String degree="未输入";
    private String school="xx大学";
    private String major="xx专业";
    private String graduate_year="1990年0月0日";
    private String photo_url=null;
	private String job="还未输入";
	private String realname="未输入";
	private String email="未输入";
	private String birthday="未输入";
	private String userpwd="";
	private String user_ID="";
	private String userResume_id="";
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	public float getPassengercredit() {
		return passengercredit;
	}
	public void setPassengercredit(float passengercredit) {
		this.passengercredit = passengercredit;
	}
	private float passengercredit;
	public Date getCurtime() {
		return curtime;
	}
	public void setCurtime(Date curtime) {
		this.curtime = curtime;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGraduate_year() {
		return graduate_year;
	}
	public void setGraduate_year(String graduate_year) {
		this.graduate_year = graduate_year;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getUserResume_id() {
		return userResume_id;
	}
	public void setUserResume_id(String userResume_id) {
		this.userResume_id = userResume_id;
	}
	
}
