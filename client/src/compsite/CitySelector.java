package compsite;

import http.HttpContectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import com.example.qqq.R;


import function.Utility;

import adapter.ListViewAdapter;
import adapter.MyStatus;
import adt.City;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CitySelector extends Activity{
	protected static final int CITY = 100;
	private HashMap<String, Integer> selector;// 存放含有索引字母的位置  
    private LinearLayout layoutIndex;  
    private ListView listView,listv2;  
    private TextView tv_show;  
    private ListViewAdapter adapter;  
    private String[] indexStr = { "A", "B", "C", "D", "E", "F", "G", "H",  
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",  
            "V", "W", "X", "Y", "Z" };  
    private List<City> persons = null;  
    private List<City> newPersons = new ArrayList<City>();  
    private int height;// 字体高度  
    private boolean flag = false;
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
	private SimpleAdapter adapter2;
	private MyStatus myStatus;
	private List<City> city;
	 ExecutorService pool = Executors.newFixedThreadPool(2);
	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		
		
		super.onCreate(savedInstanceState);
		myStatus=(MyStatus)getApplication();
		 WindowManager.LayoutParams localLayoutParams =getWindow().getAttributes();
		 localLayoutParams.gravity = Gravity.BOTTOM ;
	    localLayoutParams.width=getWindowManager().getDefaultDisplay().getWidth();
	        localLayoutParams.y = 0;
		
		 setContentView(R.layout.city_menu);  
		  
		 listView = (ListView) findViewById(R.id.listView);  
	        layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
	        tv_show = (TextView) findViewById(R.id.tv);
        layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));  
        
        listv2=(ListView)findViewById(R.id.listView_hot);
        tv_show.setVisibility(View.GONE);  
        
        pool.execute(t2);
        
       
	}
	
	 private void inilist(List <City> city) {
		 
		// TODO Auto-generated method stub
		 persons=city;
		 String[] allNames = sortIndex(persons);  
	        sortList(allNames);  
	  
	        selector = new HashMap<String, Integer>();  
	        for (int j = 0; j < indexStr.length; j++) {// 循环字母表，找出newPersons中对应字母的位置  
	            for (int i = 0; i < newPersons.size(); i++) {  
	                if (newPersons.get(i).getName().equals(indexStr[j])) {  
	                    selector.put(indexStr[j], i);  
	                }  
	            }  
	  
	        }  
	        adapter = new ListViewAdapter(this, newPersons);  
	        listView.setAdapter(adapter);  
	        new Utility().setListViewHeightBasedOnChildren(listView);
	         listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), adapter.getItem(arg2).toString(), Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(); 
	                intent.putExtra("result",adapter.getItem(arg2).toString());// 放入返回值 
	                setResult(CITY, intent);// 
	                finish();
				}
	        	 
	        	 
			});
	}

	private void iniHOT(List<City> city) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<city.size();i++){
			if(city.get(i).getIsHot()==1){
				 HashMap<String, Object> map= new HashMap<String, Object>();
				
				 map.put("ItemText",city.get(i).getName());
				  listItem.add(map);
			}
			
			
			
		}
		
 
       
         
         
         adapter2=new SimpleAdapter(this, listItem,R.layout.item4,new String[] {"ItemText"}, new int[]{R.id.textitem});
         listv2.setAdapter(adapter2);
         new Utility().setListViewHeightBasedOnChildren(listv2);
         
         listv2.setOnItemClickListener(new OnItemClickListener() {

 			@Override
 			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
 					long arg3) {
 				// TODO Auto-generated method stub
                 Toast.makeText(getApplicationContext(), listItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
 				
 				Intent intent = new Intent(); 
                intent.putExtra("result",listItem.get(arg2).get("ItemText").toString() );// 放入返回值 
                setResult(CITY, intent);// 
                finish();
 			}
        	 
        	 
 		});
         
	}

	@Override  
	    public void onWindowFocusChanged(boolean hasFocus) {  
	        // 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0  
	        if (!flag) {
	            height = layoutIndex.getMeasuredHeight() / indexStr.length;  
	            getIndexView();  
	            flag = true;  
	        }  
	    } 
	 public void getIndexView() {  
	       LinearLayout.LayoutParams params = new LayoutParams( LayoutParams.WRAP_CONTENT, height);  
	        for (int i = 0; i < indexStr.length; i++) {  
	            final TextView tv = new TextView(this);  
	            tv.setLayoutParams(params);  
	            tv.setText(indexStr[i]);  
	            tv.setPadding(10, 0, 10, 0);  
	            layoutIndex.addView(tv);  
	            layoutIndex.setOnTouchListener(new OnTouchListener() {  
	  
	                @Override  
	                public boolean onTouch(View v, MotionEvent event)  
	  
	                {  
	                    float y = event.getY();  
	                    int index = (int) (y / height);  
	                    if (index > -1 && index < indexStr.length) {// 防止越界  
	                        String key = indexStr[index];  
	                        if (selector.containsKey(key)) {  
	                            int pos = selector.get(key);  
	                            if (listView.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。  
	                                listView.setSelectionFromTop(  
	                                        pos + listView.getHeaderViewsCount(), 0);  
	                            } else {  
	                                listView.setSelectionFromTop(pos, 0);// 滑动到第一项  
	                            }  
	                            tv_show.setVisibility(View.VISIBLE);  
	                            tv_show.setText(indexStr[index]);  
	                        }  
	                    }  
	                    switch (event.getAction()) {  
	                    case MotionEvent.ACTION_DOWN:  
	                        layoutIndex.setBackgroundColor(Color  
	                                .parseColor("#606060"));  
	                        break;  
	  
	                    case MotionEvent.ACTION_MOVE:  
	  
	                        break;  
	                    case MotionEvent.ACTION_UP:  
	                        layoutIndex.setBackgroundColor(Color  
	                                .parseColor("#00ffffff"));  
	                        tv_show.setVisibility(View.GONE);  
	                        break;  
	                    }  
	                    return true;  
	                }  
	            });  
	        }  
	    }  
	private String[] sortIndex(List<City> persons2) {
		TreeSet<String> set = new TreeSet<String>();  
        // 获取初始化数据源中的首字母，添加到set中  
        for (City person : persons) {  
            set.add(person.getPinYinName().substring(  
                    0, 1));  
        }  
        // 新数组的长度为原数据加上set的大小  
        String[] names = new String[persons.size() + set.size()];  
        int i = 0;  
        for (String string : set) {  
            names[i] = string;  
            i++;  
        }  
        String[] pinYinNames = new String[persons.size()];  
        for (int j = 0; j < persons.size(); j++) {  
            
            pinYinNames[j] = persons.get(j).getPinYinName();  
        }  
        // 将原数据拷贝到新数据中  
        System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);  
        // 自动按照首字母排序  
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);  
        return names;  
	}
	private void sortList(String[] allNames) {
		// TODO Auto-generated method stub
		for (int i = 0; i < allNames.length; i++) {  
            if (allNames[i].length() != 1) {  
                for (int j = 0; j < persons.size(); j++) {  
                    if (allNames[i].equals(persons.get(j).getPinYinName())) {  
                        City p = new City(persons.get(j).getName(), persons  
                                .get(j).getPinYinName());  
                        newPersons.add(p);  
                    }  
                }  
            } else {  
                newPersons.add(new City(allNames[i]));  
            }  
        }  
		
	}
	private void setData() {
		// TODO Auto-generated method stub
      
       
	}
Thread t2=new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				final JSONObject jsonObj = new JSONObject();
				HttpContectionUtil conn = new HttpContectionUtil();
			     myStatus = (MyStatus) getApplication();
				String uri =myStatus.getUrlString() + "select_place.php";
				String responseStr = conn.ConnForResult(uri, jsonObj);
				city=City.parseJsonArray(responseStr);
				
				runOnUiThread( new Runnable() {
					public void run() {
						iniHOT(city);
						inilist(city); 
					}
				});
				 
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			
		}
	});
     
}
