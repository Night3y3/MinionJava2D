package Main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel){
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Program should terminate will window exited
        jFrame.add(gamePanel);  //Add content to the window
        jFrame.setResizable(false); // Window would not be resizable

        jFrame.pack();
        jFrame.setLocationRelativeTo(null); //Window should spawn at the  center of screen
        jFrame.setVisible(true);  //We can see it
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
