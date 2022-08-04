package clock;

import javax.swing.*;

public class Menu extends JMenu {

    JMenuBar mBar;
    JMenu menu;

    public Menu(){
        initMenu();
    }

    public void initMenu(){
        mBar = new JMenuBar();
        this.menu = new JMenu("Clock Menu");
        mBar.add(this.menu);
    }

    public void addMenuItem(String itemName){
        JMenuItem item = new JMenuItem(itemName);
        this.menu.add(item);
    }

}
