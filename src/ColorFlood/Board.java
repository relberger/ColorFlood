package ColorFlood;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board {

    private Cell[][] gameBoard;
    private final int GAME_COLS;
    private final int GAME_ROWS;
    private Color[] gameColors;
    private Color selectedColor;
    private int activeCells;

    public Board(int GAME_COLS, int GAME_ROWS) {
        this.GAME_COLS = GAME_COLS;
        this.GAME_ROWS = GAME_ROWS;
        Colors gameColors = new Colors();
        this.gameColors = gameColors.getColors();
        createGameBoard();
    }

    private void createGameBoard() {
        Random random = new Random();
        int cellColor;
        gameBoard = new Cell[GAME_COLS][GAME_ROWS];
        for (int col = 0; col < GAME_COLS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                cellColor = random.nextInt(gameColors.length - 1);
                Cell cell = new Cell(col, row, gameColors[cellColor]);
            }
        }
    }

    private Cell startCell() {
        //get start cell from user click
        //mark active
        //iterate active cells
        return null;
    }

    public void setSelectedColor(Color selectedColor)
    {
        //set color based on button clicked
        this.selectedColor = selectedColor;
    }

    private void activateNeighbors(){
        //ArrayList<Cell> neighbors = new ArrayList<>();
        for (int col = 0; col < GAME_COLS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                Cell cell = gameBoard [col][row];
                if (cell.isActive()){
                    //check for neighbors of search color and activate
                    //iterate active cells
                    neighborsToActivate(cell);
                    //neighbors.addAll(neighborsToActivate(cell));
                }
            }
        }
        /*//set active after finding all cells that are active when user clicked
        for (Cell neighbor : neighbors)
        {
            neighbor.setActive(true);
            activeCells++;
        }*/ //set active in neighborstoactivate because then it will keep checking for same colored blocks
    }

    private void neighborsToActivate(Cell cell)
    {
        //ArrayList<Cell> activatedNeighbors = new ArrayList<>();

        //top
        setCellActive(cell.getCol(), cell.getRow() - 1);

        //bottom
        setCellActive(cell.getCol(), cell.getRow() + 1);

        //left
        setCellActive(cell.getCol() - 1, cell.getRow());

        //right
        setCellActive(cell.getCol() + 1, cell.getRow());

        //return activatedNeighbors;
    }

    private void setCellActive(int col, int row)
    {
        if (boardContains(col, row)){
            if (!gameBoard[col][row].isActive()){
                if (gameBoard[col][row].getColor() == selectedColor){
                    gameBoard[col][row].setActive(true);
                    activeCells++;
                }
            }
        }
    }

    private boolean boardContains(int col, int row){
        return (col < GAME_COLS) && (row < GAME_ROWS) && (col >= 0) && (row >= 0);

    }

    private void colorActiveCells(Color color){
        for (int col = 0; col < GAME_COLS; col++) {
            for (int row = 0; row < GAME_ROWS; row++) {
                Cell cell = gameBoard [col][row];
                if (cell.isActive()){
                    cell.setColor(color);
                }
            }
        }
    }

    public void flood(Color color)
    {
        activateNeighbors();
        colorActiveCells(selectedColor); //call here or in repaint?
    }

    private boolean gameOver() {
        return (GAME_COLS * GAME_ROWS) == activeCells || timesUp();
    }

    private boolean timesUp() {
        //if timer = 0
        return false;
    }
}