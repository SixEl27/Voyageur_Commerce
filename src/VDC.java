
public class VDC extends Graphe{

	/**
	 * @param args
	 */
	
	/**CONSTRUCTEURS*/
	public VDC(String nom){
		super(nom);
	}

	/**METHODES*/
	public void ajouterVille(Ville v){
		super.AjouterNoeud(v);
                for(Noeud n : this.liste_noeud){
                        Route r=new Route(v,(Ville)n);
		}
	}
        
        public Graphe Nopt(Graphe G){
		Graphe H=new Graphe(G.nomG);
		boolean amelio=true;
		while(amelio==true){
			amelio=false;
			for(Noeud xi : H.liste_noeud){
				for(Noeud xj : H.liste_noeud){
					
					if(xj.id!=xi.id-1 && xj.id!=xi.id+1 && xj.id!=xi.id){ //modif thomas
						Noeud xip1= H.RecupererNoeud(xi.id+1);
						Noeud xim1= H.RecupererNoeud(xi.id-1);
						Noeud xjp1= H.RecupererNoeud(xj.id+1);
						Noeud xjm1= H.RecupererNoeud(xj.id-1);

						Arc xixip1 = new Arc(xi,xip1);
						Arc xjxjp1 = new Arc(xj,xjp1);
						Arc xixj = new Arc(xi,xj);
						Arc xip1xjp1 = new Arc(xip1,xjp1);

						Route r_xixip1=(Route)xixip1;
						Route r_xjxjp1=(Route)xjxjp1;
						Route r_xixj=(Route)xixj;
						Route r_xip1xjp1=(Route)xip1xjp1;

						if((r_xixip1.distance+r_xjxjp1.distance)>(r_xixj.distance+r_xip1xjp1.distance)){
							//Verifier que
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
		}
		return H;
	}
}
