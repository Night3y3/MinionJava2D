package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import static Main.Game.GAME_WIDTH;
import static Main.Game.GAME_HEIGHT;

public class GamePanel extends JPanel {
    private  MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){

        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
    }



    public void updateGame() {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }


    public Game getGame(){
        return game;
    }

}
