package com.example.qqq;

import function.DeletOfDatabase;
import http.HttpContectionUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import adapter.CompanyItemsLVAdapter;
import adapter.MyStatus;
import adt.Company;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteCompanyActivity extends Activity {
    private ListView lView;
    private List<Company> list_internitem1;
	private int pos;
	private  CompanyItemsLVAdapter itemsLVAdapter;
	private MyStatus myStatus;
	ExecutorService pool = Executors.newFixedThreadPool(5);
	private ActionBar actionBar;
	private TextView ActionTitle;
	private Button backBtn; 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_company);
		lView=(ListView)findViewById(R.id.listViewCompany);
		  myStatus = (MyStatus) getApplication();
		 	actionBar=this.getActionBar();
			 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
		        actionBar.setCustomView(R.layout.actionbarstyle4);//自定义ActionBar布局 
		        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
		    	   ActionTitle.setText("公司收藏");
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
	public void getlistitem(){
	
		
		
    Thread t1=new Thread() {
		

			


			@Override
			public void run() {
				try{
					final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("id", myStatus.getUser_ID());
    			jsonObj.put("flag", 1);
				String uri =myStatus.getUrlString() + "select_com.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				list_internitem1=Company.parseJsonArray(responseStr,0);
				
				if (list_internitem1 == null)
				{
				
					
					return;
				}
					
				// 设置数据适配器 必须在ui线程
				runOnUiThread(new Runnable(){
					public void run(){
						itemsLVAdapter=new CompanyItemsLVAdapter(FavoriteCompanyActivity.this,list_internitem1);
						lView.setAdapter( itemsLVAdapter);
						inilist();
					}


					
				});
			
				
				
				
				}
				catch (final Exception e) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(FavoriteCompanyActivity.this, " Failed:"+e.toString(), 0).show();
						
						}
					});
				}
			}
		};
	
		
		pool.execute(t1);
		
     
     
		
	}

	private void inilist() {
		// TODO Auto-generated method stub
		 lView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					
					  Toast.makeText(FavoriteCompanyActivity.this, 
		                      "您选择的是" , 
		                      Toast.LENGTH_SHORT).show(); 
		             pos=position;
					return false;
				}
		    	  
		    	  
		    	  
			})  ; 
		      lView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					    Intent intent  = new Intent(FavoriteCompanyActivity.this,CompanyInfoActivity.class);
					    Bundle bundle=new Bundle();
					     bundle.putSerializable("com_info", list_internitem1.get(arg2));
				          intent.putExtras(bundle);
						startActivity(intent);
				}
		    	  
		    	  
			});

			    lView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
		             

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// TODO Auto-generated method stub
						menu.setHeaderTitle("请选择操作");  
						
		                menu.add(0, 0, 0, "取消关注");  
		                
					}  
		        });   
	}
	 public boolean onContextItemSelected(MenuItem item) {  
	      
		 
		 
		 switch (item.getItemId()) {
			case 0:
				 new Thread(){
						
						
						

						public void run() {
			    			try {
			    			final JSONObject jsonObj = new JSONObject();
			    			
			    			jsonObj.put("sid",list_internitem1.get(pos).getCom_id());
			    		    jsonObj.put("user_id", myStatus.getUser_ID());
			    			
			    			HttpContectionUtil conn = new HttpContectionUtil();
			    			
			   				String uri =myStatus.getUrlString() + "cancel_company.php";
			   				String responseStr = conn.ConnForResult(uri,jsonObj);
			   				JSONObject response = new JSONObject(responseStr);
			   				String resultString =  response.getString("result");
							
			   				if("failed".equals(resultString)){
			   			    	runOnUiThread(new Runnable() {
			   						
			   						@Override
			   						public void run() {
			   							// TODO Auto-generated method stub
			   							Toast.makeText(FavoriteCompanyActivity.this,"取消关注失败",Toast.LENGTH_SHORT).show();
			   						}
			   					});
			   			    		
			   			    	}else{
			   			    		list_internitem1.remove(pos);
			   			    		runOnUiThread(new Runnable() {
			   							
			   							@Override
			   							public void run() {
			   								// TODO Auto-generated method stub
			   								Toast.makeText(FavoriteCompanyActivity.this,"取消关注成功", 0).show();
			   								
					   				    	itemsLVAdapter.notifyDataSetChanged();	
			   							}
			   						});
			   			    		
			   			    	}
						
			   			
			   					
			    			} catch (Exception e) {
			    				// TODO Auto-generated catch block
			    				e.printStackTrace();
			    			}
			    		}
			  		
						
						
					}.start();
			       
				break;

			default:
				break;
			}
		  
	    
	 return super.onContextItemSelected(item);  
	 }
}
