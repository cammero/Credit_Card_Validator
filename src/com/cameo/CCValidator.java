package com.cameo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cameo on 10/29/2015.
 */
public class CCValidator extends JFrame{
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JLabel validMessageLabel;
    private JComboBox ccTypeComboBox;

    public CCValidator(){

        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        final String VISA = "Visa";
        final String MC = "Mastercard";
        final String AMEX = "American Express";
        ccTypeComboBox.addItem(VISA);
        ccTypeComboBox.addItem(MC);
        ccTypeComboBox.addItem(AMEX);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = isVisaCreditCardNumberValid(ccNumber);
                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private boolean isVisaCreditCardNumberValid(String ccNumber) {
//Check to see if the length of the card number is 16
        if (ccNumber.length() != 16) {
            return false;
        }
        //Check to see if the first digit of the card number is 4
        if (ccNumber.startsWith("4") == false) {
            return false;
        }

        //initialize the variable
        int sumOfNumbers = 0;

        for (int x = 0 ; x < ccNumber.length() ; x++) {
            /*Convert each digit into an integer
            (The following two lines of code are from Clara's code that she posted on D2L
            I'm still not quite understanding how it works, but hopefully I'll understand later!)*/
            String stringDigit = ccNumber.substring(x, x + 1);
            int digit = Integer.parseInt(stringDigit);

            //If the digit is in an even (or zero) index position, multiply the digit by two, and add it to the sum
            if (x % 2 == 0) {
                int evenIndexPos = digit * 2;
                /*If the digit is greater than 10, add each place as a single digit
                (Prior to viewing Clara's code for this on D2L, I wasn't sure how I was going to add each place as
                a digit, but after seeing what she did (take the doubled number and subtract 10) that seemed like
                the simplest way to do it.)*/
                if (evenIndexPos > 10) {
                    sumOfNumbers = sumOfNumbers + 1 + (evenIndexPos - 10);
                } else {
                    sumOfNumbers = sumOfNumbers + evenIndexPos;
                }
                //If the digit is NOT in an even index position, add the digit to the sum
            } else {
                sumOfNumbers = sumOfNumbers + digit;
            }
        }

        //If the total can be divided by 10 evenly, it is a valid Visa card
        if (sumOfNumbers % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
