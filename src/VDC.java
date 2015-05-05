
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class VDC extends Graphe{

	/**
	 * @param args
	 */
	
	/**CONSTRUCTEURS*/
	public VDC(String nom){
		super(nom);
	}
	
	public VDC(int nb_noeud,int limite){
		//Aleatoire
		super("VDC aleatoire - "+nb_noeud+"noeuds");
		for (int i=0;i<nb_noeud;i++){
			this.ajouterVille(
					new Ville(
					GenerationAleatoire.genererInt(limite),
					GenerationAleatoire.genererInt(limite),
					GenerationAleatoire.genererString("aeuioywzvdg", 6)));
		}
	}

	/**METHODES*/
	public void ajouterVille(Ville source){
		//Pour la ville courante
		super.ajouterNoeud(source);
		for(Noeud destination : this.liste_noeud){
			//pour ne pas avoir de ville ayant une route sur elle meme
			if(source.id!=destination.id){
				//Création des routes reliant la nouvelle ville a toutes les autres
				//dans les deux sens
				Route sens=new Route(source,(Ville)destination);
				Route antisens=new Route((Ville)destination,source);
				//TODO créer méthode d'ajout de route
				source.liste_arc.add(sens);                 
				((Ville)destination).liste_arc.add(antisens);
			}
		}
	}
	 public ArrayList<Ville> plusProcheVoisin(Ville v, ArrayList<Ville> H){
            //TODO Remplacer la liste d'integer vide H par un obj de type chemin
            if(!H.contains(v.id)){
                H.add(v);
            }
            ArrayList<Route> Liste_R=(ArrayList<Route>)(ArrayList<?>)v.liste_arc;
            Collections.sort(Liste_R, Route.DISTANCE_COMPARATOR);
            int i=0;
            for(i=0; i<Liste_R.size();i++){
                Route r=Liste_R.get(i);
                if(!H.contains(r.dest)){
                    this.plusProcheVoisin((Ville)r.dest, H);
                }
            }
            return H;
        }
        
        public ArrayList<Ville> insertionVoisinLePlusEloigne(){
            ArrayList<Ville> H= new ArrayList<Ville>();
            ArrayList<Route> Liste_R_tot= new ArrayList<Route>();
            ArrayList<Ville> liste_v=(ArrayList<Ville>)(ArrayList<?>)this.liste_noeud; //Toute les villes du graphe
            for (Ville v : liste_v){
                ArrayList<Route> Liste_R=(ArrayList<Route>)(ArrayList<?>)v.liste_arc;
                Liste_R_tot.addAll(Liste_R);
            }
            Collections.sort(Liste_R_tot, Route.DISTANCE_COMPARATOR);
            Route r=Liste_R_tot.get(Liste_R_tot.size()-1);
            H.add((Ville)r.source);
            H.add((Ville)r.dest);
            liste_v.remove((Ville)r.source);
            liste_v.remove((Ville)r.dest);
            r=null;
            while(H.size()!=this.liste_noeud.size()){               //Tant que le circuit n’est pas complet-1
                Route route_max=null;
                for(Ville v_inconnu : liste_v){                     // Pour toutes les villes à visiter
                    Hashtable<Ville,Route> ht = new Hashtable<Ville,Route>();   //hashtable clé=ville_connu, valeur=route distance minimal     
                    for(Ville v_connu : H){                         //Pour toutes les villes déjà dans le circuit
                        for(Arc a : v_connu.liste_arc){             //Pour chaque route dont la ville connu est source
                            if(a.dest.id==v_inconnu.id){            //Si la destination est une ville inconnu
                                if(ht.get(v_connu)==null){          //Si la table e hashage est vide
                                    ht.put(v_connu, (Route)a);      //Ajout (premier passage)
                                }
                                else{                               //Sinon
                                    if(ht.get(v_connu).longueur>((Route)a).longueur){   //Si la longueur de la nouvelle route est inférieur à celle pré-enregistrée
                                         ht.put(v_connu, (Route)a);     //remplacement de l'ancienne route par la nouvelle
                                    }
                                }
                            }
                        }
                        
                    }
                    for( Ville v : ht.keySet()){
                        if(route_max==null){
                            route_max=ht.get(v);
                        }
                        else{
                            if(ht.get(v).longueur>route_max.longueur){
                                route_max=ht.get(v);
                            }
                        }
                    }
                        
                }
                int index=H.indexOf(route_max.source);
                H.add(index, (Ville)route_max.dest);
                //TODO vérifier que l'insertion n'ecrase pas ds la liste H
            }
            return H;
        };
        
         /**
            Initialisation du circuit avec les 2 villes les plus éloignées
            Suppression de ces villes des étapes à visiter
            Tant que le circuit n’est pas complet-1
                Pour toutes les villes à visiter
                    Pour toutes les villes déjà dans le circuit
                        Calcul de la ville dont la distance est minimale
                    Calcul de la ville dont la distance est maximum des distances minimales
                Insertion de la ville trouvée juste après la ville dont elle est la plus proche
                Suppression de la ville des étapes à visiter
            Fin tant que
            Circuit + retour 
        */
                /**
                for(Ville v : Liste_V){
                    ArrayList<Route> Liste_R_max= new ArrayList<Route>();
                    ArrayList<Ville> Liste_V_max= new ArrayList<Ville>();
                    for(Ville v2 : H){
                        ArrayList<Route> Liste_R_min= new ArrayList<Route>();
                        ArrayList<Ville> Liste_V_min= new ArrayList<Ville>();
                        for(Arc a : v.liste_arc){
                            if(a.dest.id==v2.id){
                                Liste_R_min.add((Route)a);
                            }
                        }
                        Collections.sort(Liste_R_min, Route.DISTANCE_COMPARATOR);
                        Liste_V_max.add((Ville)Liste_R_min.get(0).source);
                    }
                    Collections.sort(Liste_R_max, Route.DISTANCE_COMPARATOR);
                    r=Liste_R_max.get(Liste_R_max.size());
                }
                
            }*/

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Graphe VdC : ");
		sb.append(nom);
		sb.append("\n");
		//met le toString de chaque noeud dans la chaine
		for(Noeud n : this.liste_noeud){
			//on utilise le toString de Ville
			sb.append(((Ville)n).toString());
			sb.append("\n");
		}
		return sb.toString().trim();
	}
        /**
        public ArrayList<Integer> Nopt2(VDC V){
            ArrayList<Integer> H= new ArrayList<Integer>();
            boolean amelio=true;
            while(amelio==true){
		amelio=false;
                for(Noeud n : V.liste_noeud){
                    Ville v = (Ville)n;
                    for(Noeud n2 : V.liste_noeud){
                        Ville v2 = (Ville)n2;
                        if(v.id!=v2.id-1 && v.id!=v2.id+1 && v.id!=v2.id){ //modif thomas
                            for(Arc a : v.liste_arc){
                                if(a.dest.id==v2.id){
                                    Route r=(Route)a;
                                }
                            }
                            
                        }
                    }
                }
            }
            return H;
        }
        */
            	//fonction 2-opt ( G : Graphe, H : CycleHamiltonien )

	//  am�lioration : bool�en := vrai
	// Tant que am�lioration = vrai faire

	//   am�lioration := faux;
	// Pour tout sommet xi de H faire

	//   Pour tout sommet xj de H, avec j diff�rent de i-1 et i+1 faire

	//     Si distance(xi, xi+1) + distance(xj, xj+1) > distance(xi, xj) + distance(xi+1, xj+1) alors

	//       Remplacer les ar�tes (xi, xi+1) et (xj, xj+1) par (xi, xj) et (xi+1, xj+1) dans H
	//     am�lioration := vrai;

	//retourner H
            
            
        /**
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
        **/
}