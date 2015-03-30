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
		this.ida=Graph.ida;
		Graph.ida=Graph.ida+1;
	}
	/**MAIN*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**METHODES*/
	public String toString(){
		String str="Identifiant : "+this.ida+"Ville 1 : "+this.idn1+"\nVille 2 : "+this.idn2;
		return str;
	}

}
