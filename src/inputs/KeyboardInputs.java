package inputs;

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
                gamePanel.setDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                System.out.println("S pressed");
                gamePanel.setDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                System.out.println("D pressed");
                gamePanel.setDirection(RIGHT);
                break;
            case KeyEvent.VK_W:
                System.out.println("W pressed");
                gamePanel.setDirection(UP);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:

            case KeyEvent.VK_S:

            case KeyEvent.VK_D:

            case KeyEvent.VK_W:
                gamePanel.setMoving(false);
                break;

        }
    }
}
