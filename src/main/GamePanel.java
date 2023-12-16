package main;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
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
    private float xDelta=100,yDelta=100;
    //Se un buffer che contenga tutti i pixel dell'immagine
    private BufferedImage img;
    //Creo un array di immagini che contiene un'animazione completa
    private BufferedImage[][] animations;
    private int aniTick, aniIndex,aniSpeed=15;
    private int playerAction=IDLE;
    private int playerDir=-1;
    private boolean moving=false;

    public GamePanel(){
        Importimg();
        //Manda in esecuzione l' animazione
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this));
    }

    private void loadAnimations() {
        //Abbiamo 9 animazioni in altezza e massimo 6 in larghezza
    animations=new BufferedImage[9][6];
    for(int j=0;j<animations.length;j++) {
        for (int i = 0; i < animations[j].length; i++) {
            animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
        }
    }
    }

    //Metodo per importare un immagine
    private void Importimg() {

        //Crea un'istanza di Input Stream e attraverso getClass restituisce l'oggetto Class associato alla classe in cui viene
        //Eseguito il codice. GetResourceAsStream viene chiamato sull'oggetto class,cerca la risorsa associata e resituisce un
        //oggetto InputStream che può essere utilizzato per leggere i dati dalla risorsa.
        InputStream is= getClass().getResourceAsStream("/res/player_sprites.png");
        try {
            //Mettiamo l immagine nel buffer
            img= ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }


            }
        }


    private void setPanelSize() {
        //Decidiamo la dimenzione del jpanel
        //L'immagine sarà 1280/32=40 in larghezza e 800/32=25 in altezza. Il numero 32 è riferito al numero di bit nell'immagine
        Dimension size=new Dimension(1280,800);
        setPreferredSize(size);
    }

   public void setDirection(int direction){
    this.playerDir=direction;
    moving=true;
   }
   public  void  setMoving(boolean moving){
        this.moving=moving;
   }

    private void updateAnimationTick() {

        aniTick++;
        if(aniTick>=aniSpeed){
           aniTick=0;
           aniIndex++;
           if(aniIndex>=GetSpriteAmount(playerAction)){
               aniIndex=0;
           }
        }


    }
    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction=IDLE;
        }
    }

    private void updatePos() {
       if(moving){
          switch(playerDir){
              case LEFT:
                  xDelta-=5;
                  break;
              case UP:
                  yDelta-=5;
                  break;
              case RIGHT:
                  xDelta+=5;
                  break;
              case DOWN:
                  yDelta+=5;
                  break;
          }
       }
    }
    public void updateGame(){
        //Ci serve per disegnare la nostra immagine
        //Per ottenere il frame che vogliamo dobbiamo considerare la x e la y sulla matrice e rispettivamente moltiplicare questi valori per
        //larghezza ed altezza dell immagine
        updateAnimationTick();
        setAnimation();
        updatePos();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(animations[playerAction][aniIndex],(int)xDelta,(int)yDelta,256,160,null);


    }



}
