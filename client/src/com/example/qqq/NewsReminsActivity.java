package com.example.qqq;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsReminsActivity extends Activity {

	private ActionBar actionBar;
	private TextView ActionTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_remins);
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title1);
	    	   ActionTitle.setText("新消息提醒");
	}
}
