package com.example.calcul_lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String oldNumber= "0";
    String operator = "";
    Boolean flagZero = true;
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void clickNumber(View view) {

        String number = editText.getText().toString();

        if(flagZero)
            number = "";
        flagZero = false;
        if (view.getId() == R.id.button0) {
            if(number.isEmpty())
                flagZero = true;
            number += "0";
        }
        if (view.getId() == R.id.button1) {
            number += "1";
        }
        if (view.getId() == R.id.button2) {
            number += "2";
        }
        if (view.getId() == R.id.button3) {
            number += "3";
        }
        if (view.getId() == R.id.button4) {
            number += "4";
        }
        if (view.getId() == R.id.button5) {
            number += "5";
        }
        if (view.getId() == R.id.button6) {
            number += "6";
        }
        if (view.getId() == R.id.button7) {
            number += "7";
        }
        if (view.getId() == R.id.button8) {
            number += "8";
        }
        if (view.getId() == R.id.button9) {
            number += "9";
        }
        if (view.getId() == R.id.buttonDote) {
            if(!number.contains("."))
                number += ".";
        }
        if (view.getId() == R.id.buttonPlusMinus) {
            if(!number.contains("-"))
                number = "-" + number;
            else
                number = number.replace("-","");
        }

        editText.setText(number);
    }

    public void clickOperation(View view) {
        flagZero = true;
        oldNumber = editText.getText().toString();
        if (view.getId() == R.id.buttonDivide) {
            operator = "/";
        }
        if (view.getId() == R.id.buttonMultiply) {
            operator = "*";
        }
        if (view.getId() == R.id.buttonPlus) {
            operator = "+";
        }
        if (view.getId() == R.id.buttonMinus) {
            operator = "-";
        }

    }

    public void clickEquals(View view) {
        String newNumber = editText.getText().toString();
        if( flagZero == true)
            newNumber= "0";
        Double result = 0.0;
        String resultStr;
        switch (operator) {
            case "/":
                if (newNumber.equals("0")) {
                    editText.setText("делить на ноль нельзя");
                    flagZero = true;
                    return;
                }
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);

                break;
            case "*":
                result = Double.parseDouble(oldNumber) *  Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) -  Double.parseDouble(newNumber);
                break;
            case "+":
                result = Double.parseDouble(oldNumber) +  Double.parseDouble(newNumber);
                break;
        }

        resultStr = result.toString();
        if(resultStr.endsWith(".0"))
            resultStr = resultStr.replace(".0","");
        editText.setText(resultStr);
        textView.setText( oldNumber + operator +newNumber + "=");
    }

    public void clickAC(View view) {
        textView.setText("");
        editText.setText("0");
        flagZero=true;
    }
}