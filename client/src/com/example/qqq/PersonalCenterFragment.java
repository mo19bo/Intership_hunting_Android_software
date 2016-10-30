package com.example.qqq;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import adapter.MyStatus;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import compsite.tips;

@SuppressWarnings("deprecation")
public class PersonalCenterFragment extends android.support.v4.app.Fragment {
    
	private View view;
	private Button btn1,btn2,btn3,btn4,btn5,btn6;
	@SuppressWarnings("unused")
	private LinearLayout imageLayout;
	private ImageView avatorimg;
	private TextView nametitleTextView, sliceUserTextView;
	Context context;
	LocalActivityManager manager = null;
	MyStatus myStatus;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
				
		view = inflater.inflate(R.layout.personal_center_fragment,container,false);
	    context=getActivity();
	    manager = new LocalActivityManager(getActivity(), true);
        manager.dispatchCreate(savedInstanceState);
        myStatus=(MyStatus)getActivity().getApplication();
	
	        
	        
			initUI();
	
			// TODO Auto-generated catch block
		
		
		return view;
		
	}
	public void getdataini() throws MalformedURLException {
		
		
		nametitleTextView.setText(myStatus.getRealname().toString());
		sliceUserTextView.setText(myStatus.getSchool().toString());
		
		
		Transformation transformation = new RoundedTransformationBuilder()
        .borderColor(Color.WHITE)
        .borderWidthDp((float)0.2)
        .cornerRadiusDp(20)
        .oval(false)
        .build();
		 Picasso.with(context)
		      .load(myStatus.getPhoto_url()/*adList.get(arg0).getImg_url()*/)
		      .placeholder(R.drawable.avator)
		      .transform(transformation)
		      .error(R.drawable.avator)
		      .transform(transformation)
		      .into(avatorimg);
	
         
        	    // TODO Auto-generated method stub  
		
		
	}
	public void initUI() {
		btn1=(Button)view.findViewById(R.id.deliveryrecord);
		btn2=(Button)view.findViewById(R.id.fill_resume);
		btn3=(Button)view.findViewById(R.id.favorite_jobs);
		btn4=(Button)view.findViewById(R.id.favorite_company);
		btn5=(Button)view.findViewById(R.id.subcription);
		btn6=(Button)view.findViewById(R.id.setttings);
		
		imageLayout=(LinearLayout)view.findViewById(R.id.linerlayout_img);
		avatorimg=(ImageView)view.findViewById(R.id.avator);
		
		nametitleTextView=(TextView)view.findViewById(R.id.Usertitle);
		sliceUserTextView=(TextView)view.findViewById(R.id.slice_brief);
		nametitleTextView.setText("暂时还没有昵称哦");
		btn1.setOnClickListener(new btnclick());
		btn2.setOnClickListener(new btnclick());
		btn3.setOnClickListener(new btnclick());
		btn4.setOnClickListener(new btnclick());
		btn5.setOnClickListener(new btnclick());
		btn6.setOnClickListener(new btnclick());
		
		avatorimg.setOnClickListener(new btnclick());
		try {
			getdataini();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class btnclick implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()){
			case R.id.deliveryrecord:  
				Intent intent = new Intent();  
				   intent.setClass(getActivity(), DeliverRecordActivity.class);
					startActivity(intent); 
                break;  
            case R.id.fill_resume:  
            	  Intent intent2 = new Intent();  
				   intent2.setClass(getActivity(),  ResumeModifyActivity.class);
					startActivity(intent2); 
                break;  
            case R.id.favorite_jobs:  
            	Intent intent3 = new Intent();  
				   intent3.setClass(getActivity(), FavoriteJobsActivity1.class);
					startActivity(intent3); 
                break;
            case   R.id.favorite_company:  
            	Intent intent4 = new Intent();  
				   intent4.setClass(getActivity(), FavoriteCompanyActivity.class);
					startActivity(intent4);
                break;
            case   R.id.subcription:
            	Intent intent5 = new Intent();  
				   intent5.setClass(getActivity(), Tips2Activity.class);
					startActivity(intent5);
            	
            	break;
            case   R.id.avator:
            	Intent intent6=new Intent();
				intent6.setClass(getActivity(), PersonalInfoActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("flag", 0);
				intent6.putExtras(bundle);
				startActivity(intent6);
            	break;
            case R.id.setttings:
            	Intent intent7 = new Intent();  
				   intent7.setClass(getActivity(),SysSettingActivity.class);
					startActivity(intent7);
            default:  
                break;  
			
			
			
			
			
			}
			
			
		}
			// TODO Auto-generated method stub

		}
     
	private Bitmap decodeUriAsBitmap(Uri uri){
    	
	    Bitmap bitmap = null;
	
	    try {
	
	        bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
	
	    } catch (FileNotFoundException e) {
	
	        e.printStackTrace();
	
	        return null;
	
	    }
	
	    return bitmap;
	
	}   
}
