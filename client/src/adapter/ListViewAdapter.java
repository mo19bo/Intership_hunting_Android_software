package adapter;



import java.util.List;

import com.example.qqq.R;

import compsite.CitySelector;

import adt.City;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ListViewAdapter extends BaseAdapter {
	private Context context;
	private List<City> list;
	private ViewHolder viewHolder;

	public ListViewAdapter(Context context, List<City> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		if(this.list==null){
			return 0;
			}
			else{
				return this.list.size();
			}
	}
	

	@Override
	public Object getItem(int position) {
		if(this.list==null){
			return null;
			}
			else{
				return list.get(position).getName();
			}
	
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if (list.get(position).getName().length() == 1)// 如果是字母索引
			return false;// 表示不能点击
		return super.isEnabled(position);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String item = list.get(position).getName();
		viewHolder = new ViewHolder();
		if (item.length() == 1) {
			convertView = LayoutInflater.from(context).inflate(R.layout.index,
					null);
			viewHolder.indexTv = (TextView) convertView
					.findViewById(R.id.indexTv);
			
			
		} else {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_city,
					null);
			viewHolder.itemTv = (TextView) convertView
					.findViewById(R.id.itemTv);
			
			
		}
		if (item.length() == 1) {
			viewHolder.indexTv.setText(list.get(position).getName());
		} else {
			viewHolder.itemTv.setText(list.get(position).getName());
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView indexTv;
		private TextView itemTv;
	}

}
