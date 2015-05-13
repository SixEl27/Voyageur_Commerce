import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JComponent;


public class Dessin extends JComponent{

	int x,y;
	double tailleVille=10;
	//ArrayList<Shape> BufferForme;
	ArrayList<Shape> listeForme =  new ArrayList<Shape>();;
	Shape formeEnCours;
	
	public Dessin(){
		super();
		formeEnCours = null;
		//BufferForme = new ArrayList<Shape>();
		//listeForme = new ArrayList<Shape>();
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				formeEnCours = new Ellipse2D.Double(x-(tailleVille/2.0),y-(tailleVille/2.0),tailleVille,tailleVille);
				//BufferForme.add(formeEnCours);
				repaint();
			}
		});
		
	}
	
	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public ArrayList<Shape> getListeForme() {
		return listeForme;
	}

	public Shape getFormeEnCours() {
		return formeEnCours;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		if(formeEnCours!=null)
			g2.draw(formeEnCours);
		g2.setColor(Color.BLUE);
		for(int i=0;i<listeForme.size();i++)
			g2.fill(listeForme.get(i));
	}
	
	public void Refresh(VDC VDC1){
		for(Noeud n : VDC1.liste_noeud){
			Ville v = (Ville)n;
			listeForme.add(new Ellipse2D.Double(v.x-(tailleVille/2.0),v.y-(tailleVille/2.0),tailleVille,tailleVille));
		}
	}
}


