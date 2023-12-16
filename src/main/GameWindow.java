/*
JFrame è una classe nella libreria swing che fornisce una finestra con una cornice per la costruzione
di interfacce grafiche utente.
 */
package main;
import javax.swing.JFrame;

public class GameWindow{
    private JFrame  jframe;
   public GameWindow(GamePanel gamePanel){
    jframe=new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(gamePanel);
    jframe.setLocationRelativeTo(null);
    jframe.setResizable(false);
    jframe.pack();
    jframe.setVisible(true);
   }
}
