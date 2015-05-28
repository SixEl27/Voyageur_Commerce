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

/**
 * Classe permettant de peindre sur un composant graphique le problème du voyageur de commerce
 */
public class DessinVDC extends JComponent{

	/**
	 * Attributs
	 * x,y				Coordonnées du dernier point du clic sur le composant
	 * tailleVille		Taille en pixel des ellipses représentant les villes
	 * listeEllipse		Liste d' ellipses représentant les villes
	 * listeLigne		Liste de lignes 2D représentant arcs du graphe du voyageur de commerce
	 * listeChemin		Liste de liste de lignes 2D représentant les arcs de chaque chemin
	 * lienEllipseVille	table de hashage permettant a partir d'une ellipse d'avoir la ville qu'elle représente
	 * formeEnCours		forme tracé lors du clic sur le composant
	 */
	int x,y;
	double tailleVille=10;
	ArrayList<Shape> listeEllipse;
	ArrayList<Shape> listeLigne;
	ArrayList<ArrayList<Shape>> listeChemin;
	ArrayList<Color> listeCouleurChemin;
	HashMap<Shape, Ville> lienEllipseVille;
	Shape formeEnCours;
	
	/**
	 * Constructeur du composant initialisant les ecouteurs de la souris
	 */
	public DessinVDC(){
		super();
		listeEllipse =  new ArrayList<Shape>();
		listeLigne =  new ArrayList<Shape>();
		listeChemin =  new ArrayList<ArrayList<Shape>>();
		listeCouleurChemin= new ArrayList<Color>();
		lienEllipseVille = new HashMap<Shape, Ville>();
		formeEnCours = null;
		
		//permet la création d'une ellipse creuse la ou on clique
		//utilisé du coup pour recuperer la ville représenté par l'ellipse
		addMouseListener(new MouseAdapter() {
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
		
		//Nom des villes au survol
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e){
				for(Shape s : listeEllipse){
					if (s.contains(e.getX(),e.getY())){
						setToolTipText(lienEllipseVille.get(s).nom);
					}
				}
			}
		});
		
	}
	
	/**
	 * getter de x
	 * @return coordonnée x de la forme en cours
	 */
	public int getx() {
		return x;
	}
	
	/**
	 * getter de y
	 * @return coordonnée y de la forme en cours
	 */
	public int gety() {
		return y;
	}

	/**
	 * getter de la liste de forme de l'instance de DessinVDC
	 * @return liste des ellipses
	 */
//	public ArrayList<Shape> getListeForme() {
//		return listeEllipse;
//	}

	/**
	 * getter de la liste de forme
	 * @return
	 */
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
		//pour chaque chemin
		for(int i=0;i<listeChemin.size();i++){
			//on fixe sa couleur
			g2.setColor(listeCouleurChemin.get(i));
			//on dessine chaque arc le composant
			for(Shape arc :listeChemin.get(i)){
				g2.draw(arc);
			}
		}
		for(int i=0;i<listeEllipse.size();i++){
			g2.setColor(Color.GRAY);
			g2.fill(listeEllipse.get(i));
		}
		g2.setColor(Color.BLACK);
		if(formeEnCours!=null)
			g2.draw(formeEnCours);

	}
	
	public void Refresh(VDC vdc){
		listeEllipse.clear();
		listeLigne.clear();
		listeChemin.clear();
		listeCouleurChemin.clear();
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
		//on decale a chaque fois d'un pixel
		double decal=0;
		for(int j=0;j<vdc.listeSolution.size();j++){
			//on genere une couleur que l'on ajoute a liste, la rendant la plus différente possible des autres
			listeCouleurChemin.add(new Color(Color.HSBtoRGB((float)j/(float)vdc.listeSolution.size(), 1.0f, 1.0f)));
			//on prend le noeud et le noeud suivant
			//on creer une liste de forme pour les arcs d'un chemin
			Chemin c=vdc.listeSolution.get(j);
			ArrayList<Shape> listeArc = new ArrayList<Shape>();
			for(int i=0; i<c.size()-1;i++){
				double debx=c.get(i).x+decal;
				double deby=c.get(i).y+decal;
				double finx=c.get(i+1).x+decal;
				double finy=c.get(i+1).y+decal;
				listeArc.add(new Line2D.Double(debx, deby,finx,finy));
			}
			//on ajoute cette liste a la liste de forme de chemin
			listeChemin.add(listeArc);
			//on décale en haut a droite de 1.5 pixel
			if(j % 2 == 0){
				decal=Math.abs(decal);
				decal=decal+1.5;
			//sinon on imprime en miroir en bas a gauche
			} else {
				decal*=-1;
			}
		}
		this.repaint();
		
		
		
	}
}


