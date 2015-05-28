import java.util.ArrayList;

/**
 * Classe modelisant un noeud de la théorie des graphes
 */
public class Noeud {

	/**
	 * Attributs
	 * id			ID unique du noeud
	 * liste_arc	liste des arcs ayant le noeud pour source
	 */
	int id;
	ArrayList<Arc> liste_arc;
	
	/**
	 * Constructeur vide mettant un ID unique
	 */
	public Noeud(){
		this.liste_arc=new ArrayList<Arc>();
		this.id=Graphe.compteurIdNoeud;
		Graphe.compteurIdNoeud=Graphe.compteurIdNoeud+1;
	}
	
	/**
	 * masquage de toString
	 * affiche liste d'arc d'un noeud
	 */
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