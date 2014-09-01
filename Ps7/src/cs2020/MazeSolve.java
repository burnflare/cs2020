package cs2020;

import java.util.ArrayList;
import java.util.HashMap;

public class MazeSolve implements IMazeSolver{
	private final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
	
	private Maze m_maze;
	private boolean [][] m_visited;
	private ArrayList<Pair> m_frontier;
	private ArrayList<Pair> m_next;
	private HashMap<Pair, Pair> m_parents;
	private static int m_initRow;
	private static int m_initCol;
	private int [][] m_ddir = new int[][] { {-1,0}, {1,0}, {0,1}, {0,-1} }; //North, South, East, West
	
	public MazeSolve() {
		m_maze = null;
		m_frontier = new ArrayList<Pair>(0);
		m_next = new ArrayList<Pair>(0);
		m_parents = new HashMap<Pair, Pair>();
	}
	
	@Override
	public void initialize(Maze maze) {
		this.m_maze = maze;
		m_visited = new boolean[maze.getRows()][maze.getColumns()];
	}

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		if (m_maze == null) {
			throw new Exception("Please initialize maze!");
		}
		
		if (startRow < 0 || startCol < 0 || startRow >= m_maze.getRows() || startCol >= m_maze.getColumns() || 
			endRow < 0 || endCol < 0 || endRow >= m_maze.getRows() || endCol >= m_maze.getColumns() ) {
			throw new IllegalArgumentException("Invalid coordinates");
		}

		for (int i=0;i<m_maze.getRows();++i) {
			for (int j=0;j<m_maze.getColumns();++j) {
				this.m_visited[i][j] = false;
				m_maze.getRoom(i, j).onPath = false;
			}
		}
		m_initRow = startRow;
		m_initCol = startCol;
		this.endRow = endRow;
		this.endCol = endCol;
		
		return solve(startRow, startCol);		
	}
	
	//Making sure we can go
	private boolean canGo(int row, int col, int dir) {
		if (row + m_ddir[dir][0] < 0 || row + m_ddir[dir][0] >= m_maze.getRows()) return false; 
		if (col + m_ddir[dir][1] < 0 || col + m_ddir[dir][1] >= m_maze.getColumns()) return false;
		
		switch (dir) {
		case NORTH:
			return m_maze.getRoom(row, col).hasNorthWall() == false;
		case SOUTH:
			return m_maze.getRoom(row, col).hasSouthWall() == false;
		case EAST:
			return m_maze.getRoom(row, col).hasEastWall() == false;
		case WEST:
			return m_maze.getRoom(row, col).hasWestWall() == false;			
		}
		
		return false;
	}
	
	private int endRow, endCol;
	/**
	 * 
	 * @param startRow
	 * @param startCol
	 * @return
	 */
	private Integer solve(int startRow, int startCol) {
		
		boolean found = false;
		int rooms = 0;
		m_frontier.clear();
		m_next.clear();
		Pair start = new Pair(startRow, startCol);
		m_frontier.add(start);
		
		outerLoop: while(!(m_frontier.isEmpty())){			
			rooms++;
			m_next = new ArrayList<Pair>(0);
			for(int x = 0; x < m_frontier.size(); x++){
				Pair temp = m_frontier.get(x);
				if(temp.row == endRow && temp.col == endCol){
					visit(temp.row, temp.col);
					found = true;
					break outerLoop;
					
				}
				else{
					visit(temp.row, temp.col);
				}
			}
			m_frontier.clear();
			m_frontier = m_next;			
		}
		if(found){
			Pair curr = new Pair(endRow, endCol);
			
			while(curr.row != startRow || curr.col != startCol){
				m_maze.getRoom(curr.row, curr.col).onPath = true;
				curr = m_parents.get(curr);
			}		
			m_maze.getRoom(startRow, startCol).onPath = true;
			return rooms;
		}
		return null;
	}
	/**
	 * 
	 * @param row
	 * @param col
	 */
	public void visit(int row, int col){
		m_visited[row][col] = true;
		Pair curr = new Pair(row, col);
		for (int direction=0;direction<4;++direction) {
			if (canGo(row, col, direction)) {
				Pair n = new Pair(row + m_ddir[direction][0], col+m_ddir[direction][1]);
				
				
				if (!m_visited[n.row][n.col]){
					m_next.add(n);
					m_parents.put(n, curr);
				}
			}
		}
	}
	@Override
	public Integer numReachable(int k) throws Exception {
		int count = 0;
		int sr = Math.max(0, m_initRow-k);
		int sc = Math.max(0, m_initCol-k);
		int er = Math.min(m_maze.getRows(), m_initRow+k+1);
		int ec = Math.min(m_maze.getColumns(), m_initCol+k+1);
		for (int i=sr;i<er;++i) {
			for (int j=sc;j<ec;++j) {
				if(pathSearch(m_initRow, m_initCol, i, j)==k) count++;
			}
		}
		return count;
	}
	
	public static void main(String [] args) {
		try {
			Maze maze = Maze.readMaze("maze-hsnake.txt");
			IMazeSolver solver = new MazeSolve();
			
			solver.initialize(maze);
			System.out.println( solver.pathSearch(0, 0, 3, 2) );
			MazePrinter.printMaze(maze);
			
			System.out.println("\n" + solver.pathSearch(0, 0, 2, 3) );
			
			MazePrinter.printMaze(maze);
			
			System.out.println( solver.numReachable(4) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
