package com.example.qqq;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import function.Tocircle;
import function.Utils;
import function.Utils.OnLoadImageListener;
import http.HttpContectionUtil;

import org.json.JSONObject;

import com.example.qqq.R.layout;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import compsite.CustomProgressDialog;
import compsite.interview_content;
import compsite.tips;

import adapter.DeliverResumeAdapter;
import adapter.InternItemsLVAdapter;
import adapter.MyStatus;
import adt.Company;
import adt.DeliverResume;
import adt.Intern;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JobInfo_Activity extends Activity {
    private TextView company_name;
  
	private Button Delivery,Favorite,shareButton;
    private String intern_idString;
    private MyStatus mystatus;
	private TextView intern_name,intern_salary,com_name,intern_city,intern_workdayweeks,intern_pubdate,job_attraction,job_description,intern_place;
	private ImageView com_logo;
	private Intern intern;
	private String com_id;
	private Company company;
	private DeliverResume deliverResume;
  private String intern_id;
	private List<Company> list_internitem1;
	private List<Intern> list_internitem;
	ExecutorService pool = Executors.newFixedThreadPool(3);
	private Button send_btn;
	private Button Favor;
	private Button shareBtn;
	private ActionBar actionBar;
	private TextView ActionTitle;

	private Button backBtn;

	public CustomProgressDialog customProgressDialog; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 	setContentView(R.layout.job_info_fragment);
	 	actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("职位详情");
	    	   customProgressDialog=new CustomProgressDialog(JobInfo_Activity.this);   
	 	 mystatus=(MyStatus)getApplication();
	 	 iniUI();
	 	  Intent intent = this.getIntent();
		  Bundle bundle=intent.getExtras();
		  if(bundle.getString("flag").equals("1")){
			 intern_id= bundle.getString("intern_id");
			 if(bundle.getSerializable("deliver_info")!=null){
				 deliverResume=(DeliverResume) bundle.getSerializable("deliver_info");
			 }
			  inidata1();
			  
			  send_btn.setText(converttolabel(bundle.getInt("tag")));
			  iniclick(bundle.getInt("tag"));
			
			  
			  
		  }else{
			  intern=(Intern) bundle.getSerializable("job_info");
				 
			  inidata_intern();
			  
		      getData();
			  
		  }
		  
		 
	}
	private void iniclick(int i) {
		// TODO Auto-generated method stub
		  switch (i) {
		
             case 4 ://分享操作
            	 send_btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent5=new Intent();
						Bundle bundle=new Bundle();
						bundle.putString("main_theme", list_internitem.get(0).getJob());
						bundle.putSerializable("deliver_info", deliverResume);
						intent5.putExtras(bundle);
						intent5.setClass(JobInfo_Activity.this, interview_content.class);
						startActivity(intent5);
					}
				});
				break;
          
			default:
				 send_btn.setClickable(false);
				break;
			}
	}
	private void inidata1() {
		// TODO Auto-generated method stub
		new Thread() {
			

			@Override
			public void run() {
				try{
					final JSONObject jsonObj = new JSONObject();
					HttpContectionUtil conn = new HttpContectionUtil();
					jsonObj.put("id",intern_id);
					
				String uri =mystatus.getUrlString() + "select_intern_message.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				list_internitem=Intern.parseJsonArray(responseStr);
				
				if (list_internitem == null)
					return;
				// 设置数据适配器 必须在ui线程
				runOnUiThread(new Runnable(){
					public void run(){
						intern=list_internitem.get(0);
						com_id=intern.getCom_idString();
						
						 inidata_intern();
						 getData();
					}

					
				});
				
				
				
				
				}
				catch (final Exception e) {
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(JobInfo_Activity.this, "Login Failed"+e.toString(), 0).show();
						}
					});
				}
			}
		}.start();
	}
	private void inidata_intern() {
		// TODO Auto-generated method stub
		
		
		String img_urlString="";
		
				img_urlString=intern.getCom_logo_url();
				 Transformation transformation = new RoundedTransformationBuilder()
		          .borderColor(Color.WHITE)
		          .borderWidthDp((float)1)
		          .cornerRadiusDp(10)
		          .oval(false)
		          .build();
		Picasso.with(JobInfo_Activity.this)
	      .load(img_urlString)
	      .transform(transformation)
	      .placeholder(R.drawable.holderpalcer)
	      .error(R.drawable.holderpalcer)
	      .into(com_logo);
		
		
		intern_id=intern.getIntern_idString();
		intern_name.setText(intern.getJob());
		com_logo=(ImageView)findViewById(R.id.com_logo);
		com_name.setText(intern.getCom_nameString());
		intern_salary.setText(intern.getSalaryString());
		
		intern_city.setText(intern.getCityString());
		intern_workdayweeks.setText(intern.getDayweek());
		intern_pubdate.setText(intern.getPub_dateString());
		job_attraction.setText(intern.getJobattractionString());
		job_description.setText(intern.getDescription_job());
		intern_place.setText(intern.getWork_addressString());
	
	}
	private void getData() {
		// TODO Auto-generated method stub
		com_id=intern.getCom_idString();
		Thread t1=new Thread(){
			
			
			

			public void run() {
    			try {
    			final JSONObject jsonObj = new JSONObject();
    			jsonObj.put("id", com_id);
    			jsonObj.put("flag",2);
    			HttpContectionUtil conn = new HttpContectionUtil();
    			
   				String uri =mystatus.getUrlString() + "select_com.php";
   				String responseStr = conn.ConnForResult(uri,jsonObj);
   				list_internitem1=Company.parseJsonArray(responseStr,2);
   			
   				
   					
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
  		
			
			
		};
		pool.execute(t1);
	}
	
	public void iniUI(){
		//实习相关信息的初始化
	
		intern_name=(TextView)findViewById(R.id.InternName);
		com_logo=(ImageView)findViewById(R.id.com_logo);
		com_name=(TextView)findViewById(R.id.companyName);
		intern_salary=(TextView)findViewById(R.id.textView6_salary);
		intern_city=(TextView)findViewById(R.id.workplace);
		intern_workdayweeks=(TextView)findViewById(R.id.work_dayweeks);
		intern_pubdate=(TextView)findViewById(R.id.pubdate_job);
		job_attraction=(TextView)findViewById(R.id.attraction);
		job_description=(TextView)findViewById(R.id.job_description);
		intern_place=(TextView)findViewById(R.id.work_address);
		
		//公司相关信息的初始化
		
		
		//底部按钮初始化
		send_btn=(Button)findViewById(R.id.send_resume);
		Favor=(Button)findViewById(R.id.favorite_jobs);
		//顶部按钮初始化
		shareBtn=(Button)findViewById(R.id.save_button1);
		shareBtn.setText("分享");
	    backBtn=(Button)findViewById(R.id.back);
	    
	    backBtn.setOnClickListener(new btnclick());
		com_logo.setOnClickListener(new btnclick());
		send_btn.setOnClickListener(new btnclick());
		Favor.setOnClickListener(new btnclick());
		shareBtn.setOnClickListener(new btnclick());
	}
	private class btnclick implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()){
			case R.id.com_logo:  
				  Intent intent = new Intent();
				  Bundle bundle=new Bundle();
				  if(list_internitem1==null) return;
				  bundle.putSerializable("com_info", list_internitem1.get(0));
				  intent.putExtras(bundle);
				   intent.setClass(JobInfo_Activity.this,CompanyInfoActivity.class);
					startActivity(intent); 
				
                break;  
			case R.id.send_resume:
				 customProgressDialog.show();
				send_resume();
				break;
			case R.id.favorite_jobs:
				 customProgressDialog.show();
				favor();
				break;
			case R.id.save_button1:
				share();
				break;
			case R.id.back:
				finish();
				break;
			
			
			
			
			
			}	
		}

	}
	private String converttolabel(int delivertag) {
		// TODO Auto-generated method stub
		String result = null;
		switch (delivertag) {
		case 1:
		    result="投递成功";
		    send_btn.setClickable(false);
			break;
		case 2:
			result="被查看";
			send_btn.setClickable(false);
			break;
         
		
		case 4:
			result="查看面试";
			
			break;
		case 5:
			result="不合适";
			send_btn.setClickable(false);
			break;
		
		default:
			result="投递成功";
			break;
		}
		
		return result;
	}
	public void share() {
		// TODO Auto-generated method stub
		  String imgPath="/qqq/assets/icon.png";
		  Intent intent=new Intent(Intent.ACTION_SEND);   
          intent.setType("image/*");   
          intent.putExtra(Intent.EXTRA_SUBJECT, "Share");   
          intent.putExtra(Intent.EXTRA_TITLE, "请选择分享");   
          intent.putExtra(Intent.EXTRA_TEXT, "http://www.shixiseng.com/intern/"+intern_id+".html"+getResources().getString(R.string.share_content_tag).toString()); 
          if (imgPath == null || imgPath.equals("")) {
              intent.setType("text/plain"); // 纯文本
          } else {
              File f = new File(imgPath);
              if (f != null && f.exists() && f.isFile()) {
                  intent.setType("image/*");
                  Uri u = Uri.fromFile(f);
                  intent.putExtra(Intent.EXTRA_STREAM, u);
              }
          }
        
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
          startActivity(Intent.createChooser(intent, getTitle()));   
	}
	public void favor() {
		// TODO Auto-generated method stub
       Thread t3=new Thread(){
			
			
			

			public void run() {
    			try {
    			final JSONObject jsonObj = new JSONObject();
    			jsonObj.put("user_id", mystatus.getUser_ID());
    			jsonObj.put("sid",intern.getIntern_idString());
    			
    			
    			HttpContectionUtil conn = new HttpContectionUtil();
    			
   				String uri =mystatus.getUrlString() + "favor_job.php";
   				String responseStr = conn.ConnForResult(uri,jsonObj);
   				JSONObject response = new JSONObject(responseStr);
                   String result =  response.getString("result");
				
				
                  
				 if("exists".equals(result)){
					
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(JobInfo_Activity.this, "已经收藏了", 0).show();
							handler.sendEmptyMessage(0);
							
						}
					});
				}
				 else if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(JobInfo_Activity.this, " 收藏失败", 0).show();
								handler.sendEmptyMessage(0);
							}
						});
					}
					else{
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(JobInfo_Activity.this, "收藏成功", 0).show();
								
								handler.sendEmptyMessage(0);
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
	public void send_resume() {
		// TODO Auto-generated method stub
		 Thread t4=new Thread(){
				
				
				

				public void run() {
	    			try {
	    			final JSONObject jsonObj = new JSONObject();
	    			jsonObj.put("useremail", mystatus.getEmail());
	    			jsonObj.put("deliver", intern.getDeliver_email());
	    			jsonObj.put("user_id", mystatus.getUser_ID());
	    			jsonObj.put("resume_id", mystatus.getUserResume_id());
	    			jsonObj.put("user_name", mystatus.getRealname());
	    			jsonObj.put("intern_id", intern.getIntern_idString());
	    			jsonObj.put("com_id", intern.getCom_idString());
	    			jsonObj.put("com_name", intern.getCom_nameString());
	    			
	    		
	    			
	    			
	    			HttpContectionUtil conn = new HttpContectionUtil();
	    			
	   				String uri =mystatus.getUrlString() + "deliver_resume.php";
	   				String responseStr = conn.ConnForResult(uri,jsonObj);
	   				JSONObject response = new JSONObject(responseStr);
	                   String result =  response.getString("result");
					
					
					if("exists".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								handler.sendEmptyMessage(0);
								Toast.makeText(JobInfo_Activity.this, "该职位只能投递一次，你已经投递过了", 0).show();
								
							}
						});
					}
						else  if("failed".equals(result)) {
							runOnUiThread(new Runnable(){
								public void run(){
									handler.sendEmptyMessage(0);
									Toast.makeText(JobInfo_Activity.this, "发送失败 ", 0).show();
									
									
								}
							});
							
						}else{
							runOnUiThread(new Runnable(){
								public void run(){
									Toast.makeText(JobInfo_Activity.this, "发送成功 ", 0).show();
									handler.sendEmptyMessage(0);
									
								}
							});
						}
	   			
	   					
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		}
	  		
				
				
			};
			
			pool.execute(t4);
			
	}
	 Handler handler = new Handler() {  
	        @Override  
	        public void handleMessage(Message msg) {// handler接收到消息后就会执行此方法  
	            customProgressDialog.dismiss();// 关闭ProgressDialog  
	        }  
	    };
}
