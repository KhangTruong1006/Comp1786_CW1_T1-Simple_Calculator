package com.example.comp1786_cw1_t1_simple_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvOperation;
    private EditText numberInput;
    private long tempNum;
    private boolean finalResult;
    private int operationCount;
    private int operationButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        numberInput = (EditText)findViewById(R.id.number_input);
        tvOperation = findViewById(R.id.tv_operation);

        clickClearButton(null);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void getNumberFromClickedButton(View view){
        if(finalResult){
            clickClearButton(view);
        }
        Button clickedButton = (Button) view;
        String num = clickedButton.getText().toString();
        numberInput.append(num);
    }

    public void handleAdditionButtonClick(View view){
        setTextView(R.string.addition);
        handleMultipleCalculation(view);
        operationButtonId = R.id.btn_addition;
    }

    public void handleSubtractionButtonClick(View view){
        setTextView(R.string.subtraction);
        handleMultipleCalculation(view);
        operationButtonId = R.id.btn_substraction;

    }

    public void handleMultiplicationButtonClick(View view){
        setTextView(R.string.multiplication);
        handleMultipleCalculation(view);
        operationButtonId = R.id.btn_multiplication;

    }

    public void handleDivisionButtonClick(View view){
        setTextView(R.string.division);
        handleMultipleCalculation(view);
        operationButtonId = R.id.btn_division;

    }

    private void handleMultipleCalculation(View view){
        if(operationCount > 0){
            calculateResult(view);
        }
        tempNum = getNumberFromInputField();
        operationCount += 1;
        clearInputField();
    }

    private void calculateResult(View view){
        long num = getNumberFromInputField();
        String result = calculate(tempNum,num);
        numberInput.setText(result);
    }
    public void clickClearButton(View view){
        tempNum = 0;
        operationCount = 0;
        operationButtonId = 0;
        finalResult = false;
        tvOperation.setText("");
        clearInputField();
    }

    public void clickEqualButton(View view){
        calculateResult(view);
        finalResult = true;
    }

    private String calculate(long num1, long num2){
        long result;
        if (operationButtonId == R.id.btn_addition){
            result = num1 + num2;
        }

        else if (operationButtonId == R.id.btn_substraction) {
            result = num1 - num2;
        }

        else if (operationButtonId == R.id.btn_multiplication) {
            result = num1 * num2;
        }

        else if (operationButtonId == R.id.btn_division) {
            if(num2 == 0){
                return "Error: Division by Zero";
            }
            result = num1 / num2;
        }

        else{
            return "Error";
        }

        return String.format("%s",result);
    }
    //Helper functions
    private void clearInputField(){
        numberInput.setText("");
    }

    private void setTextView(int text){
        tvOperation.setText(text);
    }

    private long getNumberFromInputField(){
        String numStr = numberInput.getText().toString();
        if(numStr.isEmpty()){
            return 0;
        }
        return Long.parseLong(numStr);
    }
}