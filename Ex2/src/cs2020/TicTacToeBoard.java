package cs2020;

import java.util.ArrayList;

import org.junit.Assert;

public class TicTacToeBoard implements ITicTacToeBoard {
	// Use this 2-d array to store your board
	private ArrayList<ArrayList<ITicTacToeBoard.State>> board;

	@Override
	public void Reset(int width, int height) {
		board = new ArrayList<ArrayList<ITicTacToeBoard.State>>();
		for(int i=0;i<height;i++) {
			ArrayList<ITicTacToeBoard.State> row = new ArrayList<ITicTacToeBoard.State>();
			for(int j=0;j<width;j++) {
				row.add(ITicTacToeBoard.State.Empty);
			}
			board.add(row);
		}
	}

	@Override
	public void Mark(int x, int y, State state) {
		if(y>=board.size() || x>=board.get(y).size()) {
			return;
		}
		board.get(y).remove(x);
		board.get(y).add(x, state);		
	}

	@Override
	public State GetMark(int x, int y) {
		if(y>=board.size() || x>=board.get(y).size()) 
			return ITicTacToeBoard.State.Empty;
		return board.get(y).get(x);
	}
}
