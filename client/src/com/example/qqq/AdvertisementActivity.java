package com.example.qqq;





import http.HttpContectionUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.makeramen.roundedimageview.*;

import adapter.ImageAdapter;
import adapter.InternItemsLVAdapter;
import adapter.MyStatus;
import adt.advertisement;
import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AdvertisementActivity extends Activity {
	 ExecutorService pool = Executors.newFixedThreadPool(3);
    private MyStatus myStatus;
	private Gallery  g ;
	
	
	private List<advertisement> adlist;
	private ImageButton close;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          requestWindowFeature(Window.FEATURE_NO_TITLE);  
		
		
		super.onCreate(savedInstanceState);
		myStatus=(MyStatus)getApplication();
		 WindowManager.LayoutParams localLayoutParams =getWindow().getAttributes();
		 localLayoutParams.gravity = Gravity.CENTER_VERTICAL ;
	    localLayoutParams.width=getWindowManager().getDefaultDisplay().getWidth();
	        localLayoutParams.y = 0;
		
		 setContentView(R.layout.activity_advertisement);  
        pool.execute(t1);
		iniUI();
		
    }
	private void iniUI() {
		// TODO Auto-generated method stub
		
	       g= (Gallery) findViewById(R.id.gallery1);   
          close=(ImageButton)findViewById(R.id.imageButton1_close);
          close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
           }  
       Thread t1=new Thread(){
	   public void run() {
			final JSONObject jsonObj = new JSONObject();
			try {
				
				
				HttpContectionUtil conn = new HttpContectionUtil();
			     myStatus = (MyStatus) getApplication();
				String uri =myStatus.getUrlString() + "advertisement.php";
				String responseStr = conn.ConnForResult(uri, "");
				
				
				adlist=advertisement.parseJsonArray(responseStr);
				
				if (adlist == null){
					
				   runOnUiThread(new Runnable(){
					public void run(){
				 
						
						
						Toast.makeText(AdvertisementActivity.this, "没有相关数据", 0).show();
					}

					
				});
				
					
					return;
				}
				// 设置数据适配器 必须在ui线程
				runOnUiThread(new Runnable(){
					public void run(){
						
						iniadpter();
					
					}

					

					
				});
			}
			catch (final Exception e) {
				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(AdvertisementActivity.this, "Failed"+e.toString(), 0).show();
					}
				});
			}
		
	
	};
	   
	   
	   
	   
	   
   };
   private void iniadpter() {
		// TODO Auto-generated method stub
       //设置图片匹配器   
       g.setAdapter(new ImageAdapter(AdvertisementActivity.this,adlist));        
       //设置AdapterView点击监听器，Gallery是AdapterView的子类   
       g.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}
	
       
       
       });
       
	        
 




    }
}
