package com.leaf.sa.forecastlotoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.leaf.sa.forecastlotoapp.Utilities.DateControl;
import com.leaf.sa.forecastlotoapp.Utilities.RandomUtil;
import com.leaf.sa.forecastlotoapp.Utilities.TmpControl;
import com.leaf.sa.forecastlotoapp.Utilities.ViewControl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Loto6Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // ----------------------------------------------------------------------
    // 固定値

    /**
     * ロト6予想画面で使用する数字の数
     */
    private int TXET_VIEW_NUM = 43;

    /**
     * Loto6Activityのタグ
     */
    private String TAG = "Loto6Activity";

    /**
     * ロト予想を実行済みか判定するフラグ
     */
    private boolean isExecuted = false;

    // ----------------------------------------------------------------------
    // メソッド

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loto6);

        // ツールバー
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 更新ボタンイベント
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // その日に実行済みか
                if(!isExecuted){
                    // 全テキストビューを初期化する
                    ViewControl.getInstance().initTextView(Loto6Activity.this, TXET_VIEW_NUM);

                    // 乱数生成
                    ArrayList<Integer> randomList = RandomUtil.getInstance().run(TXET_VIEW_NUM);

                    // ランダムにテキストビューをOFFにする
                    Set<String> offViewList = ViewControl.getInstance().makeTextViewOff(Loto6Activity.this, randomList);

                    // 処理した時間を保存する
//                    Date today = new Date();
                    Date today = new SimpleDateFormat("yyyy/MM/dd").parse("2019/07/19");
                    String saveDate = String.valueOf(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
                    TmpControl.getInstance().saveString(Loto6Activity.this, Constants.LOTO6_TIME_KEY, saveDate);

                    // offにしたテキストビューの名前リストを保存する
                    TmpControl.getInstance().saveStringSet(Loto6Activity.this, Constants.LOTO6_TIME_KEY, offViewList);

                    Toast.makeText(getApplicationContext(), "ロト6の数字を半分予想しました。", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "本日の予想は終了しました。", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 更新ボタン初期設定
        initFloatingActionButton(fab);

        // ドロワーメニュー
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFloatingActionButton(FloatingActionButton fab) {
        String savedDate = TmpControl.getInstance().loadString(Loto6Activity.this, Constants.LOTO6_TIME_KEY, "");
        if (savedDate.isEmpty()) {
            return;
        }
        try {
            savedDate = "2019/07/19";
            Date previous = new SimpleDateFormat("yyyy/MM/dd").parse(savedDate);

            // 取得した日付が現在の日付より前ならfabをONにする
            if (!DateControl.getInstance().before(previous, new Date())) {
                isExecuted = true;
            } else {
                isExecuted = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "前回の処理時間のロードに失敗しました", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    /**
     * ドロワーメニューイベント処理
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Log.d(TAG, "メイン画面へ遷移");
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_loto_6) {
            Log.d(TAG, "ロト6予想画面へ遷移");
            Intent intent = new Intent(getApplication(), Loto6Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_loto_7) {
            Log.d(TAG, "ロト7予想画面へ遷移");
            Intent intent = new Intent(getApplication(), Loto7Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_mini_loto) {
            Log.d(TAG, "ミニロト予想画面へ遷移");
            Intent intent = new Intent(getApplication(), MiniLotoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
