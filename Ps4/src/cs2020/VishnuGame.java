/**
 * 
 */
package cs2020;

/**
 * @author vishnu
 *
 */
public class VishnuGame extends QuestionGameBase {

	/**
	 * @param objectFileName
	 */
	public VishnuGame(String objectFileName) {
		super(objectFileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cs2020.QuestionGameBase#CreateTree()
	 */
	@Override
	protected QuestionTreeBase CreateTree() {
		QuestionTreeBase qtb = new QuestionTreeBase() {};
		return qtb;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		VishnuGame obj = new VishnuGame("/Users/vishnu/Dev/cs2020/Ps4/TestDB_1.txt");
		obj.playGame();
	}

}
