/*
JFrame è una classe nella libreria swing che fornisce una finestra con una cornice per la costruzione
di interfacce grafiche utente.
 */
package main;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow{
    private JFrame  jframe;
   public GameWindow(GamePanel gamePanel){
    jframe=new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(gamePanel);

    jframe.setResizable(false);
    jframe.pack();
    jframe.setLocationRelativeTo(null);
    jframe.setVisible(true);
    jframe.addWindowFocusListener(new WindowFocusListener() {
        @Override
        public void windowGainedFocus(WindowEvent e) {
            gamePanel.getGame().windowFocusLost();
        }

        @Override
        public void windowLostFocus(WindowEvent e) {

        }
    });
   }
}
