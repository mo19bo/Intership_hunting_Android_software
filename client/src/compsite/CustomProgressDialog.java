package compsite;

import com.example.qqq.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

public class CustomProgressDialog extends Dialog{

	
	public CustomProgressDialog(Context context) {  
        this(context, R.style.MyDialogStyleBottom5);  
    }  
  
  
    public CustomProgressDialog(Context context, int mydialogstylebottom5) {
		// TODO Auto-generated constructor stub
    	  super(context, mydialogstylebottom5);  
    	  this.setContentView(R.layout.custom_progress_dialog);
	}


	  
}
