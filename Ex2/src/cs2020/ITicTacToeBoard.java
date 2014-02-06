package cs2020;

/**
 * The interface for implementing a Tic-Tac-Toe board.
 * 
 * @author Joel Low <joel.low@nus.edu.sg>
 */
public interface ITicTacToeBoard {
	/**
	 * The states of each possible square.
	 */
	public enum State {
		/**
		 * No one has selected the square.
		 */
		Empty,
		
		/**
		 * The square is marked with an X.
		 */
		Cross,
		
		/**
		 * The square is marked with an O.
		 */
		Circle
	}
	
	/**
	 * Resets the board to the default empty state, for a board of the given
	 * size.
	 * 
	 * @param width The width of the new board.
	 * @param height The height of the new board.
	 */
	public void Reset(int width, int height);
	
	/**
	 * Marks the given square with the given state.
	 * 
	 * If the coordinates are not valid, this function must act as if the function
	 * was never called (i.e. a no-op)
	 * 
	 * This will only be called after a call to Reset.
	 * 
	 * @param x The x-coordinate of the square to mark.
	 * @param y The y-coordinate of the square to mark.
	 * @param state The state to set.
	 */
	public void Mark(int x, int y, State state);
	
	/**
	 * Gets the state of the given square.
	 * 
	 * If the coordinates are not valid, this function must return State.Empty.
	 * 
	 * This will only be called after a call to Reset.
	 * 
	 * @param x The x-coordinate of the square to mark.
	 * @param y The y-coordinate of the square to mark.
	 * @return state The state to set.
	 */
	public State GetMark(int x, int y); 
}
