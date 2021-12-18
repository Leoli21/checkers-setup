	/*
	 * A CompSciCheckerBoard represents a playing board for a 
	 * CompSciChecker game.  In this game a board is 8x8 and starts 
	 * with black and white CompSciChecker pieces.  The fields
	 * numWhitePieces and numBlackPieces track the appropriate 
	 * number of pieces for either side.  There are 12 white 
	 * pieces starting at the top left square and fill every other space
	 * on the board for the first three rows.  No two white pieces 
	 * should be directly adjacent to start.  The same is true of the
	 * 12 black pieces but they start at the bottom right and fill the
	 * bottom three rows.  the pieces may move one place in any
	 * direction, forwards, backwards, left, right or diagonal.  The
	 * pieces may "jump" an opposing player in any direction as well.
	 * 
	 */
public class CompSciCheckerBoard 
{
	
	private CompSciChecker[][] board;
	private int numWhitePieces;
	private int numBlackPieces;
	
	/*
	 * Create a Constructor for the CompSciCheckerBoard
	 * Once created the board should look like this:
	 * 
	 *  _ _ _ _ _ _ _ _
	 * |W   W   W   W  |
	 * |  W   W   W   W|
	 * |W   W   W   W  |
	 * |               |
	 * |               |
	 * |  B   B   B   B|
	 * |B   B   B   B  |
	 * |_ B _ B _ B _ B|
	 * 
	 * Empty Spaces have a value of null
	 * 
	 * 
	 */
	
	public CompSciCheckerBoard()
	{
		//TODO PART A Gitt erDone!
		board = new CompSciChecker[8][8];
		for (int row = 0; row < 8; row++) 
		{
			for(int col = 0; col < 8; col ++)
			{
				if(row %2 == col %2)
				{
					if(row < 3)
					{
						board[row][col] = new CompSciChecker("W", col, row);
						numWhitePieces++;
					}
					else if(row > 4)
					{
						board[row][col] = new CompSciChecker("B", col, row);
						numBlackPieces++;
					}
					else
					{
						board[row][col] = null;
					}
				}
				else
				{
					board[row][col] = null;
				}
			}
		}
	}
	
	
	
	/*
	 * Next you will write the method isValidMove which will
	 * take a CompSciChecker piece and a new x and y value
	 * and determine if the move to the new space is legal.
	 * 
	 * A CompSciChecker piece can move one space in any direction
	 * including diagonally, so long as the space is not occuppied.
	 * 
	 * A CompSciChecker piece can move two spaces in any direction
	 * ONLY IF there is an opposing piece being "jumped" and the new
	 * space is not occupied.
	 * 
	 * write the method isValidMove.  return true if the desired move
	 * is valid and false if it is not.
	 */
	
	public boolean isValidMove(CompSciChecker piece, int newX, int newY)
	{
		//TODO  PART B   GitterDone!!
		//x controls columns, y controls rows

		if(newX < 0 || newX >= 8 || newY < 0 || newY >= 8)
		{
			return false;
		}
		
		if(board[newY][newX] != null)
		{
			return false;
		}
		
		int xChange = Math.abs(newX - piece.getX());
		int yChange = Math.abs(newY - piece.getY());
		
		if(xChange == 1)
		{
			if(yChange == 0 || yChange == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(yChange == 1)
		{
			if(xChange == 0 || xChange == 1)
			{
				return true;
			}
			else
			{
				return false;
			}		
		}
		else if(xChange == 2)
		{
			if(yChange !=  0 && yChange != 2)
			{
				return false;
			}
			else if(board[(piece.getY()+newY)/2] [(piece.getX()+newX)/2] == null)
			{
				return false;
			}
			else if(board[(piece.getY()+newY)/2] [(piece.getX()+newX)/2].getColor().equals(piece.getColor()))
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		else if(yChange == 2)
		{
			if(xChange != 0 && xChange != 2)
			{
				return false;
			}
			else if(board[(piece.getY()+newY)/2] [(piece.getX()+newX)/2] == null)
			{
				return false;
			}
			else if(board[(piece.getY()+newY)/2] [(piece.getX()+newX)/2].getColor().equals(piece.getColor()))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}	
	}
	/*
	 * Finally you will write the method makeMove.  This method will
	 * take a CompSciChecker piece and a new x and y as arguments.  
	 * move the desired piece ONLY IF the move is valid.  If an opposing
	 * piece is "jumped" remove it from the board and update the
	 * appropriate field.
	 * 
	 */
	
	public void makeMove(CompSciChecker piece, int x, int y)
	{
		
		//TODO PART C Gitterdone!!!
		if(isValidMove(piece,x,y))
		{
			int tempX = piece.getX();
			int tempY = piece.getY();
			piece.setX(x);
			piece.setY(y);
			board[y][x] = piece;
			int xChange = Math.abs(x - tempX);
			int yChange = Math.abs(y - tempY);
			int jumpY = (tempY + y) / 2;
			int jumpX = (tempX + x) / 2;
			if(xChange == 2 || yChange == 2) 
			{
				if(board[jumpY][jumpX].getColor().equals("W"))
				{
					board[jumpY][jumpX] = null;
					numWhitePieces--;
				}
				else
				{
					board[jumpY][jumpX] = null;
					numBlackPieces--;
				}
								
			}
		}
	}
	
	
	


	
	/**
	 * 
	 * @param x x coordinate of board
	 * @param y y coordinate of board
	 * @return the piece at (x,y) or null if no piece
	 *         is located at (x,y)
	 */
	public CompSciChecker getPiece(int x, int y)
	{
		return board[y][x];
	}
	
	/**
	 * 
	 * @return the field board
	 */
	public CompSciChecker[][] getBoard()
	{
		return board;
	}
	
	
	/**
	 * returns a string representation of the board
	 */
	public String toString()
	{
		String str = "  _ _ _ _ _ _ _ _\n";
		for(int i = 0; i<board.length; i++)
		{
			str+= i + "|";
			for(int j = 0; j<board[i].length; j++)
			{
				if(board[i][j] != null)
					str+=board[i][j].getColor();
				else
				{
					if(i<board.length-1) str+=" ";
					else str+= "_";
				}
				if(j<board[i].length-1) str+= " ";
			}
			str+="|\n";
		}
		str+= "  0 1 2 3 4 5 6 7\n";
		return str;
	}
	
	
	
	
	

}
