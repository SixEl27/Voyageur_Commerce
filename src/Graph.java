import java.util.ArrayList;


public class Graph {

	/**
	 * @param args
	 */
	String nomG;
	ArrayList<Noeud> liste_noeud;
	static int ida=0;
	static int idn=0;
	
	/**CONSTRUCTEURS*/
	public Graph(String nom){
		this.nomG=nom;
		this.liste_noeud= new ArrayList<Noeud>();
	}
	/**MAIN*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	/**METHODES*/
	public void AjouterNoeud(Noeud N){
		for(Noeud n : this.liste_noeud){
			Arc a=new Arc(N,n);
			N.liste_arc.add(a);
			n.liste_arc.add(a);
		}
		this.liste_noeud.add(N);
	}
}
