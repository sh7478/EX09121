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

    /**
     * Called when the activity is first created. This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * <p>
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solution = findViewById(R.id.solution);
        solution.setOnCreateContextMenuListener(this);
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
    }
    /**
     * Called when the context menu for the view is being built.
     * <p>
     *
     * @param menu The context menu that is being built
     * @param v The view for which the context menu is being built
     * @param menuInfo Extra information about the item for which the context menu should be shown. This information will vary depending on the class of v.
     * @return void
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("sum");
        menu.add("sub");
        menu.add("mul");
        menu.add("div");
    }
    /**
     * This hook is called whenever an item in a context menu is selected.
     * <p>
     *
     * @param item The context menu item that was selected.
     * @return boolean Returns true if the event was consumed, else false.
     */
    @Override
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
    /**
     * Attempts to parse a string into a Double.
     * <p>
     *
     * @param str The string to be parsed.
     * @return Double Returns the parsed Double value if successful, or null if the string is not a valid number.
     */
    public static Double tryParseDecimal(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)")) {
            return Double.parseDouble(str);
        }
        return null;
    }

    /**
     * Formats a double number into a specific string representation. It uses scientific notation for large numbers.
     * <p>
     *
     * @param number The number to be formatted.
     * @return String The formatted number as a string.
     */
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
    /**
     * Subtracts the second number from the first and displays the result in the solution TextView.
     * <p>
     *
     * @return void
     */
    private void subNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());

        solution.setText(formatDecimal(num1 - num2));
    }

    /**
     * Adds the two input numbers and displays the result in the solution TextView.
     * <p>
     *
     * @return void
     */
    private void sumNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());
        solution.setText(formatDecimal(num1 + num2));

    }
    /**
     * Divides the first number by the second and displays the result. If the second number is zero, it displays an error message.
     * <p>
     *
     * @return void
     */
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


    /**
     * Multiplies the two input numbers and displays the result in the solution TextView.
     * <p>
     *
     * @return void
     */
    private void mulNums() {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());
        solution.setText(formatDecimal((num2*num1)));
    }
}
