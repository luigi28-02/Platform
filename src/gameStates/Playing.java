package gameStates;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private boolean paused=false;
    private PauseOverlay pauseOverlay;
    private int xLvlOffset;
    private int leftBorder=(int) (0.5*Game.GAME_WIDTH);
    private int rightBorder=(int) (0.5*Game.GAME_WIDTH);
    //Otteniamo la larghezza dell imagine
    private int lvlTilesWide= LoadSave.GetLevelData()[0].length;
    private int maxTilesOffeset=lvlTilesWide-Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX=maxTilesOffeset*Game.TILES_SIZE;



    public Playing(Game game) {
        super(game);
        initClasses();
    }


    private void initClasses() {
        levelManager=new LevelManager(game);
        player=new Player(200,150,(int)(84*Game.SCALE),(int)(84*Game.SCALE));
        player.loadlvlData(levelManager.getCurrentLevel().getLvlData());
        pauseOverlay=new PauseOverlay(this);
        enemyManager=new EnemyManager(this);

    }




    @Override
    public void update() {
        if(!paused){
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLvlData(),player);
            checkCloseToBorder();
        }else{
            pauseOverlay.update();

        }
    }

    private void checkCloseToBorder() {
        int playerX=(int) player.getHitBox().x;
        int diff=playerX-xLvlOffset;
        if(diff>rightBorder){
            xLvlOffset+=diff-rightBorder;

        }else if(diff<leftBorder){
            xLvlOffset+=diff-leftBorder;
        }
        if(xLvlOffset>maxLvlOffsetX){
            xLvlOffset=maxLvlOffsetX;
        }else if(xLvlOffset<0){
            xLvlOffset=0;
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g,xLvlOffset);
        player.render(g,xLvlOffset);
        enemyManager.draw(g,xLvlOffset);
        if(paused){
            pauseOverlay.draw(g);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            player.setAttacking(true);
        }

    }
public void mouseDragged(MouseEvent e){
        if(paused){
            pauseOverlay.mouseDragged(e);
        }
}
    @Override
    public void mousePressed(MouseEvent e) {
        if(paused){
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paused){
            pauseOverlay.mouseReleased(e);
        }

    }

    @Override
    public void mouseMovded(MouseEvent e) {
        if(paused){
            pauseOverlay.mouseMoved(e);
        }
    }
    public void unpausedGame(){
        paused=false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLef(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused=!paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLef(false);
            break;

            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;

        }
    }
    public Player getPlayer(){

        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
