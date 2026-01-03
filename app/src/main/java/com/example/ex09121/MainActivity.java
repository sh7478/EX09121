package com.example.ex09121;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    TextView solution;
    EditText etNum1, etNum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solution = findViewById(R.id.solution);
        solution.setOnCreateContextMenuListener(this);
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("sum");
        menu.add("sub");
        menu.add("mul");
        menu.add("div");
    }@Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String oper = item.getTitle().toString();
        if(tryParseDecimal(etNum1.getText().toString()) != null && tryParseDecimal(etNum2.getText().toString()) != null)
        {
            if(oper.equals("sum"))
            {
                sumNums();
            }
            else if(oper.equals("sub"))
            {
                subNums();
            }
            else if(oper.equals("mul"))
            {
                mulNums();
            }
            else if(oper.equals("div"))
            {
                divNums();
            }
        }
        else
        {
            solution.setText("you have to put two numbers before solving");
        }
        return super.onContextItemSelected(item);
    }
    public static Double tryParseDecimal(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)")) {
            return Double.parseDouble(str);
        }
        return null;
    }

    public static String formatDecimal(double number) {
        DecimalFormat simpleFormat = new DecimalFormat("0.####");
        String formattedSimple = simpleFormat.format(number).replaceAll("[^0-9]", "");
        if (formattedSimple.length() <= 5) {
            NumberFormat standardFormat = NumberFormat.getInstance();
            standardFormat.setMaximumFractionDigits(4);
            standardFormat.setGroupingUsed(false);
            return standardFormat.format(number);
        } else {
            DecimalFormat scientificFormat = new DecimalFormat("0.00E0");
            String result = scientificFormat.format(number);
            return result.replace("E", "e");
        }
    }
    private void subNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());

        solution.setText(formatDecimal(num1 - num2));
    }

    private void sumNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());
        solution.setText(formatDecimal(num1 + num2));

    }
    private void divNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());
        if(num2 == 0)
        {
            solution.setText("Can't divide by zero");
        }
        else {
            solution.setText(formatDecimal(num1 / num2));
        }
    }

    private void mulNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());
        solution.setText(formatDecimal((num2*num1)));
    }
}
