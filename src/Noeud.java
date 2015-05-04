import java.util.ArrayList;


public class Noeud {

	/**
	 * @param args
	 */
	int id;
	ArrayList<Arc> liste_arc;
	
	/**CONSTRUCTEURS*/
	public Noeud(){
		this.liste_arc=new ArrayList<Arc>();
		this.id=Graphe.compteurIdNoeud;
		Graphe.compteurIdNoeud=Graphe.compteurIdNoeud+1;
	}
	
	/**METHODES*/
	public String toString(){
		String str="Identifiant : "+this.id;
		return str;
	}
	
}
