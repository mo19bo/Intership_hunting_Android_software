package function;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class Tocircle {

	public Tocircle() {
		// TODO Auto-generated constructor stub
	}
	public Bitmap createCircleImage(Bitmap source, int min)  
    {  
        final Paint paint = new Paint();  
        paint.setAntiAlias(true);  
        Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);  
        /** 
         * 产生一个同样大小的画布 
         */  
        Canvas canvas = new Canvas(target);  
        /** 
         * 首先绘制圆形 
         */  
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);  
        /** 
         * 使用SRC_IN 
         */  
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  
        /** 
         * 绘制图片 
         */  
        canvas.drawBitmap(source, 0, 0, paint);  
        return target;  
    } 
	 public Bitmap createRoundConerImage(Bitmap source)  
	    {  
	        final Paint paint = new Paint();  
	        paint.setAntiAlias(true);  
	        Bitmap target = Bitmap.createBitmap(50, 50, Config.ARGB_8888);  
	        Canvas canvas = new Canvas(target);  
	        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());  
	        canvas.drawRoundRect(rect, 1, 1, paint);  
	        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  
	        canvas.drawBitmap(source, 0, 0, paint);  
	        return target;  
	    }  
}
