
public class Test {
	public static void main(String[] args){
		/*Graf testniGraf = new Graf();
		Graf testniGraf2 = Graf.prazen(10);
		Graf testniGraf3 = Graf.cikel(10);
		Graf testniGraf4 = Graf.polnDvodelen(2, 5);*/
		Graf testniGraf5 = Graf.poln(10);
		
		/*for (int i = 0; i < 100; i++){
			testniGraf.dodajTocko(new Tocka(i));
		}
		for (int i = 0; i < 98; i++){
			testniGraf.dodajPovezavo(testniGraf.tocka(i), testniGraf.tocka((i+2)));
		}
		
		testniGraf.odstraniPovezavo(testniGraf.tocka(2), testniGraf.tocka(4));
		testniGraf.odstraniTocko(testniGraf.tocka(44));
		
		testniGraf.izpis();
		System.out.println(testniGraf.povezan());
		System.out.println(testniGraf.steviloKomponent());
		testniGraf2.izpis();
		System.out.println(testniGraf2.povezan());
		System.out.println(testniGraf2.steviloKomponent());
		testniGraf3.izpis();
		System.out.println(testniGraf3.povezan());
		System.out.println(testniGraf3.steviloKomponent());
		testniGraf4.izpis();
		System.out.println(testniGraf4.povezan());
		System.out.println(testniGraf4.steviloKomponent());
		testniGraf5.izpis();
		System.out.println(testniGraf5.povezan());
		System.out.println(testniGraf5.steviloKomponent());*/
		
		testniGraf5.razporedi(1280/2, 360, 200);
		Okno window = new Okno("Moje okno");
		window.pack();
		window.setVisible(true);
		window.platno.narisi(testniGraf5);
		window.platno.paintComponent(window.getGraphics());
		
		
	}
}
