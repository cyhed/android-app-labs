package com.example.lab2_two_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PathActivity extends AppCompatActivity {

    EditText et_streatA, et_streatB;
    EditText et_houseA, et_houseB;
    EditText et_flatA,et_flatB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_path);

        initViews();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initViews(){
        et_streatA = findViewById(R.id.streetAet);
        et_houseA = findViewById(R.id.houseAet);
        et_flatA = findViewById(R.id.flatAet);

        et_streatB = findViewById(R.id.streetBet);
        et_houseB = findViewById(R.id.houseBet);
        et_flatB = findViewById(R.id.flatBet);
    }
    public void setBtnOnClick(View view){
        Intent intent = new Intent(this, CallActivity.class);

        Point PointA = new Point(
                et_streatA.getText().toString(),
                et_houseA.getText().toString(),
                et_flatA.getText().toString()
        );
        Point PointB = new Point(
                et_streatB.getText().toString(),
                et_houseB.getText().toString(),
                et_flatB.getText().toString()
        );

        intent.putExtra("PointA", PointA);
        intent.putExtra("PointB", PointB);
        startActivity(intent);
    }
}