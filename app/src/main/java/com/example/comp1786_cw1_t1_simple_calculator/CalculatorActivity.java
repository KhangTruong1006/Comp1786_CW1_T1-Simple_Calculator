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
    private String operation;
//    private Button btn_add, btn_sub, btn_mul, btn_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        numberInput = (EditText)findViewById(R.id.number_input);
        operation = "";

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void getNumberFromClickedButton(View view){
        Button clickedButton = (Button) view;
        String num = clickedButton.getText().toString();
        numberInput.append(num);
    }

    public void handleAdditionButtonClick(View view){
        operation = getResources().getResourceEntryName(R.string.addition);
        numberInput.setText(operation);
    }

    public void clickClearButton(View view){
        operation = "";
        numberInput.setText("");
    }
}