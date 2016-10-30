package com.example.qqq;

import http.HttpContectionUtil;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import adapter.ArticleLvAdapter;
import adapter.MyStatus;
import adt.Article;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EassySkillsActivity extends Activity {
	ExecutorService pool = Executors.newFixedThreadPool(3);
	private PullToRefreshListView mPullRefreshListView;
	private ArticleLvAdapter adapter;
	private MyStatus  myStatus;
	private List<Article> list_Articleitem;
	private List<Article> list_Articleitemx;
	protected int index=0;
	private int type=4;
	private ListView actualListView;
	private int end=0;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gossip_talk);
		myStatus=(MyStatus)getApplication();
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		mPullRefreshListView.setMode(Mode.PULL_UP_TO_REFRESH);
	
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			
		
		

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				if(end==0){
					index++;
					pool.execute(t2);
				
				}else{
					   mPullRefreshListView.onRefreshComplete();
				}
			}
		});
		
		actualListView = mPullRefreshListView.getRefreshableView();
       
		
		pool.execute(t3);
	
		
	}
	
	Thread t3=new Thread() {
		@Override
		public void run() {
			try{
		    final JSONObject jsonObj = new JSONObject();
		    jsonObj.put("index", index);
		    jsonObj.put("type",type);
			HttpContectionUtil conn = new HttpContectionUtil();
			String uri =myStatus.getUrlString() + "select_article_exp.php";
			String responseStr = conn.ConnForResult(uri, jsonObj);
			
			list_Articleitem=Article.parseJsonArray(responseStr);
			if (list_Articleitem == null)
				{
				
				return;
				}
			runOnUiThread(new Runnable(){
				

			

				public void run(){
					adapter=new ArticleLvAdapter(EassySkillsActivity.this,list_Articleitem);
					actualListView.setAdapter(adapter);
					inilist();
				}

				

			

				
			});
			
			
			
			
			}
			catch (final Exception e) {
				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(EassySkillsActivity.this, "Failed"+e.toString(), 0).show();
					}
				});
			}
			
		}
	};

	Thread t2=new Thread() {
		

		@Override
		public void run() {
			try{
		    final JSONObject jsonObj = new JSONObject();
		    jsonObj.put("index", index);
		    jsonObj.put("type",type);
			HttpContectionUtil conn = new HttpContectionUtil();
			String uri =myStatus.getUrlString() + "select_article_exp.php";
			String responseStr = conn.ConnForResult(uri, jsonObj);
			
			list_Articleitemx=Article.parseJsonArray(responseStr);
			if (list_Articleitemx == null)
				{
				  
			     end=1;
			     runOnUiThread(new Runnable() {
						public void run() {
								

						      // Call onRefreshComplete when the list has been refreshed.
							   mPullRefreshListView.onRefreshComplete();
						}
					});
					
				return;
				}
			list_Articleitem.addAll(list_Articleitemx);
			
			runOnUiThread(new Runnable() {
				public void run() {
					adapter.notifyDataSetChanged();	

				      // Call onRefreshComplete when the list has been refreshed.
				   mPullRefreshListView.onRefreshComplete();
				}
			});
			
		
			
			
			
			
			
			}
			catch (final Exception e) {
				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(EassySkillsActivity.this, "Failed"+e.toString(), 0).show();
					}
				});
			}
			
		}
	};

	private void inilist() {
		// TODO Auto-generated method stub
		
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable(){
					public void run(){
						
						Intent intent=new Intent();
						Bundle bundle=new Bundle();
						Article article=list_Articleitem.get(arg2-1);
						bundle.putSerializable("article", article);
						intent.putExtras(bundle);
						 intent.setClass(EassySkillsActivity.this, EassyAusles_Page_Detail.class);
						 startActivity(intent);
						
					}
				});
			}
		});
		
		
		
	}
}
