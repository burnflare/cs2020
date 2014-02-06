package cs2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToeGame {
	/**
	 * The scanner used for console input.
	 */
	private static Scanner scanner;

	/**
	 * The board which we are playing on.
	 */
	private ITicTacToeBoard board;
	
	/**
	 * The dimensions of the board.
	 */
	private int dimensions;
	
	/**
	 * Stores a coordinate.
	 */
	private static class Point {
		public int x;
		public int y;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param dimensions The dimensions of the board.
	 */
	public TicTacToeGame(int dimensions) {
		this.dimensions = dimensions;
		board = new TicTacToeBoard();
		board.Reset(dimensions, dimensions);
	}
	
	public void Play() throws IOException {
		for (int i = 0; !IsFinished(); ++i) {
			//Which player's turn
			ITicTacToeBoard.State state = i % 2 == 0 ?
					ITicTacToeBoard.State.Cross : ITicTacToeBoard.State.Circle;
			
			//Print the board.
			ShowBoard();

			//Get the coordinates that the player wants to put his mark
			Point point = GetNextMove(state);
			
			//Mark the point
			board.Mark(point.x, point.y, state);
		}
		
		ITicTacToeBoard.State winner = GetWinner();
		if (winner != ITicTacToeBoard.State.Empty) {
			ShowBoard();
			
			char winningChar = GetPlayerName(winner);
			System.out.println(String.format("%c won the game!", winningChar));
		} else {
			System.out.println("It's a draw!");
		}
	}
	
	/**
	 * Prints the current state of the board to the console.
	 */
	private void ShowBoard() {
		String row;
		{
			StringBuffer separator = new StringBuffer();
			for (int i = 0; i < dimensions; ++i) {
				separator.append("--");
			}
			
			//Extra for the front.
			separator.append("-");
			
			row = separator.toString();
		}
		
		//Print the top border.
		System.out.println(row);
		
		//Each row.
		for (int i = 0; i < dimensions; ++i) {
			System.out.print('|');
			for (int j = 0; j < dimensions; ++j) {
				ITicTacToeBoard.State mark = board.GetMark(j, i);
				
				if (mark == ITicTacToeBoard.State.Empty) {
					System.out.print(' ');
				} else {
					System.out.print(GetPlayerName(mark));
				}
				
				System.out.print('|');
			}
			
			System.out.println();
			System.out.println(row);
		}
	}
	
	/**
	 * Gets the next coordinate the user wants to put his mark on. This will
	 * only return valid coordinates.
	 * 
	 * @param turn Which player's turn.
	 * @return A valid coordinate to put the user's move.
	 */
	private Point GetNextMove(ITicTacToeBoard.State turn) throws IOException {
		PromptNextMove(turn);
		String line = scanner.nextLine();
		
		final Pattern regex = Pattern.compile("([\\d]+) ([\\d]+)");
		
		for ( ; ; ) {
			Matcher matches = regex.matcher(line);
			if (matches.matches()) {
				String x = matches.group(1);
				String y = matches.group(2);
				
				Point result = new Point();
				result.x = Integer.parseInt(x);
				result.y = Integer.parseInt(y);
				
				if (result.x >= dimensions || result.y >= dimensions) {
					System.err.println("The coordinates entered is outside the board.");
				} else if (board.GetMark(result.x, result.y) !=
						ITicTacToeBoard.State.Empty) {
					System.err.println("The coordinates entered already has been marked.");
				} else {
					return result;
				}
			} else {
				System.err.println("Enter the coordinates in the format x y");
			}
			
			System.err.flush();
			PromptNextMove(turn);
			line = scanner.nextLine();
		}
	}
	
	/**
	 * Prompts the next person in turn for coordinates.
	 * 
	 * @param turn The person who should be entering his coordinates.
	 */
	private void PromptNextMove(ITicTacToeBoard.State turn) {
		System.out.print(GetPlayerName(turn));
		System.out.print("'s turn; enter x y, top-left = 0 0: ");
	}
	
	/**
	 * Checks if a winning combination has been reached or if the board has been
	 * completely filled.
	 * 
	 * @return True if someone has won the game.
	 */
	private boolean IsFinished() {
		return GetWinner() != ITicTacToeBoard.State.Empty || IsAllSquaresFilled();
	}
	
	/**
	 * Gets the player name based on his state.
	 * 
	 * @param turn The player's mark.
	 * @return The name of the player (X or O)
	 */
	private char GetPlayerName(ITicTacToeBoard.State turn) {
		return turn == ITicTacToeBoard.State.Circle ? 'O' : 'X';
	}
	
	/**
	 * Gets the winner of the game; if there is still no winner, Empty is
	 * returned.
	 * 
	 * @return The winner of the game, or Empty if there is none.
	 */
	private ITicTacToeBoard.State GetWinner() {
		//We can only win by having a row or column or diagonal with the same
		//mark.
		
		//We first check rows.
		for (int i = 0; i < dimensions; ++i) {
			ITicTacToeBoard.State first = board.GetMark(0, i);
			
			if (first == ITicTacToeBoard.State.Empty) {
				//Cannot be a winner.
				continue;
			}
			
			boolean same = true;
			for (int j = 1; j < dimensions; ++j) {
				//Check that we are still the same.
				if (board.GetMark(j, i) != first) {
					same = false;
					break;
				}
			}
			
			if (same) {
				return first;
			}
		}
		
		//Then columns.
		for (int i = 0; i < dimensions; ++i) {
			ITicTacToeBoard.State first = board.GetMark(i, 0);
			
			if (first == ITicTacToeBoard.State.Empty) {
				//Cannot be a winner.
				continue;
			}
			
			boolean same = true;
			for (int j = 1; j < dimensions; ++j) {
				//Check that we are still the same.
				if (board.GetMark(i, j) != first) {
					same = false;
					break;
				}
			}
			
			if (same) {
				return first;
			}
		}
		
		//Then diagonals. Top-left
		ITicTacToeBoard.State first = board.GetMark(0, 0);
		
		if (first != ITicTacToeBoard.State.Empty) {
			boolean same = true;
			for (int i = 1; i < dimensions; ++i) {
				if (board.GetMark(i, i) != first) {
					same = false;
				}
			}
			
			if (same) {
				return first;
			}
		}
		
		//Top-right
		first = board.GetMark(dimensions - 1, 0);
		
		if (first != ITicTacToeBoard.State.Empty) {
			boolean same = true;
			for (int i = 1; i < dimensions; ++i) {
				if (board.GetMark(dimensions - 1 - i, i) != first) {
					same = false;
				}
			}
			
			if (same) {
				return first;
			}
		}
		
		//No matches
		return ITicTacToeBoard.State.Empty;
	}
	
	/**
	 * Checks if all the squares in the board has been filled.
	 * 
	 * @return True if the board is completely filled.
	 */
	private boolean IsAllSquaresFilled() {
		int emptySquares = 0;
		for (int i = 0; i < dimensions; ++i) {
			for (int j = 0; j < dimensions; ++j) {
				if (board.GetMark(j, i) == ITicTacToeBoard.State.Empty) {
					++emptySquares;
				}
			}
		}
		
		return emptySquares == 0;
	}
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		try {
			final int dimensions = 3;
			
			//Driver loop.
			do {
				TicTacToeGame game = new TicTacToeGame(dimensions);
				game.Play();
			} while (doContinuePlaying());
		} catch (IOException e) {
		}
	}

	public static boolean doContinuePlaying() {
		for ( ; ; ) {
			System.out.print("Play again? [Y/N]: ");
			String line = scanner.nextLine().trim().toLowerCase();
			
			if (line.equals("y")) {
				return true;
			} else if (line.equals("n")) {
				return false;
			}
			System.err.println("Please enter Y/N.");
			System.err.flush();
		}
	}
}
