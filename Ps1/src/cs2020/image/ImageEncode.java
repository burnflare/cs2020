package cs2020.image;

/**
 * class ImageEncode
 * @author dcsslg
 * Description: Encodes and decodes images using a simple shift-register scheme
 */
public class ImageEncode {

	/**
	 * transform
	 * @param image is the SimpleImage to manipulate
	 * @param s
	 * @throws Exception
	 */
	static void transform(SimpleImage image, ILFShiftRegister shiftReg) {		

		// If no image, do nothing and return
		if (image == null) return;
		
		// If no shift register, do nothing and return
		if (shiftReg == null) return;
		
		// Get the height and width of the image
		int iWidth = image.getImgWidth();
		int iHeight = image.getImgHeight();
				
		// Catch all exceptions
		try{
			// Iterate over every pixel in the image
			for (int i=0; i<iWidth; i++){
				for (int j=0; j<iHeight; j++){							
					// For each pixel, get the red, green, and blue components of the color
					int red = image.getRed(j,i);
					int green = image.getGreen(j,i);
					int blue = image.getBlue(j,i);
					
					// For each color component, XOR the value with 8 bits generated from the shift register
					red = (red ^ shiftReg.generate(8));
					green = (green ^ shiftReg.generate(8));
					blue = (blue ^ shiftReg.generate(8));
										
					// Update the image
					image.setRGB(j,i,red,green,blue);
				}
			}		
		}
		catch(Exception e){
			// Print out any errors
			System.out.println("Error with transformation: " + e);
		}		
	}
	
	/**
	 * main procedure
	 * @param args
	 */	
	public static void main(String[] args){
		// Open an image
		SimpleImage image = new SimpleImage("Mystery Image", "mystery.bmp");
				
		// Transform the image using a shift register
		try{
			// Add your code here to create a shift register.
			// Use your shift register implementation, and set 
			// the tap and the correct code.
			////////////////////////////////
			int[] array = new int[11];
			array[0] = 0;
	        array[1] = 1;
	        array[2] = 1;
	        array[3] = 0;
	        array[4] = 1;
	        array[5] = 0;
	        array[6] = 0;
	        array[7] = 0;
	        array[8] = 1;
	        array[9] = 1;
	        array[10] = 1;
	        
			ShiftRegister shiftReg = new ShiftRegister(11,7);
			shiftReg.setSeed(array);
			
			//Answer for Question 2.d: Eldric looks like the facebook guy.
	        
			////////////////////////////////
			// Transform the image
			transform(image, shiftReg);		
		}
		catch(Exception e){
			System.out.println("Error in transforming image: " + e);
		}
	}	
}


