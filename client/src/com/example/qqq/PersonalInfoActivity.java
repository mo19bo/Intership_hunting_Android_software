package com.example.qqq;

import http.HttpContectionUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qqq.ExceptedInternActivity.OnClick;

import function.RegexJudge;
import function.Tocircle;
import function.Utils;
import function.Utils.OnLoadImageListener;
import function.uploadphoto;
import adapter.MyStatus;
import android.R.integer;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfoActivity extends Activity {
     
	public static final int DATE_DIALOG = 0;
	private static final int IMAGE_REQUEST_CODE = 0;
	
	private static final int CAMERA_REQUEST_CODE =1;
	private static final String IMAGE_FILE_NAME ="header.png";
	private TextView username,usersex,usertel,useremailTextView,userschool,usergraduate_year;
	MyStatus myStatus;
	private String sextemp;
	private ImageView imgAvator;
	private int flag;
	private static String path="/sdcard/myHead/";

	private Object bmp;
	private ActionBar actionBar;
	private Button saveBtn;
	
	private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";
	private Uri urixx = Uri.parse(IMAGE_FILE_LOCATION);//The Uri to store the big bitmap
	private int Year,Month,Day;
	private TextView realname;
	private TextView userdegree;
	private TextView usermajor;
	private TextView ActionTitle;
	private int flag_intent;
	private String fileName;
	private int flag2=0;
	private Button backBtn;
	ExecutorService pool = Executors.newFixedThreadPool(10);
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		myStatus=(MyStatus)getApplication();
		actionBar=this.getActionBar();
		 actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbarstyle2);//自定义ActionBar布局 
	       Bundle bundle=this.getIntent().getExtras();
	       flag_intent=bundle.getInt("flag");
	       if(flag_intent==1){
	    	   ActionTitle=(TextView)findViewById(R.id.actionbar_title);
	    	   ActionTitle.setText("请先完善个人资料");
	    	   flag2=1;
	       }
	       
	      
	                
		   iniUI();

	
			try {
				inidata();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}
//Thread t1=new Thread() {
//		
//		
//
//		@SuppressLint("NewApi")
//		@Override
//		public void run() {
//			final JSONObject jsonObj = new JSONObject();
//			try {
//				jsonObj.put("username", myStatus.getUsername());
//				jsonObj.put("password", myStatus.getUserResume_id());
//				HttpContectionUtil conn = new HttpContectionUtil();
//			     myStatus = (MyStatus) getApplication();
//				String uri =myStatus.getUrlString() + "login.php";
//				String responseStr = conn.ConnForResult(uri, jsonObj);
//				
//				JSONObject response = new JSONObject( responseStr);
//				
//				
//				String result =  response.getString("result");
//				if ("failed".equals(result)) {
//					runOnUiThread(new Runnable(){
//						public void run(){
//							Toast.makeText(PersonalInfoActivity.this, "Data Failed", 0).show();
//						}
//					});
//				} else {
//					runOnUiThread(new Runnable(){
//						public void run(){
//							//Toast.makeText(PersonalInfoActivity.this, "Data Success", 0).show();
//						}
//					});
//					
//					// 解析并存到全局变量中
//				    JSONObject userObj = new JSONObject(responseStr);
//					myStatus.setUsername(userObj.getString("username"));
//					myStatus.setEmail(userObj
//							.getString("useremail"));
//					
//					
//					
//					myStatus.setPhonenumber(userObj
//							.getString("telphone"));
//				     myStatus.setRealname(userObj.getString("realname"));
//					
//					          if(userObj.get("gender").toString()==null){
//
//					              	myStatus.setSex("男");
//					                     }
//					                   else if(userObj.getInt("gender")==1){
//						              myStatus.setSex("男");
//					                    }
//					                    else{
//						                      myStatus.setSex("女");
//					                        }
//					myStatus.setSchool(userObj
//							.getString("school"));
//					myStatus.setMajor(userObj
//							.getString("major"));
//					myStatus.setDegree(userObj
//							.getString("degree"));
//					myStatus.setGraduate_year(userObj
//							.getString("graduate_year"));
//				    myStatus.setUser_ID(userObj
//							.getString("user_id"));
//				    myStatus.setUserResume_id(userObj
//							.getString("resume_id"));
//				    myStatus.setPhoto_url(userObj
//							.getString("user_img"));
//				    SharedPreferences settings = getSharedPreferences("userinfo", 0);
//			        SharedPreferences.Editor editor = settings.edit();
//			        editor.putString("userid", myStatus.getUser_ID());
//			        editor.putString("is_auth", "true");
//			        editor.commit();
//				    
//					
//					
//					
//					
//				}
//			} catch (final Exception e1) {
//				runOnUiThread(new Runnable(){
//					public void run(){
//						Toast.makeText(PersonalInfoActivity.this, "Login Failed"+e1.toString(), 0).show();
//					}
//				});
//			}
//		}
//	};
	

	private void inidata() throws MalformedURLException{
		// TODO Auto-generated method stub
		
		
		
		if(myStatus.getPhoto_url()==null){	
			return;
		}
		Utils.onLoadImage(new URL(myStatus.getPhoto_url()), new OnLoadImageListener() {  
		    public void OnLoadImage(Bitmap bitmap, String bitmapPath) {  
		        // TODO Auto-generated method stub  
		        if(bitmap!=null){  
		        	imgAvator.setImageBitmap(new Tocircle().createCircleImage(bitmap, bitmap.getHeight()-10)); 
		        	
		        }  
		    }  
		});
	    realname.setText(myStatus.getRealname());
		username.setText(myStatus.getUsername());
		usersex.setText(myStatus.getSex());
		usertel.setText(myStatus.getPhonenumber());
		useremailTextView.setText(myStatus.getEmail());
	    userschool.setText(myStatus.getSchool());
	    usergraduate_year.setText(myStatus.getGraduate_year());
	    userdegree.setText(myStatus.getDegree());
	    usermajor.setText(myStatus.getMajor());
	    
	}

	private void iniUI() {
		// TODO Auto-generated method stub
		imgAvator=(ImageView)findViewById(R.id.imageView1_avator);
		
		//actionbar的初始化
		saveBtn=(Button)findViewById(R.id.save_button1);
		backBtn=(Button)findViewById(R.id.back);
		
		
		
		realname=(TextView)findViewById(R.id.realname_text);
		username=(TextView)findViewById(R.id.username_text);
		usersex=(TextView)findViewById(R.id.sex_label);
		usertel=(TextView)findViewById(R.id.tel_text);
		useremailTextView=(TextView)findViewById(R.id.email_text);
		userschool=(TextView)findViewById(R.id.school_text);
		usergraduate_year=(TextView)findViewById(R.id.graduate_year_text);
		userdegree=(TextView)findViewById(R.id.degree_text);
		usermajor=(TextView)findViewById(R.id.major_text);
	
		
		
		backBtn.setOnClickListener(new OnClick());
		realname.setOnClickListener(new OnClick());
		username.setOnClickListener(new OnClick());
		usersex.setOnClickListener(new OnClick());
		usertel.setOnClickListener(new OnClick());
		useremailTextView.setOnClickListener(new OnClick());
		userschool.setOnClickListener(new OnClick());
		usergraduate_year.setOnClickListener(new OnClick());
		imgAvator.setOnClickListener(new OnClick());
		saveBtn.setOnClickListener(new OnClick());
	    userdegree.setOnClickListener(new OnClick());
	    usermajor.setOnClickListener(new OnClick());
		
	}
	
    @SuppressWarnings({ "unused", "unused" })
	@SuppressLint({ "NewApi", "InflateParams" })
	class OnClick implements  OnClickListener{

	
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch ( arg0.getId()){
			case R.id.degree_text:
				textedit_dialog("请输入学位",userdegree, 0);
				break;
			case R.id.major_text:
				textedit_dialog("请输入所学专业",usermajor, 0);
				break;
			case R.id.realname_text:
				textedit_dialog("请输入真实姓名",realname, 0);
				break;
			case R.id.save_button1:
				
				save();
				break;
			case R.id.imageView1_avator:
				showChoosePhotoDialog();
				break;
			case R.id.username_text:
				
				textedit_dialog("请输入用户名", username, 0);
				break;
			case R.id.sex_label:
				sex_dialog(); 
					
				break;
			case R.id.tel_text:
				textedit_dialog("请输入手机号", usertel, 2);
				break;
			case R.id.email_text:
				textedit_dialog("请输入邮件", useremailTextView, 1);
				break;
			case R.id.school_text:
				textedit_dialog("请输入学校", userschool, 0);
				break;
			case R.id.graduate_year_text:
				 date_dialog();
				 
				break;
			case R.id.back:
				finish();
				break;
			}
			
			
		}
		private void _dialog(String title,final TextView textView,int checktype) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfoActivity.this);
           // builder.setIcon(R.drawable.ic_launcher);
            builder.setTitle(title);
            //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
            View view = LayoutInflater.from(PersonalInfoActivity.this).inflate(R.layout.username_editdig, null);
            //    设置我们自己定义的布局文件作为弹出框的Content
            builder.setView(view);
            final RegexJudge regexJudge=new RegexJudge();
            final EditText username1 = (EditText)view.findViewById(R.id.editText1_username1);
            final int flag=checktype;
            
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    String a = username1.getText().toString().trim();
                    if(a.isEmpty()){
                    	 Toast.makeText(PersonalInfoActivity.this, "不能为空" , Toast.LENGTH_SHORT).show();
                    	  
                    }
                    else if(!regexJudge.RegexJudge1(flag, a)){
                    	
                    	 Toast.makeText(PersonalInfoActivity.this, "格式不正确" , Toast.LENGTH_SHORT).show();
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
		
		
	
		private void textedit_dialog(String title,final TextView textView,int checktype) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfoActivity.this);
           // builder.setIcon(R.drawable.ic_launcher);
            builder.setTitle(title);
            //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
            View view = LayoutInflater.from(PersonalInfoActivity.this).inflate(R.layout.username_editdig, null);
            //    设置我们自己定义的布局文件作为弹出框的Content
            builder.setView(view);
            final RegexJudge regexJudge=new RegexJudge();
            final EditText username1 = (EditText)view.findViewById(R.id.editText1_username1);
            final int flag=checktype;
            
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    String a = username1.getText().toString().trim();
                    if(a.isEmpty()){
                    	 Toast.makeText(PersonalInfoActivity.this, "不能为空" , Toast.LENGTH_SHORT).show();
                    	  
                    }
                    else if(!regexJudge.RegexJudge1(flag, a)){
                    	
                    	 Toast.makeText(PersonalInfoActivity.this, "格式不正确" , Toast.LENGTH_SHORT).show();
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
		public void sex_dialog(){
			AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfoActivity.this);
	     
	        builder.setTitle("请选择性别");
	         String sex1String;
	        final String sex[] = {"男", "女"};
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
	            		usersex.setText(sextemp);
	            	}
	                
	              
	            }
	        });
	        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
	        {
	            @Override
	            public void onClick(DialogInterface dialog, int which)
	            {
	              usersex.setText(sextemp);
	         
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
    private void showChoosePhotoDialog() {
        CharSequence[] items = { "相册", "相机" };
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("选择图片来源")
                .setItems(items, new DialogInterface.OnClickListener() {
 
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if (which == IMAGE_REQUEST_CODE) {
                        	Intent intent = new Intent(Intent.ACTION_PICK, null);
                        	intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        	startActivityForResult(intent,IMAGE_REQUEST_CODE);
                        } else {
                        	Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                			intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                							"head.jpg")));
                			startActivityForResult(intent2, CAMERA_REQUEST_CODE);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
 
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                }).create();
        dialog.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case IMAGE_REQUEST_CODE:
          
            Uri uri = null;
            if (data == null) {
                return;
            }
            if (resultCode == RESULT_OK) {
                if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(this, "SD不可用",1).show();
                    return;
                }
                uri = data.getData();
                startImageAction(uri,400, 400,3, true);
            } else {
                Toast.makeText(this, "照片获取失败",1).show();
            }
            break;
        case CAMERA_REQUEST_CODE:
        	if (resultCode == RESULT_OK) {
        	File temp = new File(Environment.getExternalStorageDirectory()
					+ "/head.jpg");
                Uri uri1 = Uri.fromFile(temp);
           
                startImageAction(uri1, 200, 200,3, true);
            
        	}
        	
        	break;
            case 3:
                
                if (data == null) {
                    return;
                } else {
                	 Bitmap bitmap = decodeUriAsBitmap(urixx);
                
                    saveCropAvator(bitmap);
                    
                    if( new uploadphoto().uploadFile(myStatus.getUrlString()+"upload.php", myStatus.getUser_ID()+"head.jpg", fileName)){
     		 			
     		 			runOnUiThread(new Runnable(){
     						public void run(){
     							Toast.makeText(PersonalInfoActivity.this, "上传成功", 0).show();
     							
     						}
     					});
     		 			 myStatus.setPhoto_url(myStatus.getUrlString()+"/upload/"+myStatus.getUser_ID()+"head.jpg");
     		 		//图片名字
     		 		}else{
     		 			runOnUiThread(new Runnable(){
     						public void run(){
     							Toast.makeText(PersonalInfoActivity.this, "上传失败", 0).show();
     							
     						}
     					});
     		 			
     		 		}
                   imgAvator.setImageBitmap(bitmap);
                }
                break;

        default:
            break;
        }
    }
    private void saveCropAvator(Bitmap mBitmap) {
		// TODO Auto-generated method stub
    	 String sdStatus = Environment.getExternalStorageState();  
         if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
                return;  
            }  
 		FileOutputStream b = null;
 		File file = new File(path);
 		file.mkdirs();// 创建文件夹
 		 fileName =path + "head.jpg";
 		
 		try {
 			b = new FileOutputStream(fileName);
 			mBitmap.compress(Bitmap.CompressFormat.JPEG, 70, b);// 把数据写入文件
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				//关闭流
 				b.flush();
 				
 				b.close();
 				
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 }
    private Bitmap decodeUriAsBitmap(Uri uri){
    	
    	    Bitmap bitmap = null;
    	
    	    try {
    	
    	        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
    	
    	    } catch (FileNotFoundException e) {
    	
    	        e.printStackTrace();
    	
    	        return null;
    	
    	    }
    	
    	    return bitmap;
    	
    	}   
	



	private void startImageAction(Uri uri, int outputX, int outputY,
            int requestCode, boolean isCrop) {
        Intent intent = null;
        if (isCrop) {
            intent = new Intent("com.android.camera.action.CROP");
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, urixx);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }



	


	

	private OnDateSetListener mDateSetListener=new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			Year=arg1;
			Month=arg2+1;
			Day=arg3;
			usergraduate_year.setText(Year+"年"+Month+"月"+Day+"日");
		}
	};

	public void date_dialog() {
		
		// TODO Auto-generated method stub
		Calendar calendar=Calendar.getInstance();
		 Year = calendar.get(Calendar.YEAR);  
	        Month = calendar.get(Calendar.MONTH);  
	        Day = calendar.get(Calendar.DAY_OF_MONTH); 
	
		DatePickerDialog datePickerDialog=new DatePickerDialog(PersonalInfoActivity.this, mDateSetListener, Year, Month, Day);
		
		
		datePickerDialog.show();
	}



	public void save() {
		// TODO Auto-generated method stub
		
		final String usernsString=username.getText().toString();
		final String usernsString1=usersex.getText().toString();
		final String usernsString2=usertel.getText().toString();
		final String usernsString3=useremailTextView.getText().toString();
		final String usernsString4=userschool.getText().toString();
		final String usernsString5=usergraduate_year.getText().toString();
		final String usernsString6=realname.getText().toString();
		final String usernsString7=userdegree.getText().toString();
		final String usernsString8=usermajor.getText().toString();
		final String usernsString9=myStatus.getUser_ID();
		final String usernsString10=myStatus.getPhoto_url();
		if(usernsString.equals(myStatus.getUsername())){
			
			flag=0;
		}
		else{
			
			flag=1;
		}
	
		
		
		
		
		new Thread(){
			public void run() {
			
				
				try {
					
					final JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("realname",usernsString6);
					jsonObj.put("username", usernsString);
					if(  usernsString1.equals("男")){
						jsonObj.put("usersex", 1);
					}
					else{
						jsonObj.put("usersex", 2);
					}
					if(flag==0){
						jsonObj.put("flag", 0);
						
					}
					else{
						jsonObj.put("flag", 1);
					}
					jsonObj.put("usertel", usernsString2);
					jsonObj.put("useremail", usernsString3);
					jsonObj.put("userschool",usernsString4);
					jsonObj.put("usergraduate", usernsString5);
					jsonObj.put("user_photourl",usernsString10);
					jsonObj.put("userdegree",usernsString7);
					jsonObj.put("usermajor",usernsString8);
					jsonObj.put("userid", usernsString9);
					HttpContectionUtil conn = new HttpContectionUtil();
					String uri =myStatus.getUrlString() + "modified_personal_info_and_partResume.php";
					String responseStr = conn.ConnForResult(uri, jsonObj);
					JSONObject response = new JSONObject(responseStr);
					String result =  response.getString("result");
					
					
					if("failed".equals(result)) {
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(PersonalInfoActivity.this, " failed", 0).show();
								
							}
						});
							}
					else if("exist".equals(result)){
						
						runOnUiThread(new Runnable(){
							public void run(){
								Toast.makeText(PersonalInfoActivity.this, "用户名存在,重新输入", 0).show();
								
							}
						});
						
					}
							else{
							
							   
								myStatus.setUsername(usernsString);
								myStatus.setSex(usernsString1);
								myStatus.setPhonenumber(usernsString2);
								myStatus.setEmail(usernsString3);
								myStatus.setSchool(usernsString4);
								myStatus.setGraduate_year(usernsString5);
								myStatus.setRealname(usernsString6);
								myStatus.setMajor(usernsString8);
								myStatus.setDegree(usernsString7);
								runOnUiThread(new Runnable(){
									public void run(){
										Toast.makeText(PersonalInfoActivity.this, " Success", 0).show();
									}
								});
								finish();
							}
					
					
				} catch (final JSONException e) {
					// TODO Auto-generated catch block
					runOnUiThread(new Runnable(){
						public void run(){
							Toast.makeText(PersonalInfoActivity.this, "Register Failed"+e.toString(), 0).show();
						}
					});
				}
				
				
				
			}
			
		}.start();
		
		
		
	}
}
