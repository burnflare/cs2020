package cs2020;

public class Pair {
	int row;
	int col;
	int pow;
	
	Pair(int r, int c){
		row=r;
		col=c;
		pow=0;
	}
	
	public int hashCode(){
		return (int) Math.pow(row, col);
	}
	
	public boolean equals(Object o){
		if(o==null) return false;
		if(o==this) return true;
		if(!(o instanceof Pair))return false;
		Pair o1 = (Pair)o;
		if(o1.col!=this.col) return false;
		if(o1.row!=this.row) return false;
		return true;
	}
}
