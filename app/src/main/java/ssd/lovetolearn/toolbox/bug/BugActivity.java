package ssd.lovetolearn.toolbox.bug;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.io.*;
import android.graphics.Color;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.KeyEvent;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.content.res.Resources;

public class BugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        Window window = BugActivity.this.getWindow();
     
        
        LinearLayout linearLayout = new LinearLayout(this);      
        linearLayout.setFitsSystemWindows(true);
        linearLayout.setOrientation(1);

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);            //设置边距

        initWindows();
        LinearLayout.LayoutParams titleBarPsrans=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置LinearLayout属性(宽和高)
        Toolbar tb=new Toolbar(this);
        tb.setTitle("软件报错");
        tb.setBackgroundColor(Color.TRANSPARENT);
        
        ScrollView mScrollView=new ScrollView(this);

        ScrollView.LayoutParams scParams=new ScrollView.LayoutParams(
            ScrollView.LayoutParams.MATCH_PARENT,
            ScrollView.LayoutParams.MATCH_PARENT);

        HorizontalScrollView mHorizontalScrollView=new HorizontalScrollView(this);
        HorizontalScrollView.LayoutParams horParams=new HorizontalScrollView.LayoutParams(
            HorizontalScrollView.LayoutParams.MATCH_PARENT,
            HorizontalScrollView.LayoutParams.MATCH_PARENT);
        
            
         try{
             
        TextView tv = new TextView(this);
        tv.setTextColor(Color.BLACK);
        tv.setTextIsSelectable(true);
        tv.setPadding(30,30,30,0);
        
        mHorizontalScrollView.addView(tv,horParams);
       
        mScrollView.addView(mHorizontalScrollView,scParams);
        linearLayout.addView(tb, titleBarPsrans);
        linearLayout.addView(mScrollView,layoutParams);
        

        setContentView(linearLayout, layoutParams);
   
            Object obj = getIntent().getSerializableExtra("bug");
            Throwable error = (Throwable) obj;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            error.printStackTrace(pw);
            tv.setText(sw.toString());
            
        } catch (Throwable e) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
   

      
    }
    
    
     private void initWindows() {
        Window window = getWindow();
        int color = getResources().getColor(android.R.color.holo_blue_light);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                              | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(color);
            //设置导航栏颜色
            window.setNavigationBarColor(color);
            ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置contentview为fitsSystemWindows
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            //给statusbar着色
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
            view.setBackgroundColor(color);
            contentView.addView(view);
        }
    }
    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */ 
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
    /**
     * 获取导航栏高度
     *
     * @param context context
     * @return 导航栏高度
     */
    public static int getNavigationBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

     
    
/*
    @Override
    public void onBackPressed() {
  
        super.onBackPressed();
        System.exit(0);
        
    }
    */
    /*
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
              //  System.exit(0);
                ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
                am.killBackgroundProcesses(getPackageName());
                System.exit(0);

               // android.os.Process.killProcess(android.os.Process.myPid());
              }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    */
    private long exitTime = 0;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //彻底关闭整个APP
            int currentVersion = android.os.Build.VERSION.SDK_INT;
            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);
            } else {// android2.1
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                am.restartPackage(getPackageName());
            }
        }
    }
    
        
        
        
        /**
         * convert px to its equivalent dp
         * 
         * 将px转换为与之相等的dp
         */
        public static int px2dp(Context context, float pxValue) {
            final float scale =  context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }


        /**
         * convert dp to its equivalent px
         * 
         * 将dp转换为与之相等的px
         */
        public static int dp2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }


        /**
         * convert px to its equivalent sp 
         * 
         * 将px转换为sp
         */
        public static int px2sp(Context context, float pxValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (pxValue / fontScale + 0.5f);
        }


        /**
         * convert sp to its equivalent px
         * 
         * 将sp转换为px
         */
        public static int sp2px(Context context, float spValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        }
        
        /*
    
    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
    */
        
}







/*
public class BugActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		try {

            //相对布局，包围两个滚动，
            RelativeLayout mRelativeLayout=new RelativeLayout(this);//创建相对布局对象
            mRelativeLayout.setBackgroundColor(Color.GRAY);

            RelativeLayout.LayoutParams btnParams=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

            ScrollView mScrollView=new ScrollView(this);

            ScrollView.LayoutParams scParams=new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT);

            HorizontalScrollView mHorizontalScrollView=new HorizontalScrollView(this);

            HorizontalScrollView.LayoutParams horParams=new HorizontalScrollView.LayoutParams(
                HorizontalScrollView.LayoutParams.MATCH_PARENT,
                HorizontalScrollView.LayoutParams.MATCH_PARENT);

            mHorizontalScrollView.setLayoutParams(scParams);
            mScrollView.addView(mHorizontalScrollView);
            mScrollView.setLayoutParams(horParams);
            mRelativeLayout.addView(mScrollView);



            TextView tv = new TextView(this);
            setContentView(tv);

			Object obj = getIntent().getSerializableExtra("bug");
			Throwable error = (Throwable) obj;
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			error.printStackTrace(pw);

            tv.setLayoutParams(btnParams);

            mRelativeLayout.addView(tv);
			tv.setText(sw.toString());
            tv.setTextColor(Color.WHITE);

            mRelativeLayout.setPadding(10, 10, 10, 10);
            mHorizontalScrollView.addView(tv);
            setContentView(mRelativeLayout);

		} catch (Throwable e) {
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

}
*/
