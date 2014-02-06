package cs2020.image;


public class ShiftRegister implements ILFShiftRegister {
	private int size;
	private int tap;
	private int[] intArray;
	
	public ShiftRegister(int size, int tap) {
		this.size = size;
		this.tap = tap;
		intArray = new int[size];
	}

	public void setSeed(int[] seed) throws Exception {
		if(seed.length != size)
			throw new Exception("Seed is not the same size as declared");
			
		for(int i=0;i<seed.length;i++){
			if(seed[i] != 0 && seed[i] != 1) {
				throw new Exception("Invalid bit entry");
			}
		}
		intArray = seed;
	}

	public int shift() {
		int feedbackBit = intArray[size-1] ^ intArray[tap];
		int[] newArray = new int[size];
		
		newArray[0] = feedbackBit;
		for(int i = 0; i<size-1; i++) {
			newArray[i+1] = intArray[i];
		}
		intArray = newArray;
		
		return feedbackBit;
	}

	public int generate(int k) {
		int num = 0;
		for(int i=0; i<k; i++) {
			num = num * 2 + shift();
		}
		return num;
	}

	public static void main(String[] args) throws Exception {
		int size;
		// Test 1
		size = 7;
        int[] array = new int[size];
        array[0] = 1;
        array[1] = 1;
        array[2] = 1;
        array[3] = 0;
        array[4] = 1;
        array[5] = 0;
        array[6] = 1;

        ShiftRegister shifter = new ShiftRegister(size,4);
        shifter.setSeed(array);
        for (int i=0; i<size; i++){
            int j = shifter.shift();
            System.out.print(j);
        }
        System.out.println();
        
        //Test 2
        size = 4;
        array = new int[size];
        array[0] = 1;
        array[1] = 1;
        array[2] = 1;
        array[3] = 1;
        
        ShiftRegister shifter2 = new ShiftRegister(size,2);
        shifter2.setSeed(array);
        for (int i=0; i<size; i++){
            int j = shifter2.shift();
            System.out.print(j);
        }
        System.out.println();
        
        //Test 3
        size = 9;
        array = new int[size];
        array[0] = 1;
        array[1] = 0;
        array[2] = 1;
        array[3] = 0;
        array[4] = 1;
        array[5] = 0;
        array[6] = 1;
        array[7] = 0;
        array[8] = 1;
        
        
        ShiftRegister shifter3 = new ShiftRegister(size,2);
        shifter3.setSeed(array);
        for (int i=0; i<size; i++){
        	int j = shifter.generate(i);;
            System.out.print(j);
        }
        System.out.println();
	}
}
