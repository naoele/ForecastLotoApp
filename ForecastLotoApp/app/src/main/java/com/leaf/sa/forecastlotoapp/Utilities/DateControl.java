package com.leaf.sa.forecastlotoapp.Utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateControl {

    // ----------------------------------------------------------------------
    // インターフェース

    /**
     * {@DateControl} のメソッドを呼びだして実行するインターフェース
     *
     * @return
     */
    public static DateControl getInstance() {
        return _instance;
    }

    private static final DateControl _instance = new DateControl();

    // ----------------------------------------------------------------------
    // メソッド

    /**
     * 前の日付か判定する
     *
     * @param previous 前の日付
     * @param current  現在の日付
     * @return 結果
     */
    public static boolean before(Date previous, Date current) {
        Date compA = truncate(previous);
        Date compB = truncate(current);

        return compA.before(compB);
    }

    /**
     * 後の日付か判定する
     *
     * @param previous 前の日付
     * @param current  現在の日付
     * @return 結果
     */
    public static boolean after(Date previous, Date current) {
        Date compA = truncate(previous);
        Date compB = truncate(current);

        return compA.after(compB);
    }

    /**
     * 同じ日付か判定する
     *
     * @param previous 前の日付
     * @param current  現在の日付
     * @return 結果
     */
    public static boolean equals(Date previous, Date current) {
        Date compA = truncate(previous);
        Date compB = truncate(current);

        return compA.equals(compB);
    }

    /**
     * 日時の「時刻」を切り捨てる
     *
     * @param datetime 日時
     * @return 時刻を切り捨てた日付
     */
    public static Date truncate(Date datetime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);

        return new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).getTime();
    }

}
