public class Arc {

	/**
	 * @param args
	 */
    //ici
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