package adapter;

import java.util.List;

import com.example.qqq.R;

import adt.City;
import adt.Eduexperience;
import adt.Internship;
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


public class InternshipAdapter extends BaseAdapter{
    
	private Context context1;
	private List<Internship> list1;
	private TextView content;
	private TextView date;
	private TextView textView2_comname;
	private TextView textView3_ocuppation;

	private LayoutInflater mInflater;
	private int pos;
	public InternshipAdapter(Context context,List<Internship> list) {
		// TODO Auto-generated constructor stub
		 this.mInflater = LayoutInflater.from(context); 
		this.list1 = list;
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(this.list1==null){
			return 0;
			}
			else{
				return this.list1.size();
			}
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
		    v = mInflater.inflate(R.layout.item6_resumeitem, null); 
		   date=(TextView)v.findViewById(R.id.textView_date1);
		   textView3_ocuppation	=(TextView)v.findViewById(R.id.textView4_ocuppation);
		   textView2_comname=(TextView)v.findViewById(R.id.textView3_comname);
			
			
		setPos(arg0);	
		Internship internship=new Internship();
		internship=list1.get(arg0);
		date.setText(internship.getStarttime()+"-"+internship.getStartmonthtime()
				+"至"+internship.getEndtime()+"-"+internship.getEndmonthtime());
	    textView2_comname.setText(internship.getCompanyString());
	    textView3_ocuppation.setText(internship.getOccupationString());
	 	
		
	
		
		return v;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	 

	
}
