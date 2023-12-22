package main;

import entities.Player;
import gameStates.Gamestate;
import gameStates.Playing;
import levels.Level;
import levels.LevelManager;

import java.awt.*;
import gameStates.Menu;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET=120;
    //Update viene utilizzato per far aggiornare la scena quando dobbiamo far muovere il player e in generale far cambiare le cose nella scena
    //Mi sembra di aver capito che viene fatto molto piu spesso rispetto agli FPS
    private final int UPS_SET=200;

    private Playing playing;
    private Menu menu;


    public final static int TILES_DEFAULT_SIZE=32;
    public final static float SCALE=1f;
    public final static int TILES_IN_WIDTH=26;
    public final static int TILES_IN_HEIGHT=14;
    public final static int TILES_SIZE=(int)(TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH=TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT=TILES_SIZE*TILES_IN_HEIGHT;


    public Game(){
        initClasses();
        gamePanel=new GamePanel(this);
        gameWindow=new GameWindow(gamePanel);
        //Request of input focus
        gamePanel.requestFocusInWindow();
        startGameLoop();

    }

    private void initClasses() {
        menu=new Menu(this);
        playing=new Playing(this);
    }

    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
}
public void Update(){
        switch(Gamestate.state){

            case PLAYING:
                playing.update();
                break;
            case MENU:
                menu.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
}
public void render(Graphics g){
    switch(Gamestate.state){

        case PLAYING:
            playing.draw(g);
            break;
        case MENU:
            menu.draw(g);
            break;
    }
}
    @Override
    public void run() {
        double timeforframe=1000000000.0/FPS_SET;
        double timePerUpdate=1000000000.0/UPS_SET;
        long previousTime=System.nanoTime();
        int frames=0;
        int updates=0;
        long lastcheck=System.currentTimeMillis();
        double deltaU=0;
        double deltaF=0;
    while (true){
    long currentTime=System.nanoTime();

    deltaU+=(currentTime-previousTime)/timePerUpdate;
    deltaF+=(currentTime-previousTime)/timeforframe;
    previousTime=currentTime;

    if(deltaU>=1){
        Update();
        updates++;
        deltaU--;
    }
    if(deltaF>=1){
        gamePanel.repaint();
        frames++;
        deltaF--;
    }
        if(System.currentTimeMillis()-lastcheck>=1000){
            lastcheck=System.currentTimeMillis();
            System.out.println("FPS:" + frames + " |UPS: " + updates);
            frames=0;
            updates=0;
        }
    }
    }

    public void windowFocusLost() {
        if(Gamestate.state==Gamestate.PLAYING){
            playing.getPlayer().resetDirBooleans();
        }
    }
    public Menu getMenu(){
        return menu;
    }
    public Playing getPlaying(){
        return playing;
    }
}
