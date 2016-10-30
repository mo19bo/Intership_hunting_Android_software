package adapter;

import java.util.List;

import adt.advertisement;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.qqq.AdvertisementActivity;
import com.example.qqq.R;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


	//定义继承BaseAdapter的匹配器   
	 public class ImageAdapter extends BaseAdapter {   

	        //Item的修饰背景   
	        int mGalleryItemBackground;   

	        //上下文对象   
	        private Context mContext;   
	        private List<advertisement> adList;

			private int position;

			private int width;

			private int height;
	        public ImageAdapter(Context context,
					List<advertisement> adList) {
				// TODO Auto-generated constructor stub
	        	
	        	mContext=context;
	        	this.adList=adList;
			}

			//图片数组    

	    

	        //返回项目数量   
	        @Override  
	        public int getCount() {   
	                return adList.size();   
	        }   

	        //返回项目   
	        @Override  
	        public Object getItem(int position) {   
	                return position;   
	        }   

	        //返回项目Id   
	        @Override  
	        public long getItemId(int position) {   
	                return position;   
	        }   

	        //返回视图   
	     


			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				 ImageView iv = new ImageView(mContext);  
					position=arg0;
					 Transformation transformation = new RoundedTransformationBuilder()
			          .borderColor(Color.WHITE)
			          
			          .oval(false)
			          .build();
				 Picasso.with(mContext)
				      .load(adList.get(arg0).getImg_url())
				      .placeholder(R.drawable.loadingpage)
				      .error(R.drawable.loadingpage)
				      .transform(transformation)
				      .into(iv);
				
	            
	             //给生成的ImageView设置Id，不设置的话Id都是-1   
				 DisplayMetrics dm = new DisplayMetrics();
				 ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
				width = dm.widthPixels;//宽度
				 height = dm.heightPixels ;//高度
	            iv.setLayoutParams(new Gallery.LayoutParams(1000,450));   
	            iv.setScaleType(ImageView.ScaleType.FIT_XY);   
	             iv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View x) {
						// TODO Auto-generated method stub
						//Uri uri = Uri.parse(adList.get(position).getTarget());  
					    //Intent it = new Intent(Intent.ACTION_VIEW, uri);  
					    // mContext.startActivity(it);
					}
				});
	             
	             return iv;
	             
			}  
		}


