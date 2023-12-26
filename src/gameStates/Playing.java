package gameStates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    private boolean paused=false;
    private PauseOverlay pauseOverlay;

    public Playing(Game game) {
        super(game);
        initClasses();
    }


    private void initClasses() {
        levelManager=new LevelManager(game);
        player=new Player(200,150,(int)(84*Game.SCALE),(int)(84*Game.SCALE));
        player.loadlvlData(levelManager.getCurrentLevel().getLvlData());
        pauseOverlay=new PauseOverlay(this);

    }




    @Override
    public void update() {
        if(!paused){
            levelManager.update();
            player.update();
        }else{
            pauseOverlay.update();

        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
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
