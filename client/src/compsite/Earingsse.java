package compsite;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.qqq.R;

import function.ConvertToResult;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Earingsse extends Activity{

	private ListView listview;
	 ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		 WindowManager.LayoutParams localLayoutParams =getWindow().getAttributes();
		 localLayoutParams.gravity = Gravity.BOTTOM|Gravity.LEFT|Gravity.RIGHT ;
	      localLayoutParams.height=getWindowManager().getDefaultDisplay().getWidth();
	        localLayoutParams.y = 0;
	       
	        setContentView(R.layout.earings); 
	           HashMap<String, Object> map0 = new HashMap<String, Object>();
	           HashMap<String, Object> map9 = new HashMap<String, Object>();
	            map9.put("ItemText", "不限");
	            listItem.add(map9);
	            map0.put("ItemText", 50+"以下");
	            listItem.add(map0);
	            HashMap<String, Object> map1 = new HashMap<String, Object>();
	            map1.put("ItemText", 50+"-"+100);
	            listItem.add(map1);
	            HashMap<String, Object> map2 = new HashMap<String, Object>();
	            map2.put("ItemText", 100+"-"+150);
	            listItem.add(map2);
	            HashMap<String, Object> map = new HashMap<String, Object>();
	            map.put("ItemText",150+"-"+200);
	            listItem.add(map);
	            HashMap<String, Object> map5 = new HashMap<String, Object>();
	            map5.put("ItemText",200+"-"+300);
	            listItem.add(map5);
	            HashMap<String, Object> map6= new HashMap<String, Object>();
	            map6.put("ItemText",300+"以上");
	            listItem.add(map6);
	            
	         
		listview=(ListView)findViewById(R.id.listView_earings);
		
		listview.setAdapter(new SimpleAdapter(this, listItem,R.layout.item4,new String[] {"ItemText"}, new int[]{R.id.textitem}));
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
                  Toast.makeText(getApplicationContext(), listItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(); 

               intent.putExtra("result",listItem.get(arg2).get("ItemText").toString());// 放入返回值 
               setResult(3, intent);// 
               finish();
			}
		});
}
}
