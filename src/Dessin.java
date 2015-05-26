import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;


public class Dessin extends JComponent{

	int x,y;
	double tailleVille=10;
	ArrayList<Shape> listeEllipse;
	ArrayList<Shape> listeLigne;
	ArrayList<Shape> listeChemin;
	HashMap<Shape, Ville> lienEllipseVille;
	Shape formeEnCours;
	
	public Dessin(){
		super();
		listeEllipse =  new ArrayList<Shape>();
		listeLigne =  new ArrayList<Shape>();
		listeChemin =  new ArrayList<Shape>();
		lienEllipseVille = new HashMap<Shape, Ville>();
		formeEnCours = null;
		
		addMouseListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e){
				for(Shape s : listeEllipse){
					if (s.contains(e.getX(),e.getY())){
						System.out.println("t'es dedans !");
					}
				}
			}
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				formeEnCours = null;
				//on regarde si le clique a eu lieu sur une ville
				for(Shape s : listeEllipse){
					if (s.contains(x,y)){
						//Surligne la ville selectionnée
						formeEnCours = new Ellipse2D.Double(s.getBounds().x,s.getBounds().y,tailleVille,tailleVille);
						break;
					}
				}
				if (formeEnCours==null){
					//sinon, surligne la zone cliquée
					formeEnCours = new Ellipse2D.Double(x-(tailleVille/2.0),y-(tailleVille/2.0),tailleVille,tailleVille);
				}
				repaint();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
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
	
	public Ville getVilleEnCours(){
		return lienEllipseVille.get(formeEnCours);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		for(int i=0;i<listeLigne.size();i++){
			g2.setColor(Color.WHITE);
			g2.draw(listeLigne.get(i));
		}
		for(int i=0;i<listeChemin.size();i++){
			g2.setColor(Color.GREEN);
			g2.draw(listeChemin.get(i));
		}
		for(int i=0;i<listeEllipse.size();i++){
			g2.setColor(Color.BLUE);
			g2.fill(listeEllipse.get(i));
		}
		g2.setColor(Color.RED);
		if(formeEnCours!=null)
			g2.draw(formeEnCours);

	}
	
	public void Refresh(VDC vdc){
		listeEllipse.clear();
		listeLigne.clear();
		listeChemin.clear();
		Shape formeVille;
		formeVille=null;
		//on ajoute tout les noeuds
		for(Noeud n : vdc.liste_noeud){
			Ville v = (Ville)n;
			formeVille= new Ellipse2D.Double(v.x-(tailleVille/2.0),v.y-(tailleVille/2.0),tailleVille,tailleVille);
			listeEllipse.add(formeVille);
			//on ajoute le lien entre les formes et les villes
			lienEllipseVille.put(formeVille, v);
			//pour chaque noeud on ajoute les arcs
			for(Arc a : v.liste_arc){
				Route r = (Route)a;
				double debx=((Ville)r.source).x;
				double deby=((Ville)r.source).y;
				double finx=((Ville)r.dest).x;
				double finy=((Ville)r.dest).y;
				listeLigne.add(new Line2D.Double(debx, deby,finx,finy));
			}
		}
		//on dessine chaque chemin
		for(Chemin c : vdc.listeSolution){
			for(int i=0; i<c.size()-1;i++){
				double debx=c.get(i).x;
				double deby=c.get(i).y;
				double finx=c.get(i+1).x;
				double finy=c.get(i+1).y;
				listeChemin.add(new Line2D.Double(debx, deby,finx,finy));
			}
		}
		this.repaint();
		
	}
}


