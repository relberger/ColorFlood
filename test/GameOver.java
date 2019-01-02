import ColorFlood.Board;
import ColorFlood.Cell;
import ColorFlood.ColorFlood;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameOver
{
	@Test
	public void allCellsActiveGameOver()
	{
		//given
		Board board = new Board(10, 10);
		for (int row = 0; row < board.GAME_ROWS; row++)
		{
			for (int col = 0; col < board.GAME_COLUMNS; col++)
			{
				board.gameBoard[row][col] = new Cell(row, col, Color.BLUE);
				board.setCellActive(board.gameBoard[row][col].getRow(), board.gameBoard[row][col].getCol());
			}
		}

		//when
		board.activateFirstCell(board.gameBoard[0][0].getRow(), board.gameBoard[0][0].getCol());
		board.selectedColor = Color.BLUE;

		//then
		assertTrue((board.GAME_COLUMNS * board.GAME_ROWS) == board.getActiveCells());
	}

	@Test
	public void allTimesUpGameOver()
	{
		//given
		ColorFlood colorFlood = new ColorFlood();
		Board board = new Board(2, 2);

		//when
		board.setTime(0);

		//then
		assertTrue(colorFlood.timesUp() == true);
	}
}
