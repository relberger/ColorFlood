package ColorFlood;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Properties {

    static final Color BACKGROUND_COLOR = Color.BLACK;
     static final Color RED = Color.RED;
     static final Color CYAN = Color.CYAN;
     static final Color YELLOW = Color.YELLOW;
     static final Color GREEN = Color.GREEN;
     static final Color BLUE = Color.BLUE;
     static final Color MAGENTA = Color.MAGENTA;

     static final Color[] COLORS = new Color[]
            {
                    RED, CYAN, YELLOW, GREEN, BLUE, MAGENTA
            };


     static final String[] DIFFICULTY = {"easy", "medium", "hard"};

     static final int GAME_COLUMNS_EASY = 10;
     static final int GAME_ROWS_EASY = 10;

     static final int GAME_COLUMNS_MEDIUM = 15;
     static final int GAME_ROWS_MEDIUM = 15;

     static final int GAME_COLUMNS_HARD = 20;
     static final int GAME_ROWS_HARD = 20;


    static final int MAIN_PANEL_HEIGHT = 700;
    static final int MAIN_PANEL_WIDTH = 500;

    private static final int TIMER_PANEL_HEIGHT = 100;
    private static final int TIMER_PANEL_WIDTH = 500;
    static final Dimension TIMER_PANEL_SIZE = new Dimension(TIMER_PANEL_WIDTH, TIMER_PANEL_HEIGHT);

    static final int COLOR_BUTTON_HEIGHT = 30;
    static final int COLOR_BUTTON_WIDTH = 70;
    static final Dimension COLOR_BUTTON_SIZE = new Dimension(COLOR_BUTTON_WIDTH, COLOR_BUTTON_HEIGHT);

    static final int INITIAL_TIME = 70_000;
}
