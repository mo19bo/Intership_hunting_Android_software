package com.example.qqq;

import http.HttpContectionUtil;

import java.util.DuplicateFormatFlagsException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qqq.PersonalInfoActivity.OnClick;
import compsite.Dialog;

import adapter.MyStatus;
import android.R.integer;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ExpeduActivity extends Activity {

	private TextView eduschool,edudegree,edumajor,edustart,eduend;
	private MyStatus myStatus;
	private ActionBar actionBar;
	private Button saveBtn;
	private TextView ActionTitle;
	private String id;
	private int edustartyear=0;
	private int eduendyear=0;
	private Button backBtn;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expedu);
		myStatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	        
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("教育经历");
		iniUI();
		
		iniData();
		iniClick();
	}

	private void iniClick() {
		// TODO Auto-generated method stub
		edudegree.setOnClickListener(new OnClick());
		eduend.setOnClickListener(new OnClick());
		edumajor.setOnClickListener(new OnClick());
		eduschool.setOnClickListener(new OnClick());
		edustart.setOnClickListener(new OnClick());
		saveBtn.setOnClickListener(new OnClick());
		backBtn.setOnClickListener(new OnClick());
	}

	private void iniData() {
		// TODO Auto-generated method stub
		Bundle bundle = this.getIntent().getExtras(); 
		if(bundle==null)
			return;
		eduschool.setText(bundle.getString("scholl"));
		edudegree.setText(bundle.getString("degree"));
		edumajor.setText(bundle.getString("major"));
		edustartyear=bundle.getInt("start");
		edustart.setText(edustartyear+"年");
		eduendyear=bundle.getInt("end");
		eduend.setText(eduendyear+"年");
		
		id=bundle.getString("edu_id");
		
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		
		eduschool=(TextView)findViewById(R.id.eduschool);
		edudegree=(TextView)findViewById(R.id.edudegree1);
		edumajor=(TextView)findViewById(R.id.edumajor);
		edustart=(TextView)findViewById(R.id.edu_startyear);
		eduend=(TextView)findViewById(R.id.edu_endyears);
		
		saveBtn=(Button)findViewById(R.id.save_button1);
		backBtn=(Button)findViewById(R.id.back);
	}
	class OnClick implements  OnClickListener{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch ( arg0.getId()){
			case R.id.eduschool:
				new Dialog().textedit_dialog("请输入学校名称", eduschool, 6, ExpeduActivity.this);
				
				break;
			case R.id.edudegree1:
				new Dialog().sex_dialog(ExpeduActivity.this, edudegree,new String[]{"专科", "本科","硕士","博士","其他"});
				break;
			case R.id.edumajor:
				new Dialog().textedit_dialog("请输入专业", edumajor, 6, ExpeduActivity.this);
				break;
			case R.id.edu_startyear:
				edustartyear=Integer.parseInt(new Dialog().date_dialog(ExpeduActivity.this,edustart,1));
			break;
			case R.id.edu_endyears:
				eduendyear=Integer.parseInt(new Dialog().date_dialog(ExpeduActivity.this, eduend,1));
				break;
			case R.id.back:
				finish();
				break;
			case R.id.save_button1:
				if(!eduschool.getText().toString().isEmpty()
						&&!edudegree.getText().toString().isEmpty()
						&&!edumajor.getText().toString().isEmpty()
						&& edustartyear!=0
						&& eduendyear!=0
						){
					if(id==null){
						
						save();
						
					}else{
						Update();
						
						
					}
					
				}else{
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ExpeduActivity.this, "信息不完整", 0).show();
							
						}
					});
					
				}
				
				
				break;
			default:
					break;
			}
		}

		

	
	}
	private void Update() {
		// TODO Auto-generated method stub
		final String eduschoolString=eduschool.getText().toString();
		final String edudegreeString=edudegree.getText().toString();
		final String edumajorString=edumajor.getText().toString();
		
		final int edustart=edustartyear;
		
		final int eduend=eduendyear;
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("eduschool", eduschoolString);
					jsonObj.put("edudegree", edudegreeString);
					jsonObj.put("edumajor", edumajorString);
					jsonObj.put("edustart", edustart);
					jsonObj.put("eduend", eduend);
					jsonObj.put("id", id);
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "EducationalExperience_update.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ExpeduActivity.this, "操作失败", 0).show();
								
							}
						});
							}
				else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(ExpeduActivity.this, "操作成功", 0).show();
							
							  Intent intent2 = new Intent();  
							   intent2.setClass(ExpeduActivity.this,  ResumeModifyActivity.class);
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
							Toast.makeText(ExpeduActivity.this, "Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
	
	}
	private void save() {
		// TODO Auto-generated method stub
		final String eduschoolString=eduschool.getText().toString();
		final String edudegreeString=edudegree.getText().toString();
		final String edumajorString=edumajor.getText().toString();
	     final int edustart=edustartyear;
		
		final int eduend=eduendyear;
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("eduschool", eduschoolString);
					jsonObj.put("edudegree", edudegreeString);
					jsonObj.put("edumajor", edumajorString);
					jsonObj.put("edustart", edustart);
					jsonObj.put("eduend", eduend);
					jsonObj.put("resume_id", myStatus.getUserResume_id());
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "EducationalExperience.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ExpeduActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ExpeduActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(ExpeduActivity.this, " Success", 0).show();
							
							  Intent intent2 = new Intent();  
							   intent2.setClass(ExpeduActivity.this,  ResumeModifyActivity.class);
								startActivity(intent2); 
							//finish();
								}
							});
						}
					
					
					
					
	
				}
				catch (final Exception e) {
					// TODO Auto-generated catch block
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ExpeduActivity.this, "Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
	}
	
	
}
