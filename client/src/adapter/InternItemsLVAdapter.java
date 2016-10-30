package adapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


import com.example.qqq.R;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import function.Utils;
import function.Utils.OnLoadImageListener;


import adt.Intern;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

@SuppressLint("ViewHolder")
public class InternItemsLVAdapter extends BaseAdapter  {
    
	
	
	
	
	private  View view ;
	private ViewHolder viewHolder;
	private List<Intern> list_internitem_1;
	  private LayoutInflater mInflater; 
	  Context context;
	  public InternItemsLVAdapter(Context context,List<Intern> list_internitem) {
		// TODO Auto-generated constructor stub
		   this.mInflater = LayoutInflater.from(context);  
		  this.context=context;
	     	list_internitem_1=list_internitem;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_internitem_1.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return  arg0;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		viewHolder = null;
		view=v;
		if (view == null) {
			 viewHolder = new ViewHolder();
			 view = mInflater.inflate(R.layout.ss, null); 
			 viewHolder.comlogo=(ImageView)view.findViewById(R.id.comlogo_LV);
			viewHolder.jobtext=(TextView)view.findViewById(R.id.job_LV);
			 viewHolder.comname=(TextView)view.findViewById(R.id.comname_LV);
			 viewHolder.city=(TextView)view.findViewById(R.id.city_LV);
			 viewHolder.dayweeks=(TextView)view.findViewById(R.id.dayweeks_LV);
			 viewHolder.pubdate=(TextView)view.findViewById(R.id.pubdata_LV);
			 viewHolder.salary=(TextView)view.findViewById(R.id.salary_LV);
			 view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		
		
		
		
			
		Intern internitems=list_internitem_1.get(arg0);
		String img_urlString="";
	
	
			
        img_urlString=internitems.getCom_logo_url();
		
	
		
	
		Picasso.with(context)
	      .load(img_urlString)
	      .placeholder(R.drawable.holderpalcer)
	      .error(R.drawable.holderpalcer)
	   
	      .into(viewHolder.comlogo);
	
		
		 
	     viewHolder.jobtext.setText(internitems.getJob());
		 viewHolder.comname.setText(internitems.getCom_nameString());
		 viewHolder.city.setText(internitems.getCityString());
		 viewHolder.dayweeks.setText(internitems.getDayweek());
		 viewHolder.pubdate.setText(internitems.getPub_dateString());
		 viewHolder.salary.setText(internitems.getSalaryString());
		
		
	
		
		 
		return view;
	}
	
	 public static class ViewHolder {
		   private ImageView comlogo;
		  private TextView jobtext;
			private TextView comname;
			private TextView city;
			private TextView dayweeks;
			private TextView pubdate;
			private TextView salary;
		
		}
	
	
	

	
}
