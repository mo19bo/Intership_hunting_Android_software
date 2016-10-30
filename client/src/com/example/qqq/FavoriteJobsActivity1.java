package com.example.qqq;

import function.DeletOfDatabase;
import http.HttpContectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;


import adapter.InternItemsLVAdapter;
import adapter.MyStatus;

import adt.Intern;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;


public class FavoriteJobsActivity1 extends Activity {
	private ListView lv1;
	private List<Intern> list_internitem1=new ArrayList<Intern>();
	private MyStatus myStatus;
    

	private InternItemsLVAdapter itemsLVAdapter;
	protected int postion;
	private ActionBar actionBar;
	private TextView ActionTitle;
	private Button backBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_favorite_jobs);
		  myStatus = (MyStatus) getApplication();
		lv1=(ListView)findViewById(R.id.listView_favor_job);
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle4);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("职位收藏");
	    	   backBtn=(Button)findViewById(R.id.back);
	    	   backBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		getlistitem();
		
	}
	private void getlistitem() {
		// TODO Auto-generated method stub
		
	      new Thread() {
		

			
             
			@Override
			public void run() {
				try{
				final	 JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("id", myStatus.getUser_ID());
    		
    			
				String uri =myStatus.getUrlString() + "select_intern_favorjob.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
			
					list_internitem1=Intern.parseJsonArray2(responseStr);
				  if (list_internitem1 == null)
					return;
				// 设置数据适配器 必须在ui线程
				runOnUiThread(new Runnable(){
					public void run(){
						
						lv1.setAdapter( new InternItemsLVAdapter(FavoriteJobsActivity1.this,list_internitem1));
						inilist();
					}

					
				});
			
				
				
				
				}
				catch (final Exception e) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(FavoriteJobsActivity1.this, " Failed:"+e.toString(), 0).show();
						
						}
					});
				}
			}
		}.start();
	
		
		
		
	}
	private void inilist() {
		// TODO Auto-generated method stub
		
		

		lv1.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu1, View arg1,
					ContextMenuInfo arg2) {
				// TODO Auto-generated method stub
				menu1.setHeaderTitle("请选择操作");  
				
				
				menu1.add(0, 2, 0, "取消关注");  
				menu1.add(0, 3, 0, "取消");  
				
			 
			}
		});
		lv1.setOnItemClickListener(new OnItemClickListener() {
			
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				
				Bundle bundle=new Bundle();
				bundle.putSerializable("job_info", list_internitem1.get(arg2));
				bundle.putString("flag", "0");
				intent.putExtras(bundle);
				intent.setClass(FavoriteJobsActivity1.this, JobInfo_Activity.class);
				startActivity(intent);
			}
		});
		lv1.setOnItemLongClickListener(new OnItemLongClickListener() {

			

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
	      
		 switch(item.getItemId()) {
		    
		    case 2:
		   	 new Thread(){
					
					
					

					public void run() {
		    			try {
		    			final JSONObject jsonObj = new JSONObject();
		    			
		    			jsonObj.put("sid",list_internitem1.get(postion).getIntern_idString());
		    		    jsonObj.put("user_id", myStatus.getUser_ID());
		    			
		    			HttpContectionUtil conn = new HttpContectionUtil();
		    			
		   				String uri =myStatus.getUrlString() + "cancel_job.php";
		   				String responseStr = conn.ConnForResult(uri,jsonObj);
		   				JSONObject response = new JSONObject(responseStr);
		   				String resultString =  response.getString("result");
						
		   				if("failed".equals(resultString)){
		   			    	runOnUiThread(new Runnable() {
		   						
		   						@Override
		   						public void run() {
		   							// TODO Auto-generated method stub
		   							Toast.makeText(FavoriteJobsActivity1.this,"取消关注失败",Toast.LENGTH_SHORT).show();
		   						}
		   					});
		   			    		
		   			    	}else{
		   			    		runOnUiThread(new Runnable() {
		   							
		   							@Override
		   							public void run() {
		   								// TODO Auto-generated method stub
		   								Toast.makeText(FavoriteJobsActivity1.this,"取消关注成功", 0).show();
		   							}
		   						});
		   			    		list_internitem1.remove(postion);
		   				    	itemsLVAdapter.notifyDataSetChanged();	
		   			    	}
					
		   			
		   					
		    			} catch (Exception e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}
		    		}
		  		
					
					
				}.start();
		    	
		    	
		    	
		    	
		    	break;
		    default:
		    return   super.onContextItemSelected(item); 
	        
		    }
		    return  super.onContextItemSelected(item); 
	    }
	
	
}
