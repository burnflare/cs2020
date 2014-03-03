package cs2020;

import java.io.*;

public class CopyPaste {
	BufferedReader br;
	
	public CopyPaste(String path) {
		toFile(path);
	}

	public void toFile(String path) {
		try {
			br  = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found!");
		}
	}

	public static void main(String[] args) throws IOException {
		CopyPaste cp = new CopyPaste("hamlet.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("newhamlet.txt"));		
		String strLine;
		
		while((strLine = cp.br.readLine())!= null) {
			try {
				bw.write(strLine.toUpperCase());
				bw.newLine();
			} catch (Exception e) {
				System.out.println("Error: Writing exception");
			}
		}
		bw.close();
		cp.br.close();
	}
}
