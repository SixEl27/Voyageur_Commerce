
/**
 * Classe modélisant un arc de la théorie des graphes
 */
public class Arc {

	/**
	 * Attributs
	 * source	Le noeud de depart de l'arc
	 * dest		Le noeud d'arrivée de l'arc
	 * id 		l'identifiant unique de l'arc (grace au compteur de Graphe)
	 */
	Noeud source;
	Noeud dest;
	int id;
	
	/**
	 * Constructeur prenant deux noeud pour créer un arc
	 * @param source Noeud source
	 * @param dest Noeud de destination
	 */
	public Arc(Noeud source, Noeud dest){
		this.source=source;
		this.dest=dest;
		this.id=Graphe.compteurIdArc;
		Graphe.compteurIdArc=Graphe.compteurIdArc+1;
	}

	/**
	 * Masquage de toString pour permettre une bonne visualisation de l'arc
	 * @return String décrivant l'objet
	 */
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("Arc : ");
		sb.append(this.id);
		sb.append(", source : ");
		sb.append(this.source.id);
		sb.append(", dest : ");
		sb.append(this.dest.id);
		return sb.toString();
	}

}