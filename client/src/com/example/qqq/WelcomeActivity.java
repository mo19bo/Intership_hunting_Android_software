package com.example.qqq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import http.HttpContectionUtil;

import org.json.JSONObject;

import adapter.MyStatus;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	 private SharedPreferences sharedPreferences;  
	  private SharedPreferences.Editor judage;
	private SharedPreferences share1;
	private String useridString;
	protected MyStatus myStatus;
	 ExecutorService pool = Executors.newFixedThreadPool(3);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
	     myStatus=(MyStatus)getApplication();
		Runnable r = new Runnable() {
			

			@Override
			public void run() {
			// TODO Auto-generated method stub
				if(!judgement1()){
					
					
					if(judgement2()){
					 useridString=share1.getString("userid", "");
						login2();
					
					
					}else{
						
						Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
						
					}
				}
				else{
				
					Intent intent=new Intent(WelcomeActivity.this,LoadingPageActivity.class);
					startActivity(intent);
					finish();
				}
		
			}

		
			};
		
		new Handler().postDelayed(r, 1000);
		
	}
	protected void login2() {
		// TODO Auto-generated method stub
		
	
		
		pool.execute(t1);
		
	}
	private boolean judgement2() {
		// TODO Auto-generated method stub
      share1 = getSharedPreferences("userinfo", MODE_PRIVATE);
		
		String flag=share1.getString("is_auth", "false");
		if("true".equals(flag)){
			
			return true;
			
		}else{
			
			
			return false;
		}
		
	}
	public boolean judgement1(){
		
		SharedPreferences share = getSharedPreferences("my_pref", MODE_PRIVATE);
		
		String flag=share.getString("guide_activity", "true");
		if("false".equals(flag)){
			
			
			return false;
		}else{
			
			return true;
		}
		
	}
     Thread t1=new Thread() {
		
		

		@SuppressLint("NewApi")
		@Override
		public void run() {
			final JSONObject jsonObj = new JSONObject();
			try {
				jsonObj.put("user_id", useridString);
				
				HttpContectionUtil conn = new HttpContectionUtil();
			     myStatus = (MyStatus) getApplication();
				String uri =myStatus.getUrlString() + "login2.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				
				JSONObject response = new JSONObject( responseStr);
				
				
				String result =  response.getString("result");
				if ("failed".equals(result)) {
					runOnUiThread(new Runnable(){
						public void run(){
							Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
							startActivity(intent);
							finish();
						}
					});
				} else {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(WelcomeActivity.this, "Login Success", 0).show();
						
						}
					});
					
					// 解析并存到全局变量中
				    JSONObject userObj = new JSONObject(responseStr);
					myStatus.setUsername(userObj.getString("username"));
					myStatus.setEmail(userObj
							.getString("useremail"));
					
					
					if(userObj.getString("realname").isEmpty()){
						Intent intent=new Intent();
						intent.setClass(WelcomeActivity.this, PersonalInfoActivity.class);
						Bundle bundle=new Bundle();
						bundle.putInt("flag", 1);
						intent.putExtras(bundle);
						startActivity(intent);
				   }
					else{
					myStatus.setPhonenumber(userObj
							.getString("telphone"));
				     myStatus.setRealname(userObj.getString("realname"));
					
					          if(userObj.get("gender").toString()==null){

					              	myStatus.setSex("男");
					                     }
					                   else if(userObj.getInt("gender")==1){
						              myStatus.setSex("男");
					                    }
					                    else{
						                      myStatus.setSex("女");
					                        }
					myStatus.setSchool(userObj
							.getString("school"));
					myStatus.setMajor(userObj
							.getString("major"));
					myStatus.setDegree(userObj
							.getString("degree"));
					myStatus.setGraduate_year(userObj
							.getString("graduate_year"));
				    myStatus.setUser_ID(userObj
							.getString("user_id"));
				    myStatus.setUserResume_id(userObj
							.getString("resume_id"));
				    myStatus.setPhoto_url(userObj
							.getString("user_img"));
				    SharedPreferences settings = getSharedPreferences("userinfo", 0);
			        SharedPreferences.Editor editor = settings.edit();
			        editor.putString("userid", myStatus.getUser_ID());
			        editor.putString("is_auth", "true");
			        editor.commit();
				    
					
					// 进入到4个选项的页面
			    	Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
					}
					
				}
			} catch (final Exception e) {
				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(WelcomeActivity.this, "Failed"+e.toString(), 0).show();
						
						
						
					}
				});
			}
		}
	};
}
