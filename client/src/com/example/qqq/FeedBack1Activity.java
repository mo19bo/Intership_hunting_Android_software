package com.example.qqq;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FeedBack1Activity extends Activity {

	private ActionBar actionBar;
	private TextView ActionTitle;
	private Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back);
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle4);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("反馈意见");
	    	   backBtn=(Button)findViewById(R.id.back);
	    	   backBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	}
}
