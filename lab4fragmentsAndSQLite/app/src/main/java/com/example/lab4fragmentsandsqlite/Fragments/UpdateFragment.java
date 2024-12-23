package com.example.lab4fragmentsandsqlite.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab4fragmentsandsqlite.DB.DB;
import com.example.lab4fragmentsandsqlite.Note;
import com.example.lab4fragmentsandsqlite.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class UpdateFragment extends Fragment {

    DB db;
    public UpdateFragment( DB db) {

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
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        //Inflate the layout for this fragment

        initViews(view);



        return view;
    }

    void initViews(View view){
        Button buttonUdate = view.findViewById(R.id.buttonUpdate);
        View.OnClickListener update = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et_update_id = view.findViewById(R.id.et_update_id);
                EditText et_update_name = view.findViewById(R.id.et_update_name);
                EditText et_update_descriptoin = view.findViewById(R.id.et_update_descriptoin);

                int id;
                try {
                    id = Integer.parseInt(et_update_id.getText().toString());
                }catch (NumberFormatException e){
                    id =0;
                }
                db.update(id,
                        et_update_name.getText().toString(),
                        et_update_descriptoin.getText().toString());

                et_update_id.setText("");
                et_update_name.setText("");
                et_update_descriptoin.setText("");
            }
        };
        buttonUdate.setOnClickListener(update);
    }



}