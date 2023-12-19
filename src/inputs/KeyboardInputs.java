package inputs;

import entities.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Direction.*;


public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                System.out.println("A pressed");
                //gamePanel.setDirection(LEFT);
                gamePanel.getGame().getPlayer().setLef(true);
                break;
            case KeyEvent.VK_S:
                System.out.println("S pressed");
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                System.out.println("D pressed");
                gamePanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_W:
                System.out.println("W pressed");
                gamePanel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_L:
                System.out.println("L pressed");
                gamePanel.getGame().getPlayer().setAttacking(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLef(false);
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);

            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);

            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_L:
                gamePanel.getGame().getPlayer().setAttacking(false);
                break;

        }
    }
}
