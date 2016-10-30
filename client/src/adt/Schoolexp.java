package adt;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Schoolexp {

	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getContentString() {
		return contentString;
	}
	public void setContentString(String contentString) {
		this.contentString = contentString;
	}
	
	public Schoolexp(String contentString, String tag) {
		super();
		this.contentString = contentString;
		this.tag = tag;
	}
	private String tag;
	public Schoolexp() {
		super();
	}
	private String contentString;
	private String idString;
	public static List<Schoolexp> parseJsonArray(String jsonStr) {
		// TODO Auto-generated method stub
		List<Schoolexp> SchoolexpList = new ArrayList<Schoolexp>();
		try {
			JSONArray internItems = new JSONArray(jsonStr);
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				Schoolexp schoolexpitem = new Schoolexp();
				schoolexpitem.setIdString(jsonObj.getString("sid"));
				schoolexpitem.setTag(jsonObj.getString("exp_type"));
				schoolexpitem.setContentString(jsonObj.getString("exp_info"));
	
				
                
                
				SchoolexpList.add(schoolexpitem);
			}
			return SchoolexpList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		
	
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}

	
}
