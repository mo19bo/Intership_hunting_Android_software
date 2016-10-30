package com.example.qqq;

import http.HttpContectionUtil;

import org.json.JSONObject;

import function.RegexJudge;
import adapter.InternItemsLVAdapter;
import adapter.MyStatus;
import adt.Intern;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
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

@SuppressLint("NewApi")
public class AccountSettingActivity extends Activity {
   
	private EditText renewpwd;
	private EditText newpwd;
	private EditText oldpwd;
	private ActionBar actionBar;
	private Button save;
	private MyStatus myStatus;
	private TextView ActionTitle;
	private TextView curpwd;
	protected String renewString;
	private Button back;
	protected String newString2;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_setting);
		
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	    
		        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
		    	   ActionTitle.setText("账号设置");
		myStatus=(MyStatus)getApplication();
		
		iniUI();
		inidata();
	}

	private void inidata() {
		// TODO Auto-generated method stub
		curpwd.setText(myStatus.getEmail());
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		oldpwd=(EditText)findViewById(R.id.editCurrent_psd);
		newpwd=(EditText)findViewById(R.id.editNewPWD);
		renewpwd=(EditText)findViewById(R.id.editReconfirm);
		curpwd=(TextView)findViewById(R.id.tcurrenteamail);
		
		
		back=(Button)findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		save=(Button)findViewById(R.id.save_button1);
		save.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    String newstring=newpwd.getText().toString();
			   newString2=oldpwd.getText().toString();
			    renewString=renewpwd.getText().toString();
			    if(!newstring.isEmpty()&&newstring.equals(renewString)){
			    	if(new RegexJudge().ispwd(newstring)){
			    		
			    	
			    			
			    			sumbit();
			    		
			    		
			    	}else{
			    		Toast.makeText(AccountSettingActivity.this, "密码至少为6-16位数字与字母组合" , Toast.LENGTH_SHORT).show();
			    		
			    		
			    	}
			    	
			    	
			    }else{
			    	
			    	Toast.makeText(AccountSettingActivity.this, "两次新密码输入不相等" , Toast.LENGTH_SHORT).show();
			    	
			    }
			
			
			
			}
		});
	}

	protected void sumbit() {
		// TODO Auto-generated method stub
		new Thread() {
			@Override
			public void run() {
		    try{
					final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("user_id",myStatus.getUser_ID());
				jsonObj.put("oldpassword",newString2);
				jsonObj.put("password",renewString);
				String uri =myStatus.getUrlString() + "motified_pwd.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
                    JSONObject response = new JSONObject( responseStr);
				
				
				String result =  response.getString("result");
				
				if ("success".equals(result)){
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(AccountSettingActivity.this, "修改成功", 0).show();
						}
					});
				}else if("exists".equals(result)){
					
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(AccountSettingActivity.this, "修改失败原密码错误", 0).show();
						}
					});
					
					
				}
				else{
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(AccountSettingActivity.this, "修改失败", 0).show();
						}
					});

				
				}
				
				
				}
				catch (final Exception e) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(AccountSettingActivity.this, "Failed"+e.toString(), 0).show();
						}
					});
				}
			}
		}.start();
	}
	
}
