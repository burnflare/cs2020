package cs2020;

import org.junit.*;

/**
 * Tests the Tic-Tac-Toe Board implementation.
 * 
 * To use this, ensure that your TicTacToeBoard class has a default constructor
 * which takes no parameters.
 * 
 * @author Joel Low <joel.low@nus.edu.sg>
 */
public class TicTacToeBoardTest {
	/**
	 * The state pattern used for the tests.
	 */
	private static final ITicTacToeBoard.State[] States = new ITicTacToeBoard.State[] {
		ITicTacToeBoard.State.Circle,
		ITicTacToeBoard.State.Cross,
		ITicTacToeBoard.State.Circle,
		ITicTacToeBoard.State.Empty,
	};
	
	private ITicTacToeBoard board;
	
	@Before
	public void SetUp() {
		board = new TicTacToeBoard();
	}
	
	@Test
	public void TestReset() {
		int width = 4;
		int height = 4;
		board.Reset(width, height);
		
		//Ensure that the board is correctly initialised.
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				Assert.assertEquals("Ensure that the board is marked with " +
						"Empty.", board.GetMark(j, i),
						ITicTacToeBoard.State.Empty);
			}
		}
	}
	
	@Test
	public void TestMark() {
		int width = 4;
		int height = 5;
		board.Reset(width, height);
		
		//Fill the board with the prescribed pattern; deliberately setting such
		//that we go beyond the bounds of the board (testing that it is a no-op)
		for (int i = 0; i <= height; ++i) {
			for (int j = 0; j <= width; ++j) {
				board.Mark(j, i, States[(i * (width + 1) + j) % States.length]);
				
			}
		}
		
		//Unconditional pass, if it finishes running.
	}
	
	@Test
	public void TestGetMark() {
		TestMark();
		int width = 4;
		int height = 5;
		
		//Check the pattern.
		for (int i = 0; i <= height; ++i) {
			for (int j = 0; j <= width; ++j) {
				if (i >= height || j >= width) {
					//Beyond bounds of board
					Assert.assertEquals("Ensure that querying a square beyond " +
							"the bounds of the board returns Empty.",
							ITicTacToeBoard.State.Empty,
							board.GetMark(j, i));
				} else {
					Assert.assertEquals(String.format("Ensure that after " +
							"setting a square the correct flag is retained, " +
							"at (%d, %d)", j, i),
							States[(i * (width + 1) + j) % States.length],
							board.GetMark(j, i));
				}
			}
		}
	}
}
