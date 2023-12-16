package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET=120;
    //Update viene utilizzato per far aggiornare la scena quando dobbiamo far muovere il player e in generale far cambiare le cose nella scena
    //Mi sembra di aver capito che viene fatto molto piu spesso rispetto agli FPS
    private final int UPS_SET=200;
    public Game(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(gamePanel);
        //Request of input focus
        gamePanel.requestFocusInWindow();
        startGameLoop();

    }
private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
}
public void Update(){
        gamePanel.updateGame();
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
}
