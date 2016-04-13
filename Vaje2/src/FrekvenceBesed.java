import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class FrekvenceBesed {
	public static void main(String[] args) throws IOException{
		if (args.length != 1){
			System.out.println("Error String[] args: Too few or too many arguments (" + args.length + " arguments)!");
			System.exit(1);
		}
		
		Map<String, Integer> pojavitve = prestejPojavitve(args[0]);
		List<String> urejeneBesede = urediKljuce(pojavitve);
		
		// Ker je besed ogromno, nam prav pride, ce jih shranimo v file.
		PrintWriter izhod = new PrintWriter("prestete_besede.txt", "UTF-8");
		
		for (int i = 0; i < urejeneBesede.size(); i++){
			String beseda = urejeneBesede.get(i);
			String izpis = beseda + ": " + pojavitve.get(beseda);
			System.out.println(izpis);
			izhod.write(izpis + "\n");
		}
		izhod.close();
	}
	
	// Iz vsake vrstice vhodne datoteke pobrisemo nezeljene znake, navedene v vrstici 38
	// (pri konstrukciji StringTokenizerja). Nato vsako besedo, ce je se ni v slovarju,
	// dodamo vanj. Drugace le povecamo stevec.
	private static Map<String, Integer> prestejPojavitve(String imeDat) throws IOException{
		BufferedReader vhod = new BufferedReader(new FileReader(imeDat));
		Map<String, Integer> slovarPojavitev = new HashMap<String, Integer>();
		String line;
		
		while((line = vhod.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line, " .,()[]<>\"-*");
			while (st.hasMoreTokens()){
				String beseda = st.nextToken();
				if (slovarPojavitev.containsKey(beseda)){
					slovarPojavitev.put(beseda, slovarPojavitev.get(beseda)+1);
				} else {
					slovarPojavitev.put(beseda, 1);
				}
			}
		}
		vhod.close();
		return slovarPojavitev;
	}
	
	// Kljuce uredimo tako, da si definiramo svojo compare metodo, ki bo urejala po
	// vrednostih (value). Ko smo uredili slovar, prepisemo kljuce v list kljuci.
	// Opomba: Ce zelimo sortirati v obratnem vrstnem redu, zamenjamo vnos1 in vnos2
	// v vrstici 61 (return vnos2.getValue...).
	private static List<String> urediKljuce(Map<String, Integer> map){
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
	        public int compare(Map.Entry<String, Integer> vnos1, Map.Entry<String, Integer> vnos2)
	        {
	            return (vnos2.getValue()).compareTo(vnos1.getValue());
	        }
		});
	    
	    List<String> besede = new LinkedList<String>();
	    for (int i = 0; i < list.size(); i++){
	    	besede.add(list.get(i).getKey());
	    }
	    return besede;
	}
}
