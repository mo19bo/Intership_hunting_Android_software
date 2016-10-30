package function;

import http.HttpContectionUtil;

import org.json.JSONObject;

import adapter.MyStatus;
import adt.Intern;
import android.widget.Toast;

import com.example.qqq.JobInfo_Activity;

public class DeletOfDatabase {

	private String idString;
	private String url;
	private String resultString;
	private String flag;


	
	public DeletOfDatabase(String idString, String url, String flag) {
		// TODO Auto-generated constructor stub
		super();
		this.idString = idString;
		this.url=url;
		this.flag=flag;
	}




	public String delete(){
		
		 new Thread(){
				
				
				

				public void run() {
	    			try {
	    			final JSONObject jsonObj = new JSONObject();
	    			
	    			jsonObj.put("id",idString);
	    		
	    			
	    			HttpContectionUtil conn = new HttpContectionUtil();
	    			
	   				String uri =url + "delete.php";
	   				String responseStr = conn.ConnForResult(uri,jsonObj);
	   				JSONObject response = new JSONObject(responseStr);
	   				resultString =  response.getString("result");
					
					
				
	   			
	   					
	    			} catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		}
	  		
				
				
			}.start();
			
			
		
		
		return resultString;
		
		
		
		
	}

}
