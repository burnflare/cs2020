package cs2020.cellular;

import java.util.*;

public class CoverageCalculator {
	//Storing highwayLength & ListOfTowers
	private int highwayLength;
	private static ArrayList<Tower> towerList;
	
	/**
	 * Constructor: Takes in highwaylength and initializes stuff
	 * @param highwayLength
	 */
	public CoverageCalculator(int highwayLength) {
		this.highwayLength = highwayLength;
		towerList = new ArrayList<Tower>(0);
	}
	
	/**
	 * addTower() adds a Tower object into the list. It also checks if
	 * it's location is outside range and throws an error. 
	 * 
	 * It runs in O(1) time.  
	 * @param location
	 * @param range
	 * @throws Exception
	 */
    void addTower(int location, int range) throws Exception {
    	if(location > highwayLength)
    		throw new Exception("Invalid Location");
    	Tower o = new Tower(location, range);
    	towerList.add(o);
    }
    
    /**
     * getCoverage() calculates the total coverage range of 
     * all the towers in the highway and returns that. My strategy for
     * calculating coverage is to group as many towers as possible into 
     * continuous coverage blocks. When we find a break in the coverage,
     * we start a new coverage block. In the end, we sum up all the 
     * continuous coverage blocks we found and return that as the global
     * coverage.  
     * 
     * First it sorts the list according to the Tower's beginLocaiton
     * index(location-range). Then it iterates through every Tower in the 
     * list monitoring it's start and end location. If it's 
     * startLocation is < the continuous block's start location, it
     * expand its block accordingly. Same happens if it's endLocation
     * is > the continuous block's end location.
     * 
     * It repeat this until it finds a break in the block(when tower's 
     * start location is > continuous block's end location), then it adds
     * that to a globalCoverage var and repeats itself.
     * 
     * It runs in O(NLogN) time.
     * @return coverage
     */
    int getCoverage() {
    	Collections.sort(towerList);
    	int globalCoverage = 0;
    	int coverageBlockRange = 0; 
    	int coverageBlockMin = 0;
    	int coverageBlockMax = 0;
    	
    	for(Tower x : towerList) {
    		if(x.beginLocation > coverageBlockMax) {
    			globalCoverage += coverageBlockRange;
    			coverageBlockRange = 0;
    			coverageBlockMin = x.beginLocation;
    			coverageBlockMax = x.endLocation;
    		}
    		if(x.beginLocation < coverageBlockMin)
    			coverageBlockMin = x.beginLocation;
    		if(x.endLocation > coverageBlockMax)
    			coverageBlockMax = x.endLocation;
    		
    		if(coverageBlockMin<0) coverageBlockMin = 0;
    		coverageBlockRange = coverageBlockMax - coverageBlockMin;
    	}
    	// Before we return globalCoverage, we need to add the last coverageBlock
    	// back into it as the for-loop would have missed it out
    	return globalCoverage + coverageBlockRange;
    }
}
