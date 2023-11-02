package levels;

import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    public LevelManager(Game game){
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j*12 + i;
                levelSprite[index] = img.getSubimage(i*32,j*32,32,32);
            }
        }
    }

    public void draw(Graphics g, int lvlOffset){
        for (int j= 0; j< Game.TILES_IN_HEIGHT;j++){
            for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i,j);
                g.drawImage(levelSprite[index],TILES_SIZE*i - lvlOffset,TILES_SIZE*j,TILES_SIZE,TILES_SIZE,null);
            }
        }
        g.drawImage(levelSprite[2],0,0,null);
    }

    public void update(){

    }

    public Level getCurrentLevel(){
        return levelOne;
    }
}
