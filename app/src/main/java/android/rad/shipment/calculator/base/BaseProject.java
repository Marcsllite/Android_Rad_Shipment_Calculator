package android.rad.shipment.calculator.base;

import android.app.Application;

import java.io.InputStream;

public class BaseProject extends Application {
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

    public InputStream openRawResource(int id) { return getResources().openRawResource(id);}
}
