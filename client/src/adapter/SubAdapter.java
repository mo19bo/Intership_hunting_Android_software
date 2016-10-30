package adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.qqq.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater layoutInflater;
	
    List<String[]> cities=new ArrayList<String[]>()  ;
	public int foodpoition;
	private int selectedPosition=-1;

	public SubAdapter(Context context, int position) {
		this.context = context;



	String[]     String1=this.context.getResources().getStringArray(R.array.array_sub_1);
	String[]	String2=this.context.getResources().getStringArray(R.array.array_sub_2);
	String[]	String3=this.context.getResources().getStringArray(R.array.array_sub_3);
	String[]	String4=this.context.getResources().getStringArray(R.array.array_sub_4);
	String[]	String5=this.context.getResources().getStringArray(R.array.array_sub_5);
	String[]	String6=this.context.getResources().getStringArray(R.array.array_sub_6);
	String[]	String7=this.context.getResources().getStringArray(R.array.array_sub_7);
	String[]	String8=this.context.getResources().getStringArray(R.array.array_sub_8);
	String[]	String9=this.context.getResources().getStringArray(R.array.array_sub_9);
	String[]	String10=this.context.getResources().getStringArray(R.array.array_sub_10);
	
  
   cities.add(String1);	 
   cities.add(String2);
   cities.add(String3);
   cities.add(String4);
   cities.add(String5);
   cities.add(String6);
   cities.add(String7);
   cities.add(String8);
   cities.add(String9);
   cities.add(String10);	
	
	
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.foodpoition = position;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  cities.get(foodpoition).length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cities.get(foodpoition)[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		final int location=position;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item2, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.textview1);
			viewHolder.layout=(LinearLayout)convertView.findViewById(R.id.colorlayout2);
	       
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		if(selectedPosition == position)   
	    {   
			viewHolder.textView.setTextColor(Color.BLUE);   

	    
			viewHolder.layout.setBackgroundColor(Color.LTGRAY);   
	   } else {   
		   viewHolder.textView.setTextColor(Color.WHITE);   
		   viewHolder.layout.setBackgroundColor(Color.TRANSPARENT);   
	     }
		
		
		if(position>=cities.get(foodpoition).length){
			
			return  convertView;
		}
			
			
			
		
		viewHolder.textView.setText(cities.get(foodpoition)[position]);
		viewHolder.textView.setTextColor(Color.BLACK);
		
		return convertView;
	}

	public static class ViewHolder{
		public TextView textView;
	
		public LinearLayout layout;
	}

	public void setSelectedPosition(int position) {   
	   selectedPosition = position;   
	}

}
