package entities;

import main.Game;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.HepMethods.CaneMoveHere;
import static utilz.Constants.Direction.*;
import static utilz.Constants.Direction.DOWN;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean lef, up, right, down;
    private float playerSpeed = 2.0f;
    private int [][] lvlData;
    private float xDrawOffset=21* Game.SCALE;
    private float yDrawOffset=4*Game.SCALE;

    public Player(float x, float y,int width,int height) {
        super(x, y,width,height);
        loadAnimations();
        initHitBox(x,y,20*Game.SCALE,28*Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex],(int)(hitBox.x-xDrawOffset),(int)(hitBox.y-yDrawOffset), width, height, null);
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
        if(!lef && !right && !up && !down){
            return;
        }
        float xSpeed=0,ySpeed=0;
        if (lef && !right) {
            xSpeed = -playerSpeed;
        } else if (right && !lef) {
            xSpeed = playerSpeed;
        }
        if (up && !down) {
            ySpeed = -playerSpeed;
        } else if (down && !up) {
            ySpeed = playerSpeed;
        }
        /*
        if(CaneMoveHere(x+xSpeed,y+ySpeed,width,height,lvlData)){
            this.x +=xSpeed;
            this.y +=ySpeed;
            moving=true;
        }
         */
        if(CaneMoveHere(hitBox.x+xSpeed,hitBox.y+ySpeed, hitBox.width,hitBox.height,lvlData)){
            hitBox.x +=xSpeed;
            hitBox.y +=ySpeed;
            moving=true;
        }
    }

    private void loadAnimations() {
        //Mettiamo l immagine nel buffer
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }
    public void loadlvlData(int [][] lvlData){
        this.lvlData=lvlData;

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


}