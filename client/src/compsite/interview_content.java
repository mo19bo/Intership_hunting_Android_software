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
import adt.DeliverResume;
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

public class interview_content extends Activity{
	protected static final int CITY = 100;
	private List<City> newPersons = new ArrayList<City>();  
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
	private MyStatus myStatus;
	ExecutorService pool = Executors.newFixedThreadPool(2);
	private Button stayhere;
	private Button gowebsite;
	private DeliverResume deliverResume;
	private TextView internview_addtion;
	private TextView internview_tel;
	private TextView internview_person;
	private TextView internview_address;
	private TextView internview_time;
	private TextView interview_theme;
	private String main_theme;
	private LinearLayout finishBtn;
	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		
		
		super.onCreate(savedInstanceState);
		myStatus=(MyStatus)getApplication();
		 WindowManager.LayoutParams localLayoutParams =getWindow().getAttributes();
		 localLayoutParams.gravity = Gravity.CENTER_VERTICAL ;
	    localLayoutParams.width=getWindowManager().getDefaultDisplay().getWidth();
	        localLayoutParams.y = 0;
		
		 setContentView(R.layout.interview_content);  
		 Intent intent = this.getIntent();
		  Bundle bundle=intent.getExtras();
		 if(bundle.getSerializable("deliver_info")!=null){
			 deliverResume=(DeliverResume) bundle.getSerializable("deliver_info");
		 }
		 if(bundle.getSerializable("deliver_info")!=null){
			 main_theme=bundle.getString("main_theme");
		 }
		  iniUI();
		inidata();
      iniclick();
        
       
	}

	private void iniclick() {
		// TODO Auto-generated method stub
		finishBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void inidata() {
		// TODO Auto-generated method stub
		interview_theme.setText(main_theme+"面试");
		internview_time.setText(deliverResume.getInterview_time());
		internview_address.setText(deliverResume.getInterview_place());
		internview_person.setText(deliverResume.getContact());
		internview_tel.setText(deliverResume.getContact_tel());
		internview_addtion.setText(deliverResume.getContent());
	}

	private void iniUI() {
		// TODO Auto-generated method stub
	  interview_theme=(TextView)findViewById(R.id.internview_maintheme);
	  internview_time=(TextView)findViewById(R.id.internview_time);
	  internview_address=(TextView)findViewById(R.id.internview_address);
	  internview_person=(TextView)findViewById(R.id.internview_person);
	  internview_tel=(TextView)findViewById(R.id.internview_tel);
	  internview_addtion=(TextView)findViewById(R.id.internview_addtion);
	  
	  finishBtn=(LinearLayout)findViewById(R.id.fininsh);
	}
	
     
}
