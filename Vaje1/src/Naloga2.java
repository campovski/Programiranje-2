
public class Naloga2 {
	public static void main(String[] args){
		if (args.length != 1){
			System.out.println("Error String[] args: Too few or too many arguments (" + args.length + " arguments)!");
			System.exit(1);
		}
		
		int n = Integer.parseInt(args[0]);
		int i = 2;
		int eksponent = 0;
		String rezultat = n + " = ";
		
		while (true){
			if (n % i == 0){
				n /= i;
				eksponent++;
			} else {
				if (eksponent != 0){
					if (eksponent == 1){
						rezultat += i + " * ";
					} else {
						rezultat += i + "^" + eksponent + " * ";
					}
				}
				eksponent = 0;
				i++;
			}
			if (n == 1){
				if (eksponent == 1){
					rezultat += i;
				} else {
					rezultat += i + "^" + eksponent;
				}
				break;
			}
		}
		
		System.out.println(rezultat);
	}
}
