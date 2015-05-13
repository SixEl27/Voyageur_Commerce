import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;


public class Dessin extends JComponent{

	int x,y;
	double tailleVille=10;
	//ArrayList<Shape> BufferForme;
	ArrayList<Shape> listeEllipse =  new ArrayList<Shape>();
	ArrayList<Shape> listeLigne =  new ArrayList<Shape>();
	ArrayList<Shape> listeChemin =  new ArrayList<Shape>();
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
		return listeEllipse;
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
		System.out.println("");
		for(int i=0;i<listeLigne.size();i++){
			g2.setColor(Color.WHITE);
			g2.draw(listeLigne.get(i));
		}
		for(int i=0;i<listeEllipse.size();i++){
			g2.setColor(Color.BLUE);
			g2.fill(listeEllipse.get(i));
		}
		if(!listeChemin.isEmpty()){
			for(int i=0;i<listeChemin.size();i++){
				g2.setColor(Color.GREEN);
				g2.draw(listeChemin.get(i));
			}
		}
		else{
			System.out.println("Chemin vide");
		}
	}
	
	public void Refresh(VDC VDC1){
		listeEllipse.clear();
		listeLigne.clear();
		Shape ville;
		ville=null;
		for(Noeud n : VDC1.liste_noeud){
			Ville v = (Ville)n;
			ville= new Ellipse2D.Double(v.x-(tailleVille/2.0),v.y-(tailleVille/2.0),tailleVille,tailleVille);
			listeEllipse.add(ville);
			for(Arc a : v.liste_arc){
				Route r = (Route)a;
				double debx=((Ville)r.source).x;
				double deby=((Ville)r.source).y;
				double finx=((Ville)r.dest).x;
				double finy=((Ville)r.dest).y;
				listeLigne.add(new Line2D.Double(debx, deby,finx,finy));
			}
		}
		this.repaint();
		
	}
}


