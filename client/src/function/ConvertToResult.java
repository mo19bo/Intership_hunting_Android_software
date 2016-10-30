package function;

import com.example.qqq.R.id;

public class ConvertToResult {
    String srcString;
    String desString;
	public ConvertToResult() {
		// TODO Auto-generated constructor stub
	}
	public String convertToEarnningint(String src){
		String resultString="";
		if (src.equals("50以下")) {
			
			resultString="0";
			
		} else if(src.equals( 50+"-"+100)){
			
			resultString="1";
			
		}else if(src.equals( 100+"-"+150)){
			
			resultString="2";
			

		}else if(src.equals(150+"-"+200)){
			
			resultString="3";
			
		}else if(src.equals(200+"-"+300)){
			
			resultString="4";
			
		}else if(src.equals(300+"以上")){
			
			resultString="5";
			
		}else {
			
			resultString="6";
			
		}
		
		
		
		
		
		
		return resultString;
	}
	public String converttostring(String src){
		String resultString="";
		if (src.equals("0")) {
			
			resultString="50以下";
			
		} else if(src.equals("1")){
			
			resultString=50+"-"+100;
			
		}else if(src.equals("2")){
			
			resultString=100+"-"+150;
			

		}else if(src.equals("3")){
			
			resultString=150+"-"+200;
			
		}else if(src.equals("4")){
			
			resultString=200+"-"+300;
			
		}else if(src.equals("5")){
			
			resultString=300+"以上";
			
		}else {
			
			resultString="";
			
		}
		
		
		
		
		
		
		return resultString;
		
		
	}
	

}
