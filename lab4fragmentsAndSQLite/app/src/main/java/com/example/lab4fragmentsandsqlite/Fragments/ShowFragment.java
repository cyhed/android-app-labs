package com.example.lab4fragmentsandsqlite.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.lab4fragmentsandsqlite.DB.DB;
import com.example.lab4fragmentsandsqlite.NoteAdapter;
import com.example.lab4fragmentsandsqlite.Note;
import com.example.lab4fragmentsandsqlite.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ShowFragment extends Fragment {

    DB db;

    public ShowFragment(DB db) {

        // Required empty public constructor
        this.db = db;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_showfragment, container, false);
        // получаем элемент ListView
        ListView countriesList = view.findViewById(R.id.MainList);

        //добавим header
        View view_header = inflater.inflate(R.layout.list_header, null);
        countriesList.addHeaderView(view_header);

        // создаем адаптер
        BaseAdapter adapter = new NoteAdapter(getContext(), db);
        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
        // добавляем для списка слушатель

        return view;
    }
}