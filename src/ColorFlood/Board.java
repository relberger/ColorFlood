package ColorFlood;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Board {

    public Cell[][] gameBoard;
    public final int GAME_ROWS;
    public final int GAME_COLUMNS;
    public Color selectedColor;
    private int activeCells;
    private int time = 60;

    /**
     * The constructor's fields are meant to take one of three set options depending on the user's choice of level.
     *
     * @param GAME_COLUMNS
     * @param GAME_ROWS
     */
    public Board(int GAME_ROWS, int GAME_COLUMNS) {
        this.GAME_COLUMNS = GAME_COLUMNS;
        this.GAME_ROWS = GAME_ROWS;

        createGameBoard();
    }

    private void createGameBoard() {
        Random random = new Random();
        int cellColor;
        gameBoard = new Cell[GAME_COLUMNS][GAME_ROWS];
        for (int row = 0; row < GAME_ROWS; row++) {
            for (int col = 0; col < GAME_COLUMNS; col++) {
                cellColor = random.nextInt(Properties.COLORS.length);
                Cell newCell = new Cell(row, col, Properties.COLORS[cellColor]);
                gameBoard[row][col] = newCell;
            }
        }
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public void setSelectedColor(Color selectedColor) {
        //set color based on button clicked
        this.selectedColor = selectedColor;
    }

    private void activateNeighbors() {
        for (int col = 0; col < GAME_COLUMNS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                Cell cell = gameBoard[col][row];
                if (cell.isActive()) {
                    neighborsToActivate(cell);
                }
            }
        }
    }

    private void neighborsToActivate(Cell cell) {
        //top
        setCellActive(cell.getCol(), cell.getRow() - 1);

        //bottom
        setCellActive(cell.getCol(), cell.getRow() + 1);

        //left
        setCellActive(cell.getCol() - 1, cell.getRow());

        //right
        setCellActive(cell.getCol() + 1, cell.getRow());
    }

    public void setCellActive(int col, int row) {
        if (boardContains(col, row)) {
            if (!gameBoard[col][row].isActive()) {
                if (gameBoard[col][row].getColor() == selectedColor) {
                    gameBoard[col][row].setActive(true);
                    activeCells++;
                }
            }
        }
    }

    private boolean boardContains(int col, int row) {
        return (col < GAME_COLUMNS) && (row < GAME_ROWS) && (col >= 0) && (row >= 0);
    }

    private void colorActiveCells(Color color) {
        for (int col = 0; col < GAME_COLUMNS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                Cell cell = gameBoard[col][row];
                if (cell.isActive()) {
                    cell.setColor(color);
                }
            }
        }
    }

    public void flood() {
        activateNeighbors();
        colorActiveCells(selectedColor); //call here or in repaint?
    }

    public boolean gameOver() {
        return (GAME_COLUMNS * GAME_ROWS) == activeCells || timesUp();
    }

    public int getTime()
    {
        return time;
    }

    /*private void timer()
    {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println(timeRemaining());
            }
        }, 1000, 1000);
    }

    private String timeRemaining()
    {

    }*/

    public boolean timesUp() {
        if (time == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}