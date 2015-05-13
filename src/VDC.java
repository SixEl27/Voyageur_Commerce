
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

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
            H.add(H.get(0));
            return H;
        }
        
        
        public ArrayList<Ville> two_opt(){
           // ArrayList<Ville> H= new ArrayList(this.liste_noeud);
            ArrayList<Ville> H= new ArrayList();
            for (int j = 0; j < this.liste_noeud.size(); j++) {
                H.add((Ville)this.liste_noeud.get(j));
            }
            
            //H.add((Ville)this.liste_noeud.get(0));
            
            boolean b=true;
            if(H.isEmpty()){
                b=false;
            }
            while(b){
                b=false;
                for (int i = 0; i < H.size()-1; i++) {
                    for (int j = 0; j < H.size()-1; j++) {
                        if(j!=i && j!=j+1 && j!=j-1){
                            Ville vi=H.get(i);
                            Ville vj=H.get(j);

                            Ville vi1=null;
                            Ville vj1=null;
                            if(i==H.size()-1){
                                vi1=H.get(0);
                            }
                            else{
                                vi1=H.get(i+1);
                            }
                            if(j==H.size()-1){
                                vj1=H.get(0);
                            }
                            else{
                                vj1=H.get(j+1);
                            }
                            double dii1=0;
                            double djj1=0;
                            double dij=0;
                            double di1j1=0;
                            for(Arc a :vi.liste_arc){
                                    Route r = (Route)a;
                                    if((r.source.id==vj.id && r.dest.id==vi.id) ||(r.dest.id==vj.id && r.source.id==vi.id)){
                                            dij=r.longueur;
                                    }

                                    else if((r.source.id==vi.id && r.dest.id==vi1.id) ||(r.dest.id==vi.id && r.source.id==vi1.id)){
                                            dii1=r.longueur;
                                    }	
                            }
                            for(Arc a :vj1.liste_arc){
                                    Route r1=(Route)a;
                                    if((r1.source.id==vj1.id && r1.dest.id==vi1.id) ||(r1.dest.id==vj1.id && r1.source.id==vi1.id)){
                                            di1j1=r1.longueur;
                                    }

                                    else if((r1.source.id==vj1.id && r1.dest.id==vj.id) ||(r1.dest.id==vj1.id && r1.source.id==vj.id)){
                                            djj1=r1.longueur;
                                    }
                            }
                            if(dii1+djj1>dij+di1j1){
                                    // Remplacer les arêtes (xi, xi+1) et (xj, xj+1) par (xi, xj) et (xi+1, xj+1) dans H
                                    if(i==H.size()-1){
                                        H.set(0,vj);
                                    }
                                    else{
                                        H.set(i+1,vj);
                                    }	
                                    H.set(j, vi1);

                                    b=true;
                            }
                        }
                    }
                }
            }
            //System.out.println(H.size()+" "+this.liste_noeud.size()+"    v");
            H.add(H.get(0));
            return H;	
	}
         
         
        public ArrayList<Ville> insertionVoisinLePlusEloigne(){
            VDC VDC_copie=new VDC("copie");
            //VDC_copie.clone(this);
            //TODO Ne pas modifier le vrai model
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
            while(!this.liste_noeud.isEmpty()){               //Tant que le circuit n’est pas complet-1
                Route route_max=null;
                Hashtable<Ville,Route> ht = new Hashtable<Ville,Route>();   //hashtable clé=ville_connu, valeur=route distance minimal     
                //System.out.println(H.size());
                //System.out.println(String.valueOf(this.liste_noeud.size())+"ici");  //reduit petit a petit sans savoir pk
                for(Ville v_connu : H){                         //Pour toutes les villes déjà dans le circuit
                    for(Ville v_inconnu : liste_v){                     // Pour toutes les villes à visiter
                        for(Arc a : v_connu.liste_arc){             //Pour chaque route dont la ville connu est source
                            if(a.dest.id==v_inconnu.id){            //Si la destination est une ville inconnu
                                if(ht.get(v_connu)==null){          //Si la table e hashage est vide
                                    ht.put(v_connu, (Route)a);      //Ajout (premier passage)
                                    //System.out.println(v_connu.id);
                                }
                                else{                               //Sinon
                                    if(ht.get(v_connu).longueur>((Route)a).longueur){   //Si la longueur de la nouvelle route est inférieur à celle pré-enregistrée
                                        ht.put(v_connu, (Route)a);     //remplacement de l'ancienne route par la nouvelle
                                        //System.out.println(v_connu.id);
                                    }
                                }
                            }
                        }
                        
                    }
                    for( Arc a : ht.values()){
                        if(route_max==null){
                            route_max=(Route)a;
                        }
                        else{
                            if(((Route)a).longueur>route_max.longueur){
                                route_max=(Route)a;
                            }
                        }
                    }
                    
                }
                if(route_max!=null){
                    liste_v.remove((Ville)route_max.dest); 
                    int index=H.indexOf(route_max.source);
                    H.add(index, (Ville)route_max.dest);
                }
                //TODO vérifier que l'insertion n'ecrase pas ds la liste H
            }
            return H;
        };
        
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

}