package com.example.qqq;

import http.HttpContectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import adapter.DeliverResumeAdapter;
import adapter.MyStatus;
import adt.DeliverResume;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class NotificationInterviewActivity extends Activity {

	private MyStatus mystatus;
	ExecutorService pool = Executors.newFixedThreadPool(3); 
	private ListView list_interView;
  DeliverResumeAdapter adapter;
private String test;
private List<DeliverResume> list_item_interview=new ArrayList<DeliverResume>();
private int postion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_interview);
        mystatus=(MyStatus)getApplication();
        Intent intent = this.getIntent();
        
		iniUI();
		iniData();
		
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		list_interView=(ListView)findViewById(R.id.listView_Interview);
	}

	private void iniData() {
		// TODO Auto-generated method stub
	Thread t1=	  new Thread(){
      		
      		


  			public void run() {
        			try {
        			final JSONObject jsonObj = new JSONObject();
        			jsonObj.put("user_id", mystatus.getUser_ID());
        			jsonObj.put("flag", 4);
        			HttpContectionUtil conn = new HttpContectionUtil();
        			
       				String uri =mystatus.getUrlString() + "select_message.php";
       				String responseStr = conn.ConnForResult(uri,jsonObj);
       				list_item_interview=DeliverResume.parseJsonArray(responseStr);
       				runOnUiThread( new Runnable() {
						public void run() {
							adapter=new DeliverResumeAdapter(NotificationInterviewActivity.this, list_item_interview);
							list_interView.setAdapter(adapter);
							inilist();
						}
					});
       				
       					
        			} catch (Exception e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
      		
      	};

 	     pool.execute(t1);			
						
				
     	     	 
     	     				
      
	
	}

	private void inilist() {
		// TODO Auto-generated method stub
	
		
		
		list_interView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu1, View arg1,
					ContextMenuInfo arg2) {
				// TODO Auto-generated method stub
				menu1.setHeaderTitle("请选择操作");  
				
				
				menu1.add(0, 2, 0, "删除");  
				menu1.add(0, 3, 0, "取消");  
				
			 
			}
		});
		list_interView.setOnItemClickListener(new OnItemClickListener() {
			
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("intern_id", list_item_interview.get(arg2).getIntern_ID());
				bundle.putSerializable("deliver_info", list_item_interview.get(arg2));
				bundle.putString("flag", "1");
				bundle.putInt("tag",list_item_interview.get(arg2).getDelivertag() );
				intent.putExtras(bundle);
				intent.setClass(NotificationInterviewActivity.this, JobInfo_Activity.class);
				startActivity(intent);
			}
		});
		list_interView.setOnItemLongClickListener(new OnItemLongClickListener() {

			

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				postion=arg2;
				  
				
				return false;
			}
			
		});
	}
	public boolean onContextItemSelected(MenuItem item) {  
	      
		 AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		   
		    
		    switch(item.getItemId()) {
		    
		    case 2:
		    	list_item_interview.remove(postion);
		    	adapter.notifyDataSetChanged();	
		    	break;
		    default:
		    return   super.onContextItemSelected(item); 
	        
		    }
		    return  super.onContextItemSelected(item); 
	    }
}
