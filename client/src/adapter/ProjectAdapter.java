package adapter;

import java.util.List;

import com.example.qqq.R;

import adt.City;
import adt.Eduexperience;
import adt.Internship;
import adt.Project;
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
public class ProjectAdapter extends BaseAdapter{
    
	private Context context1;
	private List<Project> list1;
    
	private TextView date3;
	private TextView textView2_projectname;
	private TextView textView3_role;

	private LayoutInflater mInflater;
	private int pos;
	public ProjectAdapter(Context context,List<Project> list) {
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
		    v = mInflater.inflate(R.layout.item8_project, null); 
		  
		   date3=(TextView)v.findViewById(R.id.textView_date2);
		   textView2_projectname=(TextView)v.findViewById(R.id.textView3_projectname);
		   textView3_role=(TextView)v.findViewById(R.id.textView4_playrole);
		   
			
			
		setPos(arg0);	
	    Project project=new Project();
	    
	    project=list1.get(arg0);

	    date3.setText(project.getBegin_years()+"-"+project.getBegin_month()
	    		+"至"+project.getEnd_year()+"-"+project.getEnd_month());
	    textView2_projectname.setText(project.getProjectname());
	    textView3_role.setText(project.getPlay_roleString());
		
	
		
		return v;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	 

	
}
