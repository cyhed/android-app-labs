package com.example.lab4fragmentsandsqlite;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lab4fragmentsandsqlite.DB.DB;
import com.example.lab4fragmentsandsqlite.Fragments.ShowFragment;
import com.example.lab4fragmentsandsqlite.Fragments.UpdateFragment;


public class    MainActivity extends AppCompatActivity {
    ViewPager pager;
    PagerAdapter pagerAdapter;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initDB();
        initView();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initView() {

        pager = (ViewPager) findViewById(R.id.pager);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), db);
        pager.setAdapter(pagerAdapter);


    }
    private void initDB() {
        // открываем подключение к БД
        db = new DB(this);
        db.open();
        //db.delAll(); // если нужно все вернуть к исходному состоянию
        //db.write(); // при каждом запуске дописываем данные из write()

    }


}
