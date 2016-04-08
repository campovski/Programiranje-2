// Idea: Convert to common units, compute average velocity and if greater then
// SPEED_LIMIT print to file and increase the count of maniacs by 1.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Naloga5 {
	public static int LENGTH = 622;
	public static double SPEED_LIMIT = 80/3.6;
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		if (args.length != 1){
			System.out.println("Error String[] args: Too few or too many arguments (" + args.length + " arguments)!");
			System.exit(1);
		}
		
		BufferedReader file = new BufferedReader(new FileReader(args[0]));
		PrintWriter writer = new PrintWriter("maniacs.txt", "UTF-8");
		String line;
		int countManiacs = 0;
		
		while ((line = file.readLine()) != null){
			String[] parts = line.split(" ");
			int time = Integer.parseInt(parts[1]) - Integer.parseInt(parts[0]);
			double speed = LENGTH / time;
			if (speed > SPEED_LIMIT){
				writer.println(parts[2] + ": " + (int)(speed*3.6));
				countManiacs++;
			}
		}
		
		file.close();
		writer.close();
		System.out.println(countManiacs);
	}
}
