import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel {
	int sirina;
	int visina;
	Graf graf;
	
	public Platno(int sirina, int visina){
		this.sirina = sirina;
		this.visina = visina;
		this.graf = null;
	}
	
	public void narisi(Graf g){
		this.graf = g;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.sirina, this.visina);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		for (Object tocka : this.graf.tocke.keySet()){
			int startX = this.graf.tocka(tocka).x.intValue();
			int startY = this.graf.tocka(tocka).y.intValue();
			for (Tocka soseda : this.graf.tocka(tocka).sosedi){
				int endX = soseda.x.intValue();
				int endY = soseda.y.intValue();
				
				g.drawLine(startX, startY, endX, endY);
			}
		}
		
		for (Object tocka : this.graf.tocke.keySet()){
			g.fillOval(this.graf.tocka(tocka).x.intValue() - 10, this.graf.tocka(tocka).y.intValue() - 10, 20, 20);
		}
	}
	
	
	
	
}
