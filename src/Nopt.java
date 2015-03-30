import java.util.ArrayList;


public class Nopt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Ville> Nopt(Graph G){
		Graph H=new Graph(G.nomG);
		
		boolean amelio=true;
		while(amelio==true){
			amelio=false;
			for(Noeud xi : G.liste_noeud){
				for(Noeud xj : G.liste_noeud){
					if(xj.idn!=xi.idn-1 && xj.idn!=xi.idn+1){
						
					}
				}
			}
		
		return H;
	}
	
	//fonction 2-opt ( G : Graphe, H : CycleHamiltonien )

	  //  amélioration : booléen := vrai
	   // Tant que amélioration = vrai faire

	     //   amélioration := faux;
	       // Pour tout sommet xi de H faire

	         //   Pour tout sommet xj de H, avec j différent de i-1 et i+1 faire

	           //     Si distance(xi, xi+1) + distance(xj, xj+1) > distance(xi, xj) + distance(xi+1, xj+1) alors

	             //       Remplacer les arêtes (xi, xi+1) et (xj, xj+1) par (xi, xj) et (xi+1, xj+1) dans H
	               //     amélioration := vrai;

	    //retourner H


}
