package ssd.lovetolearn.toolbox.bug;
import android.app.*;

public class MyApplication extends Application
{
	@Override
	public void onCreate()
	{
		// TODO: Implement this method
		super.onCreate();
		ExceptionHandler.getInstance(this).init();
	}
}
