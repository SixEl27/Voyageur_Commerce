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
	public void AjouterNoeud(Noeud source){      //Pour le noeud courant
		for(Noeud destination : this.liste_noeud){    //Parcours des autres noeuds dans le graphe
			Arc sens=new Arc(source,destination);   //Arc source-destination
                        Arc antisens=new Arc(destination,source);   //Arc destination-source
			source.liste_arc.add(sens);                 
			destination.liste_arc.add(antisens);
		}
		this.liste_noeud.add(source);
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
