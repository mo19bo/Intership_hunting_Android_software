package com.example.qqq;

import http.HttpContectionUtil;

import java.io.ObjectOutputStream.PutField;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import com.example.qqq.PersonalInfoActivity.OnClick;

import compsite.CustomProgressDialog;
import compsite.Dialog;
import compsite.tips;

import function.Tocircle;
import function.Utility;
import function.Utils;
import function.Utils.OnLoadImageListener;

import adapter.InternItemsLVAdapter;
import adapter.InternshipAdapter;
import adapter.MyStatus;
import adapter.ProjectAdapter;
import adapter.SchoolAdapter;
import adapter.listresume_adpater;

import adt.Eduexperience;
import adt.Intern;
import adt.Internship;
import adt.Project;
import adt.Resume;
import adt.Schoolexp;

import android.R.integer;
import android.R.string;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pools.Pool;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ResumeModifyActivity extends Activity {
    
	
	private static final int PROJECT = 7;
	private TextView topuseremail;
	private TextView topusertel;
    private int EDU=1;
    private int INTERN=3;
    private int SCHOOL=5;
	private String date_length;
    
	private TextView toprealname;
	private TextView topeducation;
	private ImageView img_avator;
	private Button personalinfo_motified_btn;
	private MyStatus mystatus;
	private TextView topusersexandyear;
	private TextView excepted_intern;

	private TextView internships;
	private TextView school_experience;
	private Button moreBtn;
	private TextView add_experience_education;
	private TextView add_internships;
	private TextView add_school_experience;
	private LinearLayout edulayout;
	private ListView listeduex;
	private listresume_adpater adapter;
	private InternshipAdapter adapter1;
    private List<Eduexperience> list=new ArrayList<Eduexperience>();
    private List<Internship> list1=new ArrayList<Internship>();
	private ListView listintern;
	private ListView listproject;
	private SchoolAdapter adapter2;
	private List<Schoolexp> list2=new ArrayList<Schoolexp>();
	private ListView listschool;
	private List<Project> list3=new ArrayList<Project>();
	private ProjectAdapter adapter3;
	private TextView add_project_experience;
    private String user_ID;
    private Resume resume;
	private static String temp;
   ExecutorService pool = Executors.newFixedThreadPool(15);
private ActionBar actionBar;
private TextView ActionTitle;
private int postion;
private CustomProgressDialog customProgressDialog; 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resume_modify);
		mystatus=(MyStatus)getApplication();
		user_ID=mystatus.getUser_ID();
		
		
		
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle);//自定义ActionBar布局 
	        ActionTitle=(TextView)findViewById(R.id.actionbar_title1);
	    	   ActionTitle.setText("填写简历");
		iniUI1();
		   customProgressDialog=new CustomProgressDialog(ResumeModifyActivity.this);
		   customProgressDialog.show();
		iniData();
		
		try {
			inidata2();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
   
	private void inidata2() throws Exception{
		// TODO Auto-generated method stub
    	
    	Thread t1=new Thread(){
    		
    		public void run() {
      			try {
      			final JSONObject jsonObj = new JSONObject();
      			jsonObj.put("resume_id", mystatus.getUserResume_id());
      			HttpContectionUtil conn = new HttpContectionUtil();
      			
     				String uri =mystatus.getUrlString() + "Select_Eduexperience.php";
     				String responseStr = conn.ConnForResult(uri,jsonObj);
     				       list=Eduexperience.parseJsonArray(responseStr);
     	     			
     	     				adapter=new listresume_adpater(ResumeModifyActivity.this, list);
     	     				iniListView(listeduex, adapter, EDU,list);
     	     				
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
      		}
    		
    	};
        Thread t2=new Thread(){
    		
    		public void run() {
      			try {
      			final JSONObject jsonObj = new JSONObject();
      			jsonObj.put("resume_id", mystatus.getUserResume_id());
      			HttpContectionUtil conn = new HttpContectionUtil();
      			
     				String uri =mystatus.getUrlString() + "Select_Internship.php";
     				String responseStr = conn.ConnForResult(uri,jsonObj);
     				       list1=Internship.parseJsonArray(responseStr);
     	     			
     	     				adapter1=new InternshipAdapter(ResumeModifyActivity.this, list1);
     	     				 iniListView(listintern, adapter1, INTERN, list1);
      			} catch (Exception e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
      		}
    	 	
    	 };
    	 Thread t3=new Thread(){
     		
     		public void run() {
       			try {
       			final JSONObject jsonObj = new JSONObject();
       			jsonObj.put("resume_id", mystatus.getUserResume_id());
       			HttpContectionUtil conn = new HttpContectionUtil();
       			
      				String uri =mystatus.getUrlString() + "Select_SchoolExperienc.php";
      				String responseStr = conn.ConnForResult(uri,jsonObj);
      				list2=Schoolexp.parseJsonArray(responseStr);
      			
      				 adapter2=new SchoolAdapter(ResumeModifyActivity.this, list2);
      				 iniListView(listschool, adapter2, SCHOOL, list2);
       			} catch (Exception e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
       		}
     	 	
     	 };
          Thread t4=new Thread(){
     		
     		public void run() {
       			try {
       			final JSONObject jsonObj = new JSONObject();
       			jsonObj.put("resume_id", mystatus.getUserResume_id());
       			HttpContectionUtil conn = new HttpContectionUtil();
       			
      				String uri =mystatus.getUrlString() + "Select_ProjectExperienc.php";
      				String responseStr = conn.ConnForResult(uri,jsonObj);
      				list3=Project.parseJsonArray(responseStr); 
      			
      				 adapter3=new ProjectAdapter(ResumeModifyActivity.this, list3);
      				 iniListView(listproject, adapter3, PROJECT, list3);
       			} catch (Exception e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
       		}
     	 	
     	   };
     	  Thread t5=new Thread(){
       		
       		

		

			public void run() {
         			try {
         			final JSONObject jsonObj = new JSONObject();
         			jsonObj.put("resume_id", mystatus.getUserResume_id());
         			HttpContectionUtil conn = new HttpContectionUtil();
         			
        				String uri =mystatus.getUrlString() + "Select_ExceptedIntern.php";
        				String responseStr = conn.ConnForResult(uri,jsonObj);
        				resume=Resume.parseJsonArray(responseStr);
                      if(resume.getDate_length()>3){
        					
        					date_length="3个月以上";
        				}
        				else {
        					 date_length=resume.getDate_length()+"个月";
        					
        				}
        				
        				
        				runOnUiThread(new Runnable() {
							
							

						

							@Override
							public void run() {
								
								// TODO Auto-generated method stub
								excepted_intern.setText(resume.getDayperweeks()+"天/周"+
								","+"实习时间"+date_length+","+resume.getSalary()+"元/日薪"+","+"预计到岗时间"
										+resume.getReport_time());
							}
						});
        				
        				
         			} catch (Exception e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
         		}
       	 	
       	   };
       	   
       	   
	       pool.execute(t1);
		   pool.execute(t2);
		   pool.execute(t3);
		   pool.execute(t4);
		   pool.execute(t5);
		  
		   
	}
	private void iniData(){
		
    	toprealname.setText(mystatus.getRealname());
    	topusersexandyear.setText(mystatus.getSex()+"|"+mystatus.getGraduate_year());
    	topuseremail.setText(mystatus.getEmail());
    	topeducation.setText(mystatus.getSchool()+"|"+mystatus.getMajor()+"|"+mystatus.getDegree());
    	topusertel.setText(mystatus.getPhonenumber());
    	if(mystatus.getPhoto_url()==null){	
			return;
		}
		try {
			Utils.onLoadImage(new URL(mystatus.getPhoto_url()), new OnLoadImageListener() {  
			    public void OnLoadImage(Bitmap bitmap, String bitmapPath) {  
			        // TODO Auto-generated method stub  
			        if(bitmap!=null){  
			        	img_avator.setImageBitmap(new Tocircle().createCircleImage(bitmap, bitmap.getHeight()-10)); 
			        	
			        }  
			    }  
			});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	private void  iniListView(final ListView listview1,Adapter adapter1,final int menuID,List<?> listx){
		if (listx== null)
		{	handler.sendEmptyMessage(0);	
	 		return;
		}
	     final Adapter temp=adapter1;
		// 设置数据适配器 必须在ui线程
	     
				// TODO Auto-generated method stub		
				   runOnUiThread(new Runnable() {
						@Override
						public void run() {
							 
							 listview1.setAdapter((ListAdapter) temp);
							 
						}
					});
	  listview1.setOnItemLongClickListener(new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			postion=arg2;
			
			
			return false;
		}
		  
		  
	});
		listview1.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu1, View arg1,
					ContextMenuInfo arg2) {
				// TODO Auto-generated method stub
				menu1.setHeaderTitle("请选择操作");  
				
				menu1.add(0, menuID+1, 0, "编辑");  
				menu1.add(0, menuID+2, 0, "删除");  
				menu1.add(0, 20, 0, "取消");  
				
			 
			}
		});
		 runOnUiThread(new Runnable() {
				@Override
				public void run() {
					
					new Utility().setListViewHeightBasedOnChildren(listview1); 
					handler.sendEmptyMessage(0);
				}
			});
	         
		
	}
	private void iniUI1() {
		// 个人资料部分的UI的初始化
		toprealname=(TextView)findViewById(R.id.resume_usernane);
		topusersexandyear=(TextView)findViewById(R.id.resume_sex);
		topusertel=(TextView)findViewById(R.id.resume_tel);
		topuseremail=(TextView)findViewById(R.id.resume_email);
		topeducation=(TextView)findViewById(R.id.resume_eduaction);
		
		img_avator=(ImageView)findViewById(R.id.resume_avator);
		
		personalinfo_motified_btn=(Button)findViewById(R.id.button_modified);
		
		personalinfo_motified_btn.setOnClickListener(new OnClick());
		
		
		//简历部分的UI初始化部分
		excepted_intern=(TextView)findViewById(R.id.excepted_intern);
	    excepted_intern.setSingleLine(false);
		listeduex=(ListView)findViewById(R.id.listViewedu);
		listintern=(ListView)findViewById(R.id.listViewintern);
		listschool=(ListView)findViewById(R.id.listViewschool); 
		listproject=(ListView)findViewById(R.id.listViewproject);
		 //填写更多 这个目前指向网站
		 moreBtn=(Button)findViewById(R.id.moreBtn);
		
		
		
		excepted_intern.setOnClickListener(new OnClick());
		

	
		
		moreBtn.setOnClickListener(new OnClick());
		//添加UI之四个添加部分初始化
		add_experience_education=(TextView)findViewById(R.id.addexpedu);
		add_internships=(TextView)findViewById(R.id.addinternships);
		add_school_experience=(TextView)findViewById(R.id.addschoolexp);
		add_project_experience=(TextView)findViewById(R.id.addprojectexp);
		
		
		add_experience_education.setOnClickListener(new OnClick());
		add_internships.setOnClickListener(new OnClick());
		add_school_experience.setOnClickListener(new OnClick());
		add_project_experience.setOnClickListener(new OnClick());
		
	}
	
	class OnClick implements  OnClickListener{

		
		@Override
		public void onClick(View arg0) {
			switch ( arg0.getId()){
			case R.id.button_modified:
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putInt("flag", 0);
				intent.putExtras(bundle);
				intent.setClass(ResumeModifyActivity.this, PersonalInfoActivity.class);
				startActivity(intent);
				
				break;
			case R.id.excepted_intern:
				Intent intent2=new Intent();
				Bundle bundle2=new Bundle();
		        if(resume!=null){
				bundle2.putString("Dayperweeks",resume.getDayperweeks()+"");
				bundle2.putString("date_length",date_length );
				bundle2.putString("excepted_city",resume.getCity() );
				bundle2.putString("Salary",resume.getSalary() +"" );
				bundle2.putString("job",resume.getExceptedjob());
				bundle2.putString("job_catagroy",resume.getExceptedjob_category() );
				bundle2.putString("Report_time",resume.getReport_time() );
		        }
				intent2.putExtras(bundle2);
				intent2.setClass(ResumeModifyActivity.this,ExceptedInternActivity.class);
				startActivity(intent2);
				break;
			case R.id.addexpedu:
				Intent intent10=new Intent();
				intent10.setClass(ResumeModifyActivity.this, ExpeduActivity.class);
				startActivity(intent10);
				break;
			case R.id.addinternships:
				Intent intent8=new Intent();
				intent8.setClass(ResumeModifyActivity.this, InternShipActivity.class);
				startActivity(intent8);
				break;
			case R.id.addschoolexp:
				Intent intent9=new Intent();
				intent9.setClass(ResumeModifyActivity.this, SchoolExpActivity.class);
				startActivity(intent9);
				break;
			case R.id.addprojectexp:
				Intent intent12=new Intent();
				intent12.setClass(ResumeModifyActivity.this, ProjectActivity.class);
				startActivity(intent12);
				break;	
			case R.id.moreBtn:
				//对话框！
				Intent intent13=new Intent();
				intent13.setClass(ResumeModifyActivity.this, tips.class);
				startActivity(intent13);
				break;
			default:
					break;
			}
		}

		
	}
	public boolean onContextItemSelected(MenuItem item) {  
	      
		 AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		   
		    
		    switch(item.getItemId()) {
		    case 2:
		        // 教育事件
		        Intent intent=new Intent();
		         intent.setClass(ResumeModifyActivity.this, ExpeduActivity.class);
		     	Bundle bundle=new Bundle();
		     	Eduexperience eduexperience=new Eduexperience();
		     	eduexperience=list.get(adapter.getPos());
		     	  bundle.putString("edu_id", eduexperience.getIdString());
		         bundle.putString("scholl", eduexperience.getSchoolname());
		         bundle.putString("degree", eduexperience.getDegree());
		         bundle.putString("major", eduexperience.getMajor());
		         bundle.putInt("start", eduexperience.getStarttime());
		         bundle.putInt("end", eduexperience.getEndtime());
		         intent.putExtras(bundle);
		         startActivity(intent);
		    	
		       break;
		    case 3:
		    	
			    pool.execute(t6);
		    	
		    
		      
		        // do something
		    break;
		    //实习经验
		    case 4:
		    	  Intent intent2=new Intent();
			         intent2.setClass(ResumeModifyActivity.this, InternShipActivity.class);
			     	Bundle bundle2=new Bundle();
			     	Internship internship=new Internship();
			     	internship=list1.get(adapter1.getPos());
			     
			         bundle2.putString("comname", internship.getCompanyString());
			         bundle2.putString("ocuupation", internship.getOccupationString());
			         bundle2.putString("intern_id",internship.getIdString());
			         bundle2.putInt("start_year", internship.getStarttime());
			         bundle2.putInt("end_year", internship.getEndtime());
			         bundle2.putInt("start_month", internship.getStartmonthtime());
			         bundle2.putInt("end_month", internship.getEndmonthtime());
			         intent2.putExtras(bundle2);
			         startActivity(intent2);
		        // do something
		      break;
		    case 5:
		    
		
		    	pool.execute(t7);
		    	break;
		    	//校园经历
		    case 6:
		    	 Intent intent3=new Intent();
		         intent3.setClass(ResumeModifyActivity.this, SchoolExpActivity.class);
		     	Bundle bundle3=new Bundle();
		     	Schoolexp schoolexp=new Schoolexp();
		     	schoolexp=list2.get(adapter2.getPos());
		         bundle3.putString("tag", schoolexp.getTag());
		         bundle3.putString("content",schoolexp.getContentString());
		         bundle3.putString("school_id",schoolexp.getIdString());
		         intent3.putExtras(bundle3);
		         startActivity(intent3);
		         break;
		    case 7:
		    	
		    
		    	pool.execute(t8);
		    	break;
		    	//项目经验
		    case 8:
		    	 Intent intent4=new Intent();
		         intent4.setClass(ResumeModifyActivity.this, ProjectActivity.class);
		     	Bundle bundle4=new Bundle();
		     	Project project=new Project();
		     	project=list3.get(adapter3.getPos());
		     	bundle4.putString("project_id", project.getIdstString());
		         bundle4.putString("projectname", project.getProjectname());
		         bundle4.putString("Des", project.getDescription());
		         bundle4.putString("role",project.getPlay_roleString());
		         bundle4.putString("Begin",project.getBegin_years()+"-"+project.getBegin_month());
		         bundle4.putString("End",project.getEnd_year()+"-"+project.getEnd_month());
		         intent4.putExtras(bundle4);
		         startActivity(intent4);
		    	
		    	break;
		    case 9:
		    
		    	
		    	
		    	pool.execute(t9);
		    	break;
		      default:
		    	
		    return   super.onContextItemSelected(item); 
		        
		    }
		    return  super.onContextItemSelected(item); 
	    }

	private void toast(String resultString) {
		// TODO Auto-generated method stub
		
					
	   				
	}
	 Thread  t6=new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
	    		final JSONObject jsonObj = new JSONObject();
      			jsonObj.put("id", list.get(postion).getIdString());
      			jsonObj.put("flag", 1);
      			HttpContectionUtil conn = new HttpContectionUtil();
      			
     				String uri =mystatus.getUrlString() + "delete.php";
     				String responseStr = conn.ConnForResult(uri,jsonObj);
	   				JSONObject response = new JSONObject(responseStr);
	   				String resultString =  response.getString("result");
	   				if("failed".equals(resultString)){
	   			    	runOnUiThread(new Runnable() {
	   						
	   						@Override
	   						public void run() {
	   							// TODO Auto-generated method stub
	   							Toast.makeText(ResumeModifyActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
	   						}
	   					});
	   			    		
	   			    	}else{
	   			    		list.remove(postion);
	   			    		
	   			    		runOnUiThread(new Runnable() {
	   							
	   							@Override
	   							public void run() {
	   								// TODO Auto-generated method stub
	   								Toast.makeText(ResumeModifyActivity.this,"删除成功", 0).show();
	   								
	   								adapter.notifyDataSetChanged();
	   							}
	   						});
	   			    	}
	   			    		
	   			 
				
				
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	});
	 Thread  t7=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
		    		final JSONObject jsonObj = new JSONObject();
	      			jsonObj.put("id", list1.get(postion).getIdString());
	      			jsonObj.put("flag", 2);
	      			HttpContectionUtil conn = new HttpContectionUtil();
	      			
	     				String uri =mystatus.getUrlString() + "delete.php";
	     				String responseStr = conn.ConnForResult(uri,jsonObj);
		   				JSONObject response = new JSONObject(responseStr);
		   				String resultString =  response.getString("result");
		   				if("failed".equals(resultString)){
		   			    	runOnUiThread(new Runnable() {
		   						
		   						@Override
		   						public void run() {
		   							// TODO Auto-generated method stub
		   							Toast.makeText(ResumeModifyActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
		   						}
		   					});
		   			    		
		   			    	}else{
		   			    		list1.remove(postion);
		   			    		
		   			    		runOnUiThread(new Runnable() {
		   							
		   							@Override
		   							public void run() {
		   								// TODO Auto-generated method stub
		   								Toast.makeText(ResumeModifyActivity.this,"删除成功", 0).show();
		   								
		   								adapter1.notifyDataSetChanged();
		   							}
		   						});
		   			    	}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	 Thread  t8=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
		    		final JSONObject jsonObj = new JSONObject();
	      			jsonObj.put("id", list2.get(postion).getIdString());
	      			jsonObj.put("flag", 3);
	      			HttpContectionUtil conn = new HttpContectionUtil();
	      			
	     				String uri =mystatus.getUrlString() + "delete.php";
	     				String responseStr = conn.ConnForResult(uri,jsonObj);
		   				JSONObject response = new JSONObject(responseStr);
		   				String resultString =  response.getString("result");
		   				toast(resultString);
		   				if("failed".equals(resultString)){
		   			    	runOnUiThread(new Runnable() {
		   						
		   						@Override
		   						public void run() {
		   							// TODO Auto-generated method stub
		   							Toast.makeText(ResumeModifyActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
		   						}
		   					});
		   			    		
		   			    	}else{
		   			    		list2.remove(postion);
		   			    		
		   			    		runOnUiThread(new Runnable() {
		   							
		   							@Override
		   							public void run() {
		   								// TODO Auto-generated method stub
		   								Toast.makeText(ResumeModifyActivity.this,"删除成功", 0).show();
		   								
		   								adapter2.notifyDataSetChanged();
		   							}
		   						});
		   			    	}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	 Thread  t9=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
		    		final JSONObject jsonObj = new JSONObject();
	      			jsonObj.put("id", list3.get(postion).getIdstString());
	      			jsonObj.put("flag", 4);
	      			HttpContectionUtil conn = new HttpContectionUtil();
	      			
	     				String uri =mystatus.getUrlString() + "delete.php";
	     				String responseStr = conn.ConnForResult(uri,jsonObj);
		   				JSONObject response = new JSONObject(responseStr);
		   				String resultString =  response.getString("result");
		   				if("failed".equals(resultString)){
		   			    	runOnUiThread(new Runnable() {
		   						
		   						@Override
		   						public void run() {
		   							// TODO Auto-generated method stub
		   							Toast.makeText(ResumeModifyActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
		   						}
		   					});
		   			    		
		   			    	}else{
		   			    		list3.remove(postion);
		   			    		
		   			    		runOnUiThread(new Runnable() {
		   							
		   							@Override
		   							public void run() {
		   								// TODO Auto-generated method stub
		   								Toast.makeText(ResumeModifyActivity.this,"删除成功", 0).show();
		   								
		   								adapter3.notifyDataSetChanged();
		   							}
		   						});
		   			    	}
		   				
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	 Handler handler = new Handler() {  
	        @Override  
	        public void handleMessage(Message msg) {// handler接收到消息后就会执行此方法  
	            customProgressDialog.dismiss();// 关闭ProgressDialog  
	        }  
	    }; 
	
}
