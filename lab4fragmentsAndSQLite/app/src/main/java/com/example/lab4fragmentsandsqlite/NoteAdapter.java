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

public class NoteAdapter extends BaseAdapter  {
    private Context context;
    private LayoutInflater inflater;
    private DB db;
    private Cursor cursor;
    private int idColIndex;
    private int nameColIndex;
    private int descriptionColIndex;

    public NoteAdapter(Context context, DB db) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.db = db;

        cursor = db.getAllData();
        idColIndex = cursor.getColumnIndex("id");
        nameColIndex = cursor.getColumnIndex("name");
        descriptionColIndex = cursor.getColumnIndex("description");

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
        vh.tv_id.setText(cursor.getString(idColIndex)+"");
        vh.tv_goodDesc.setText(cursor.getString(descriptionColIndex)+"");
        vh.tv_goodName.setText(cursor.getString(nameColIndex)+"");
        return view;
    }


    public class ViewHolder {
        private TextView tv_goodDesc;
        private TextView tv_goodName;
        private TextView tv_id;
        public ViewHolder() {
        }
        public void initViewHolder(View view) {
            tv_id = (TextView) view.findViewById(R.id.itemId);
            tv_goodDesc = (TextView) view.findViewById(R.id.itemDesc);
            tv_goodName = (TextView) view.findViewById(R.id.itemName);
        }
    }
}