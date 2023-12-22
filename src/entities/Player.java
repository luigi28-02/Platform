package entities;

import main.Game;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.HepMethods.*;
import static utilz.Constants.Direction.*;
import static utilz.Constants.Direction.DOWN;
import static utilz.Constants.PlayerConstants.*;


public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean lef, up, right, down,jump;
    private float playerSpeed = 2.0f;
    private int [][] lvlData;
    private float xDrawOffset=21* Game.SCALE;
    private float yDrawOffset=20*Game.SCALE;


    //Jumping/Gravity
    private float airSpeed=0;
    private float gravity=0.04f*Game.SCALE;
    private float jumpSpeed=-2.25f*Game.SCALE;
    private float fallSpeedAfterCollision=0.5f*Game.SCALE;
    private boolean inAir=false;


    public Player(float x, float y,int width,int height) {
        super(x, y,width,height);
        loadAnimations();
        initHitBox(x,y-1,25*Game.SCALE,29*Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        //ydrawoffset
        g.drawImage(animations[playerAction][aniIndex],(int)(hitBox.x-25),(int)(hitBox.y-50), width, height, null);
        drawHitBox(g);
    }


    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }


    }

    private void setAnimation() {
        int startAni = playerAction;
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if(inAir){
            if(airSpeed<0){
                playerAction=JUMP;
            }else {
                playerAction=FALLING;
            }
        }
        if (attacking) {
            playerAction = ATTACK_1;
            if (startAni != playerAction) {
                resetAniTick();
            }
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;
        if(jump){
            jump();
        }
        if(!lef && !right && !inAir){
            return;
        }
        float xSpeed=0;
        if (lef) {
            xSpeed -=playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        if(!inAir){
            if(!IsEntityOnFloor(hitBox,lvlData)) {
                inAir = true;
            }
        }
        if(inAir){
            if(CaneMoveHere(hitBox.x,hitBox.y+airSpeed, hitBox.width, hitBox.height, lvlData)){
                hitBox.y+=airSpeed;
                airSpeed+=gravity;
                updateXPos(xSpeed);
            }else {
                hitBox.y=GetEntityYUnderRoofOrAboveFloor(hitBox,airSpeed);
                if(airSpeed>0){
                    resetInAir();
                }else {
                    airSpeed=fallSpeedAfterCollision;
                    updateXPos(xSpeed);
                }
            }

        }else {
            updateXPos(xSpeed);
        }
        moving=true;
    }

    public boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvlData) {
        //Check the pixel below bottomlef and bottomright
        if(!IsSolid(hitBox.x,hitBox.y+hitBox.height+1,lvlData)){
            if(!IsSolid(hitBox.x+hitBox.width,hitBox.y+hitBox.height+1,lvlData)){
                return false;
            }
        }
        return true;
    }

    private void jump() {
        if(inAir){
            return;
        }else {
            inAir=true;
            airSpeed=jumpSpeed;
        }
    }

    private void resetInAir() {
        inAir=false;
        airSpeed=0;
    }

    private void updateXPos(float xSpeed) {

        if(CaneMoveHere(hitBox.x+xSpeed,hitBox.y, hitBox.width,hitBox.height,lvlData)){
            hitBox.x +=xSpeed;
        }else{
            hitBox.x=GetEntityPosNextToWall(hitBox,xSpeed);
        }
    }

    private void loadAnimations() {
        //Mettiamo l immagine nel buffer
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 128, j * 130, 128, 130);
            }
        }
    }
    public void loadlvlData(int [][] lvlData){
        this.lvlData=lvlData;
        if(!IsEntityOnFloor(hitBox,lvlData)){
            inAir=true;
        }

    }
    //Abbiamo 9 animazioni in altezza e massimo 6 in larghezza

    public void resetDirBooleans() {
        lef=false;
        right=false;
        up=false;
        down=false;
    }

    public boolean isLef() {
        return lef;
    }

    public void setLef(boolean lef) {
        this.lef = lef;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    public void setAttacking(boolean attacking){
        this.attacking=attacking;
    }
    public void setJump(boolean jump){
        this.jump=jump;
    }


}