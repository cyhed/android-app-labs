package com.example.lab4fragmentsandsqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lab4fragmentsandsqlite.DB.DB;


import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private Context context;
    private LayoutInflater inflater;
    private DB db;
    private Cursor cursor;
    private int idColIndex;
    private int nameColIndex;
    private int priceColIndex;
    private ArrayList<Boolean> arr_is_checked_goods_adapter = new ArrayList<Boolean>();
    private OnChangeListener onChangeListener;
    public ProductAdapter(Context context, DB db, OnChangeListener onChangeListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.db = db;
        this.onChangeListener = onChangeListener;
        cursor = db.getAllData();
        idColIndex = cursor.getColumnIndex("id");
        nameColIndex = cursor.getColumnIndex("name");
        priceColIndex = cursor.getColumnIndex("price");
        for (int i=0; i<cursor.getCount(); i++) {
            arr_is_checked_goods_adapter.add(false);
        }
    }
    @Override
    public int getCount() {
        return cursor.getCount();
    }
    @Override
    public Cursor getItem(int i) {
        cursor.moveToPosition(i);
        return cursor;
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.item, null);
        }
        cursor.moveToPosition(position);
        ViewHolder vh = new ViewHolder();
        vh.initViewHolder(view);
        vh.tv_goodPrice.setText(cursor.getInt(priceColIndex)+"");
        vh.tv_goodName.setText(cursor.getString(nameColIndex)+"");
        vh.cb_good.setChecked(arr_is_checked_goods_adapter.get(position));
        vh.cb_good.setOnCheckedChangeListener(this);
        vh.cb_good.setTag(position);
        return view;
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.isShown()) {
            int i = (int) compoundButton.getTag();
            if (isChecked){
                arr_is_checked_goods_adapter.set(i, true);
            }else {
                arr_is_checked_goods_adapter.set(i, false);
            }
            cursor.moveToPosition(i);
            int id = cursor.getInt(idColIndex);
            String name = cursor.getString(nameColIndex);
            int price = cursor.getInt(priceColIndex);
            int check = 0;
            if (isChecked){
                check = 1;
            }
            db.update(id, name, price, check);
            notifyDataSetChanged();
            onChangeListener.onDataChanged();
        }
    }
    public ArrayList<Boolean> getIsCheckedGoods() {
        return arr_is_checked_goods_adapter;
    }
    public class ViewHolder {
        private TextView tv_goodPrice;
        private TextView tv_goodName;
        private CheckBox cb_good;
        public ViewHolder() {
        }
        public void initViewHolder(View view) {
            tv_goodPrice = (TextView) view.findViewById(R.id.itemCost);
            tv_goodName = (TextView) view.findViewById(R.id.itemName);
            cb_good = (CheckBox) view.findViewById(R.id.itemCheck);
        }
    }
}