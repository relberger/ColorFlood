package ColorFlood;

class BoardBuilder {

    private Board board;
    private String difficulty;

    BoardBuilder(String difficulty) {
        this.difficulty = difficulty;

        initializeBoard();
    }

    private void initializeBoard() {
        if(difficulty.equals(Properties.DIFFICULTY[0])) {
            board = new Board(Properties.GAME_COLUMNS_EASY, Properties.GAME_ROWS_EASY);
        }
        else if(difficulty.equals(Properties.DIFFICULTY[1])) {
            board = new Board(Properties.GAME_COLUMNS_MEDIUM, Properties.GAME_ROWS_MEDIUM);
        }
        else if(difficulty.equals(Properties.DIFFICULTY[2])) {
            board = new Board(Properties.GAME_COLUMNS_HARD, Properties.GAME_ROWS_HARD);
        }
    }

    Board getBoard() {
        return board;
    }
}