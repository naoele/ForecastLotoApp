package com.leaf.sa.forecastlotoapp.Utilities;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leaf.sa.forecastlotoapp.R;

import java.util.Arrays;

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
    // 共有メソッド

    /**
     * ロトの数字を作成する
     *
     * @param activity
     * @param layout
     * @param count
     * @return
     */
    public TextView[] createLotoNumbers(Activity activity, ConstraintLayout layout, int count, Integer[] leftSideNums, Integer[] rightSideNums) {

        // テキストビュー作成
        TextView[] textViews = createLotoTextViews(activity, count, ContextCompat.getColor(activity, R.color.txt_on));
        for (int i = 0; i < textViews.length; i++) {
            int num = i + 1;
//            // ビューをセット（しないとエラーになる）
//            layout.addView(textViews[i]);
//            ConstraintLayout.LayoutParams layoutParams;
//            if (Arrays.asList(leftSideNums).contains(num)) {
//                // 左端の数字のレイアウト
//                layoutParams = setConstraintLayoutParamsLeft((ConstraintLayout.LayoutParams) textViews[i].getLayoutParams(), textViews, i);
//            } else if (Arrays.asList(rightSideNums).contains(num)) {
//                // 右端の数字のレイアウト
//                layoutParams = setConstraintLayoutParamsRight((ConstraintLayout.LayoutParams) textViews[i].getLayoutParams(), textViews, i);
//            } else {
//                // その他の数字のレイアウト
//                layoutParams = setConstraintLayoutParamsCenter((ConstraintLayout.LayoutParams) textViews[i].getLayoutParams(), textViews, i);
//            }
//            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
//            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//            textViews[i].setLayoutParams(layoutParams);
        }
        return textViews;
    }

    private ConstraintLayout.LayoutParams setConstraintLayoutParamsLeft(
            ConstraintLayout.LayoutParams layoutParams, TextView[] textViews, int i) {
        if (i + 1 == 1) {
            // top
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToStart = textViews[i].getId();
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToTop = textViews[i + 7].getId();
        } else if (i + 1 == 36) {
            // bottom
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToStart = textViews[i].getId();
            layoutParams.topToBottom = textViews[i - 7].getId();
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        } else {
            // center
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToStart = textViews[i].getId();
            layoutParams.topToBottom = textViews[i - 7].getId();
            layoutParams.bottomToTop = textViews[i + 7].getId();
        }
        return layoutParams;
    }

    private ConstraintLayout.LayoutParams setConstraintLayoutParamsRight(
            ConstraintLayout.LayoutParams layoutParams, TextView[] textViews, int i) {
        if (i + 1 == 7) {
            // top
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToStart = textViews[i].getId();
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToTop = textViews[i + 7].getId();
        } else if (i + 1 == 43) {
            // bottom
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topToBottom = textViews[i - 7].getId();
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        } else {
            // center
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToStart = textViews[i].getId();
            layoutParams.topToBottom = textViews[i - 7].getId();
            layoutParams.bottomToTop = textViews[i + 7].getId();
        }
        return layoutParams;
    }

    private ConstraintLayout.LayoutParams setConstraintLayoutParamsCenter(
            ConstraintLayout.LayoutParams layoutParams, TextView[] textViews, int i) {
        if (2 <= i + 1 && i + 1 <= 6) {
            // top
            layoutParams.startToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToStart = textViews[i].getId();
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToTop = textViews[i + 7].getId();
        } else if (35 <= i + 1 && i + 1 <= 43) {
            // bottom
            layoutParams.startToEnd = textViews[i - 1].getId();
            layoutParams.endToEnd = textViews[i - 1].getId();
            layoutParams.topToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        } else {
            // center
            layoutParams.startToEnd = textViews[i - 1].getId();
            layoutParams.endToStart = textViews[i + 1].getId();
            layoutParams.topToBottom = textViews[i - 7].getId();
            layoutParams.bottomToTop = textViews[i + 7].getId();
        }
        return layoutParams;
    }


    /**
     * ロト数字用テキストビュー作成
     *
     * @param activity
     * @param msg
     * @param color
     * @return
     */
    public TextView createLotoTextView(Activity activity, int msg, int color) {
        TextView textView = new TextView(activity);
        textView.setTag(msg);
        textView.setText(String.valueOf(msg));
        textView.setTextColor(color);
        textView.setId(View.generateViewId());
        return textView;
    }

    /**
     * ロト数字用テキストビュー複数作成
     *
     * @param activity
     * @param count
     * @param color
     * @return
     */
    public TextView[] createLotoTextViews(Activity activity, int count, int color) {
        TextView[] textViews = new TextView[count];
        for (int i = 0; i < count; i++) {
            // TextView作成
            TextView textView = createLotoTextView(activity, i + 1, color);
            textViews[i] = textView;
        }
        return textViews;
    }

}
