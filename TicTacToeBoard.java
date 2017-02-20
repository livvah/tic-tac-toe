package tictactoe;

public interface TicTacToeBoard {
	
	
	public final static int ROW_COUNT = 3;
	public final static int COLUMN_COUNT = 3;
	
	//pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//post: rv == null <== the (row, column) spot on the board is empty
	public Mark getMark(int row, int column);
	
	//pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//pre: getMArk(row, column) == null
	//pre: !isGameOver()
	//post: 	WRITE
	public void setMark(int row, int column);
	
	//post: rv == null <==> it is neither player's turn (i.e. game is over)
	//post: "number of Marks on board is even" --> rv == Mark.X
	//post: "number of Marks on board is odd" --> rv == Mark.O
	public Mark getTurn();
	
	//post: WRITE (SEE TIC TAC TOE RULES TO WRITE)
	public boolean isGameOver();
	
	//pre: isGameOver()
	//post: rv = null <==> neither player won (i.e. the game ended in a tie)
	public Mark getWinner();
	
	
}
