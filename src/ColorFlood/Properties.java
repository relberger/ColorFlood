package ColorFlood;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Properties {

    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color RED = Color.RED;
    public static final Color CYAN = Color.CYAN;
    public static final Color YELLOW = Color.YELLOW;
    public static final Color GREEN = Color.GREEN;
    public static final Color BLUE = Color.BLUE;
    public static final Color MAGENTA = Color.MAGENTA;

    public static final Color[] COLORS = new Color[]
            {
                    RED, CYAN, YELLOW, GREEN, BLUE, MAGENTA
            };


    public static final String[] DIFFICULTY = {"easy", "medium", "hard"};

    public static final int GAME_COLUMNS_EASY = 10;
    public static final int GAME_ROWS_EASY = 10;

    public static final int GAME_COLUMNS_MEDIUM = 15;
    public static final int GAME_ROWS_MEDIUM = 15;

    public static final int GAME_COLUMNS_HARD = 20;
    public static final int GAME_ROWS_HARD = 20;


    public static final int MAIN_PANEL_HEIGHT = 700;
    public static final int MAIN_PANEL_WIDTH = 500;

    public static final int TIMER_PANEL_HEIGHT = 100;
    public static final int TIMER_PANEL_WIDTH = 500;
    public static final Dimension TIMER_PANEL_SIZE = new Dimension(TIMER_PANEL_WIDTH, TIMER_PANEL_HEIGHT);

    public static final int COLOR_BUTTON_HEIGHT = 40;
    public static final int COLOR_BUTTON_WIDTH = 40;
    public static final Dimension COLOR_BUTTON_SIZE = new Dimension(COLOR_BUTTON_WIDTH, COLOR_BUTTON_HEIGHT);

    public static final int BOARD_VIEW_HEIGHT = 500;
    public static final int BOARD_VIEW_WIDTH = 500;
    public static final Dimension BOARD_TABLE_SIZE = new Dimension(BOARD_VIEW_WIDTH, BOARD_VIEW_HEIGHT);


    public static ImageIcon createImageIcon(Color color, int width, int height) {
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(color);
        graphics.fillRoundRect( 0, 0, width, height, 10, 10);
        return new ImageIcon(image);
    }
}
