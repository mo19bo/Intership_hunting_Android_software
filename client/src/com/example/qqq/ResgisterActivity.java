package com.example.qqq;

import function.Code;
import http.HttpContectionUtil;

import org.json.JSONObject;

import adapter.MyStatus;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResgisterActivity extends Activity {
    private EditText regaccounText,regpwdEditText,verEditText;
    private String email,pwdString;
    private Button regButton;
	MyStatus myStatus;
	Code code;
    private ImageView VerImg;
	private String answerString;
	private ActionBar actionBar;
	private TextView ActionTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resgister);
		
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title1);
	    	   ActionTitle.setText("请先注册");
		iniUI();
	}
	private void iniUI(){
		regaccounText=(EditText)findViewById(R.id.emailinput);
		regpwdEditText=(EditText)findViewById(R.id.pwd_register);
		regButton=(Button)findViewById(R.id.sumbitbtn);
		VerImg=(ImageView)findViewById(R.id.verimg);
		verEditText=(EditText)findViewById(R.id.verification);
		
	      VerImg.setImageBitmap(code.getInstance().createBitmap());
	      answerString= code.getInstance().getCode();
	     VerImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 VerImg.setImageBitmap(code.getInstance().createBitmap());
			      answerString= code.getInstance().getCode();
			}
		});
		regButton.setOnClickListener(new OnClickListener() {
			
			@Override
			   public void onClick(View arg0) {
				   email=regaccounText.getText().toString();
				   pwdString=regpwdEditText.getText().toString();
				   String vercode=verEditText.getText().toString();
				  
				   if(vercode.equalsIgnoreCase(answerString)){
				
					   register(email,pwdString);
				
				   }
				else{
					
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ResgisterActivity.this, "验证码不正确", 0).show();
							
						}
					});
					VerImg.setImageBitmap(code.getInstance().createBitmap());
					 answerString= code.getInstance().getCode();
				}
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
	@SuppressLint("NewApi")
	private void register(final String email, final String pwdString) {
		// TODO Auto-generated method stub
		new Thread() {
			public void run(){
				try{
				final JSONObject jsonObj = new JSONObject();
				jsonObj.put("email", email);
				jsonObj.put("password", pwdString);
				if(ResgisterActivity.this.email.isEmpty()){
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ResgisterActivity.this, "不能为空！！", 0).show();
							
						}
					});
					
				}
				
				else{
				HttpContectionUtil conn = new HttpContectionUtil();
			     myStatus = (MyStatus) getApplication();
				String uri =myStatus.getUrlString() + "register.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				JSONObject response = new JSONObject(removeBOM( responseStr));
				
				String result =  response.getString("result");
				
				if ("exist".equals(result)) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ResgisterActivity.this, "email has already exists", 0).show();
							
						}
					});
				} 
				else if("failed".equals(result)) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ResgisterActivity.this, "Register failed", 0).show();
							
						}
					});
				}
				else{
					
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ResgisterActivity.this, "Register Success", 0).show();
						}
					});
					myStatus.setUser_ID(response.getString("usersid"));
					Intent intent=new Intent(ResgisterActivity.this,LoginActivity.class);
					startActivity(intent);
					ResgisterActivity.this.finish();
				}
				}	
				}
				catch (final Exception e) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(ResgisterActivity.this, "Register Failed"+e.toString(), 0).show();
						}
					});
				}
			}
		
			
		}.start();
	}
}
