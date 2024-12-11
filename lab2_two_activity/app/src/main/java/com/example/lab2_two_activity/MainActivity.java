package com.example.lab2_two_activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sPref;
    private boolean Auth;
    private EditText numberET, fNameET, sNameET;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        numberET = findViewById(R.id.numberET);
        fNameET =  findViewById(R.id.fNameET);
        sNameET =  findViewById(R.id.sNameET);
        login = findViewById(R.id.regb);

        sPref = getPreferences(MODE_PRIVATE);
        String auth = sPref.getString("sNameET", "");

        if (auth.equals("true")){
            loadDataRegistration();
            Auth =true;
            login.setText("LOGIN");
        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void regBtnOnClick(View view){
        String numberETs = numberET.getText().toString();
        String fNameETs = fNameET.getText().toString();
        String sNameETs = sNameET.getText().toString();
        if(!numberETs.isEmpty() && !fNameETs.isEmpty() && !sNameETs.isEmpty()) {
            if (!Auth) {
                registration();
            }
            Intent intent = new Intent(this, CallActivity.class);
            intent.putExtra("fName", fNameETs);
            intent.putExtra("sName", sNameETs);
            intent.putExtra("number", numberETs);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "input data", Toast.LENGTH_SHORT).show();
        }

    }

    public void registration(){
        saveDataRegistration();
        Toast.makeText(this, "Data save", Toast.LENGTH_SHORT).show();
    }
    private void saveDataRegistration() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("numberET", numberET.getText().toString());
        editor.putString("fNameET", fNameET.getText().toString());
        editor.putString("sNameET", sNameET.getText().toString());
        editor.putString("auth", "true");
        editor.apply();
    }
    private void loadDataRegistration() {
        sPref = getPreferences(MODE_PRIVATE);
        String numberETs = sPref.getString("numberET", "");
        String fNameETs = sPref.getString("fNameET", "");
        String sNameETs = sPref.getString("sNameET", "");
        numberET.setText(numberETs);
        fNameET.setText(fNameETs);
        sNameET.setText(sNameETs);
        Toast.makeText(this, "Data loaded", Toast.LENGTH_SHORT).show();
    }
}