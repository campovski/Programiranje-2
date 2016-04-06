
public class Naloga1 {
	public static void main(String[] args){
		if (args.length != 1){
			System.out.println("Error String[] args: Too few or too many arguments (" + args.length + " arguments)!");
			System.exit(1);
		}
		
		int n = Integer.parseInt(args[0]);
		for (int i = 1; i <= n; i++){
			if (n % i == 0){
				System.out.println(i);
			}
		}
	}
}