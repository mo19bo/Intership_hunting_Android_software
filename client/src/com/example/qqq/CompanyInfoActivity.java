package com.example.qqq;

import http.HttpContectionUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import function.Utils;
import function.Utils.OnLoadImageListener;
import adapter.InternItemsLVAdapter;
import adapter.MyStatus;
import adt.Company;
import adt.Intern;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CompanyInfoActivity extends Activity {
     
	private ImageView com_logoImageView;
	private TextView ComNama,ComLocation,ComType,ComScale,ComUrl;
	private ListView listJobsListView;
	private Company company;
	private String com_id;
	private List<Intern> list_internitem1;
	ExecutorService pool = Executors.newFixedThreadPool(3); 
	private InternItemsLVAdapter itemsLVAdapter;
	private MyStatus myStatus;
	private ActionBar actionBar;
	private TextView ActionTitle;
	private Button favor_comBtm;
	private ImageButton homeBtn;
	private Button backBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company_info);
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2_com);//自定义ActionBar布局 
	        //actionBar.setDisplayHomeAsUpEnabled(true); 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("公司详情");
		myStatus=(MyStatus)getApplication();
		iniUI();
		  Intent intent = this.getIntent();
		  Bundle bundle=intent.getExtras();
		  company=(Company) bundle.getSerializable("com_info");
		  com_id=company.getCom_id();
		 
		iniDatainfo();
		inidatalist();
		
	}
	
	private void inidatalist() {
		// TODO Auto-generated method stub
         Thread t1=new Thread() {
		

			

			

			@Override
			public void run() {
				try{
					final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("id",com_id);
				
    			
				String uri =myStatus.getUrlString() + "select_intern_company.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				list_internitem1=Intern.parseJsonArray(responseStr);
				
				if (list_internitem1 == null)
					return;
				// 设置数据适配器 必须在ui线程
				runOnUiThread(new Runnable(){
				

					public void run(){
						
						listJobsListView.setAdapter( new InternItemsLVAdapter(CompanyInfoActivity.this,list_internitem1));
						inilist();
					}

					

					
				});
			
				
				
				
				}
				catch (final Exception e) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(CompanyInfoActivity.this, " Failed:"+e.toString(), 0).show();
						
						}
					});
				}
			}
		};
	
		
		pool.execute(t1);
	}
	private void iniDatainfo() {
		// TODO Auto-generated method stub
		String urlString=company.getLogo_url();
		
		 Transformation transformation = new RoundedTransformationBuilder()
         .borderColor(Color.WHITE)
         .borderWidthDp((float)1)
         .cornerRadiusDp(10)
         .oval(false)
         .build();
		 Picasso.with(CompanyInfoActivity.this)
 
		 .load(urlString)
 
		 .transform(transformation)
 
		 .placeholder(R.drawable.holderpalcer)

		 .error(R.drawable.holderpalcer)
 
		 .into(com_logoImageView);

		
		ComNama.setText(company.getCom_nameString());
		ComLocation.setText(company.getCom_place());
		
		ComType.setText(company.getCom_industry());
		
		ComScale.setText(company.getCom_scale());
		
		ComUrl.setText(company.getCom_home_page());
		
		
		
		
		
		
		
	}
	public  void iniUI(){
		//顶部actionbar布局初始化
	    backBtn=(Button)findViewById(R.id.back);
	    homeBtn=(ImageButton)findViewById(R.id.homeBtn);
	    
		//主要布局控件初始化
		com_logoImageView=(ImageView)findViewById(R.id.com_logo);
		ComNama=(TextView)findViewById(R.id.com_name);
		ComLocation=(TextView)findViewById(R.id.com_location);
		ComType=(TextView)findViewById(R.id.com_type);
		ComScale=(TextView)findViewById(R.id.com_scale);
		ComUrl=(TextView)findViewById(R.id.com_url);	
		favor_comBtm=(Button)findViewById(R.id.favor_com);
	    listJobsListView=(ListView)findViewById(R.id.listViewjob);
		//监听事件回调函数
		favor_comBtm.setOnClickListener( new onclick());
		backBtn.setOnClickListener( new onclick());
		homeBtn.setOnClickListener( new onclick());
	}
	public class onclick implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			switch (arg0.getId()) {
			case R.id.favor_com:
				 favor_com();
				 
				break;
			case R.id.back:
				finish();
				break;
			case R.id.homeBtn:
				Intent intent=new Intent(CompanyInfoActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				
				
			   break;
			default:
				
				
				break;
			}
		}
		
		
		
	}
	private void inilist() {
		// TODO Auto-generated method stub

		listJobsListView.setOnItemClickListener(new OnItemClickListener() {
			
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("flag", "0");
				bundle.putSerializable("job_info", list_internitem1.get(arg2));
				intent.putExtras(bundle);
				intent.setClass(CompanyInfoActivity.this, JobInfo_Activity.class);
				startActivity(intent);
			}
		});
	}

	public void favor_com() {
		// TODO Auto-generated method stub
		 Thread t3=new Thread(){
				
				
				

				public void run() {
	    			try {
	    			final JSONObject jsonObj = new JSONObject();
	    			jsonObj.put("user_id", myStatus.getUser_ID());
	    			jsonObj.put("sid",company.getCom_id());
	    			
	    			
	    			HttpContectionUtil conn = new HttpContectionUtil();
	    			
	   				String uri =myStatus.getUrlString() + "favor_com.php";
	   				String responseStr = conn.ConnForResult(uri,jsonObj);
	   				JSONObject response = new JSONObject(responseStr);
	                   String result =  response.getString("result");
					
					 if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(CompanyInfoActivity.this, " 收藏失败", 0).show();
								
							}
						});
					}

	                   else if("exists".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(CompanyInfoActivity.this, "已经收藏了", 0).show();
								
								
							}
						});
					}
						else{
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(CompanyInfoActivity.this, "收藏成功", 0).show();
									
									
								}
							});
							
						}
	   			
	   					
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		}
	  		
				
				
			};
			
			pool.execute(t3);
	}
	
	
	
	
	
	
	
	
	
}
