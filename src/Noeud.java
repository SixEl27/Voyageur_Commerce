import java.util.ArrayList;


public class Noeud {

	/**
	 * @param args
	 */
	int idn;
	ArrayList<Arc> liste_arc;
	
	/**CONSTRUCTEURS*/
	public Noeud(){
		this.liste_arc=new ArrayList<Arc>();
		this.idn=Graphe.idn;
		Graphe.idn=Graphe.idn+1;
	}
	
	/**METHODES*/
	public String toString(){
		String str="Identifiant : "+this.idn;
		return str;
	}
	
}
