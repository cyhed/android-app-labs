package com.example.listadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Backet extends AppCompatActivity {
    private LayoutInflater layoutInflater;

    private ArrayList<Product> productsChecks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_backet);

        layoutInflater = LayoutInflater.from(this);
        Intent intent = new Intent();
        productsChecks = getIntent().getParcelableArrayListExtra("MyList");

        TextView textView = this.findViewById(R.id.tvCheckProduct);
        int size = productsChecks.size();
        textView.setText("Count of products in basket= " + size + "");


        ListView list = (ListView)findViewById(R.id.CheckProductList);




        ArrayAdapter<Product> adapter = new ChecProductAdapter(this,productsChecks);


        list.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class ChecProductAdapter extends ArrayAdapter<Product> {

        public ChecProductAdapter(Context context, List<Product> products) {
            super(context, android.R.layout.simple_list_item_2, products);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Product product = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(android.R.layout.simple_list_item_2, null);
            }

            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(product.getArticle());
            ((TextView) convertView.findViewById(android.R.id.text2))
                    .setText(product.getName() + " " +(product.getCost()+"$"));
            return convertView;
        }
    }
}