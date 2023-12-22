package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HepMethods {
    public static boolean CaneMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if(!IsSolid(x,y,lvlData)){
           if(!IsSolid(x+width,y+height,lvlData)){
               if(!IsSolid(x+width,y,lvlData)){
                   if(!IsSolid(x,y+height,lvlData)){
                       return true;

                   }

               }
           }
        }return false;
    }

    public static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
            float xIndex = x / Game.TILES_SIZE;
            float yIndex = y / Game.TILES_SIZE;
            int value = lvlData[(int) yIndex][(int) xIndex];
            if (value >= 48 || value < 0 || value != 11) {
                return true;
            } else {
                return false;
            }
        }
        public static float GetEntityPosNextToWall(Rectangle2D.Float hitbox,float xSpeed){
        //Il tile a cui ci troviamo
        int currentTile=(int)(hitbox.x/Game.TILES_SIZE);
        if(xSpeed>0){
            //right
            //tilexpos ci permette di calcolare la pozione del tile a cui dpbbiamo avvinarci
            int tileXpos=currentTile*Game.TILES_SIZE;
            //offset è la distanza che occore alla hitbox per arrivare alla collissione con il muro
            int xOffset=(int)(Game.TILES_SIZE-hitbox.width);
            return tileXpos+xOffset-1;
        }else {
            //Left

            return currentTile*Game.TILES_SIZE;

        }
        }
    public static float GetEntityYUnderRoofOrAboveFloor(Rectangle2D.Float hitbox,float airSpeed){
        int currentTile=(int)(hitbox.y/Game.TILES_SIZE);
        System.out.println("Current Tiles Y:"+currentTile);
        if(airSpeed>0){
            //Falling
            int tileYPos=currentTile*Game.TILES_SIZE;
            int YOffset=(int)(Game.TILES_SIZE-hitbox.height);
            return tileYPos+YOffset-1;
        }else {
            //Jumping
            return currentTile*Game.TILES_SIZE;
        }

    }
    }

