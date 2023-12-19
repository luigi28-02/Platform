package main;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Direction.*;


import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
private Game game;
    public GamePanel(Game game){
        this.game=game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this));
    }


    private void setPanelSize() {
        //Decidiamo la dimenzione del jpanel
        //L'immagine sarà 1280/32=40 in larghezza e 800/32=25 in altezza. Il numero 32 è riferito al numero di bit nell'immagine
        Dimension size=new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size " + GAME_WIDTH + ": " + GAME_HEIGHT);

    }
    public void updateGame(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }


}
