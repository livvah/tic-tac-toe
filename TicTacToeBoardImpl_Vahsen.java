package tictactoe;

 public class TicTacToeBoardImpl_Vahsen implements TicTacToeBoard{

		//Symbolics:
		protected static final int NO_MOVE = -1;
		protected static final int NO_MATCH = -1;
		
		protected int[] movesArray;
		
		public TicTacToeBoardImpl_Vahsen()
		{
			final int CELL_COUNT = ROW_COUNT * COLUMN_COUNT;
			movesArray = new int[CELL_COUNT];
			for(int i = 0; i < CELL_COUNT; i++)
			{
				movesArray[i] = NO_MOVE;
			}
		}
		
		
		
		public String toString(){
			
			String grid = "";
			
			//int gridPos = 0;
			
			for(int row = 0; row < ROW_COUNT; row++ ){
				
				for(int column = 0; column < COLUMN_COUNT; column++){
					
					//gridPos++;
					
					Mark newMark = getMark(row, column);
					
					if(getMarkPosition(row, column) == 3 || getMarkPosition(row, column) == 6)
					
						grid += "\n" + "------" + "\n";
					
					else if(getMarkPosition(row, column) == 8)
						
						grid += "";
					
					if(newMark == null)
						
						grid += " ";
					
					else
						grid += newMark;
					
					if (column < COLUMN_COUNT - 1) 
						grid += "|";
					
					
				}
			}
			
			return grid;
		}
		
		
		//Mathematical operations for finding placement by specific number as opposed to coordinates
		private int getMarkPosition(int row, int column) {
			
			int markPosition = 0;
			
			if(row == 0){
				
				markPosition = column;
			}
			
			else if(row == 1){
				
				markPosition = column + 3;
			}

			else{
				
				markPosition = column + 6;
				
			}
			
				
			return markPosition;
		}
		
		//helper for getMark
		//use index of value in MovesArray to perform odd/even operation
		private Mark markAtIndex(int arrayIndex){
			
			Mark markAtIndex = null;
			
			if (arrayIndex % 2 != 0){
				
				markAtIndex = Mark.O;
			}
			else if (arrayIndex % 2 == 0){
				
				markAtIndex = Mark.X;
				
			}
			return markAtIndex;

		}
		
		
		
		//pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
		//post: rv == null <== the (row, column) spot on the board is empty
		public Mark getMark(int row, int column) {
			assert row >= 0 : "row = " + row + " < 0!";
			
			int markPosition = getMarkPosition(row, column);
			
			
			Mark returnMark = null;
			
			for(int i = 0; i<movesArray.length; i++){
				
				if(movesArray[i] == markPosition){
					
					returnMark = markAtIndex(i);
				}//how to move this for-loop to another method
				//while replacing the "returnMark = markAtIndex(i);" line correctly
			}
			
			//System.out.println("getMark: " + returnMark);
			return returnMark;
			
		}

		//Use loop through movesArray to increment a mark counter
		private int findNumberOfMarks(){
			
			int numberOfMarks = 0;
			
			for(int i = 0; i < movesArray.length; i++){
				
				if(movesArray[i]== NO_MOVE){
					
					break;
				}
				
				numberOfMarks++;
			}
			
			return numberOfMarks;
		}
		
		//pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
		//pre: getMark(row, column) == null
		//pre: !isGameOver()
		//post: mark set according to current array index in relation to non-occupied place
		public void setMark(int row, int column) {
			
			assert !isGameOver();
			assert row >= 0 && row <3 :"row out of range";
			assert column >= 0 && column <3: "column out of range";
			
			int arrayIndexNow = findNumberOfMarks();
			
			int markPosition = getMarkPosition(row, column);
			
	
			movesArray[arrayIndexNow] = markPosition;
			//number of moves is n, adding n+1 mark at index n

			
			return;
			
		}

		//post: rv == null <==> it is neither player's turn (i.e. game is over)
		//post: "number of Marks on board is even" --> rv == Mark.X
		//post: "number of Marks on board is odd" --> rv == Mark.O
		public Mark getTurn() {
			
			//boolean isXTurn = 
					
			Mark markForTurn = null;
			
			for(int i = 0; i < movesArray.length; i++){
				
				if(movesArray[i] == NO_MOVE){
					
					if(i %2 == 0){
						
						markForTurn = Mark.X;
						break;
					}
					
					else{
						
						markForTurn = Mark.O;
						break;
					}
					
				}
					
			}
			
			System.out.println(markForTurn);
			return markForTurn;	
			
		}
		
		//checks possible winning lines one by one unfortunately
		private Mark findMarkWinner(){
			
			Mark markWinner = null;
			
			//ROWS--------------------------------------------------------------------------------------
			if(getMark(0,0) == getMark(0,1) && getMark(0,1) == getMark(0,2) && getMark(0,0) != null)
				
				if(getMark(0,0) == Mark.X)
					
					markWinner = Mark.X;

				else if(getMark(0,0) == Mark.O)
					
					markWinner = Mark.O;

						
			if(getMark(1,0) == getMark(1,1) && getMark(1,1) == getMark(1,2) && getMark(1,0) != null)
						
				if(getMark(1,0) == Mark.X)
							
					markWinner = Mark.X;
					
				else if(getMark(1,0) == Mark.O)
						
					markWinner = Mark.O;
			
			if(getMark(2,0) == getMark(2,1) && getMark(2,1) == getMark(2,2) && getMark(2,0) != null)
						
				if(getMark(2,0) == Mark.X)
							
					markWinner = Mark.X;
					
				else if(getMark(2,0) == Mark.O)
						
					markWinner = Mark.O;
			
			//COLUMNS-----------------------------------------------------------------------------------
			if(getMark(0,0) == getMark(1,0) && getMark(1,0) == getMark(2,0) && getMark(0,0) != null)
						
				if(getMark(0,0) == Mark.X)
							
					markWinner = Mark.X;
					
				else if(getMark(0,0) == Mark.O)
						
					markWinner = Mark.O;
			
			if(getMark(0,1) == getMark(1,1) && getMark(1,1) == getMark(2,1) && getMark(0,1) != null)
				
				if(getMark(0,1) == Mark.X)
					
					markWinner = Mark.X;
			
				else if(getMark(0,1) == Mark.O)
				
					markWinner = Mark.O;
			
			if(getMark(0,2) == getMark(1,2) && getMark(1,2) == getMark(2,2) && getMark(0,2) != null)
				
				if(getMark(0,2) == Mark.X)
					
					markWinner = Mark.X;
			
				else if(getMark(0,2) == Mark.O)
				
					markWinner = Mark.O;
			
			//DIAGONALS-----------------------------------------------------------------------------------
			if(getMark(0,0) == getMark(1,1) && getMark(1,1) == getMark(2,2) && getMark(0,0) != null)
				
				if(getMark(0,0) == Mark.X)
					
					markWinner = Mark.X;
			
				else if(getMark(0,0) == Mark.O)
				
					markWinner = Mark.O;
			
			if(getMark(0,2) == getMark(1,1) && getMark(1,1) == getMark(2,0) && getMark(0,2) != null)
				
				if(getMark(0,2) == Mark.X)
					
					markWinner = Mark.X;
			
				else if(getMark(0,2) == Mark.O)
				
					markWinner = Mark.O;
				
			
			return markWinner;
			
		}

		//post: true if one mark is a horizontal, vertical or diagonal winner 
		//OR if too many marka are made with no winner
		public boolean isGameOver() {
			
			boolean gameEnded = false;
			
			if(findNumberOfMarks() ==9)
				
				gameEnded = true;
			
			else if(findMarkWinner() != null)
				
				gameEnded = true;
			
			
			System.out.println("GameOver: " + gameEnded);
			
			return gameEnded;
			
		}

		//pre: isGameOver()
		//post: rv = null <==> neither player won (i.e. the game ended in a tie)
		public Mark getWinner() {
			
			assert isGameOver() : "Game is not over!";
		
			Mark getWinnerMark = findMarkWinner();
			
			System.out.println("Winner: " + getWinnerMark);
			
			return getWinnerMark;
		}
		
		
	}

