package cs2020;

public class PizzaCircles {
	
	/**
	 * MinSizedPizza
	 * @param area
	 * @return the minimum non-negative, integral radius of a pizza with the specified area
	 */
	public static long MinSizedPizza(double area){
		return (long)Math.ceil(Math.sqrt((area/Math.PI)));
	}
}
