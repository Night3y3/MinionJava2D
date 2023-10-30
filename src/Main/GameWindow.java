package Main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel){
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Program should terminate will window exited
        jFrame.add(gamePanel);  //Add content to the window
//        jFrame.setLocationRelativeTo(null); //Window should spawn at the  center of screen
        jFrame.setResizable(false); // Window would not be resizable

        jFrame.pack();
        jFrame.setVisible(true);  //We can see it
    }
}
