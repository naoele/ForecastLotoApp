package com.leaf.sa.forecastlotoapp.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public final class TmpControl {

    // ----------------------------------------------------------------------
    // メソッド

    /**
     * 連想配列で文字列を保存する
     *
     * @param activity
     * @param key
     * @param value
     */
    public static void saveString(Activity activity, String key, String value) {
        SharedPreferences data = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 連想配列で保存した文字列を取得する
     *
     * @param activity
     * @param key
     * @param initVal
     * @return
     */
    public static String loadString(Activity activity, String key, String initVal) {
        SharedPreferences data = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String val = data.getString(key, initVal);
        return val;
    }

    /**
     * 重複のない文字列の集まりを保存する
     *
     * @param activity
     * @param key
     * @param vals
     */
    public static void saveStringSet(Activity activity, String key, Set<String> vals) {
        SharedPreferences prefs = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(key, vals);
        editor.apply();
    }

    /**
     * 重複のない文字列の集まりを取得する
     *
     * @param activity
     * @param key
     * @return
     */
    public static Set<String> loadStringSet(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        return prefs.getStringSet(key, new HashSet<String>());
    }

}
