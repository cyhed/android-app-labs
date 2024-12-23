package com.example.lab4fragmentsandsqlite.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab4fragmentsandsqlite.DB.DB;
import com.example.lab4fragmentsandsqlite.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AddFragment extends Fragment {

    DB db;
    public AddFragment(DB db) {
        this.db = db;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        initViews(view);
        return  view;
    }

    void initViews(View view){
        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        View.OnClickListener add = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et_name = view.findViewById(R.id.et_add_name);
                EditText et_descriptoin = view.findViewById(R.id.et_add_descriptoin);
                db.addRec(
                        et_name.getText().toString(),
                        et_descriptoin.getText().toString());
                et_name.setText("");
                et_descriptoin.setText("");
            }
        };
        buttonAdd.setOnClickListener(add);
    }
}