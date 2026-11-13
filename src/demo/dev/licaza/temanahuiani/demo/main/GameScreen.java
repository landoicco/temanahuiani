package dev.licaza.temanahuiani.demo.main;

import dev.licaza.temanahuiani.demo.inputs.*;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension size;
    private MyMouseListener mouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);
    }

    public void initInputs() {
        mouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
