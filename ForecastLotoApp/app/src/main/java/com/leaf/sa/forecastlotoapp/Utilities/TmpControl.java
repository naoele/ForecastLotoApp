package com.leaf.sa.forecastlotoapp.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class TmpControl {

    // ----------------------------------------------------------------------
    // 固定値


    // ----------------------------------------------------------------------
    // インターフェース

    /**
     * {@TmpControl} のメソッドを呼びだして実行するインターフェース
     *
     * @return
     */
    public static TmpControl getInstance() {
        return _instance;
    }

    private static final TmpControl _instance = new TmpControl();

    // ----------------------------------------------------------------------
    // メソッド


    public void saveString(Activity activity, String key, String value) {
        SharedPreferences data = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String loadString(Activity activity, String key, String initVal) {
        SharedPreferences data = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String val = data.getString(key, initVal);
        return val;
    }

    public void saveStringSet(Activity activity, String key, Set<String> vals) {
        SharedPreferences prefs = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(key, vals);
        editor.apply();
    }

    public Set<String> loadStringSet(Activity activity, String key) {
        SharedPreferences prefs = activity.getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        return prefs.getStringSet(key, new HashSet<String>());
    }

}
