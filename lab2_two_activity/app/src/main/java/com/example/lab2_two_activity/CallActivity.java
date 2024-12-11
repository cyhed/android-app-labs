package com.example.lab2_two_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallActivity extends AppCompatActivity {


    private TextView numberTv , fullNameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call);

        initViews();

        Intent intent = getIntent();
        String fullNameS = intent.getStringExtra("fName") + " " +intent.getStringExtra("sName");
        fullNameTv.setText(fullNameS);
        numberTv.setText(intent.getStringExtra("number"));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initViews(){
        numberTv = findViewById(R.id.number);
        fullNameTv = findViewById(R.id.fullName);
    }

    public void  setPathOnClick(View view){
        Intent intent = new Intent(this, PathActivity.class);
        startActivity(intent);
    }
}