import java.util.ArrayList;


public class Nopt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	

	public Graph Nopt(Graph G){
		Graph H=new Graph(G.nomG);
		boolean amelio=true;
		while(amelio==true){
			amelio=false;
			for(Noeud xi : H.liste_noeud){
				for(Noeud xj : H.liste_noeud){
					if(xj.idn!=xi.idn-1 && xj.idn!=xi.idn+1){
						Noeud xip1= H.RecupererNoeud(xi.idn+1);
						Noeud xim1= H.RecupererNoeud(xi.idn-1);
						Noeud xjp1= H.RecupererNoeud(xj.idn+1);
						Noeud xjm1= H.RecupererNoeud(xj.idn-1);
						
						Arc xixip1 = new Arc(xi,xip1);
						Arc xjxjp1 = new Arc(xj,xjp1);
						Arc xixj = new Arc(xi,xj);
						Arc xip1xjp1 = new Arc(xip1,xjp1);
						
						Route r_xixip1=(Route)xixip1;
						Route r_xjxjp1=(Route)xjxjp1;
						Route r_xixj=(Route)xixj;
						Route r_xip1xjp1=(Route)xip1xjp1;
						
						if((r_xixip1.distance+r_xjxjp1.distance)>(r_xixj.distance+r_xip1xjp1.distance)){
							Route sauv=r_xixip1;
							Route sauv2=r_xjxjp1;
							r_xixip1=r_xixj;
							r_xjxjp1=r_xip1xjp1;
							r_xixj=sauv;
							r_xip1xjp1=sauv2;
							amelio=true;
						}
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
