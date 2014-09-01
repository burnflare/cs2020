package cs2020tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Coordinate {
	int x;
	int y;
	
	Coordinate(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int hashCode(){
		return (int) Math.pow(x, y);
	}
	
	public boolean equals(Object o){
		if(o==null) return false;
		if(o==this) return true;
		if(!(o instanceof Coordinate))return false;
		Coordinate o1 = (Coordinate) o;
		if(o1.x!=this.x) return false;
		if(o1.y!=this.y) return false;
		return true;
	}
}

public class TSPGraph implements IApproximateTSP{
	ArrayList<Double> pairPointsArray = new ArrayList<Double>();
	HashMap<Double, Coordinate> helper = new HashMap<Double, Coordinate>();
	int[] visitedIndexes;
	TSPMap map;
	
	/**
	 * initialize
	 * @param map
	 * Set up your object with a new map.
	 */
	public void initialize(TSPMap m){
		this.map=m;
		pairPointsArray.clear();
		helper.clear();
		visitedIndexes = new int[map.getCount()];
		
		for(int i=0; i<map.getCount(); i++){
			visitedIndexes[i]=0;
		}
		
		for(int x=0 ; x<map.getCount(); x++){
			for(int y=x+1; y<map.getCount(); y++){
					pairPointsArray.add(map.pointDistance(x, y));
					helper.put(map.pointDistance(x, y), new Coordinate(x,y));
			}
		}
		//We sort the distances in ascending order.
		Collections.sort(pairPointsArray);
	}
	
	/**
	 * Checks is x leads to y
	 * @param x
	 * @param y
	 * @return true if there is a path from x to y. false otherwise
	 */
	private boolean leadsTo(int x, int y){
		if(x==y) return true;
		while(map.getLink(x)!=-1){
			x = map.getLink(x);
			if(x==y) return true;
		}
		return false;
	}
	
	/**
	 * MST
	 * Calculate an MST for the specified map.
	 * 
	 */
	public void MST(){
		
		for(int i=1; i<map.getCount(); i++){
			while((visitedIndexes[helper.get(pairPointsArray.get(0)).x]==1 || leadsTo(helper.get(pairPointsArray.get(0)).y,helper.get(pairPointsArray.get(0)).x)) && (visitedIndexes[helper.get(pairPointsArray.get(0)).y]==1|| leadsTo(helper.get(pairPointsArray.get(0)).x,helper.get(pairPointsArray.get(0)).y)))
					pairPointsArray.remove(0);
			
			if((visitedIndexes[helper.get(pairPointsArray.get(0)).x]==0 && !leadsTo(helper.get(pairPointsArray.get(0)).y,helper.get(pairPointsArray.get(0)).x))){
				map.setLink(helper.get(pairPointsArray.get(0)).x, helper.get(pairPointsArray.get(0)).y, false);
				visitedIndexes[helper.get(pairPointsArray.get(0)).x] = 1;
			}
			
			else{
				map.setLink(helper.get(pairPointsArray.get(0)).y, helper.get(pairPointsArray.get(0)).x, false);
				visitedIndexes[helper.get(pairPointsArray.get(0)).y] = 1;
			}
		}
		map.redraw();
	}
	
	public void TSP(){
		int count=0;
		int i;
		initialize(this.map);
		i=0;		
		visitedIndexes[i]=1;
		count++;

		while(count<map.getCount()){
			while(map.getLink(i)!=-1){
				if(visitedIndexes[map.getLink(i)]==1){
					map.eraseLink(i, false);
					break;
				}
				i=map.getLink(i);
				visitedIndexes[i]=1;
				count++;
			}
			if(count>=map.getCount()) break;
			int j=0;
			while((helper.get(pairPointsArray.get(j)).x!=i || visitedIndexes[helper.get(pairPointsArray.get(j)).y]==1)&&(helper.get(pairPointsArray.get(j)).y!=i || visitedIndexes[helper.get(pairPointsArray.get(j)).x]==1))
				j++;
			if(helper.get(pairPointsArray.get(j)).x==i && visitedIndexes[helper.get(pairPointsArray.get(j)).y]==0)
				map.setLink(i, helper.get(pairPointsArray.get(j)).y, false);
			else
				map.setLink(i, helper.get(pairPointsArray.get(j)).x, false);
			i=map.getLink(i);
			visitedIndexes[i]=1;
			count++;
		}
		map.setLink(i, 0);
	}
	
	/**
	 * isValidTour
	 * @return Returns true if the links on the map form a valid tour.
	 */
	public boolean isValidTour(){
		for(int i=0; i<map.getCount(); i++){
			visitedIndexes[i]=0;
		}
		int j,i=0;
		visitedIndexes[i]=1;
		for(j=1; j<=map.getCount()-1; j++){
			if(map.getLink(i)==-1 || visitedIndexes[map.getLink(i)]==1) return false;
			i = map.getLink(i);
			visitedIndexes[i]=1;
		}
		return map.getLink(i) == 0 ? true:false;
	}
	
	/**
	 * tourDistance
	 * @return If the links on the map form a valid tour, then this method returns the total length of that tour
	 */
	public double tourDistance(){
		double dist=0.0;
		if(!isValidTour()) return -1;
		int i=0;
		for(int j=1; j<=map.getCount(); j++){
			dist+=map.pointDistance(i, map.getLink(i));
			i = map.getLink(i);
		}
		return dist;
	}

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		TSPGraph graph = new TSPGraph();
		TSPMap map = new TSPMap("tenclusters.txt");
		graph.initialize(map);
		graph.MST();
		System.out.println(graph.tourDistance());
		graph.TSP();
		System.out.println(graph.tourDistance());
	}
}
