package cs2020.image;

public class PSOne {
	static int MysteryFunction(int argA, int argB)
	{
	     int c = 1;
	     int d = argA;
	     int e = argB;
	     while (e > 0)
	     {
	          if (2*(e/2) !=e)
	          {
	c = c*d; }
	d = d*d;
	e = e/2; }
	return c; 
	}

	public static void main(String args[])
	{
		//MysteryFunction is calculating the exponent between the first 2 numbers. Example, 9^6=531441
		int output = MysteryFunction(9, 6);
	     System.out.printf("The answer is: " + output + ".");
	}
}
