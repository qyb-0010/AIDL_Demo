package qyb.demo.logger;

import android.util.Log;

/**
 * Created by qiaoyubo on 2015/5/20.
 */
public class LogHelper {
    private static final String TAG = "aidldemo";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }
}
