package com.example.qqq;

import http.HttpContectionUtil;

import java.util.DuplicateFormatFlagsException;

import org.json.JSONObject;

import com.example.qqq.PersonalInfoActivity.OnClick;
import compsite.Dialog;

import adapter.MyStatus;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ProjectActivity extends Activity {

	private TextView eduschool,edudegree,edumajor,edustart,eduend;
	private MyStatus myStatus;
	private ActionBar actionBar;
	private Button saveBtn;
	private TextView projectend;
	private TextView projectstart;
	private TextView projectname;
	private TextView playrole;
	private EditText projectDescription;
	private TextView ActionTitle;
	private String id;
	

	public String edu_endyears="";
	public String edu_startyear="";
	private Button backBtn;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		myStatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("项目经验");
		iniUI();
		
		iniData();
		iniClick();
	}

	private void iniClick() {
		// TODO Auto-generated method stub
		projectname.setOnClickListener(new OnClick());
		playrole.setOnClickListener(new OnClick());
		projectstart.setOnClickListener(new OnClick());
		projectend.setOnClickListener(new OnClick());
		saveBtn.setOnClickListener(new OnClick());
		backBtn.setOnClickListener(new OnClick());
	}

	private void iniData() {
		// TODO Auto-generated method stub
		Bundle bundle = this.getIntent().getExtras(); 
		if(bundle==null)
			return;
		projectname.setText(bundle.getString("projectname"));
		playrole.setText(bundle.getString("role"));
		
		projectstart.setText(bundle.getString("Begin"));
		projectend.setText(bundle.getString("End"));
		projectDescription.setText(bundle.getString("Des"));
		id=bundle.getString("project_id");
		
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		projectname=(TextView)findViewById(R.id.projectname);
		playrole=(TextView)findViewById(R.id.projectrole);
		
		projectstart=(TextView)findViewById(R.id.edu_startyear2);
		projectend=(TextView)findViewById(R.id.edu_endyears2);
		
		projectDescription=(EditText)findViewById(R.id.editText1_project_content);
		
		
		saveBtn=(Button)findViewById(R.id.save_button1);
		backBtn=(Button)findViewById(R.id.back);
	}
	class OnClick implements  OnClickListener{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch ( arg0.getId()){
			case R.id.projectname:
				new Dialog().textedit_dialog("请输入名称", projectname, 6, ProjectActivity.this);
				
				break;
			case R.id.projectrole:
              new Dialog().textedit_dialog("请输入名称", playrole, 6, ProjectActivity.this);
				
				break;
			
			case R.id.edu_startyear2:
				edu_startyear= new Dialog().date_dialog(ProjectActivity.this,projectstart,2);
			break;
			case R.id.edu_endyears2:
				edu_endyears=new Dialog().date_dialog(ProjectActivity.this, projectend,2);
				break;
			case R.id.back:
				finish();
				break;
			case R.id.save_button1:
				if(!projectname.getText().toString().isEmpty()
						&&!playrole.getText().toString().isEmpty()
						&&!projectDescription.getText().toString().isEmpty()
						&&!projectstart.getText().toString().isEmpty()
						&&!projectend.getText().toString().isEmpty())
				{
					if(id==null){
						sumbit();
					}else{
						Update();
					}
					
				}else{
					
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ProjectActivity.this, "信息不完整", 0).show();
							
						}
					});
				}
				
				
			
			break;
			default:
					break;
			}
		}

		
	}
	

	public void sumbit() {
		// TODO Auto-generated method stub
		final String projectnameString=projectname.getText().toString();
		final String playroleString=playrole.getText().toString();
		final String project_descriptionString=projectDescription.getText().toString();
		
	
		
		final int project_startyear=Integer.parseInt(edu_startyear.split("-")[0]);
	
		final int project_endyear=Integer.parseInt(edu_endyears.split("-")[0]);
		final int project_startmonth=Integer.parseInt(edu_startyear.split("-")[1]);
		
		final int project_endmonth=Integer.parseInt(edu_endyears.split("-")[1]);
		
		
		
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("projectname", projectnameString);
					jsonObj.put("playrole", playroleString);
					
					jsonObj.put("project_startyear", project_startyear);
					jsonObj.put("project_endyear", project_endyear);
					jsonObj.put("project_startmonth", project_startmonth);
					jsonObj.put("project_endmonth", project_endmonth);
					jsonObj.put("project_description", project_descriptionString);
					
					
					jsonObj.put("resume_id", myStatus.getUserResume_id());
					
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "ProjectExperience.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ProjectActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ProjectActivity.this, "存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(ProjectActivity.this, " Success", 0).show();
							
							Intent intent2 = new Intent();  
							   intent2.setClass(ProjectActivity.this,  ResumeModifyActivity.class);
								startActivity(intent2); 
							
							finish();
								}
							});
						}
					
					
					
					
	
				}
				catch (final Exception e) {
					// TODO Auto-generated catch block
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ProjectActivity.this, "Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
	}

	public void Update() {
		// TODO Auto-generated method stub
		final String projectnameString=projectname.getText().toString();
		final String playroleString=playrole.getText().toString();
		final String projectstartString=projectstart.getText().toString().substring(0,4);
		final String projectendString=projectend.getText().toString().substring(0,4);
		final String projectstartString1=projectstart.getText().toString().substring(5,6);
		final String projectendString1=projectend.getText().toString().substring(5,6);
		final String project_descriptionString=projectDescription.getText().toString();
		
		final int project_startyear=Integer.parseInt(projectstartString);
	
		final int project_endyear=Integer.parseInt(projectendString);
		final int project_startmonth=Integer.parseInt(projectstartString1);
		
		final int project_endmonth=Integer.parseInt(projectendString1);
		
		
		
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("projectname", projectnameString);
					jsonObj.put("playrole", playroleString);
					
					jsonObj.put("project_startyear", project_startyear);
					jsonObj.put("project_endyear", project_endyear);
					jsonObj.put("project_startmonth", project_startmonth);
					jsonObj.put("project_endmonth", project_endmonth);
					jsonObj.put("project_description", project_descriptionString);
					
					
					jsonObj.put("id", id);
					
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "ProjectExperience_update.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ProjectActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ProjectActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(ProjectActivity.this, " Success", 0).show();
							
							Intent intent2 = new Intent();  
							   intent2.setClass(ProjectActivity.this,  ResumeModifyActivity.class);
								startActivity(intent2); 
							
							finish();
								}
							});
						}
					
					
					
					
	
				}
				catch (final Exception e) {
					// TODO Auto-generated catch block
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ProjectActivity.this, "Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
		
		
	}
	
	
	
}
