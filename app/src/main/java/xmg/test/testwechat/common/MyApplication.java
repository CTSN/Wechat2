package xmg.test.testwechat.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by xmg on 2016/11/29.
 */

public class MyApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
