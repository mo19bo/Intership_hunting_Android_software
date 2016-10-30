package com.example.qqq;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import function.Utils;
import function.Utils.OnLoadImageListener;

import adapter.MyStatus;
import adt.Article;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EassyAusles_Page_Detail extends Activity {

	private ActionBar actionBar;
	private Button shareBtn;
	private MyStatus mystatus;
	private Article article;
	private ImageView image_cover;
	private TextView article_info;
	private TextView article_title;
	private TextView article_contents;
	private TextView actiontitle;
	private Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eassy_ausles__page__detail);
		 mystatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		
		
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局
	        actiontitle=(TextView)findViewById(R.id.actionbar_title);
	       
	      iniUI();
		inidata();
		iniClick();
	}

	private void iniClick() {
		// TODO Auto-generated method stub
		//shareBtn.setOnClickListener(new Onclick());
		backBtn.setOnClickListener(new Onclick());
	
		
	
	}
	public class Onclick implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			switch (arg0.getId()) {
		
			case R.id.back:
				finish();
				break;
			case R.id.share_button:
			share();
				
			   break;
			default:
				
				
				break;
			}
		}
		
		
		
	}
	private void inidata() {
		// TODO Auto-generated method stub
		Intent intent = this.getIntent();
		  Bundle bundle=intent.getExtras();  
          article=(Article) bundle.getSerializable("article");
          
        try {
  			Utils.onLoadImage(new URL(article.getLogo_url()), new OnLoadImageListener() {  
  				    public void OnLoadImage(Bitmap bitmap, String bitmapPath) {  
  				        // TODO Auto-generated method stub  
  				        if(bitmap!=null){  
  				        	image_cover.setImageBitmap(bitmap);  
  				        }  
  				    }  
  				});
  		} catch (MalformedURLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
        
        article_info.setText(article.getCategory_label()+" | "+article.getComment_num()+"COMMENTS");
       
        
        actiontitle.setText(article.getCategory_label());
        
        article_title.setText(article.getArticleTitle());
        article_contents.setText(article.getArticleContent());
 
      
	}

	public void share() {
		// TODO Auto-generated method stub
		String imgPath="/qqq/assets/icon.png";
		  Intent intent=new Intent(Intent.ACTION_SEND);   
        intent.setType("image/*");   
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");   
        intent.putExtra(Intent.EXTRA_TITLE, "请选择分享"); 
        
        intent.putExtra(Intent.EXTRA_TEXT, "http://blog.shixiseng.com/"+article.getCategory_label()+"/"+article.getIdString()+getResources().getString(R.string.share_content_tag).toString()); 
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/*");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
      
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
        startActivity(Intent.createChooser(intent, getTitle()));   
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		//顶部actionbar 初始化
		shareBtn=(Button)findViewById(R.id.share_button);
		backBtn=(Button)findViewById(R.id.back);
		
		
		
		
		image_cover=(ImageView)findViewById(R.id.image_cover);
		article_info=(TextView)findViewById(R.id.article_info);
		article_title=(TextView)findViewById(R.id.article_title);
		article_contents=(TextView)findViewById(R.id.article_content);
		
		
		
		
	}
	
	
	
}
