package com.example.lab4fragmentsandsqlite;

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


public class    MainActivity extends AppCompatActivity implements
        View.OnClickListener{
    final String LOG_TAG = "myLogs";
    private Context context;
    private DB db;
    private Cursor cursor;
    private int idColIndex;
    private int nameColIndex;
    private int priceColIndex;
    private int countColIndex;
    private EditText etName, etPrice, etCount, etId;
    private Button btnAdd, btnRead, btnClear, btnUpdate, btnDelete;
    private String name_temp;
    private int id_temp, price_temp, count_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        initView();
        initDB();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etCount = (EditText) findViewById(R.id.etCount);
        etId = (EditText) findViewById(R.id.etId);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
    }
    private void initDB() {
    // открываем подключение к БД
        db = new DB(this);
        db.open();
        //db.delAll(); // если нужно все вернуть к исходному состоянию
        db.write(); // при каждом запуске дописываем данные из write()
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.btnAdd) {
            name_temp = etName.getText().toString();
            try {
                price_temp = Integer.parseInt(etPrice.getText().toString());
            }
            catch (NumberFormatException e){
                price_temp = 0;
            }
            try {
                count_temp = Integer.parseInt(etCount.getText().toString());
            }
            catch (NumberFormatException e){
                count_temp = 0;
            }

            db.addRec(name_temp, price_temp, count_temp);
        } else if (id == R.id.btnRead) {// получаем курсор
            cursor = db.getAllData();
            // ставим позицию курсора на первую строку выборки
            // если в выборке нет строк, вернется false
            if (cursor.moveToFirst()) {
                // определяем номера столбцов по имени в выборке
                int idColIndex = cursor.getColumnIndex("id");
                int nameColIndex = cursor.getColumnIndex("name");
                int priceColIndex = cursor.getColumnIndex("price");
                int countColIndex = cursor.getColumnIndex("count");
                do {
                    // получаем значения по номерам столбцов и пишем в лог
                    Log.d(LOG_TAG,
                            "ID = " + cursor.getInt(idColIndex) +
                                    ", name = " + cursor.getString(nameColIndex) +
                                    ", price = " + cursor.getInt(priceColIndex) +
                                    ", count = " + cursor.getInt(countColIndex));
                    // переход на следующую строку, а если следующей нет (текущая - последняя), то выходим из цикла
                } while (cursor.moveToNext());
            } else {
                Log.d(LOG_TAG, "0 rows");
            }
            Log.d(LOG_TAG, "cursor.getCount()=" +
                    String.valueOf(cursor.getCount()));
            cursor.close();
        } else if (id == R.id.btnClear) {
            db.delAll();
        } else if (id == R.id.btnUpdate) {
            try {
                id_temp = Integer.parseInt(etId.getText().toString());
            }
            catch (NumberFormatException e){
                id_temp = 0;
            }
            try {
                price_temp = Integer.parseInt(etPrice.getText().toString());
            }
            catch (NumberFormatException e){
                price_temp = 0;
            }
            try {count_temp = Integer.parseInt(etCount.getText().toString()); }
            catch (NumberFormatException e) {count_temp = 0;}

            name_temp = etName.getText().toString();

            db.update(id_temp, name_temp, price_temp, count_temp);
        } else if (id == R.id.btnDelete) {
            try {id_temp = Integer.parseInt(etId.getText().toString()); }
            catch (NumberFormatException e) {id_temp = 0;}
            db.delRec(id_temp);
        }
    }
}
