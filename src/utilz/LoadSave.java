package utilz;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final  String PLAYER_ATLAS = "player_sprites.png";
    public static final  String LEVEL_ATLAS = "outside_sprites.png";
    public static final  String LEVEL_ONE_DATA = "level_one_data.png";
    public static BufferedImage GetSpriteAtlas(String filename){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
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
        return img;
    }

    public static int[][] GetLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if (value >=48){         // It is used to check if in case we forget a pixel and value is 150 which will return a error of array out of bounch
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}