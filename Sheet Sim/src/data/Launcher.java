package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Launcher {
	private static int[] dimensions = {0,0};
	public static String fileName = "Error";
	
	public static void main(String[] args) {
		try {
			File file = new File("src/res/save.txt");
			Scanner scanner = new Scanner(file);
			int lineNumber=0;
			 while(scanner.hasNextLine()) {
				 lineNumber++;
				 String value = scanner.nextLine();
				 switch(lineNumber) {
				 case 1:
					 dimensions[0] = Integer.parseInt(value.split(", ")[0]);
					 dimensions[1] = Integer.parseInt(value.split(", ")[1]);
					 break;
				 case 2:
					 fileName = value;
					 break;
				 }
			 }
			 scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Frame sim = new Frame();
		sim.show(dimensions[0], dimensions[1]);
	}

}
