package utilz;

import entities.Crabby;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.CRABBY;

public class LoadSave {
    public  static final String PLAYER_ATLAS="Fighter_Spritelist.png";
    public  static final String LEVEL_ATLAS="outside_sprites.png";
    public  static final String LEVEL_ONE_DATA="level_one_data_long.png";
    public  static final String  MENU_BUTTONS="button_atlas.png";
    public  static final String  MENU_BACKGROUND="menu_background.png";
    public  static final String  PAUSE_BACKGROUND="pause_menu.png";
    public  static final String  SOUND_BUTTONS="sound_button.png";
    public  static final String  URM_BUTTONS="urm_buttons.png";
    public  static final String  VOLUME_BUTTONS="volume_buttons.png";
    public  static final String  CRABBY_SPRITE="crabby_sprite.png";







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
    public static ArrayList<Crabby> GetCrabs(){
        BufferedImage img=GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list=new ArrayList<>();
        for (int j=0;j<img.getHeight();j++){
            for(int i=0;i< img.getWidth();i++){
                Color color=new Color(img.getRGB(i,j));
                int value=color.getGreen();
                if(value==CRABBY){
                    list.add(new Crabby(i*Game.TILES_SIZE,j*Game.TILES_SIZE));
                }
                System.out.println("i= "+ i  + " j= " +  j + " value= " + value);

            }
        }
        return list;
    }
    public static int[][] GetLevelData(){
        BufferedImage img=GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] levelData=new int[img.getHeight()][img.getWidth()];
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
