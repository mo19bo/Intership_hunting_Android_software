package adapter;

import java.util.List;

import com.example.qqq.R;

import adt.City;
import adt.Eduexperience;
import adt.Internship;
import adt.Schoolexp;
import android.R.menu;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class SchoolAdapter extends BaseAdapter{
    
	private Context context1;
	private List<Schoolexp> list1;

	private TextView textView2_content;
	private TextView textView3_tag;

	private LayoutInflater mInflater;
	private int pos;
	public SchoolAdapter(Context context,List<Schoolexp> list) {
		// TODO Auto-generated constructor stub
		 this.mInflater = LayoutInflater.from(context); 
		this.list1 = list;
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.list1.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
   
	@SuppressLint("InflateParams")
	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		    v = mInflater.inflate(R.layout.item7_schoolexp, null); 
		  
		   textView3_tag=(TextView)v.findViewById(R.id.textView_tag);
		   textView2_content=(TextView)v.findViewById(R.id.textView3_content);
			
			
		setPos(arg0);	
		Schoolexp schoolexp=new Schoolexp();
		schoolexp=list1.get(arg0);

		textView3_tag.setText(schoolexp.getTag());
		textView2_content.setText(schoolexp.getContentString());
	 	
		
	
		
		return v;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	 

	
}
