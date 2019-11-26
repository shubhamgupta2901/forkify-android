package com.sg.template.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.sg.template.AppMain;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;


public class SharedPreferenceUtils {

    private static SharedPreferenceUtils mInstance;
    static SharedPreferences mSharedPreferences;
    private static String defaultValue = "";


    public static SharedPreferences getSharedPref() {
        mSharedPreferences = AppMain.getSharedPreferences();
        return mSharedPreferences;

    }

    public static String getStringValue(String key) {
        mSharedPreferences = getSharedPref();
        String value = mSharedPreferences.getString(key, defaultValue);
        return value;
    }

    public static String getStringValue(String key, String defValue) {
        mSharedPreferences = getSharedPref();
        String value = mSharedPreferences.getString(key, defValue);
        return value;
    }

    public static void putStringValue(String key, String value) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        save(editor);
    }

    public static long getLongValue(String key, long defValue) {

        mSharedPreferences = getSharedPref();
        long value = mSharedPreferences.getLong(key, defValue);
        return value;
    }

    public static void putLongValue(String key, long value) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        save(editor);
    }

    public static float getFloatValue(String key, float defValue) {
        mSharedPreferences = getSharedPref();
        float value = mSharedPreferences.getFloat(key, defValue);
        return value;
    }

    public static void putFloatValue(String key, float value) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        save(editor);
    }

    public static void putDoubleValue(String key, double value) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        save(editor);

    }

    public static double getDoubleValue(String key, double defValue) {
        mSharedPreferences = getSharedPref();
        double value = Double.longBitsToDouble(mSharedPreferences.getLong(key, Double.doubleToLongBits(defValue)));
        return value;
    }

    public static void putBooleanValue(String key, boolean value) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        save(editor);
    }

    public static boolean getBooleanValue(String key) {

        mSharedPreferences = getSharedPref();
        boolean result = mSharedPreferences.getBoolean(key, false);
        return result;
    }

    public static boolean getBooleanValue(String key, boolean defValue) {

        mSharedPreferences = getSharedPref();
        boolean result = mSharedPreferences.getBoolean(key, defValue);
        return result;
    }

    public static int getIntValue(String key) {

        mSharedPreferences = getSharedPref();
        int result = mSharedPreferences.getInt(key, 0);
        return result;
    }

    public static void putIntValue(String key, int value) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        save(editor);
    }

    public static void removeKey(String key) {
        mSharedPreferences = getSharedPref();
        Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        save(editor);
    }

    private static boolean isEditorApplyAvailable() {
        return SDK_INT >= GINGERBREAD;
    }

    /**
     * Save preferences in given editor
     *
     * @param editor
     */
    public static void save(final Editor editor) {
        /*commit() writes the data synchronously (blocking the thread its called from).
                It then informs you about the success of the operation.

          apply() schedules the data to be written asynchronously.
           It does not inform you about the success of the operation.*/

        if (isEditorApplyAvailable())
            editor.apply();
        else
            editor.commit();
    }


    /**
     * Business Logic Methods here
     */

}
