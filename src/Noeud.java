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
		StringBuffer sb= new StringBuffer();
		sb.append("Noeud : ");
		sb.append(this.id);
		sb.append("\n");
		for(Arc a : liste_arc){
			sb.append(a.toString());
			sb.append('\n');
		}
		return sb.toString();
	}
	
}