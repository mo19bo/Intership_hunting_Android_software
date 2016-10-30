package compsite;

import http.HttpContectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import com.example.qqq.R;


import function.Utility;

import adapter.ListViewAdapter;
import adapter.MyStatus;
import adt.City;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class tips extends Activity{
	protected static final int CITY = 100;
	private List<City> newPersons = new ArrayList<City>();  
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
	private MyStatus myStatus;
	ExecutorService pool = Executors.newFixedThreadPool(2);
	private Button stayhere;
	private Button gowebsite;
	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		
		
		super.onCreate(savedInstanceState);
		myStatus=(MyStatus)getApplication();
		 WindowManager.LayoutParams localLayoutParams =getWindow().getAttributes();
		 localLayoutParams.gravity = Gravity.CENTER_VERTICAL ;
	    localLayoutParams.width=getWindowManager().getDefaultDisplay().getWidth();
	        localLayoutParams.y = 0;
		
	        
	        
	        iniUI1();
		
	
        
       
	}

	private void iniUI1() {
		// TODO Auto-generated method stub
		 setContentView(R.layout.tips_item);  
		  gowebsite=(Button)findViewById(R.id.gowebsite);
		  stayhere=(Button)findViewById(R.id.stayhere);
		  
		  gowebsite.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://www.shixiseng.com");  
				    Intent it = new Intent(Intent.ACTION_VIEW, uri);  
				   startActivity(it);
			}
		});
       stayhere.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
     
}
