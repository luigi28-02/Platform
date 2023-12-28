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
        }
        return false;
    }

    public static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth=lvlData[0].length*Game.TILES_SIZE;
        if (x < 0 || x >=maxWidth) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
            float xIndex = x / Game.TILES_SIZE;
            float yIndex = y / Game.TILES_SIZE;
            int value = lvlData[(int) yIndex][(int) xIndex];
            return IsTileSolid((int)xIndex,(int)yIndex,lvlData);
        }
        public static boolean IsTileSolid(int XTile,int YTile,int [][] lvlData){
            int value = lvlData[(int) YTile][(int) XTile];
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
        int currentTile=((int)(hitbox.y+hitbox.height-1)/Game.TILES_SIZE);
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

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {

        //Controllo sul pixel di estrema destra ed estrema sinistra, se sono entrambi non blocco, allora siamo in aria
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData) && !IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData)) {
            return false;
        }
        return true;
    }
    public static boolean IsFloor(Rectangle2D.Float hitbox,float xSpeed, int[][] levelData){
        return IsSolid(hitbox.x+xSpeed,hitbox.y+hitbox.height+1,levelData);
    }

    public static boolean IsAllTileWalkable(int xStart,int XEnd,int y,int [][] lvlData){
        for (int i=0;i<XEnd-xStart;i++){
            if(IsTileSolid(xStart+i,y,lvlData)){
                return false;
            }
            if(!IsTileSolid(xStart+i,y+1,lvlData)){
                return false;
            }

        }
        return true;
    }

    public static boolean isSightClear(int[][] lvlData, Rectangle2D.Float firsthitBox, Rectangle2D.Float secondhitBox, int tileY) {
        int firstXTile=(int)(firsthitBox.x/Game.TILES_SIZE);
        int secondXTile=(int)(secondhitBox.x/Game.TILES_SIZE);
        if(firstXTile>secondXTile){
           return IsAllTileWalkable(secondXTile,firstXTile,tileY,lvlData);
        }else{
            return IsAllTileWalkable(firstXTile,secondXTile,tileY,lvlData);
        }
    }
    }

