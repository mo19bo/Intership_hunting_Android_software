package com.example.qqq;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Prudtionctivity extends Activity {
    
	private String name;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prudtionctivity);
		Bundle myBundle = this.getIntent().getExtras();
		name=myBundle.getString("Name");
		init();
	}
	public void init(){
		textView=(TextView)findViewById(R.id.com_type);
		textView.setText(name);
	}
	  
	  
}
