package clock;

import javax.swing.*;

class Dialog{

    public Dialog() {

    }

    public void messageDialog(String title, String message){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

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
}

/*
* Sources:
* How to Make Dialogs (https://docs.oracle.com/javase/tutorial/uiswing/examples/components/DialogDemoProject/src/components/CustomDialog.java)
* */