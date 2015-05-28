import java.lang.Math;
import java.util.Comparator;

/**
 * Classe modelisant une route du probleme du voyageur de commerce
 * etend d'Arc, en ajoutant une longueur
 */
public class Route extends Arc{

	/**
	 * Attributs
	 * longueur
	 */
	double longueur;
	
	/**
	 * constructeur produisant une route a partir de sa source et sa destination
	 * @param source Ville source
	 * @param dest Ville destination
	 */
	public Route(Ville source, Ville dest){
		super(source,dest);
		this.longueur=source.calculDistanceEuc(dest);
	}

	/**
	 * getter de l'ID
	 * @return ID de la route
	 */
        public int getId() {
            return this.id;
	}
    /**
     * setter de l'ID
     * @param id ID de la route
     */
        public void setId(int id) {
            this.id = id;
	}
 
    /**
     * getter de la ville source
     * @return ville source
     */
	public Ville getSource() {
            return (Ville)this.source;
	}
    /**
     * getter de la ville destination
     * @return ville destination
     */
	public Ville getDestination() {
            return (Ville)this.dest;
	}
 
		/**
		 * Comparateur de route fonctionnant sur la longueur
		 */
        public static final Comparator<Route> DISTANCE_COMPARATOR = new Comparator<Route>() {
 
            public int compare(Route arg0, Route arg1) {
		// TODO Auto-generated method stub
		Route p=(Route) arg0;
		Route q=(Route) arg1;
		if(p.longueur == q.longueur){    
                    return 0;
		}
                else if (p.longueur > q.longueur)  return 1;
		else return -1;				
            }
 
	};

	/**
	 * Masquage de tostring
	 * affiche toutes les infos de l'arc plus la longueur
	 */
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("ID Route: ");
		sb.append(this.id);
		sb.append(", source : ");
		sb.append(((Ville)this.source).nom);
		sb.append(", dest : ");
		sb.append(((Ville)this.dest).nom);
		sb.append(", longueur : ");
		sb.append(this.longueur);
		return sb.toString();
	}
}