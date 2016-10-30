package com.example.qqq;

import http.HttpContectionUtil;

import org.json.JSONObject;

import com.example.qqq.ExpeduActivity.OnClick;
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
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class InternShipActivity extends Activity {

	private MyStatus myStatus;
	private ActionBar actionBar;
	private TextView InternComName;
	private TextView Internedustart;
	private TextView Interneduend;
	private TextView InternOcuppation;
	private Button saveBtn;
	private TextView ActionTitle;
	private String id;
	private Button backBtn;
	public String internstartyear;
	public String internendyear;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intern_ship);
		myStatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("实习经历");
		iniUI();
		
		iniData();
		iniClick();
	}

	private void iniClick() {
		// TODO Auto-generated method stub
		InternComName.setOnClickListener(new OnClick());
		InternOcuppation.setOnClickListener(new OnClick());
		Internedustart.setOnClickListener(new OnClick());
		Interneduend.setOnClickListener(new OnClick());
		saveBtn.setOnClickListener(new OnClick());
		backBtn.setOnClickListener(new OnClick());
	}

	private void iniData() {
		// TODO Auto-generated method stub
		Bundle bundle = this.getIntent().getExtras(); 
		if(bundle==null)
			return;
		InternComName.setText(bundle.getString("comname"));
		InternOcuppation.setText(bundle.getString("ocuupation"));
		
		Internedustart.setText(bundle.getInt("start_year")+"-"+bundle.getInt("start_month"));
		Interneduend.setText(bundle.getInt("end_year")+"-"+bundle.getInt("end_month"));
		id=bundle.getString("intern_id");
		
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		InternComName=(TextView)findViewById(R.id.intern_com);
		InternOcuppation=(TextView)findViewById(R.id.intern_ocupation);
		Internedustart=(TextView)findViewById(R.id.edu_startyear1);
		Interneduend=(TextView)findViewById(R.id.edu_endyears1);
		
		//actionbar初始化事件
		saveBtn=(Button)findViewById(R.id.save_button1);
		backBtn=(Button)findViewById(R.id.back);
	}
	class OnClick implements  OnClickListener{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch ( arg0.getId()){
			case R.id.intern_com:
				new Dialog().textedit_dialog("请输入公司名称", InternComName, 6, InternShipActivity.this);
				
				break;
			case R.id.intern_ocupation:
				new Dialog().textedit_dialog("请输入职业", InternOcuppation, 6, InternShipActivity.this);
				break;
			case R.id.edu_startyear1:
			  internstartyear=new Dialog().date_dialog(InternShipActivity.this,Internedustart,2);
			break;
			case R.id.edu_endyears1:
				internendyear=new Dialog().date_dialog(InternShipActivity.this, Interneduend,2);
				break;
			case R.id.back:
				finish();
				break;
			case R.id.save_button1:
				if(!InternComName.getText().toString().isEmpty()
						&&!InternOcuppation.getText().toString().isEmpty()
						&&!Internedustart.getText().toString().isEmpty()
						&&!Interneduend.getText().toString().isEmpty()){
					
					if(id==null){
						sumbit();
					}else{
						Update();
					}

				}else{
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(InternShipActivity.this, "信息不完整", 0).show();
							
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
		final String InternComNameString=InternComName.getText().toString();
		final String InternOcuppationString=InternOcuppation.getText().toString();
		
		
		final String InternedustartString=Internedustart.getText().toString().substring(0,4);
		final String InterneduendString=Interneduend.getText().toString().substring(0,4);
		final String InternedustartString1=Internedustart.getText().toString().substring(5,6);
		final String InterneduendString1=Interneduend.getText().toString().substring(5,6);
		
		final int Internstartyear=Integer.parseInt(internstartyear.split("-")[0]);
	
		final int Internendyear=Integer.parseInt(internendyear.split("-")[0]);
		final int Internstartmonth=Integer.parseInt(internstartyear.split("-")[1]);
		
		final int Internendmonth=Integer.parseInt(internendyear.split("-")[1]);
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("InternComName", InternComNameString);
					jsonObj.put("InternOcuppation", InternOcuppationString);
					
					jsonObj.put("Internstartyear", Internstartyear);
					jsonObj.put("Internendyear", Internendyear);
					jsonObj.put("Internstartmonth", Internstartmonth);
					jsonObj.put("Internendmonth", Internendmonth);
					
					jsonObj.put("resume_id", myStatus.getUserResume_id());
					
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "InternalExperience.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(InternShipActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(InternShipActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(InternShipActivity.this, " Success", 0).show();
							
							Intent intent2 = new Intent();  
							   intent2.setClass(InternShipActivity.this,  ResumeModifyActivity.class);
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
							Toast.makeText(InternShipActivity.this, "Register Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
	}

	public void Update() {
		// TODO Auto-generated method stub
		final String InternComNameString=InternComName.getText().toString();
		final String InternOcuppationString=InternOcuppation.getText().toString();
		final String InternedustartString=Internedustart.getText().toString().substring(0,4);
		final String InterneduendString=Interneduend.getText().toString().substring(0,4);
		final String InternedustartString1=Internedustart.getText().toString().substring(5,6);
		final String InterneduendString1=Interneduend.getText().toString().substring(5,6);
		
		final int Internstartyear=Integer.parseInt(InternedustartString);
	
		final int Internendyear=Integer.parseInt(InterneduendString);
		final int Internstartmonth=Integer.parseInt(InternedustartString1);
		
		final int Internendmonth=Integer.parseInt(InterneduendString1);
		new Thread(){
			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("InternComName", InternComNameString);
					jsonObj.put("InternOcuppation", InternOcuppationString);
					
					jsonObj.put("Internstartyear", Internstartyear);
					jsonObj.put("Internendyear", Internendyear);
					jsonObj.put("Internstartmonth", Internstartmonth);
					jsonObj.put("Internendmonth", Internendmonth);
					
					jsonObj.put("id",id);
					
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "InternalExperience_update.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(InternShipActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(InternShipActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(InternShipActivity.this, " Success", 0).show();
							
							Intent intent2 = new Intent();  
							   intent2.setClass(InternShipActivity.this,  ResumeModifyActivity.class);
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
							Toast.makeText(InternShipActivity.this, "Register Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
	}
	
}
