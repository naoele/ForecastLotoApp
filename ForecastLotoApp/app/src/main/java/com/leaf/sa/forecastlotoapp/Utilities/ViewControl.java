package com.leaf.sa.forecastlotoapp.Utilities;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.leaf.sa.forecastlotoapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ViewControl {

    // ----------------------------------------------------------------------
    // 固定値

    private String ID_PREFIX_TXV = "txt_";

    // ----------------------------------------------------------------------
    // インターフェース

    /**
     * {@ViewControl} のメソッドを呼びだして実行するインターフェース
     *
     * @return
     */
    public static ViewControl getInstance() {
        return _instance;
    }

    private static final ViewControl _instance = new ViewControl();

    // ----------------------------------------------------------------------
    // メソッド

    /**
     * 対象IDのテキストビューをOFFにする(複数)
     *
     * @param activity
     * @param ids
     */
    public Set<String> makeTextViewOff(Activity activity, ArrayList<Integer> ids) {
        Resources res = activity.getResources();
        Set<String> viewNames = new HashSet<>();
        for (int id : ids) {
            String viewName = ID_PREFIX_TXV + id;
            setTextViewOff(res, viewName, activity);
            viewNames.add(viewName);
        }
        return viewNames;
    }

    /**
     * 対象IDのテキストビューをOFFにする(単数)
     *
     * @param activity
     * @param id
     */
    public void makeTextViewOff(Activity activity, int id) {
        String viewName = ID_PREFIX_TXV + id;
        Resources res = activity.getResources();
        setTextViewOff(res, viewName, activity);
    }

    /**
     * 対象アクティビティのテキストビューを初期化(すべてONに)する
     *
     * @param activity
     * @param viewNum
     */
    public void initTextView(Activity activity, int viewNum) {
        Resources res = activity.getResources();
        for (int i = 1; i <= viewNum; i++) {
            String viewName = ID_PREFIX_TXV + i;
            setTextViewOn(res, viewName, activity);
        }
    }

    /**
     * TextViewをONにする
     *
     * @param res
     * @param viewName
     * @param activity
     */
    private void setTextViewOn(Resources res, String viewName, Activity activity) {
        int viewId = res.getIdentifier(viewName, "id", activity.getPackageName());
        TextView textView = (TextView) activity.findViewById(viewId);
        textView.setTextColor(ContextCompat.getColor(activity, R.color.txt_on));
    }

    /**
     * TextViewをOFFにする
     *
     * @param res
     * @param viewName
     * @param activity
     */
    private void setTextViewOff(Resources res, String viewName, Activity activity) {
        int viewId = res.getIdentifier(viewName, "id", activity.getPackageName());
        TextView textView = (TextView) activity.findViewById(viewId);
        textView.setTextColor(ContextCompat.getColor(activity, R.color.txt_off));
    }

}
