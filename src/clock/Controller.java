package clock;

import java.awt.event.*;
import javax.swing.Timer;

public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }

    public Controller(Model m, pQueue alarm){
        model = m;

        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
                alarm.checkForAlarms();
            }
        };

        timer = new Timer(1000, listener);
        timer.start();

    }
}