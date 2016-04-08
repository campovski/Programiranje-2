// Idea: Use method delitelji to compute divisors. Since we are working with sets
// and order is not important, we can restrict our search to $\sqrt{n}$ and add
// both $i$ and $n/i$ in same run.

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Naloga1Napredna {
	public static Set<Integer> delitelji(Integer n){
		Set<Integer> del = new HashSet<Integer>();
		for (int i = 1; i*i <= n; i++){
			if (n % i == 0){
				del.add(i);
				del.add(n/i);
			}
		}
		return del;
	}
	
	public static void main(String[] args){
		if (args.length != 1){
			System.out.println("Error String[] args: Too few or too many arguments (" + args.length + " arguments)!");
			System.exit(1);
		}
		
		int n = Integer.parseInt(args[0]);
		Iterator<Integer> iter = delitelji(n).iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}