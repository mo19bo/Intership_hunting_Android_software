package adapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import adapter.InternItemsLVAdapter.ViewHolder;
import adt.Article;
import adt.Intern;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqq.R;
import com.squareup.picasso.Picasso;

import function.Utils;
import function.Utils.OnLoadImageListener;

public class ArticleLvAdapter extends BaseAdapter {


	
	
	private  View view ;
	private ViewHolder viewHolder;
	private List<Article> list_Articleitem_1;
	  private LayoutInflater mInflater;
	private Context context; 
	  public ArticleLvAdapter(Context context,List<Article> list_internitem) {
		// TODO Auto-generated constructor stub
		   this.mInflater = LayoutInflater.from(context);  
		this.context=context;
	     	list_Articleitem_1=list_internitem;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(this.list_Articleitem_1==null){
			return 0;
			}
			else{
				return list_Articleitem_1.size();
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
			 view = mInflater.inflate(R.layout.item10_article_item, null); 
			 viewHolder.articlelogo=(ImageView)view.findViewById(R.id.coverimg);
			viewHolder.articletitle=(TextView)view.findViewById(R.id.article_title);
			
			 view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		
		
		
		
			
		Article articleitems=list_Articleitem_1.get(arg0);
		
		Picasso.with(context)
	      .load(articleitems.getLogo_url())
	      .placeholder(R.drawable.ic_launcher)
	      .error(R.drawable.ic_launcher)
	      .into(viewHolder.articlelogo);
		
	     viewHolder.articletitle.setText(articleitems.getArticleTitle());
		
		
		
	
		
		 
		return view;
	}
	
	 public static class ViewHolder {
		   private ImageView articlelogo;
		
			private TextView articletitle;
			
		}

}
