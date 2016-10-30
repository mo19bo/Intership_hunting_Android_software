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
public class SchoolExpActivity extends Activity {


	private MyStatus myStatus;
	private ActionBar actionBar;
	private Button saveBtn;
	private TextView schooltag;
	private EditText content;
	private TextView ActionTitle;
	private String id;
	private Button backBtn;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_exp);
		myStatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("校园经历");
		iniUI();
		
		iniData();
		iniClick();
	}

	private void iniClick() {
		// TODO Auto-generated method stub
		schooltag.setOnClickListener(new OnClick());
		content.setOnClickListener(new OnClick());
		saveBtn.setOnClickListener(new OnClick());
		backBtn.setOnClickListener(new OnClick());
	}

	private void iniData() {
		// TODO Auto-generated method stub
		Bundle bundle = this.getIntent().getExtras(); 
		if(bundle==null)
			return;
		schooltag.setText(bundle.getString("tag"));
		content.setText(bundle.getString("content"));
	    id=bundle.getString("school_id");
		
		
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		schooltag=(TextView)findViewById(R.id.school_tag);
		content=(EditText)findViewById(R.id.editText1_content);
		
	
		//顶部actionbar的初始化
		saveBtn=(Button)findViewById(R.id.save_button1);
		backBtn=(Button)findViewById(R.id.back);
	}
	class OnClick implements  OnClickListener{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch ( arg0.getId()){
			case R.id.school_tag:
				new Dialog().sex_dialog(SchoolExpActivity.this, schooltag,new String[]{"校内职务", "获得证书","社会实践","获奖经历","其他"});
				break;
			case R.id.back:
				finish();
				break;
			case R.id.save_button1:
				if(!schooltag.getText().toString().isEmpty()&&
						!content.getText().toString().isEmpty()
						){
					if(id==null){
						sumbit();
					}else{
						Update();
					}
					
					
				}else{
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(SchoolExpActivity.this, "信息不完整", 0).show();
							
						}
					});
					
				}
				
			
			default:
					break;
			}
		}
	}
	public void sumbit() {
		// TODO Auto-generated method stub
		final String schooltagString=schooltag.getText().toString();
		final String contentString=content.getText().toString();
		
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("schooltag", schooltagString);
					jsonObj.put("contentString", contentString);
					
				
					jsonObj.put("resume_id", myStatus.getUserResume_id());
					
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "SchoolExperience.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(SchoolExpActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(SchoolExpActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(SchoolExpActivity.this, " Success", 0).show();
							
							Intent intent2 = new Intent();  
							   intent2.setClass(SchoolExpActivity.this,  ResumeModifyActivity.class);
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
							Toast.makeText(SchoolExpActivity.this, "Register Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
	}

	public void Update() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				final String schooltagString=schooltag.getText().toString();
				final String contentString=content.getText().toString();
				
				new Thread(){
					public void run() {
					
						
						try {

							final JSONObject jsonObj = new JSONObject();
							
							jsonObj.put("schooltag", schooltagString);
							jsonObj.put("contentString", contentString);
							
						
							jsonObj.put("id",id);
							
							
							HttpContectionUtil conn = new HttpContectionUtil();
							String uri =myStatus.getUrlString() + "SchoolExperience_update.php";
							String responseStr = conn.ConnForResult(uri, jsonObj);
							JSONObject response = new JSONObject(responseStr);
							String result =  response.getString("result");
							if("failed".equals(result)) {
								runOnUiThread(new Runnable(){
									public void run(){
										Toast.makeText(SchoolExpActivity.this, " failed", 0).show();
										
									}
								});
									}
							else if("exist".equals(result)){
								
								runOnUiThread(new Runnable(){
									public void run(){
										Toast.makeText(SchoolExpActivity.this, "用户名存在,重新输入", 0).show();
										
									}
								});
							
							}else if("success".equals(result)){
									
									runOnUiThread(new Runnable(){
										public void run(){
											Toast.makeText(SchoolExpActivity.this, " Success", 0).show();
									
									Intent intent2 = new Intent();  
									   intent2.setClass(SchoolExpActivity.this,  ResumeModifyActivity.class);
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
									Toast.makeText(SchoolExpActivity.this, "Register Failed"+e.toString(), 0).show();
								}
							});
						}
						
						
						
						
						
						
						
						
						
						
						
						
						}
				}.start();
	}
	
	
	
}
