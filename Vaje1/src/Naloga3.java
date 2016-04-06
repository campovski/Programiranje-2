
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Naloga3 {
	public static void main(String[] args) throws IOException, FileNotFoundException{
		if (args.length != 1){
			System.out.println("Error String[] args: Too few or too many arguments (" + args.length + " arguments)!");
			System.exit(1);
		}
		
		String fileName = args[0];
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		String line;
		int count = 0;
		
		while ((line = file.readLine()) != null){
			for (int i = 0; i < line.length(); i++){
			    if (line.charAt(i) == ' '){
			    	count++;
			    }
			}
			count++;
		}
		
		file.close();
		
		System.out.println(count);
	}
}