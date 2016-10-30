package adt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Article implements Serializable {
     private String logo_url;
     public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_label() {
		return category_label;
	}
	public void setCategory_label(String category_label) {
		this.category_label = category_label;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getView_num() {
		return view_num;
	}
	public void setView_num(int view_num) {
		this.view_num = view_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	private String articleTitle;
     private String articleContent;
     private String category_id;
     private String category_label;
     private int comment_num;
     private int view_num;
     private String status;
     private String idString;
     //private static String URL="http://192.168.0.17:80/1/";
	public Article() {
		// TODO Auto-generated constructor stub
	}
	public static List<Article> parseJsonArray(String responseStr) {
		// TODO Auto-generated method stub
		List<Article> ArticleList = new ArrayList<Article>();
		try {
			JSONObject jsonObj_data= new JSONObject(responseStr);
			JSONArray internItems =jsonObj_data.getJSONArray("data");
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Article Articleitem = new Article();
				 
				Articleitem.setArticleContent(jsonObj.getString("content"));
				Articleitem.setArticleTitle(jsonObj.getString("titile"));
				Articleitem.setCategory_id(jsonObj.getString("category_id"));
				Articleitem.setCategory_label(jsonObj.getString("label"));

				Articleitem.setComment_num(jsonObj.getInt("comment_num"));
				Articleitem.setIdString(jsonObj.getString("sid"));
				Articleitem.setLogo_url(new staticurl().getURL()+jsonObj.getString("cover_img"));
				Articleitem.setView_num(jsonObj.getInt("view_num"));
	            
	            
				ArticleList.add(Articleitem);
			}
			return ArticleList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
