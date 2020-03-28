package ssd.lovetolearn.toolbox.utils;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.Context;
import android.content.pm.Signature;

public class CommentUtils
{
    
    
    
    public static int getSignature(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        StringBuilder sb = new StringBuilder();
        // 获取签名信息
        try {
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = pi.signatures;
            for (Signature signature : signatures) {
                sb.append(signature.toCharsString());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString().hashCode();
    }
    
    
}
