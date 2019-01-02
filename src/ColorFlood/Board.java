package ColorFlood;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {

    public Cell[][] gameBoard;
    public final int GAME_ROWS;
    public final int GAME_COLUMNS;
    public Color selectedColor;
    private int activeCells;
    private int time;
    private Cell firstCell;

    /**
     * The constructor's fields are meant to take one of three set options depending on the user's choice of level.
     *
     * @param GAME_COLUMNS
     * @param GAME_ROWS
     */
    public Board(int GAME_COLUMNS, int GAME_ROWS) {
        this.GAME_COLUMNS = GAME_COLUMNS;
        this.GAME_ROWS = GAME_ROWS;

        activeCells = 0;

        createGameBoard();

        setUpBoardPanel();
    }

    private void createGameBoard() {
        Random random = new Random();
        int cellColor;
        gameBoard = new Cell[GAME_COLUMNS][GAME_ROWS];
        for (int row = 0; row < GAME_ROWS; row++) {
            for (int col = 0; col < GAME_COLUMNS; col++) {
                cellColor = random.nextInt(Properties.COLORS.length);
                Cell newCell = new Cell(row, col,Properties.COLORS[cellColor]);
                gameBoard[row][col] = newCell;
            }
        }
    }

    private void setUpBoardPanel() {
        setLayout(new GridLayout(GAME_COLUMNS, GAME_ROWS));
        setBorder(new EmptyBorder(20, 10, 20, 10));
        setBackground(Properties.BACKGROUND_COLOR);

        addBoardPanelComponents();
    }

    private void addBoardPanelComponents() {
        for(int col = 0; col < GAME_COLUMNS; col++) {
            for(int row = 0; row < GAME_ROWS; row++) {
                add(gameBoard[row][col]);
            }
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
        flood();
    }


    private void activateNeighbors() {
        for (int col = 0; col < GAME_COLUMNS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                Cell cell = gameBoard[row][col];
                if (cell.isActive()) {
                    neighborsToActivate(cell);
                }
            }
        }
    }

    private void neighborsToActivate(Cell cell) {
        //top
        setCellActive(cell.getRow() - 1, cell.getCol());

        //bottom
        setCellActive(cell.getRow() + 1, cell.getCol());

        //left
        setCellActive(cell.getRow(), cell.getCol() - 1);

        //right
        setCellActive(cell.getRow(), cell.getCol() + 1);
    }

    public void setCellActive(int row, int col) {
        if (boardContains(row, col)) {
            if (!gameBoard[row][col].isActive()) {
                if (gameBoard[row][col].getColor() == selectedColor) {
                    gameBoard[row][col].setActive(true);
                    activateNeighbors();
                    activeCells++;
                }
            }
        }
    }

    public void activateFirstCell(int row, int col)
    {
        if (boardContains(row, col))
        {
            gameBoard[row][col].setActive(true);
            activeCells++;
            firstCell = gameBoard[row][col];
            setSelectedColor(firstCell.getColor());
        }
    }


    private boolean boardContains(int row, int col) {
        return (col < GAME_COLUMNS) && (row < GAME_ROWS) && (col >= 0) && (row >= 0);
    }

    private void colorActiveCells(Color color) {
        for (int col = 0; col < GAME_COLUMNS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                Cell cell = gameBoard[row][col];
                if (cell.isActive()) {
                    cell.setColor(color);
                }
            }
        }
        repaint();
    }

    public void flood() {
        activateNeighbors();
        colorActiveCells(selectedColor);
        System.out.println(activeCells + selectedColor.toString());
    }

    public int getActiveCells() {
        return activeCells;
    }

    public int getTime() {
        return time;
    }
}