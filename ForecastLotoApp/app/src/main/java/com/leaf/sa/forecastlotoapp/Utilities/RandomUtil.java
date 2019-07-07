package com.leaf.sa.forecastlotoapp.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomUtil {

    // ----------------------------------------------------------------------
    // インターフェース

    /**
     * {@ViewControl} のメソッドを呼びだして実行するインターフェース
     *
     * @return
     */
    public static RandomUtil getInstance() {
        return _instance;
    }

    private static final RandomUtil _instance = new RandomUtil();

    // ----------------------------------------------------------------------
    // メソッド

    /**
     * 重複のない乱数生成
     *
     * @param targetNum
     * @return
     */
    public ArrayList<Integer> run(int targetNum) {

        // listに指定値まで数値をいれる
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= targetNum; i++) {
            list.add(i);
        }

        // シャッフルして、順番を変える
        Collections.shuffle(list);

        // シャッフルした結果を1/2返す
        ArrayList<Integer> halfList = new ArrayList<Integer>();
        for (int i = 0; i < list.size() / 2; i++) {
            halfList.add(list.get(i));
        }

        return halfList;
    }
}
