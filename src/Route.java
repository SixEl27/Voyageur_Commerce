import java.lang.Math;
import java.util.Comparator;

public class Route extends Arc{

	/**
	 * @param args
	 */
	double longueur;
	
	/**CONSTRUCTEURS*/
	public Route(Ville source, Ville dest){
		super(source,dest);
		this.longueur=calculDistanceEuc(source, dest);
	}

	/**METHODES*/
	public double calculDistanceEuc(Ville source, Ville dest){
		double x1=source.x;
		double x2=dest.x;
		double y1=source.y;
		double y2=dest.y;
		double d=Math.sqrt( Math.pow((x2-x1),2) +  Math.pow((y2-y1),2) );
		return d;
	}
	
        public int getId() {
            return this.id;
	}
 
        public void setId(int id) {
            this.id = id;
	}
 
	public Ville getSource() {
            return (Ville)this.source;
	}
 
	public Ville getDestination() {
            return (Ville)this.dest;
	}
 
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