package com.example.qqq;

import function.ConvertToResult;
import http.HttpContectionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;  
import com.handmark.pulltorefresh.library.PullToRefreshListView; 

import compsite.Carousel;
import compsite.CarouselData;
import compsite.CitySelector;
import compsite.CustomProgressDialog;
import compsite.DayweeksMenu;
import compsite.Earingsse;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools.Pool;
import android.text.format.DateUtils;
import android.R.integer;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import adapter.ImageAdapter;
import adapter.InternItemsLVAdapter;
import adapter.MyStatus;
import adt.*;

@SuppressWarnings("deprecation")
public class mainpage extends Fragment {
	private ListView lv;



	private List<advertisement> adlist;
	private ImageButton close;
	private TextView JobType,Location,Days,Earnings;
	private View view;
	private List<Intern> list_internitem;
	 private PullToRefreshListView mPullToRefreshListView;  
	HashMap <String,String> map_daysHashMap=new HashMap<String, String>(){
		{   put("不限","");
			put("2天","2");
		put("3天","3");
		put("4天","4");
		put("5天","5");}
		};
		HashMap <String,String> earingsHashMap=new HashMap<String, String>(){
			{  put("不限","7");
			put(50+"以下","0");
			put(50+"-"+100,"1");
			put(100+"-"+150,"2");
			put(150+"-"+200,"3");
			put(200+"-"+300,"4");
			put(300+"以上","5");
			}
			};
      MyStatus myStatus;
      Context context;
	private LocalActivityManager manager=null;
	private SearchView search;
	 private int visibleLastIndex = 0;   //最后的可视项索引  
	    private int visibleItemCount;       // 当前窗口可见项总数 
	    private InternItemsLVAdapter adapter;
	   
		protected List<Intern> list_internitemmore;
		protected int index=0;  
		private String keyword="";
		private int flag_search=0;
		private String textString;
		private String textString1;
		private String textString2;
		private String textString3;
		private Gallery  g ;
		protected int end=1;
		 ExecutorService pool = Executors.newFixedThreadPool(10);
		private String result;
		protected CustomProgressDialog customProgressDialog; 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}
	@SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		 view = inflater.inflate(R.layout.activity_mainpage,container,false);
		 
	     context=getActivity();
	     manager = new LocalActivityManager(getActivity(), true);
	        manager.dispatchCreate(savedInstanceState);
	        myStatus= (MyStatus) getActivity().getApplication();
	        customProgressDialog=new CustomProgressDialog(context);
	        customProgressDialog.show();
	        
	        iniUI();
	        pool.execute(t9);
	          iniData();
	        
	        mPullToRefreshListView=(PullToRefreshListView) view.findViewById(R.id.jobs_Listview);
	        mPullToRefreshListView.setMode(Mode.PULL_UP_TO_REFRESH);
	    
	        mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			
	        	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
	        	     
	        		  
	        		if(end==1){
		        		index++;
			        	
			        	loadmore();
		        	}else{
		        		mPullToRefreshListView.onRefreshComplete();
		        		
		        	}
	        		
	        	}
	        
	        
	        });
	        
	        lv=mPullToRefreshListView.getRefreshableView();
        
         
		 return view; 
	     
	}
	 private Carousel c;
	 private List<CarouselData> data;
	  Thread t9=new Thread(){
		  
		  private void initControl() {
		       
		        c.setCallback(new Carousel.ClickCallback() {
		            @Override
		            public void perform(int id, int position) {
		                //Toast.makeText(getActivity(), "id:" + id + "position" + position + "title:" + data.get(position).getTitle(), Toast.LENGTH_LONG).show();
		            }
		        });
		    }

		    protected void onStop() {
		        super.stop();
		        c.shutdown();
		    }
		   public void run() {
				final JSONObject jsonObj = new JSONObject();
				try {
					
					
					HttpContectionUtil conn = new HttpContectionUtil();
				   
					String uri =myStatus.getUrlString() + "advertisement.php";
					String responseStr = conn.ConnForResult(uri, "");
					
					
					adlist=advertisement.parseJsonArray(responseStr);
					
					if (adlist == null){
						
						  getActivity().runOnUiThread(new Runnable(){
						public void run(){
					 
							
							
							Toast.makeText(  getActivity(), "没有相关数据", 0).show();
						}

						
					});
					
						
						return;
					}
					// 设置数据适配器 必须在ui线程
					  getActivity().runOnUiThread(new Runnable(){
						public void run(){
							 initControl();
						        //开启轮播控件
						        data = new ArrayList<CarouselData>();
						        String[] urls = new String[]{
						        		adlist.get(0).getImg_url(),
						        		adlist.get(1).getImg_url(),
						        		adlist.get(2).getImg_url()
						        };
						        for (int i = 0; i < urls.length; i++) {
						            CarouselData d = new CarouselData();
						            d.setImage(urls[i]);
						           // d.setTitle("测试tile" + i);
						            d.setId(i);
						            data.add(d);
						        }
						        c.startup(data);
							
						
						}

						
						

						
					});
				}
				catch (final Exception e) {
					  getActivity().runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(  getActivity(), "Failed"+e.toString(), 0).show();
						}
					});
				}
			
		
		};
		   
		   
		   
		   
		   
	   };



	
	   private void iniadpter() {
			// TODO Auto-generated method stub
	       //设置图片匹配器   
	       g.setAdapter(new ImageAdapter( getActivity(),adlist));        
	       //设置AdapterView点击监听器，Gallery是AdapterView的子类   
	       g.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		
	       
	       
	       });
	       
		        
	 




	    }
	     int len = 3;
	    int currentItem = 0;
	    /** 定时滑动 */
	    private final Handler slideHandler = new Handler();
	    /** 滑动 */
	    private final Runnable slideRun = new Runnable() {

	        @Override
	        public void run() {
	            // TODO Auto-generated method stub
	            currentItem++;
	            currentItem = checkPosition(currentItem);
//   ((Mygallery) g).slide(Mygallery.RIGHT);
	            slideHandler.postDelayed(this, 2000);
	        }
	    };
	   public void startSlide() {
		           slideHandler.postDelayed(slideRun, 2000);
		      }
	   public int checkPosition(int position) {
	        if (position >= len) {
	            position = position % len;
	        }

	        return position;
	    }

	  
		public void iniUI(){
		
		JobType=(TextView)view.findViewById(R.id.jobType);
		Location=(TextView)view.findViewById(R.id.location);
		Days=(TextView)view.findViewById(R.id.days);
		Earnings=(TextView)view.findViewById(R.id.earningsBTN);
		search=(SearchView)view.findViewById(R.id.searchView1);
		  
		  c = (Carousel) view.findViewById(R.id.crs);
		//getResources().getStringArray(R.array.array);
	    
		
		
		 // lv.addFooterView(loadMoreView);
		 
		// lv.removeFooterView(loadMoreView);
		  JobType.setOnClickListener(new onclick());
		  Location.setOnClickListener(new onclick());
		  Days.setOnClickListener(new onclick());
		  Earnings.setOnClickListener(new onclick());
         
		 
	       
	      search.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String arg0) {
				// TODO Auto-generated method stub
				keyword=arg0;
				 customProgressDialog.show();
				 iniData();
				
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	
	class onclick implements  OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getId())
			{
			case R.id.jobType:
				Intent intent=new Intent();
				intent.setClass(context, JobSelectActivity.class);
				startActivityForResult(intent, 1);
				break;
			case R.id.location:
				Intent intent2=new Intent();
				intent2.setClass(context, CitySelector.class);
				startActivityForResult(intent2, 2);
				break;
			case R.id.days:
				Intent intent3=new Intent();
				intent3.setClass(context, DayweeksMenu.class);
				startActivityForResult(intent3, 3);
				break;
			case R.id.earningsBTN:
				Intent intent4=new Intent();
				intent4.setClass(context, Earingsse.class);
				startActivityForResult(intent4, 4);
				break;
			
			default:
				break;
			
			
			}
		}
	
	}
	
	public void iniData(){
		
		Thread  t4=new Thread() {
			@Override
			public void run() {
				try{
				index=0;
					end=1;
					final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("keyword",keyword);
				jsonObj.put("index",index);
				String uri =myStatus.getUrlString() + "select_intern_search.php";
				
			  
							
				
				String responseStr = conn.ConnForResult(uri, jsonObj);
				
				list_internitem=Intern.parseJsonArray(responseStr);
				
				if (list_internitem == null){
				
				    getActivity().runOnUiThread(new Runnable(){
					public void run(){
				 
						
						handler.sendEmptyMessage(0);
						Toast.makeText(getActivity(), "没有相关数据", 0).show();
						mPullToRefreshListView.onRefreshComplete();
					}

					
				});
				
					
					return;
				}
				// 设置数据适配器 必须在ui线程
				getActivity().runOnUiThread(new Runnable(){
					public void run(){
						adapter=new InternItemsLVAdapter(context,list_internitem);
						lv.setAdapter( adapter);
						handler.sendEmptyMessage(0);
						inilist();
					//	adapter.notifyDataSetChanged();
					}

					
				});
				
				
				
				
				}
				catch (final Exception e) {
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(getActivity(), "Failed"+e.toString(), 0).show();
							mPullToRefreshListView.onRefreshComplete();
						}
					});
				}
			}
		};
		  pool.execute(t4);
		
	}
	private void inilist() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				Intern intern=list_internitem.get(arg2-1);
				bundle.putString("flag", "0");
				bundle.putSerializable("job_info", intern);
				intent.putExtras(bundle);
				intent.setClass(context, JobInfo_Activity.class);
				startActivity(intent);
			}
		});
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) { 
        // TODO Auto-generated method stub 
        super.onActivityResult(requestCode, resultCode, data);
        
        if (data != null) { 
        	 result = data.getStringExtra("result"); 
          
            switch (requestCode) {
			case 1:
				JobType.setText(result);
				
				break;
           case 2:
        	   Location.setText(result);
				break;
             case 3:
            	 Days.setText(result);
	            break;
               case 4:
            	   Earnings.setText(result);
            	   
	                break;
			default:
				break;
			}
           
        }else{
        	
        	return;
        	
        }
        flag_search=1;
        if(JobType.getText().toString().equals("类别")){
        	textString="";
        	
        }
        else{
        	textString=JobType.getText().toString();
        }
         if(Location.getText().toString().equals("地点")){
        	 textString1="";
        	
        }else{
        	textString1=Location.getText().toString();
        }
          if(Days.getText().toString().equals("天数")){
        	  textString2="";
	
              }else{
            	textString2=map_daysHashMap.get(Days.getText().toString());
   }
            if(Earnings.getText().toString().equals("日薪")){
            	  textString3="7";
                  }else{
                	  textString3=earingsHashMap.get(Earnings.getText().toString());
                  }
            customProgressDialog.show();
        searchbytag(textString,textString2,textString1,textString3);

    }
	
	private void searchbytag(String text, String text2,
			String text3, String text4) {
		
		// TODO Auto-generated method stub
		final String textString1=text;
		final String textString2=text2;
		final String textString3=text3;
		final String textString4=text4;
		Thread t3=new Thread() {
			@Override
			public void run() {
				try{
					index=0;
					end=1;
					final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("keyword1",textString1);
				jsonObj.put("keyword2",textString2);
				jsonObj.put("keyword3",textString3);
				jsonObj.put("keyword4",textString4);
				
				jsonObj.put("index",index);
				String uri =myStatus.getUrlString() + "connection_search.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				
				list_internitemmore=Intern.parseJsonArray(responseStr);
				if (list_internitemmore == null)
					{
					flag_search=0;
					list_internitem.clear();
					
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
							handler.sendEmptyMessage(0);
							adapter.notifyDataSetChanged();	
						
							Toast.makeText(getActivity(), "没有相关数据", 0).show();
							mPullToRefreshListView.onRefreshComplete();
							end=0;
						}

						
					});
					
					return;
					}
				list_internitem.clear();
				list_internitem.addAll(list_internitemmore);
				
				// 设置数据适配器 必须在ui线程
				getActivity().runOnUiThread(new Runnable(){
					public void run(){
						adapter.notifyDataSetChanged();	
						handler.sendEmptyMessage(0);
					
					}

					
				});
				
				
				
				
				}
				catch (final Exception e) {
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(getActivity(), "Failed"+e.toString(), 0).show();
						}
					});
				}
				
			}
		};
		
		
		  pool.execute(t3);
		
		
		
		
	}
	private void searchbytagmore(String text, String text2,
			String text3, String text4) {
		
		// TODO Auto-generated method stub
		final String textString1=text;
		final String textString2=text2;
		final String textString3=text3;
		final String textString4=text4;
	   Thread t2=	new Thread() {
			@Override
			public void run() {
				try{
					final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
				jsonObj.put("keyword1",textString1);
				jsonObj.put("keyword2",textString2);
				jsonObj.put("keyword3",textString3);
				jsonObj.put("keyword4",textString4);
				
				jsonObj.put("index",index);
				String uri =myStatus.getUrlString() + "connection_search.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				
				list_internitemmore=Intern.parseJsonArray(responseStr);
				if (list_internitemmore == null)
					{
					end=0;
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
					
							mPullToRefreshListView.onRefreshComplete();
							end=0;
						}

						
					});
					
					return;
					}
			
				list_internitem.addAll(list_internitemmore);
				
				// 设置数据适配器 必须在ui线程
				getActivity().runOnUiThread(new Runnable(){
					public void run(){
						adapter.notifyDataSetChanged();	
						mPullToRefreshListView.onRefreshComplete();
						 
					}

					
				});
				
				
				
				
				}
				catch (final Exception e) {
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(getActivity(), "Login Failed"+e.toString(), 0).show();
							mPullToRefreshListView.onRefreshComplete();
						}
					});
				}
				
			}
		};
		
		
		
		
		  pool.execute(t2);
		
		
	}
	

	private void loadmore() {
		// TODO Auto-generated method stub
	
		if(flag_search==1){
			 if(JobType.getText().toString().equals("类别")){
		        	textString="";
		        	
		        }
		        else{
		        	textString=JobType.getText().toString();
		        }
		         if(Location.getText().toString().equals("地点")){
		        	 textString1="";
		        	
		        }else{
		        	textString1=Location.getText().toString();
		        }
		          if(Days.getText().toString().equals("天数")){
		        	  textString2="";
			
		              }else{
		            	  textString2=map_daysHashMap.get(Days.getText().toString());
		            	  
		              }
		            if(Earnings.getText().toString().equals("日薪")){
		            	  textString3="7";
			
		                  }else{
		                	  textString3=earingsHashMap.get(Earnings.getText().toString());  
		                	  
		                  }
		            
		        searchbytagmore(textString,textString2,textString1,textString3);
		  
	  }else{
		  
			iniDatamore();
		  
	  }
		
	}
	private void iniDatamore() {
		
		// TODO Auto-generated method stub
		Thread t1=new Thread() {
			@Override
			public void run() {
				try{
					final JSONObject jsonObj = new JSONObject();
					HttpContectionUtil conn = new HttpContectionUtil();
					jsonObj.put("keyword",keyword);
					jsonObj.put("index",index);
					
				String uri =myStatus.getUrlString() + "select_intern_search.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				list_internitemmore=Intern.parseJsonArray(responseStr);
				if (list_internitemmore == null)
					{
					end=0;
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
					
							
							mPullToRefreshListView.onRefreshComplete();
							
						}

						
					});
				
					return;
					}
				list_internitem.addAll(list_internitemmore);
				
				// 设置数据适配器 必须在ui线程
				getActivity().runOnUiThread(new Runnable(){
					public void run(){
						adapter.notifyDataSetChanged();	
						mPullToRefreshListView.onRefreshComplete();
					}

					
				});
				
				
				
				
				}
				catch (final Exception e) {
					getActivity().runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(getActivity(), "Failed"+e.toString(), 0).show();
							mPullToRefreshListView.onRefreshComplete();
						}
					});
				}
			}
		         };
		         pool.execute(t1);
	     }
	   Handler handler = new Handler() {  
	        @Override  
	        public void handleMessage(Message msg) {// handler接收到消息后就会执行此方法  
	            customProgressDialog.dismiss();// 关闭ProgressDialog  
	        }  
	    };  
	
}
