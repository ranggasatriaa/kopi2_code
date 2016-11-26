package com.kopi.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import com.kopi.myapplication.R;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        if (quantity<=0)
        {
            quantity=0;
        }
        else{
            quantity--;
            displayQuantity(quantity);
        }

    }
    public void submitOrder(View view) {
        //Figure out name that input
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(message);
    }

    /**
     * Calculates the price of the order.
     * @addwhippedCream is whether or not use whipped cream toping
     * @addChocolate is whether or not use chocolate toping
     * @return total price
     */
    private int calculatePrice(boolean addwhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if(addwhippedCream)
            basePrice = basePrice + 1;
        if(addChocolate){
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name \t\t: " + name;
        priceMessage += "\nQuantity\t: " + quantity;
        priceMessage += "\nTotal\t\t\t: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
        orderSummaryTextView.setText(message);
    }

}
