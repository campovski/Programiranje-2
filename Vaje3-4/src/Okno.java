import java.awt.GridLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	protected Platno platno;
	
	public Okno(String naslov){
		super();
		setTitle(naslov);
		setLayout(new GridLayout());
		platno = new Platno(1280, 720);
		add(platno);
	}
}
