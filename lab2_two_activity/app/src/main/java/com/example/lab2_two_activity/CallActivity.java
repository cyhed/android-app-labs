package com.example.lab2_two_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallActivity extends AppCompatActivity {


    private TextView numberTv , fullNameTv, pathTV;
    private Button callBtn;
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

        Bundle arguments = getIntent().getExtras();

        Point pointA,pointB;
        if(arguments!=null){
            pointA = (Point) arguments.getSerializable("PointA");
            pointB = (Point) arguments.getSerializable("PointB");
            if (pointA != null && pointB != null) {
                pathTV.setText(pointA.toString() + " ->\n" + pointB.toString());
                callBtn.setEnabled(true);
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initViews(){
        numberTv = findViewById(R.id.number);
        fullNameTv = findViewById(R.id.fullName);
        pathTV = findViewById(R.id.pathTV);
        callBtn = findViewById(R.id.callBTN);
    }

    public void  setPathOnClick(View view){
        Intent intent = new Intent("android.intent.action.PathActivity");
        startActivity(intent);
    }
    public  void CallClick(View view){
        Toast.makeText(this, "Call Taxi", Toast.LENGTH_SHORT).show();
    }
}
