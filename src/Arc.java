//Modification ici en plus

public class Arc {

	/**
	 * @param args
	 */
	Noeud idn1;
	Noeud idn2;
	int ida;
	
	/**CONSTRUCTEURS*/
	public Arc(Noeud idn1, Noeud idn2){
		this.idn1=idn1;
		this.idn2=idn2;
		this.ida=Graphe.ida;
		Graphe.ida=Graphe.ida+1;
	}

	/**METHODES*/
	public String toString(){
		String str="Identifiant : "+this.ida+"Ville 1 : "+this.idn1+"\nVille 2 : "+this.idn2;
		return str;
	}

}
