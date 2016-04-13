import java.util.*;

public class Graf {
	// Ustvarimo razredno spremenljivko graf.
	public Map<Integer, Set<Integer>> graf;

	// Konstruktor naredi graf.
	public Graf(){
		graf = new HashMap<Integer, Set<Integer>>();
	}
	
	// Preverimo, da tocka, ki jo zelimo dodati, se ni v grafu.
	public void dodajTocko(Integer v){
		if (!graf.containsKey(v)){
			graf.put(v, new HashSet<Integer>());
		}
	}
	
	// Povezavo dodamo tako, da najprej preverimo, ali graf vsebuje obe krajisci;
	// ce ju, dodamo povezavo tako, da jo dodamo v obe smeri.
	public void dodajPovezavo(Integer v, Integer u){
		if (v != u && graf.containsKey(v) && graf.containsKey(u)){
			graf.get(v).add(u);
			graf.get(u).add(v);
		}
	}
	
	// Povezavo odstranimo tako, da najprej preverimo, ali sploh obstaja. Ce jo,
	// jo nato odstranimo v obe smeri.
	public void odstraniPovezavo(Integer v, Integer u){
		if (graf.containsKey(v) && graf.containsKey(u) && graf.get(v).contains(u)){
			graf.get(v).remove(u);
			graf.get(u).remove(v);
		}
	}
	
	// Tocko odstranimo tako, da najprej preverimo, ali jo graf vsebuje.
	// Ce jo, pogledamo najprej, s katerimi tockami je povezana. Nato odstranimo
	// vse njene povezave in na koncu se tocko samo.
	public void odstraniTocko(Integer v){
		if (graf.containsKey(v)){
			Set<Integer> kljuciOdstranitev = graf.get(v);
			Iterator<Integer> iter = kljuciOdstranitev.iterator();
			while (iter.hasNext()){
				Integer kljuc = iter.next();
				graf.get(kljuc).remove(v);
			}
			graf.remove(v);
		}
	}
	
	// Zapeljemo se cez urejen seznam tock in za vsako tocko preverimo, ali morda
	// pripada kaksni ze obstojeci komponenti. Ce da, potem njo in tocke, s katerimi
	// je povezana, dodamo v komponento. V nasprotnem vrnemo false. Ce pridemo do konca,
	// to pomeni, da smo uspesno dodali vse tocke v eno komponento, vrnemo true.
	public boolean povezan(){
		List<Integer> tocke = new ArrayList<Integer>(graf.keySet());
		Collections.sort(tocke);
		List<Set<Integer>> komponente = new LinkedList<Set<Integer>>();
		
		for (int i = 0; i < tocke.size(); i++){
			Integer tocka = tocke.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if (komponente.get(j).contains(tocka)){
						komponente.get(j).addAll(graf.get(tocka));
						break;
					} else {
						if (j + 1 == komponente.size()){
							return false;
						}
					}
				}
			} else {
				komponente.add(graf.get(tocka));
			}
		}
		return true;
	}
	
	// Zapeljemo se cez urejen seznam tock in za vsako tocko preverimo, ali morda
	// pripada kaksni ze obstojeci komponenti. Ce da, potem njo in tocke, s katerimi
	// je povezana, dodamo v komponento. V nasprotnem naredimo novo komponento, v
	// katero dodamo tocko in tocke, s katerimi je povezana.
	public int steviloKomponent(){
		List<Integer> tocke = new ArrayList<Integer>(graf.keySet());
		Collections.sort(tocke);
		List<Set<Integer>> komponente = new LinkedList<Set<Integer>>();
		
		for (int i = 0; i < tocke.size(); i++){
			Integer tocka = tocke.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if (komponente.get(j).contains(tocka)){
						komponente.get(j).add(tocka);
						komponente.get(j).addAll(graf.get(tocka));
						break;
					} else {
						if (j + 1 == komponente.size()){
							komponente.add(j+1, graf.get(tocka));
							komponente.get(j+1).add(tocka);
						}
					}
				}
			} else {
				komponente.add(graf.get(tocka));
				komponente.get(0).add(tocka);
			}
		}
		return komponente.size();
	}
	
	// Alternativna (lepsa) resitev, ki preveri, ali je stevilo komponent enako 1.
	// Opomba: Ta implementacija je pocasnejsa, saj moramo najprej poiskati vse
	// komponente grafa, medtem ko metoda povezan vrne false takoj, ko najde
	// drugo komponento. Metoda povezan2 deluje v \Omega(n) = \Theta(n) = O(n),
	// metoda povezan pa v O(n).
	public boolean povezan2(){
		return this.steviloKomponent2() == 1;
	}
	
	// Alternativna (lepsa) resitev, ki naredi kompozicijo grafa in vrne moc kompozicije.
	public int steviloKomponent2(){
		return this.kompozicija().size();
	}
	
	// Metoda, ki jo uporablja lepsa resitev steviloKomponent2 in posredno potem tudi
	// povezan2. Lepsa resitev, vendar sta grsi implementirani v primeru, da lahko
	// razred Graf vsebuje le metode, predpisane v navodilu za vaje.
	public List<Set<Integer>> kompozicija(){
		List<Integer> tocke = new ArrayList<Integer>(graf.keySet());
		Collections.sort(tocke);
		List<Set<Integer>> komponente = new LinkedList<Set<Integer>>();
		
		for (int i = 0; i < tocke.size(); i++){
			Integer tocka = tocke.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if (komponente.get(j).contains(tocka)){
						komponente.get(j).add(tocka);
						komponente.get(j).addAll(graf.get(tocka));
						break;
					} else {
						if (j + 1 == komponente.size()){
							komponente.add(j+1, graf.get(tocka));
							komponente.get(j+1).add(tocka);
						}
					}
				}
			} else {
				komponente.add(graf.get(tocka));
				komponente.get(0).add(tocka);
			}
		}
		return komponente;
	}
	
	// Naredimo en graf in preverimo, ali deluje... Lahko pustimo tudi prazno, ce mislimo
	// ta razred uporabljati znotraj drugih razredov.
	public static void main(String[] args){
		Graf x = new Graf();
		for (int i = 0; i < 100; i++){
			x.dodajTocko(i);
		}
		for (int i = 0; i < 98; i++){
			x.dodajPovezavo(i, i+2);
		}
		System.out.println(x.povezan());
		System.out.println(x.steviloKomponent());
	}
}