package adapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.example.qqq.R;
import com.squareup.picasso.Picasso;

import function.Utils;
import function.Utils.OnLoadImageListener;
import adt.Company;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
@SuppressLint("ViewHolder")
public class CompanyItemsLVAdapter extends BaseAdapter {

	private ViewHolder viewHolder;
	private View view;
	private List<Company> list_internitem_2;
	  private LayoutInflater mInflater; 
	  Context context;
	public CompanyItemsLVAdapter(Context context,List<Company> list_internitem1) {
		// TODO Auto-generated constructor stub
		
		 this.mInflater = LayoutInflater.from(context);  
			this.context=context;
			list_internitem_2=list_internitem1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(this.list_internitem_2==null){
			return 0;
			}
			else{
				return list_internitem_2.size();
			}
		
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
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
			 view = mInflater.inflate(R.layout.company_item, null); 
			 viewHolder.comlogo=(ImageView)view.findViewById(R.id.com_logo1);
		
			 viewHolder.comname=(TextView)view.findViewById(R.id.com_name1);
			 viewHolder.cominfo=(TextView)view.findViewById(R.id.com_info);
		
			 view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		
		
		
		
			
		Company internitems=list_internitem_2.get(arg0);
        String img_urlString="";
		if(internitems.getLogo_url().isEmpty()){
			
			img_urlString="http://115.29.44.82:81/jd.png";
			
		}else{
			
			img_urlString=internitems.getLogo_url();
		}
		Picasso.with(context)
	      .load(img_urlString)
	      .placeholder(R.drawable.ic_launcher)
	      .error(R.drawable.ic_launcher)
	      .into(viewHolder.comlogo);
	
		
		 

		 viewHolder.comname.setText(internitems.getCom_nameString());
		 viewHolder.cominfo.setText(internitems.getCom_industry()+"/"+internitems.getCom_scale()+"人/");
		
		
	
		
		 
		return view;
	}
	 public static class ViewHolder {
		   public TextView cominfo;

		private ImageView comlogo;
		  
			private TextView comname;
		
		}

}
