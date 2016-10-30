package adt;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class City {
	private String name;  
    private String pinYinName;  
     private int isHot;
    public City(String name) {  
        super();  
        this.name = name;  
    }  
  
    public City() {  
        super();  
   
    }  
  
    public City(String name2, String pinYinName2) {
		// TODO Auto-generated constructor stub
    	this.name=name2;
    	this.pinYinName=pinYinName2;
	}

	public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getPinYinName() {  
        return pinYinName;  
    }  
  
    public void setPinYinName(String pinYinName) {  
        this.pinYinName = pinYinName;  
    }

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
    private String fisrtletter;
	public static List<City> parseJsonArray(String responseStr) {
		// TODO Auto-generated method stub
		List<City> CityList = new ArrayList<City>();
		try {
			JSONArray internItems = new JSONArray(responseStr);
			for (int i = 0; i <  internItems.length(); i++) {
				JSONObject jsonObj = internItems.getJSONObject(i);

				City city = new City();
				 
				city.setIsHot(jsonObj.getInt("is_hot"));
				city.setName(jsonObj.getString("place"));
				city.setFisrtletter(jsonObj.getString("first_letter"));
				city.setPinYinName(jsonObj.getString("first_letter")+"abxcde");
			
	            
	            
				CityList.add(city);
			}
			return CityList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getFisrtletter() {
		return fisrtletter;
	}

	public void setFisrtletter(String fisrtletter) {
		this.fisrtletter = fisrtletter;
	}  
}
