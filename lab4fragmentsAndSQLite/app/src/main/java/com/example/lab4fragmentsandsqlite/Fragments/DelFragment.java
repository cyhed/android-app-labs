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
public class DelFragment extends Fragment {

    DB db;
    public DelFragment(DB db) {
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
        View view = inflater.inflate(R.layout.fragment_del, container, false);

        initViews(view);

        return  view;
    }

    void initViews(View view){
        Button buttonUdate = view.findViewById(R.id.btn_id_del);
        View.OnClickListener delete = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et_id = view.findViewById(R.id.et_id_del);
                int id;
                try {
                    id = Integer.parseInt(et_id.getText().toString());
                }
                catch (NumberFormatException e){
                    id = 0;
                }

                db.delRec(id);
                et_id.setText("");
            }
        };
        buttonUdate.setOnClickListener(delete);
    }
}