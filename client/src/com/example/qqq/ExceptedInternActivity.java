package com.example.qqq;

import http.HttpContectionUtil;

import org.json.JSONObject;

import com.example.qqq.PersonalInfoActivity.OnClick;
import compsite.CitySelector;
import compsite.Dialog;

import adapter.MyStatus;
import android.R.integer;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ExceptedInternActivity extends Activity {

	private ActionBar actionBar;
	private TextView exceptedCity;
	private TextView worktime;
	private TextView expceteddayweek;
	private TextView exceptedjob,exceptedearings,exceptedworktime;
	private Button saveBtn;
	private TextView ActionTitle;
	private TextView exceptedInternType;
	private int work_temp2;
	protected MyStatus myStatus;
	private Button backBtn;
	public String workdate;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_excepted_intern);
		
		myStatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("实习期望");
		
	    	   iniUI();
		
		    iniData();
	}

	private void iniData() {
		// TODO Auto-generated method stub
		Bundle bundle = this.getIntent().getExtras(); 
		if(bundle==null)
			return;
		expceteddayweek.setText(bundle.getString("Dayperweeks"));
		exceptedCity.setText(bundle.getString("excepted_city"));
		worktime.setText(bundle.getString("date_length"));
		exceptedjob.setText(bundle.getString("job"));
		exceptedInternType.setText(bundle.getString("job_catagroy"));
		exceptedearings.setText(bundle.getString("Salary"));
		exceptedworktime.setText(bundle.getString("Report_time"));
		
		
		
	}

	private void iniUI() {
		//顶部actionbar的初始化
        saveBtn=(Button)findViewById(R.id.save_button1);
		backBtn=(Button)findViewById(R.id.back);
        
        
        
		exceptedCity=(TextView)findViewById(R.id.exceptedcity);
		expceteddayweek=(TextView)findViewById(R.id.excepteddayweeks);//期望实习的天数/周
		worktime=(TextView)findViewById(R.id.interntime);//可以实习的时长
		exceptedjob=(TextView)findViewById(R.id.exceptedjob);//期望职业
		exceptedearings=(TextView)findViewById(R.id.exceptedearings);//期望薪水
		exceptedworktime=(TextView)findViewById(R.id.exceptedworktime);//到岗日期
		exceptedInternType=(TextView)findViewById(R.id.textview_excepted_type);//期望行业

		
		saveBtn.setOnClickListener(new OnClick());
		backBtn.setOnClickListener(new OnClick());
		exceptedCity.setOnClickListener(new OnClick());
		expceteddayweek.setOnClickListener(new OnClick());
		worktime.setOnClickListener(new OnClick());
		exceptedjob.setOnClickListener(new OnClick());
		exceptedearings.setOnClickListener(new OnClick());
		exceptedworktime.setOnClickListener(new OnClick());
		exceptedInternType.setOnClickListener(new OnClick());
	}
	
	class OnClick implements  OnClickListener{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View arg0) {
			switch ( arg0.getId()){
			case R.id.exceptedcity:
				Intent intent2=new Intent();
				intent2.setClass(ExceptedInternActivity.this, CitySelector.class);
				startActivityForResult(intent2, 1);
				break;
			case R.id.excepteddayweeks:
				
			    new Dialog().sex_dialog(ExceptedInternActivity.this, expceteddayweek, new String[]{"2天","3天","4天","5天"});
				break;
			case R.id.interntime:
				 new Dialog().sex_dialog(ExceptedInternActivity.this, worktime, new String[]{"1个月","2个月","3个月","3个月以上"});
				break;
			case R.id.exceptedjob:
				new Dialog().textedit_dialog("请输入期望职位", exceptedjob, 9, ExceptedInternActivity.this);
				break;
			case R.id.textview_excepted_type:
				Intent intent15=new Intent();
				intent15.setClass(ExceptedInternActivity.this, JobSelectActivity.class);
				startActivityForResult(intent15, 2);
				break;	
			case R.id.exceptedearings:
				new Dialog().textedit_dialog("请输入期望日薪", exceptedearings, 9, ExceptedInternActivity.this);
				
				break;
			case R.id.exceptedworktime:
				workdate=new Dialog().date_dialog(ExceptedInternActivity.this, exceptedworktime, 3);
				break;
			case R.id.back:
				finish();
			    break;
			case R.id.save_button1:
				if(!exceptedCity.getText().toString().isEmpty()
				   &&!expceteddayweek.getText().toString().isEmpty()
				   &&!worktime.getText().toString().isEmpty()
				   &&!exceptedjob.getText().toString().isEmpty()
				   
				   &&!exceptedworktime.getText().toString().isEmpty()
				   &&!exceptedInternType.getText().toString().isEmpty())
				{
					
					sumbit();
					
				}else{
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ExceptedInternActivity.this, "信息不完整", 0).show();
							
						}
					});
					
				}
				
				
				break;
			default:
					break;
			}
		}

		
	}
	private void sumbit() {
		// TODO Auto-generated method stub
		/*以下为取前台的数据*/
		final String exceptedCityString=exceptedCity.getText().toString();
		final String expceteddayweekString=expceteddayweek.getText().toString();
		final String worktimeString=worktime.getText().toString();
		final String exceptedjobString=exceptedjob.getText().toString();
		final String exceptedearingsbString=exceptedearings.getText().toString();
		final String exceptedworktimeString=exceptedworktime.getText().toString();
		final String exceptedInternTypeString=exceptedInternType.getText().toString();
		
		/*以下为处理前台数据*/
		 final int dayweek=Integer.parseInt(expceteddayweekString.substring(0,1));
		 
		 work_temp2=4;
	     if(worktimeString.length()<3){
	    	 work_temp2=Integer.parseInt(worktimeString.substring(0, 1));
	    	 
	     }
	     final int excepted_salary=Integer.parseInt(exceptedearingsbString);
	     
		 
		new Thread(){
		

			public void run() {
			
				
				try {

					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("exceptedCity", exceptedCityString);
					jsonObj.put("dayweek", dayweek);
					jsonObj.put("worktime", work_temp2);
					jsonObj.put("exceptedjob", exceptedjobString);
					jsonObj.put("exceptedearings", excepted_salary);
					jsonObj.put("exceptedworktime", exceptedworktimeString);
					jsonObj.put("exceptedInternType", exceptedInternTypeString);
					
					jsonObj.put("user_id", myStatus.getUser_ID());
					
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "EducationalIntern.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ExceptedInternActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(ExceptedInternActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
					
					}else if("success".equals(result)){
							
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(ExceptedInternActivity.this, " Success", 0).show();
								}
							});
							finish();
						}
					
					
					
					
	
				}
				catch (final Exception e) {
					// TODO Auto-generated catch block
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ExceptedInternActivity.this, "Register Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
				
				
				
				
				
				
				
				
				
				}
		}.start();
		
		
		
		
		
		
	}
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	        // TODO Auto-generated method stub 
	        super.onActivityResult(requestCode, resultCode, data); 
	        if (data != null) { 
	            String result = data.getStringExtra("result"); 
	            if (requestCode == 1) {// 因为有2个按钮,所以要区分是触发了那个按钮的单击事件,然后把返回的数据放到对应的EditText中 
	            	exceptedCity.setText(result); 
	            } else if (requestCode == 2) { 
	            	exceptedjob.setText(result); 
	            	 String result1 = data.getStringExtra("result1"); 
	            	exceptedInternType.setText(result1);
	            } 
	        } 


	    } 

}
