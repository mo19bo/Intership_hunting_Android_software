package adapter;

import java.util.List;

import com.example.qqq.R;
import adt.DeliverResume;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class DeliverResumeAdapter extends BaseAdapter{
    
	private List<DeliverResume> list1;
    

	private LayoutInflater mInflater;
	private int pos;
	
	private String intern_id;
	public DeliverResumeAdapter(Context context,List<DeliverResume> list) {
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
		ViewHolder viewHolder = null;
		if (v == null) {
			v = mInflater.inflate(R.layout.item9_deliver_resume, null);
			viewHolder = new ViewHolder();
			viewHolder.deliver_resume_job=(TextView)v.findViewById(R.id.deliver_resume_job);
			viewHolder.deliver_resume_time=(TextView)v.findViewById(R.id.deliver_resume_time);
			viewHolder.deliver_resume_com=(TextView)v.findViewById(R.id.deliver_resume_com);
			viewHolder.deliver_tag=(TextView)v.findViewById(R.id.deliver_tag);
			viewHolder.deliver_resume=(TextView)v.findViewById(R.id.deliver_resume);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}	
        DeliverResume deliverResume=new DeliverResume();
	    
	    deliverResume=list1.get(arg0);
	    intern_id=deliverResume.getIntern_ID();
	    viewHolder.deliver_resume_job.setText(deliverResume.getDeliverjob());
	    viewHolder.deliver_resume_time.setText(deliverResume.getDelivertime());
	    viewHolder.deliver_resume_com.setText(deliverResume.getDelivercomname());
	    viewHolder.deliver_tag.setText(converttolabel(deliverResume.getDelivertag()));
	   
	    viewHolder.deliver_resume.setText("在线简历");
	    
		return v;
	}
	private String converttolabel(int delivertag) {
		// TODO Auto-generated method stub
		String result = null;
		switch (delivertag) {
		case 2:
			result="被查看";
			break;
         
		
		case 4:
			result="通知面试";
			break;
		case 5:
			result="不合适";
			break;
		case 1:
			result="投递成功";
			break;
		default:
			result="投递成功";
			break;
		}
		
		return result;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
    public static class ViewHolder {
		
		private TextView deliver_resume_job;
		private TextView deliver_resume_time;
		private TextView deliver_resume_com;
		private TextView deliver_tag;
		private TextView deliver_resume;
	}
	
	 

	
}
