package ColorFlood;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel{

    private int row;
    private int col;
    private boolean active = false;

    private Color color;

    public Cell(int row, int col, Color color) {
        this.col = col;
        this.row = row;

        this.color = color;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBackground(g);

        paintSquare(g);
    }

    private void paintBackground(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintSquare(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                10,
                10);
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
    }

    public boolean isActive() {
        return active;
    }

    void setActive() {
        this.active = true;
    }

    public Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }
}
