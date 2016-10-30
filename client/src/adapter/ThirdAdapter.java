package adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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

public class ThirdAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater layoutInflater;
	private int selectedPosition = -1; 
	List<List<String[]>> list=new ArrayList<List<String[]>>();
	
	
	String cities[][] = new String[][] {  
            new String[] {"全部美食", "本帮江浙菜", "川菜", "粤菜", "湘菜","东北菜","台湾菜","新疆/清真","素菜","火锅","自助餐","小吃快餐","日本","韩国料理",  
            "东南亚菜","西餐","面包甜点","其他"},  
            new String[] {"全部休闲娱乐","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全部购物", "综合商场", "服饰鞋包", "运动户外","珠宝饰品","化妆品","数码家电","亲子购物","家居建材"  
                ,"书店","书店","眼镜店","特色集市","更多购物场所","食品茶酒","超市/便利店","药店"},  
            new String[] {"全部休闲娱乐","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全","咖啡厅","酒吧","茶馆","KTV","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全部","咖啡厅","酒吧","茶馆","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全部休","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全部休闲","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全部休闲娱","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏"},  
            new String[] {"全部休闲娱乐","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏","更多休闲娱乐"},  
            new String[] {"全部休闲aaa","咖啡厅","酒吧","茶馆","KTV","电影院","游乐游艺","公园","景点/郊游","洗浴","足浴按摩","文化艺术",  
                    "DIY手工坊","桌球馆","桌面游戏"},  
            }; 
	public int foodpoition;
	private int foodpoitionsub;
	private List<String[]> listthis=new ArrayList<String[]>();
	  public ThirdAdapter(Context context, int position, int position2) {
		this.context = context;
		
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_1_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_1_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_1_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_1_4));
		
		list.add(0, listthis);
		listthis=new ArrayList<String[]>();;
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_2_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_2_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_2_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_2_4));
		list.add(1, listthis);
		listthis=new ArrayList<String[]>();;
		
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_4));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_5));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_6));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_3_7));
		list.add(2, listthis);
		listthis=new ArrayList<String[]>();;
		
		
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_4_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_4_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_4_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_4_4));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_4_5));
		list.add(3, listthis);
		listthis=new ArrayList<String[]>();;
		
		
		
		
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_5_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_5_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_5_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_5_4));
		list.add(4, listthis);
		listthis=new ArrayList<String[]>();;
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_6_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_6_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_6_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_6_4));
		list.add(5, listthis);
		listthis=new ArrayList<String[]>();;

		listthis.add(this.context.getResources().getStringArray(R.array.array_third_7_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_7_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_7_3));
		list.add(6, listthis);
		listthis=new ArrayList<String[]>();;
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_8_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_8_2));
		list.add(7, listthis);
		listthis=new ArrayList<String[]>();;
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_9_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_9_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_9_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_9_4));
		list.add(8, listthis);
		listthis=new ArrayList<String[]>();;
		
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_10_1));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_10_2));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_10_3));
		listthis.add(this.context.getResources().getStringArray(R.array.array_third_10_4));
		list.add(9, listthis);
		listthis=new ArrayList<String[]>();;
		
		
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.foodpoition = position;
		this.foodpoitionsub=position2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.get(foodpoition).get(foodpoitionsub).length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return  list.get(foodpoition).get(foodpoitionsub)[position];
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
			convertView = layoutInflater.inflate(R.layout.item3, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.textview3);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText( list.get(foodpoition).get(foodpoitionsub)[position]);
		viewHolder.textView.setTextColor(Color.BLACK);
		
		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
		
	}

}
