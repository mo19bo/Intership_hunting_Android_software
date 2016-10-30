package com.example.qqq;

import javax.security.auth.PrivateCredentialPermission;

import compsite.MyListView;

import adapter.MyAdapter;
import adapter.SubAdapter;
import adapter.ThirdAdapter;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class JobSelectActivity extends Activity {
	 private MyListView listView;   
	    private MyListView subListView; 
	    private MyListView thirdListView; 
	    private MyAdapter myAdapter;  
	    private SubAdapter subAdapter;  
	    private ThirdAdapter thirdAdapter;  
	    private Intent intent=new Intent();
		private int JOB=200;
		protected int locationx;
	       
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.job_select);
		
		 WindowManager.LayoutParams localLayoutParams =getWindow().getAttributes();
		 localLayoutParams.gravity = Gravity.BOTTOM ;
	    localLayoutParams.width=getWindowManager().getDefaultDisplay().getWidth();
	        localLayoutParams.y = 0;
	        init();  
	        myAdapter=new MyAdapter(getApplicationContext());  
	        listView.setAdapter(myAdapter);  
	         
	        selectDefult();  
	          
	        listView.setOnItemClickListener(new OnItemClickListener() {  
	  
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1,  int position,  
	                    long arg3) {  
	                // TODO Auto-generated method stub  
	                 locationx=position;  
	                myAdapter.setSelectedPosition(locationx);  
	                myAdapter.notifyDataSetInvalidated();  
	                subAdapter=new SubAdapter(getApplicationContext(), locationx);  
	                subListView.setAdapter(subAdapter);  
	                subListView.setOnItemClickListener(new OnItemClickListener() {
                    
	                	@Override  
	                    public void onItemClick(AdapterView<?> arg0, View arg1,  
	                            int position2, long arg3) {  
	                		subAdapter.setSelectedPosition(position2); 
	                		subAdapter.notifyDataSetInvalidated();
	                		
	                		 intent.putExtra("result1",subAdapter.getItem(position2).toString() );
	                		 
	                		 
	                		thirdAdapter=new ThirdAdapter(getApplicationContext(),locationx,position2);
	                		thirdListView.setAdapter(thirdAdapter);
	                		thirdListView.setOnItemClickListener(new OnItemClickListener() {
								@Override
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int arg2, long arg3) {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),thirdAdapter.getItem(arg2).toString(), Toast.LENGTH_SHORT).show();
									   
						               intent.putExtra("result",thirdAdapter.getItem(arg2).toString() );// 放入返回值 
						               setResult(JOB, intent);// 
						               finish();
									
								}
	                			
	                			
	                			
	                			
							});
	                    } 
	  
	                      
	                });  
	            }  
	        });  
	}

	private void init() {
		// TODO Auto-generated method stub
		listView=(MyListView) findViewById(R.id.listView);  
	       subListView=(MyListView) findViewById(R.id.subListView); 
	       thirdListView=(MyListView)findViewById(R.id.thirdListView);
	}

	private void selectDefult() {
		// TODO Auto-generated method stub
		final int location=0;  
        myAdapter.setSelectedPosition(0);  
       myAdapter.notifyDataSetInvalidated();  
       
  
		
	}
}
