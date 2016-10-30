package function;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Getimg {

	public Getimg() {
		// TODO Auto-generated constructor stub
	}
	 public Bitmap getImageFromAssetsFile(String fileName, AssetManager am1)
	    {
	        Bitmap image = null;
	        AssetManager am = am1;
	        try
	        {
	            InputStream is = am.open(fileName);
	            image = BitmapFactory.decodeStream(is);
	            is.close();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	 
	        return image;
	 
	    }

}
