package cs2020.shakespeare;

public class Shakespeare {

	public static void main(String[] args) {
		VectorTextFile hamlet = initialize("hamlet.txt");
		VectorTextFile henryv = initialize("henryv.txt");
		VectorTextFile macbeth = initialize("macbeth.txt");
		VectorTextFile mystery = initialize("mystery.txt");
		VectorTextFile cromwell = initialize("cromwell.txt");
		VectorTextFile vortigern = initialize("vortigern.txt");
		
		hamlet.Norm();
		henryv.Norm();
		macbeth.Norm();
		mystery.Norm();
		cromwell.Norm();
		vortigern.Norm();
		
		//hamlet & henryv
		calculateAngle(hamlet, henryv);
		//henryv & macbeth
		calculateAngle(macbeth, henryv);
		//macbeth & hamlet
		calculateAngle(macbeth, hamlet);
		
		//vortigern & hamlet
		calculateAngle(vortigern, hamlet);
		//vortigern & macbeth
		calculateAngle(vortigern, macbeth);
		//vortigern & henryv
		calculateAngle(vortigern, henryv);
		
		//cromwell & hamlet
		calculateAngle(cromwell, hamlet);
		//cromwell & macbeth
		calculateAngle(cromwell, macbeth);
		//cromwell & henryv
		calculateAngle(cromwell, henryv);
		
		//mystery & macbeth
		calculateAngle(mystery, macbeth);
		//mystery & hamlet
		calculateAngle(mystery, hamlet);
		//mystery & henryv
		calculateAngle(mystery, henryv);
		
/*		Looking at the output angles, I would set the threshold to be 0.40
		hamlet -> Shakespeare 
		henryv -> Shakespeare
		macbeth -> Shakespeare
		vortigern -> !Shakespeare
		cromwell -> !Shakespeare
		mystery -> Shakespeare
*/	
}
	
	private static VectorTextFile initialize(String fileName){
		VectorTextFile vtf = new VectorTextFile("texts/"+fileName);
		return vtf;
	}

	private static double calculateAngle(VectorTextFile A, VectorTextFile B) {
		double angle = VectorTextFile.Angle(A, B);
		String[] ASplit = A.fileName.split("/texts/");
		String[] BSplit = B.fileName.split("/texts/");
		
		System.out.println("<" + ASplit[1] + " & " + BSplit[1] + ">: "+ angle);
		return angle;
	}
}