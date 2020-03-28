package ssd.lovetolearn.toolbox.utils;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Context;


/*
   吐司工具类
*/

public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("禁止实例化");
    }

    private static boolean isShow = true;
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param msg     显示内容
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, String msg) {
        if (isShow && toast == null) {
            //第一次创建
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            //如果toast存在，只修改文字，防止点的次数多一直弹出
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context  上下文
     * @param msgResID 显示内容
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, int msgResID) {

        if (isShow && toast == null) {
            toast = Toast.makeText(context, msgResID, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msgResID);
        }
        toast.show();
    }


    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 显示内容
     */
    @SuppressLint("ShowToast")
    public static void showLong(Context context, CharSequence message) {
        if (isShow && toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 显示内容
     */
    @SuppressLint("ShowToast")
    public static void showLong(Context context, int message) {
        if (isShow && toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param message  显示内容
     * @param duration 显示时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow) Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param message  显示内容
     * @param duration 显示时间
     */
    public static void show(Context context, int message, int duration) {
        if (isShow) Toast.makeText(context, message, duration).show();
    }

}
