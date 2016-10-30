package adapter;
/*个人中心----教育经历的listview 的Adapter*/
import java.util.List;

import com.example.qqq.R;

import adt.City;
import adt.Eduexperience;
import android.R.menu;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ViewHolder")
public class listresume_adpater extends BaseAdapter{
    
	private Context context1;
	private List<Eduexperience> list1;
	private TextView content;
	private TextView date;
	private TextView textView2_degree;
	private TextView textView3_school;
	private TextView textView4_major;
	private LayoutInflater mInflater;
	private int pos;
	
	
	public listresume_adpater(Context context,List<Eduexperience> list) {
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
		if(this.list1==null){
			return null;
			}
			else{
				return this.list1.size();
			}
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
		 v = mInflater.inflate(R.layout.item5, null); 
		 date=(TextView)v.findViewById(R.id.textView_date);
			textView2_degree=(TextView)v.findViewById(R.id.textView2_degree);
			textView3_school=(TextView)v.findViewById(R.id.textView3_school);
			textView4_major=(TextView)v.findViewById(R.id.textView4_major);
			
			Eduexperience eduexperience=new Eduexperience();
			eduexperience=list1.get(arg0);
		
		date.setText(eduexperience.getStarttime()+"至"+eduexperience.getEndtime()+"年");
	    textView2_degree.setText(eduexperience.getDegree());
	    textView3_school.setText(eduexperience.getSchoolname());
	    textView4_major.setText(eduexperience.getMajor());
		
		
		return v;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	 

	
}
