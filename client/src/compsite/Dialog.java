package compsite;

import java.util.Calendar;

import com.example.qqq.PersonalInfoActivity;
import com.example.qqq.R;

import function.RegexJudge;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Dialog extends Activity {
	
	
	protected int Month;
	protected int Year;
	protected int Day;
	protected TextView textView;
	protected TextView textView1;
	
	public void textedit_dialog(String title,final TextView textView,int checktype,final Activity activity) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
       // builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle(title);
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(activity).inflate(R.layout.username_editdig, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);
        final RegexJudge regexJudge=new RegexJudge();
        final EditText username1 = (EditText)view.findViewById(R.id.editText1_username1);
        final int flag=checktype;
        
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @SuppressLint("NewApi")
			@Override
            public void onClick(DialogInterface dialog, int which)
            {
                String a = username1.getText().toString().trim();
                if(a.isEmpty()){
                	 Toast.makeText(activity, "不能为空" , Toast.LENGTH_SHORT).show();
                	  
                }
                else if(!regexJudge.RegexJudge1(flag, a)){
                	
                	 Toast.makeText(activity, "格式不正确" , Toast.LENGTH_SHORT).show();
                }
                else{
                	
                	
                	  textView.setText(a);
                }
              
                
               
            } 
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
        builder.show();
	}

	
      public String date_dialog(Activity activity,TextView textView,int flag) {
		
		// TODO Auto-generated method stub
		Calendar calendar=Calendar.getInstance();
		  Year = calendar.get(Calendar.YEAR);  
	       Month = calendar.get(Calendar.MONTH);  
	       Day = calendar.get(Calendar.DAY_OF_MONTH); 
	 textView1=textView;
	   if(flag==1){
		DatePickerDialog datePickerDialog=new DatePickerDialog(activity, mDateSetListener, Year, Month, Day);
		datePickerDialog.show();
		return Year+"";
	   }
	   else if(flag==2){
		   DatePickerDialog datePickerDialog=new DatePickerDialog(activity, mDateSetListener1, Year, Month, Day);
		   datePickerDialog.show();
		   return Year+"-"+Month;
	   }else if(flag==3){
		   DatePickerDialog datePickerDialog=new DatePickerDialog(activity, mDateSetListener2, Year, Month, Day);
		   datePickerDialog.show();
		   return Year+"-"+Month+"-"+Day;
	   }
	    
		return "";
		
	}
      private OnDateSetListener mDateSetListener=new OnDateSetListener() {
  		
  	

		@Override
  		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
  			// TODO Auto-generated method stub
  			Year=arg1;
  			Month=arg2+1;
  			Day=arg3;
  			textView1.setText(Year+"年");
  		}
		
  	};
  	private OnDateSetListener mDateSetListener1=new OnDateSetListener() {
  		
  	  	

		@Override
  		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
  			// TODO Auto-generated method stub
  			Year=arg1;
  			Month=arg2+1;
  			Day=arg3;
  			textView1.setText(Year+"-"+Month);
  		}
		
		
  	};
	private OnDateSetListener mDateSetListener2=new OnDateSetListener() {
  		
  	  	

		@Override
  		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
  			// TODO Auto-generated method stub
  			Year=arg1;
  			Month=arg2+1;
  			Day=arg3;
  			textView1.setText(Year+"-"+Month+"-"+Day);
  			
  		}
  	};
	protected String sextemp;  	@SuppressLint("NewApi")
	public void sex_dialog(Activity activity,final TextView text, final String sex1[] ){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
  
        builder.setTitle("请选择性别");
         String sex1String;
         final String sex[]= sex1 ;
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合
         * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */
        builder.setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            	sextemp=sex[which].toString();
            	if(sextemp.isEmpty()){
            		sextemp=sex[0].toString();
            	}else{
            		text.setText(sextemp);
            	}
                
              
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
              text.setText(sextemp);
         
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
        builder.show();
		
	}
	
}

