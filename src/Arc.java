//Modification ici en plus

public class Arc {

	/**
	 * @param args
	 */
	Noeud source;
	Noeud dest;
	int id;
	
	/**CONSTRUCTEURS*/
	public Arc(Noeud source, Noeud dest){
		this.source=source;
		this.dest=dest;
		this.id=Graphe.compteurIdArc;
		Graphe.compteurIdArc=Graphe.compteurIdArc+1;
	}

	/**METHODES*/
	public String toString(){
		String str="Identifiant : "+this.id+"Ville 1 : "+this.source+"\nVille 2 : "+this.dest;
		return str;
	}

}
