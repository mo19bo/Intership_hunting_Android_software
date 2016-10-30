package com.example.qqq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import http.HttpContectionUtil;

import org.json.JSONObject;

import adapter.MyStatus;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Button LoginBtn,RegBtn;
    private EditText UsernameLabel,pwdLabel;
    public static String username;
	MyStatus myStatus;
	private String usernameString;
	private String pwdString;
	 ExecutorService pool = Executors.newFixedThreadPool(3);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		iniUI();
	}
	public void iniUI(){
		LoginBtn=(Button)findViewById(R.id.loginbtn);
		RegBtn=(Button)findViewById(R.id.regbtn);
		UsernameLabel=(EditText)findViewById(R.id.editText_username);
		pwdLabel=(EditText)findViewById(R.id.editText_pwd);
		
		

		
		
		LoginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String usernameString=UsernameLabel.getText().toString();
				String pwdString=pwdLabel.getText().toString();
				login(usernameString,pwdString);
				
				
			
				
			}

		
		});
		RegBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, ResgisterActivity.class);
				startActivity(intent); 
				finish();
			}
		});
		
	}
	public static final String removeBOM(String data) {
		if (TextUtils.isEmpty(data)) {
		return data;
		}

		if (data.startsWith("\ufeff")) {
		return data.substring(1);
		} else {
		return data;
		}
		}
	protected void login(final String usernameString, final String pwdString) {
		// TODO Auto-generated method stub
		this.usernameString=usernameString;
		this.pwdString=pwdString;
		
		pool.execute(t1);
	}
	Thread t1=new Thread() {
		
		

		@SuppressLint("NewApi")
		@Override
		public void run() {
			final JSONObject jsonObj = new JSONObject();
			try {
				jsonObj.put("username", usernameString);
				jsonObj.put("password", pwdString);
				HttpContectionUtil conn = new HttpContectionUtil();
			     myStatus = (MyStatus) getApplication();
				String uri =myStatus.getUrlString() + "login.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				
				JSONObject response = new JSONObject( responseStr);
				
				
				String result =  response.getString("result");
				if ("failed".equals(result)) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(LoginActivity.this, "Login Failed", 0).show();
						}
					});
				} else {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(LoginActivity.this, "Login Success", 0).show();
						}
					});
					
					// 解析并存到全局变量中
				    JSONObject userObj = new JSONObject(responseStr);
					myStatus.setUsername(userObj.getString("username"));
					myStatus.setEmail(userObj
							.getString("useremail"));
					
					
					if(userObj.getString("realname").isEmpty()){
						Intent intent=new Intent();
						intent.setClass(LoginActivity.this, PersonalInfoActivity.class);
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
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					}
					
				}
			} catch (final Exception e) {
				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(LoginActivity.this, "Login Failed"+e.toString(), 0).show();
					}
				});
			}
		}
	};
	
}
