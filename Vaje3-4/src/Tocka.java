import java.util.*;

// V razredu Tocka bomo hranili ime tocke in mnozico tock,
// s katerimi je ta tocka povezana. 
public class Tocka {
	public Object ime;
	public Set<Tocka> sosedi;
	public Double x;
	public Double y;
	
	public Tocka(Object name){
		this.ime = name;
		this.sosedi = new HashSet<Tocka>();
		this.x = new Double(0);
		this.y = new Double(0);
	}
	
	// Stevilo sosedov je kar velikost mnozice sosedov.
	public int stopnja(){
		return this.sosedi.size();
	}
}
