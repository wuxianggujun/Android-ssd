package ssd.lovetolearn.toolbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import ssd.lovetolearn.toolbox.activity.JianHuangShi;
import android.widget.Toast;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.widget.TextView;
import ssd.lovetolearn.toolbox.utils.CommentUtils;
import ssd.lovetolearn.toolbox.utils.MD5Util;
import java.security.NoSuchAlgorithmException;
import ssd.lovetolearn.toolbox.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    public static final boolean bl=true;
    private  static TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.activitymainTextView1);

		Intent intent=new Intent(MainActivity.this, JianHuangShi.class);
		intent.setAction("ssd.lovetolearn.toolbox.JianHuangShi");
		startActivity(intent);
		ToastUtils.showToast(MainActivity.this,"软件没问题😊");
		
		
            try {
                int signature = CommentUtils.getSignature(getApplicationContext());
                
                tv.setText(MD5Util.getMD5(String.valueOf(signature)));
                      
                if(bl&&MD5Util.getMD5(String.valueOf(signature)).equals("0366df1904a06f4f29b8ced757257751")){
                int a = 000000;   
                  /*  Intent intent=new Intent(MainActivity.this, JianHuangShi.class);
                    intent.setAction("ssd.lovetolearn.toolbox.JianHuangShi");
                    startActivity(intent);
                    ToastUtils.showToast(MainActivity.this,"软件没问题😊");
                  */   
                 }else{
                     
                    //finish();  
                    
                 }
            

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        }




    
        private void errout() {
            //获取包名
        String pm = this.getPackageName();
        tv.setText(pm);

      }


   // tv.setText(MD5Util.getMD5(String.valueOf(signature)));
   


}
