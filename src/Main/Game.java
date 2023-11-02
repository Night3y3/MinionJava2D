package Main;

import entities.Player;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;


import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 100;

    private Playing playing;
    private Menu menu;


    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static  int TILES_IN_WIDTH = 26;
    public final static  int TILES_IN_HEIGHT = 14;
    public final static  int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static  int GAME_WIDTH =  TILES_SIZE * TILES_IN_WIDTH;
    public final static  int GAME_HEIGHT =  TILES_SIZE * TILES_IN_HEIGHT;
    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gamePanel.setFocusable(true);  //It is used for input focus in the panel
        gameWindow = new GameWindow(gamePanel);

        startGameLoop();

    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){

        switch (Gamestate.state){

            case PLAYING -> {
                playing.update();
            }
            case MENU -> {
                menu.update();
            }
        }
    }

    public void render(Graphics g){
        switch (Gamestate.state) {
            case PLAYING:
                playing.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }


    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;


        int frames = 0;
        long lastCheck =  System.currentTimeMillis();

        long previousTime = System.nanoTime();
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        while (true){

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames  = 0;
                updates = 0;
            }
        }
    }


    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING){
            playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }
}
