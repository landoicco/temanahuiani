package dev.licaza.temanahuiani.demo.ui;

import static dev.licaza.temanahuiani.demo.configs.Colors.*;

import dev.licaza.temanahuiani.engine.commons.misc.ColorPalette;
import dev.licaza.temanahuiani.engine.tools.gui.MyButton;

import java.awt.Color;
import java.awt.Graphics;

public abstract sealed class Bar permits ActionBar, ToolBar {

    private static final Color BORDER_BASE_COLOR = GetColorFromPalette(ColorPalette.ACCENT_ONE);
    private static final Color BORDER_HOVER_COLOR = GetColorFromPalette(ColorPalette.PRIMARY);

    public int x, y, width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawButtonFeedback(Graphics g, MyButton b) {
        // Set border color on MouseOver
        if (b.isMouseOver()) {
            g.setColor(BORDER_BASE_COLOR);
        } else {
            g.setColor(BORDER_HOVER_COLOR);
        }

        // Set border color on MousePressed
        if (b.isMousePressed()) {
            g.setColor(BORDER_BASE_COLOR);
        }

        // Draw border
        g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
        g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
    }
}
