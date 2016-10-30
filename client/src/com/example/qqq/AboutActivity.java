package com.example.qqq;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {

	private Button rule_of_serveBtn;
	private Button suggestionsBtn;
	private Button clear_cacheBtn;
	private ActionBar actionBar;
	private TextView ActionTitle;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		actionBar=this.getActionBar();
		
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title1);
	    	   ActionTitle.setText("关于"+this.context.getResources().getString(R.string.app_name));
	   
		iniUI();
	}

	private void iniUI() {
		// TODO Auto-generated method s
		rule_of_serveBtn=(Button)findViewById(R.id.rule_of_serve);
		suggestionsBtn=(Button)findViewById(R.id.suggestions);
		clear_cacheBtn=(Button)findViewById(R.id.clear_cache);
		
		rule_of_serveBtn.setOnClickListener(new Onclick());
		suggestionsBtn.setOnClickListener(new Onclick());
		clear_cacheBtn.setOnClickListener(new Onclick());
		
		


	}

      public class Onclick implements OnClickListener {

	@Override
	     public void onClick(View v) {
		   switch (v.getId()){
		   case R.id.rule_of_serve:
			   Intent intent1 = new Intent();  
			   intent1.setClass(AboutActivity.this, ServerRuleActivity.class);
				startActivity(intent1); 
			   break;
		   case R.id.suggestions:
			   Intent intent2= new Intent();  
			   intent2.setClass(AboutActivity.this, FeedBack1Activity.class);
				startActivity(intent2); 
			   break;
		   case R.id.check_update:
			   Intent intent3 = new Intent();  
			   intent3.setClass(AboutActivity.this, DeliverRecordActivity.class);
				startActivity(intent3);
				break;
		   default:
				   break;
		  
		  }
	   }


	
}
}
