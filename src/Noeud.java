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
		this.idn=Graph.idn;
		Graph.idn=Graph.idn+1;
	}
	
	/**MAIN*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**METHODES*/
	public String toString(){
		String str="Identifiant : "+this.idn;
		return str;
	}
	
}
