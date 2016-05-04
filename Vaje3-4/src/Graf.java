import java.util.*;

public class Graf {
	// Tu bodo shranjene vse tocke nekega grafa. Kljuc je ime tocke,
	// pripadajoca vrednost pa je Tocka kljuc.
	public Map<Object, Tocka> tocke;
	
	public Graf(){
		this.tocke = new HashMap<Object, Tocka>();
	}
	
	// Vrne tocko z danim imenom.
	public Tocka tocka(Object name){
		return this.tocke.get(name);
	}
	
	// Preveri, ali obstaja povezava med Tocka a in Tocka b.
	// Preveris torej, ali ima Tocka a za sosedo Tocko b.
	public boolean povezava(Tocka a, Tocka b){
		return a.sosedi.contains(b.ime);
	}
	
	// Najprej se prepricamo, da tocke se ni v grafu. Nato jo dodamo.
	public void dodajTocko(Tocka a){
		if (!this.tocke.containsKey(a.ime))
			this.tocke.put(a.ime, a);
	}
	
	// Prepricati se moramo, da Tocka a nima enakega imena kot Tocka b,
	// da sta Tocka a in Tocka b v grafu in da se nista sosedi.
	public void dodajPovezavo(Tocka a, Tocka b){
		if (a.ime != b.ime && this.tocke.containsKey(a.ime) && this.tocke.containsKey(b.ime)  && !a.sosedi.contains(b.ime)){
			a.sosedi.add(b);
			b.sosedi.add(a);
		}
	}
	
	// Preverimo, ali omenjena povezava obstaja. Ce da, jo izbrisemo.
	public void odstraniPovezavo(Tocka a, Tocka b){
		if (a.ime != b.ime && this.tocke.containsKey(a.ime) && this.tocke.containsKey(b) && a.sosedi.contains(b.ime)){
			a.sosedi.remove(b);
			b.sosedi.remove(a);
		}
	}
	
	// Vsaki tocki, ki je povezana s Tocko a, odstranimo njeno povezavo
	// s Tocko a. Na koncu se izbrisemo Tocko a.
	public void odstraniTocko(Tocka a){
		if (this.tocke.containsKey(a.ime)){
			for (Tocka tockica : a.sosedi){
				odstraniPovezavo(a,  tockica);
			}
			this.tocke.remove(a);
		}
	}
	
	// Naredimo nov graf, vanj dodamo n tock in ga vrnemo.
	public static Graf prazen(int n){
		Graf x = new Graf();
		for (int i = 0; i < n; i++){
			x.dodajTocko(new Tocka(i));
		}
		return x;
	}
	
	// Naredimo prazen graf in mu dodamo povezave dveh zaporednih tock.
	public static Graf cikel(int n){
		Graf x = prazen(n);
		for (int i = 0; i < n; i++){
			x.dodajPovezavo(x.tocka(i), x.tocka((i+1)%n));
		}
		return x;
	}
	
	// Naredimo prazen graf in vsaki tocki dodamo vse mozne povezave.
	// Ne rabimo preveriti, ali je i == j, saj ce je, bo ze dodajPovezavo
	// preprecila dodajanje.
	public static Graf poln(int n){
		Graf x = prazen(n);
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				x.dodajPovezavo(x.tocka(i), x.tocka(j));
			}
		}
		return x;
	}
	
	// Naredimo prazen graf velikosti n+m in nato prvim n tockam dodamo
	// povezave z drugimi m tockami.
	public static Graf polnDvodelen(int n, int m){
		Graf x = prazen(n+m);
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				x.dodajPovezavo(x.tocka(i), x.tocka(j+n));
			}
		}
		return x;
	}
	
	// Preverimo, ali je graf povezan ali ne. To storimo tako, da zacnemo
	// graditi komponente. Takoj, ko najdemo drugo komponento, vrnemo false,
	// drugace na koncu vrnemu true.
	public boolean povezan(){
		List<Object> graf = new ArrayList<Object>(this.tocke.keySet());
		List<Set<Tocka>> komponente = new LinkedList<Set<Tocka>>();
		
		for (int i = 0; i < this.tocke.size(); i++){
			Object tocka = graf.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if(komponente.get(j).contains(tocka)){
						komponente.get(j).addAll(this.tocke.get(tocka).sosedi);
						break;
					} else {
						if (j + 1 == komponente.size())
							return false;
					}
				}
			} else {
				komponente.add(this.tocke.get(tocka).sosedi);
			}
		}
		return true;
	}
	
	// Podobno kot pri metodi povezan, le da sedaj naredimo mnozico vseh
	// komponent in vrnemo njeno velikost.
	public int steviloKomponent(){
		Vector<Object> graf = new Vector<Object>(this.tocke.keySet());
		Vector<Set<Tocka>> komponente = new Vector<Set<Tocka>>();
		
		for (int i = 0; i < this.tocke.size(); i++){
			Object tocka = graf.get(i);
			if (komponente.size() != 0){
				for (int j = 0; j < komponente.size(); j++){
					if(komponente.get(j).contains(tocka)){
						komponente.get(j).addAll(this.tocke.get(tocka).sosedi);
						break;
					} else {
						if (j + 1 == komponente.size())
							break;
					}
				}
			} else {
				komponente.add(this.tocke.get(tocka).sosedi);
			}
		}
		return komponente.size();
	}
	
	// Metoda za izpis.
	public void izpis(){
		for (Object tocka : this.tocke.keySet()){
			String sosede = "";
			for (Tocka sosedeTocke : this.tocka(tocka).sosedi){
				sosede += sosedeTocke.ime + ", ";
			}
			System.out.println(tocka + ": [" + sosede.replaceAll(", $", "") + "]");
		}
	}
	
	public void razporedi(int x, int y, int r){
		double kot = 2 * Math.PI / this.tocke.size();
		int i = 0;
		for (Object tocka : this.tocke.keySet()){
			this.tocka(tocka).x = r * Math.cos(i*kot) + x;
			this.tocka(tocka).y = r * Math.sin(i*kot) + y;
			i++;
		}
	}
}
