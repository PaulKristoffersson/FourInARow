package fyrairad;

import java.util.*;

public class FourInARow {

	// There are two players: 1 and 2.
	// Player 1 starts.
	static int currentPlayer = 1;

	// Number of rows in the board
	static int ROWS = 6;

	// Number of columns in the board
	static int COLS = 9;

	// Create an empty board (already initialised to
	// 0, i.e. all places are free). Tokens of player 1
	// are represented with the number 1 in the board.
	// Those of player 2 are represented with the number 2.
	static int[][] board = new int[ROWS][COLS];

	public static void main(String[] args) {
		// You do not have to change this method
		printBoard();
		while (true) {
			int col = getColumn();
			int row = getColumnsSmallestFreeRow(col);
			board[row][col] = currentPlayer;
			printBoard();
			if (CurrentPlayerHasWon(row, col)) {
				System.out.println("Player " + currentPlayer + " won !!");
				break;
			}
			nextPlayer();
		}
	}

	static boolean CurrentPlayerHasWon(int row, int col) {
		// TODO: write a method that determines, given row and col,
		// if the current player has at least 4 tokens in a horizontal,
		// vertical or diagonal line passing by position board[row][col].
		// It is assumed that row satisfies 0 <= row < ROWS and that
		// col satisfies 0 <= col < COLS (i.e., you do not have to check
		// here that the input position is in the board)
		int startRow = row-3;
		int startCol = col-3;
		int streak = 0;
		for (int i = 0; i<COLS; i++) {
			for (int j = 0; j<ROWS; j++) {
				if(board[j][i] == currentPlayer) {
					streak++;
					if(streak == 4) {
						return true;
					}
				}else {
					streak = 0;
				}
				
			}
		}
		for(int j = 0; j<ROWS; j++) {
			streak = 0;
			for (int i = 0; i<COLS; i++) {
				if(board[j][i] == currentPlayer) {
					streak++;
					if(streak == 4) {
						return true;
					}
				}else {
					streak = 0;
				}
			}
		}
	
		for (int j = 0; j<ROWS; j++) {
		//	int x = 0; 
			int y = (row-j);	
			streak = 0;	
			for (int i = 0; i<COLS; i++) {
				if (board[j][i]== currentPlayer) {
					int x = col-j; 
					
					//streak++;
					while(y<ROWS) {
						try {
							if(board[y][x]==currentPlayer) {
								x++;   
								streak++;
								y++;
							}
							else {
								streak = 0;
								y++;
								x++;
							}
							if (streak == 4) {
								return true;
							}

						}
						
						catch (IndexOutOfBoundsException e) {
							System.out.println("Krash");
							break;
						}
					}

				}
				else {
					y = 0;
					streak = 0;
				}


			}
		}
		
		for (int j = ROWS-1; j>=0; j--) {
		//	int x = 0; 
			int y = (row+j);	
			streak = 0;	
			for (int i = 0; i<COLS; i++) {
				if (board[j][i]== currentPlayer) {
					int x = col+j; 
					
					//streak++;
					while(y<ROWS) {
						try {
							if(board[y][x]==currentPlayer) {
								System.out.println("y värdet"+ y +"streak" + streak);
								System.out.println(x);
								x--;   
								streak++;
								y++;
							}
							else {
								System.out.println("y värdet"+ y +"streak" + streak);
								System.out.println(x);
								streak = 0;
								y++;
								x--;
							}
							if (streak == 4) {
								return true;
							}

						}
						
						catch (IndexOutOfBoundsException e) {
							System.out.println("Krash");
							break;
						}
					}

				}
				else {
					y = 0;
					streak = 0;
				}


			}
		}
		
		return false;
	}
	

	public static void nextPlayer() {
		// TODO: write a method that changes the currentPlayer to be
		// the other player.
		if (currentPlayer == 1) {
			currentPlayer = 2;
		}else {
			currentPlayer = 1;
		}
	}

	public static int getColumnsSmallestFreeRow(int col) {
		// TODO: write a method that returns, for the column col,
		// the smallest row, larger or equal to 0, that is not
		// occupied by any of the players.
		// The method should return ROWS if all rows are occupied.
		// You can assume that col satisfies 0 <= col < COLS (i.e., you
		// do not have to check it here).
		for (int i = 0; i<ROWS; i++) {
			if (board[i][col] == 0) {		
				return i;
			}
		} 
		return ROWS-1;
		
	}

	public static int getColumn() {
		Scanner scan = new Scanner(System.in);
		// TODO: write a method that keeps asking the current player for a
		// column between 1 and COLS (inclusive) that is not full until it
		// obtains such a column col. In that case, the method returns col - 1.
		// In other words, the method returns a col that satisfies:
		// 0 <= col < COLS and there is some row such that 0 <= row < ROWS
		// and board[row][col] is 0.
		// Make use of the nextInt Scanner method and of the
		// InputMismatchException exception when asking for the column.
		int rad = 0;
		while (rad <1 || rad>9 ) {
			System.out.print("Player " + currentPlayer + " Choose a non-full column between 1 and " + (COLS) + ": ");
			try {
				rad = scan.nextInt();
			}
			catch (InputMismatchException e) {
				//System.out.println("Du angav ett felaktigt nummer. Försök igen");
				scan.nextLine();
			}
		}
		return rad-1;
	}

	public static void printBoard() {
		// You do not have to change this method
		System.out.println();

		// For each row
		for (int row = ROWS - 1; row >= 0; row--) {
			// Print separator first on row
			System.out.print("|");
			// For each column in that row
			for (int col = 0; col < COLS; col++) {
				// Print player number or space
				if (board[row][col] == 0) {
					System.out.print(" 0");
				} else {
					System.out.print(" " + board[row][col]);
				}
			}
			// Print separator and row number last on row
			System.out.println(" | " + (row + 1));
		}

		// Print separators in bottom
		for (int col = 0; col < COLS + 2; col++) {
			System.out.print("--");
		}

		System.out.println();

		// Print column numbers
		System.out.print("|");
		for (int col = 0; col < COLS; col++) {
			System.out.print(" " + (col + 1));
		}
		System.out.println(" |\n");
	}

}