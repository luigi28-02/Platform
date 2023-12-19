package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public  static final String PLAYER_ATLAS="player_sprites.png";
    public  static final String LEVEL_ATLAS="outside_sprites.png";
    public  static final String LEVEL_ONE_DATA="level_one_data.png";
    public static BufferedImage GetSpriteAtlas(String fileName) {
        //Crea un'istanza di Input Stream e attraverso getClass restituisce l'oggetto Class associato alla classe in cui viene
        //Eseguito il codice. GetResourceAsStream viene chiamato sull'oggetto class,cerca la risorsa associata e resituisce un
        //oggetto InputStream che può essere utilizzato per leggere i dati dalla risorsa.
        BufferedImage img = null;
        InputStream is =LoadSave.class.getResourceAsStream("/res/" + fileName);
        try {
            img=ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      return img;
    }
    public static int[][] GetLevelData(){
        int[][] levelData=new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img=GetSpriteAtlas(LEVEL_ONE_DATA);
        for (int j=0;j<img.getHeight();j++){
            for(int i=0;i< img.getWidth();i++){
                Color color=new Color(img.getRGB(i,j));
                int value=color.getRed();
                if(value>=48){
                    System.out.println("i= "+ i  + " j= " +  j + " value= " + value);
                    value=0;
                }
                System.out.println("i= "+ i  + " j= " +  j + " value= " + value);
                levelData[j][i]=value;

            }
        }
        return levelData;
    }
}
