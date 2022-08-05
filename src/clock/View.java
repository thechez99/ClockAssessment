package clock;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.annotation.Inherited;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {
    
    ClockPanel panel;
    pQueue alarmQueue = new pQueue();
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Controller c = new Controller(model, alarmQueue);

        //Menu Code

        JMenuBar menuBar;
        JMenu mainMenu, alarmMenu;
        JMenuItem aboutItem, addItem, viewItem, exitItem;

        menuBar = new JMenuBar();

        mainMenu = new JMenu("Menu");
        alarmMenu = new JMenu("Alarm");
        mainMenu.setMnemonic(KeyEvent.VK_M);
        mainMenu.getAccessibleContext().setAccessibleDescription("This menu contains a list of options regarding the Clock Application");
        alarmMenu.setMnemonic(KeyEvent.VK_A);
        alarmMenu.getAccessibleContext().setAccessibleDescription("This menu contains a list of options to manipulate the alarms and set ones");
        menuBar.add(mainMenu);
        menuBar.add(alarmMenu);

        addItem = new JMenuItem("Add Alarm");
        addItem.setMnemonic(KeyEvent.VK_D);
        alarmMenu.add(addItem);

        viewItem = new JMenuItem("View List of Alarms");
        viewItem.setMnemonic(KeyEvent.VK_V);
        alarmMenu.add(viewItem);

        aboutItem = new JMenuItem("About this app");
        aboutItem.setMnemonic(KeyEvent.VK_I);
        mainMenu.add(aboutItem);

        exitItem = new JMenuItem("Quit");
        exitItem.setMnemonic(KeyEvent.VK_Q);
        mainMenu.add(exitItem);

        frame.setJMenuBar(menuBar);

        addItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                alarmQueue.add();
            }
        });

        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmQueue.listAlarmDialog();
            }
        });


        aboutItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Dialog d = new Dialog();
                d.messageDialog("About this app", "A basic clock app with alarm functionality \n\nBase code provided from Graham Wilson on github (mo04gw/Clock\nCode base developed upon by Chester Roberts 11014020 \n\nAlarm sound provided by user Microsammy on Pixabay");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });


        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a mainMenu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();

        String nextAlarmTime;

        if(alarmQueue.isEmpty()){
            nextAlarmTime = "No alarms currently set";
        } else{
            nextAlarmTime = "Next alarm at: " + alarmQueue.head().getAlarmTime().toString();
        }



        JButton button = new JButton(nextAlarmTime);
        pane.add(button, BorderLayout.PAGE_START);
         
        panel.setPreferredSize(new Dimension(350, 350));
        pane.add(panel, BorderLayout.CENTER);
         
        /*JButton listButton = new JButton("View Alarms");
        pane.add(listButton, BorderLayout.LINE_START);
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alarmQueue.listAlarmDialog();
            }
        });*/
         
        JButton addButton = new JButton("Add Alarm");
        pane.add(addButton, BorderLayout.PAGE_END);
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                alarmQueue.add();
            }
        });

         
        /*JButton chimeButton = new JButton("Test Chime");
        pane.add(chimeButton, BorderLayout.LINE_END);
        chimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Alarm a = new Alarm();
                a.ring();
            }
        });*/
        
        // End of borderlayout code

        frame.pack();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);

    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
}
