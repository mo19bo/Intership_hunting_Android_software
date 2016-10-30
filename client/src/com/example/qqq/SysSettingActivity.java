package com.example.qqq;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SysSettingActivity extends Activity {
   
	private Button newsreminds;
	private Button noborther;
	private Button account_setting;
	private Button aboutbtn;
	private Button check;
	private Button logout;
	private ActionBar actionBar;
	private TextView ActionTitle;
	private Button backBtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sys_setting);
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle4);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("系统设置");
	    	   backBtn=(Button)findViewById(R.id.back);
	    	   backBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		iniUI();
		
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		newsreminds=(Button)findViewById(R.id.news_notification);
		noborther=(Button)findViewById(R.id.no_borther);
		account_setting=(Button)findViewById(R.id.account_setting);
		aboutbtn=(Button)findViewById(R.id.about);
		check=(Button)findViewById(R.id.check_update);
		logout=(Button)findViewById(R.id.log_out);
		
		newsreminds.setOnClickListener(new OnClick());
		noborther.setOnClickListener(new OnClick());
		account_setting.setOnClickListener(new OnClick());
		aboutbtn.setOnClickListener(new OnClick());
           check.setOnClickListener(new OnClick());
           logout.setOnClickListener(new OnClick());
}

     public class OnClick implements OnClickListener {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()){
				case R.id.news_notification:
					Intent intent = new Intent();  
					   intent.setClass(SysSettingActivity.this, NewsReminsActivity.class);
						startActivity(intent); 
					
					break;
				case R.id.no_borther:
					Intent intent2 = new Intent();  
					   intent2.setClass(SysSettingActivity.this, NewsReminsActivity.class);
						startActivity(intent2); 
					break;
				case R.id.account_setting:
					Intent intent3 = new Intent();  
					   intent3.setClass(SysSettingActivity.this,AccountSettingActivity.class);
						startActivity(intent3); 
					break;
				case R.id.about:
					Intent intent4 = new Intent();  
					   intent4.setClass(SysSettingActivity.this, AboutActivity.class);
						startActivity(intent4); 
					break;
				case R.id.check_update:
				check_update();
					break;
				case R.id.log_out:
					log_out();
					break;
					
				
				
				
				}
			}
	
	
	
         }

	public void log_out() {
		// TODO Auto-generated method stub
		    SharedPreferences settings = getSharedPreferences("userinfo", 0);
	        SharedPreferences.Editor editor = settings.edit();
	        editor.putString("userid", "");
	        editor.putString("is_auth", "false");
	        editor.commit();
	        runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(SysSettingActivity.this, "清除成功", 0);
				}
			});
	       
	        Intent intent=new Intent(SysSettingActivity.this,LoginActivity.class);
			startActivity(intent);
		    SysSettingActivity.this.finish();
		    
	        
	        
	}

	public void check_update() {
		// TODO Auto-generated method stub
		
	}

}