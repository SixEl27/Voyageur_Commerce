import java.util.ArrayList;

//TODO Vérifier hsitoire abstraction
public class Graphe {

	/**
	 * @param args
	 */
	String nomG;
	ArrayList<Noeud> liste_noeud;
	static int compteurIdArc=0;
	static int compteurIdNoeud=0;
	
	/**CONSTRUCTEURS*/
	public Graphe(String nom){
		this.nomG=nom;
		this.liste_noeud= new ArrayList<Noeud>();
	}
	public Graphe(Graphe G){
		Graphe H = new Graphe(G.nomG+"_copie");
		for(Noeud n : G.liste_noeud){
			H.liste_noeud.add(n);
		}
	}

	/**METHODES*/
	public void AjouterNoeud(Noeud N){
		for(Noeud n : this.liste_noeud){
			Arc a=new Arc(N,n);
			N.liste_arc.add(a);
			n.liste_arc.add(a);
		}
		this.liste_noeud.add(N);
	}
	public Noeud RecupererNoeud(int idno){
		Noeud n=new Noeud();
		for(Noeud n2 : this.liste_noeud){
			if(n2.id==idno){
				n=n2;
			}
		}
		return n;
	}
}
