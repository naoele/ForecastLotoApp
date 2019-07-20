package com.leaf.sa.forecastlotoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // ----------------------------------------------------------------------
    // 固定値

    /**
     * MainActivityのタグ
     */
    private String TAG = "MainActivity";

    // ----------------------------------------------------------------------
    // メソッド

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ツールバー
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ドロワーメニュー
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // ロト6ボタン
        findViewById(R.id.btn_loto6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "ロト6ボタンonClick");
                Intent intent = new Intent(getApplication(), Loto6Activity.class);
                startActivity(intent);
            }
        });

        // ロト7ボタン
        findViewById(R.id.btn_loto7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "ロト7ボタンonClick");
                Intent intent = new Intent(getApplication(), Loto7Activity.class);
                startActivity(intent);
            }
        });

        // ミニロトボタン
        findViewById(R.id.btn_miniloto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "ミニロトボタンonClick");
                Intent intent = new Intent(getApplication(), MiniLotoActivity.class);
                startActivity(intent);
            }
        });
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
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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
