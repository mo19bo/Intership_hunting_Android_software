package com.example.qqq;

import java.util.Timer;
import java.util.TimerTask;

import android.support.v4.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {

	private RadioGroup rGroup;
    private ActionBar actionBar;
	private TextView actiontitle;
	private Button actionbatBtn;
	
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page3);
		mainpage chat = new mainpage();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle5);//自定义ActionBar布局 
	        actiontitle=(TextView)findViewById(R.id.actionbar_title);
	        actiontitle.setText("实习虾");
	        actionbatBtn=(Button)findViewById(R.id.ad_buttton);
	        actionbatBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent  intent=new Intent();
				intent.setClass(MainActivity.this, AdvertisementActivity.class);
				startActivity(intent);
				}
			});
	        
	        
	        
	        
	        
	        
		getSupportFragmentManager().beginTransaction().replace(R.id.frame, chat).commit();
	//   
		
		rGroup=(RadioGroup)findViewById(R.id.radioGroup1);
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				
				switch (arg1) {
				case R.id.radio0:
					mainpage chat = new mainpage();
					getSupportFragmentManager().beginTransaction().replace(R.id.frame, chat).commit();
					break;
				case R.id.radio1:
					Notificationfragment chat2= new Notificationfragment();
					getSupportFragmentManager().beginTransaction().replace(R.id.frame, chat2).commit();
					break;
				case R.id.radio2:
					FindpageFragment chat3 = new FindpageFragment();
					getSupportFragmentManager().beginTransaction().replace(R.id.frame, chat3).commit();
					break;
				case R.id.radio3:
					PersonalCenterFragment chat4 = new PersonalCenterFragment();
					getSupportFragmentManager().beginTransaction().replace(R.id.frame, chat4).commit();
					break;
				default:
					break;
				}
				
			}
		});
			
			
			
			
	
	/*	   */
	}
	private long mExitTime;
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                        Object mHelperUtils;
                        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        mExitTime = System.currentTimeMillis();

                } else {
                        finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




}
