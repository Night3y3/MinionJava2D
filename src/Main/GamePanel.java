package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private  MouseInputs mouseInputs;
    private float xDelta = 100,yDelta = 100;
    private BufferedImage img;  // While using BufferedImage we can draw a specific section of the image aka slice sprite
    private BufferedImage[][] animation;
    private int aniTick,aniIndex,aniSpeed = 10;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        importImage();
        loadAnimation();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimation() {
        animation = new BufferedImage[9][6];
        for (int j = 0; j < animation.length; j++) {
            for (int i = 0; i < animation[j].length; i++) {
                animation[j][i] = img.getSubimage(i*64,j*40,64,40);
            }

        }

    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setPreferredSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void setAnimation() {
        if (moving){
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePos() {
        if (moving){
            switch (playerDir){
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -=5;
                case RIGHT -> xDelta +=5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animation[playerAction][aniIndex],(int) xDelta,(int) yDelta,128,80,null);

    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }


}
