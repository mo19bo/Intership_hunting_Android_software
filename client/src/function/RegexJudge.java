package function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.qqq.NewsReminsActivity;

public class RegexJudge {

	

	

	public boolean RegexJudge1( int type,  String string) {
		switch (type) {
		case 1:
			return ismail(string);
		case 2:
			return ismoblienumber(string);
		case 3:
			return ispwd(string);
		default:
			return true;
			
		}
		
		
		
		
		// TODO Auto-generated constructor stub
	}
 
    public boolean ismail(String mail){
    	String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";   
        Pattern p = Pattern.compile(strPattern);   
        Matcher m = p.matcher( mail);   
        return m.matches();   
    	
    }
   public boolean ismoblienumber(String phonenumber){
	   String strPattern = "[1][358]\\d{9}";   
       Pattern p = Pattern.compile(strPattern);   
       Matcher m = p.matcher( phonenumber);   
       return m.matches();   
   	
   }
   public boolean ispwd(String pwd){
	   
	   String strPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";   
       Pattern p = Pattern.compile(strPattern);   
       Matcher m = p.matcher( pwd);   
       return m.matches();
   }

}
