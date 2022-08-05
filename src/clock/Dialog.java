package clock;

import javax.swing.*;

/**
 * Extends the JOptionPane to create a reusable dialog
 * with very little code on the use side.
 *
 * @extends JOptionPane
 *
 */

class Dialog extends JOptionPane{

    public Dialog() {

    }

    /**
     * messageDialog will show a simple dialog window
     * that has a title, message and the information icon.
     *
     * Intended to be used for low importance messages to the user
     *
     * @param title The title of the window.
     * @param message The message to be displayed in the window.
     * @return void
     *
     * */

    public void messageDialog(String title, String message){
        JOptionPane.showMessageDialog(getParent(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * selectDialog opens a JOptionPane selection window
     * that will allow the user to select from an array of integers
     * which they would like to choose.
     *
     * @param message A message to be displayed above the select tool
     * @param inputArray An array of values to display to the user for them to choose.
     *
     * @return Object
     * */

    public Object selectDialog(String message, Integer[] inputArray){
        return JOptionPane.showInputDialog(
                null,
                message,
                "Input alarm time",
                JOptionPane.QUESTION_MESSAGE,
                null,
                inputArray,
                inputArray[0]);
    }

    /**
     * messageDialog will show a simple dialog window
     * that has a title, message and the warning icon.
     *
     * Intended to be used for high importance messages to the user
     *
     * @param title The title of the window.
     * @param message The message to be displayed in the window.
     * @return void
     *
     * */

    public void warningDialog(String title, String message){
        JOptionPane.showMessageDialog(getParent(), message, title, WARNING_MESSAGE);
    }
}

/*
* Sources:
* How to Make Dialogs (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/DialogDemoProject/src/components/CustomDialog.java)
* */