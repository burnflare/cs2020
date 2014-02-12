package cs2020.cellular;

public class Tower implements Comparable<Tower>{

	// Storing begin & end locations. That's all we care about,
	// we don't need to store location & range actually
	protected int beginLocation;
	protected int endLocation;
	
	/**
	 * Constructor: Takes in location and range, then finds minimum & maximum index
	 * and saves that in itself
	 * @param location
	 * @param range
	 */
	public Tower(int location, int range) {
		this.beginLocation = location - range;
		this.endLocation = location + range;
	}

	/**
	 * When I sort a list of Towers, I want it to be sorted ascending order by it's
	 * beginLocation index. So that's how I implemented compareTo() 
	 */
	@Override
	public int compareTo(Tower o) {
		if(o.beginLocation==this.beginLocation)
			return 0;
		else if(this.beginLocation < o.beginLocation)
			return -1;
		else if(this.beginLocation > o.beginLocation)
			return 1;
		return 0;
	}

}
