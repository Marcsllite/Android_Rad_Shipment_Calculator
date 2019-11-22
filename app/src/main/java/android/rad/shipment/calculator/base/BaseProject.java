package android.rad.shipment.calculator.base;

import android.app.Application;


public class BaseProject extends Application {

    private static final String TAG = BaseProject.class.getSimpleName();
    private static BaseProject mAppController;

    public static BaseProject getInstance() {
        return mAppController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppController = this;

    }

    public String getStringFromRes(int resId) throws Exception {
        return getString(resId);
    }
}
