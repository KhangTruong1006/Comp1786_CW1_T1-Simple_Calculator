package com.example.comp1786_cw1_t1_simple_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    private EditText numberInput;
    private String result;
    private String operation;
    private long tempNum;
    private int operationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        enableButton(R.id.btn_addition, false);
        numberInput = (EditText)findViewById(R.id.number_input);
        operation = "";
        operationCount = 0;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void getNumberFromClickedButton(View view){
        enableButton(R.id.btn_addition,true);
        Button clickedButton = (Button) view;
        String num = clickedButton.getText().toString();
        numberInput.append(num);
    }

    public void handleAdditionButtonClick(View view){
        operation = getResourceUniqueName(R.string.addition);
        if(operationCount > 0){
            calculateResult(view);
        }
        tempNum = getNumberFromInputField();
        operationCount += 1;
        clearInputField();
    }

    public void calculateResult(View view){
        long num = getNumberFromInputField();
        result= calculate(tempNum,num,operation);
        numberInput.setText(result);
    }
    public void clickClearButton(View view){
        operation = "";
        tempNum = 0;
        operationCount = 0;
        clearInputField();
    }

    private String calculate(long num1, long num2, String operation){
        long methodResult;
        if (operation.equals("addition")){
            methodResult = num1 + num2;
            result = String.format("%d",methodResult);
        }

        else{
            result = "Error";
        }
        return result;
    }
//Helper functions
    private void clearInputField(){
        numberInput.setText("");
    }

    private String getResourceUniqueName(int value){
        return getResources().getResourceEntryName(value);
    }

    private long getNumberFromInputField(){
        return Long.parseLong(numberInput.getText().toString());
    }

    private void enableButton(int id, boolean status){
        Button button = findViewById(id);
        button.setEnabled(status);
    }
}