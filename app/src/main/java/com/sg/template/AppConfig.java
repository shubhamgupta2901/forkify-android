package com.sg.template;

import android.content.Context;

public class AppConfig {
    public static String getApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }

    public static String getHostUrl() {
        return BuildConfig.HOST_URL;
    }

    public static boolean isDevelopmentServer() {
        return BuildConfig.DEBUG;
    }

    public static boolean isLogEnabled() {
        return BuildConfig.DEBUG;
    }

    public static String getDefaultLogTag() {
        return BuildConfig.LOG_TAG;
    }

    public static String getDatabaseName() {
        return BuildConfig.DATABASE_NAME;
    }

    public static String getSharedPrefName() {
        return BuildConfig.SHARED_PREF_NAME;
    }

    public static String getAppPlaystoreLink(Context mContext){
        return "market://details?id="+ mContext.getPackageName().toString();
    }
}
