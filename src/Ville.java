
public class Ville extends Noeud{

	/**
	 * @param args
	 */
	String nom;
	double x;
	double y;
	
	/**CONSTRUCTEURS*/
	public Ville(double x, double y, String nom){
		super();
		this.x=x;
		this.y=y;
		this.nom=nom;
	}
	
	public Ville(){
		super();
		
	}

	/**METHODES*/
	
	public double calculDistanceEuc(Ville dest){
		//Calcul distance entre la ville actuelle et une ville de destination
		double x1=this.x;
		double x2=dest.x;
		double y1=this.y;
		double y2=dest.y;
		double d=Math.sqrt( Math.pow((x2-x1),2) +  Math.pow((y2-y1),2) );
		return d;
	}
	public String toString(){
		StringBuffer sb= new StringBuffer();
		sb.append("Ville : ");
		sb.append(this.nom);
		sb.append(", ID : ");
		sb.append(this.id);
		sb.append(", x : ");
		sb.append(this.x);
		sb.append(", y : ");
		sb.append(this.y);
		sb.append("\n");
		for(Arc a : liste_arc){
			sb.append(((Route)a).toString());
			sb.append('\n');
		}
		return sb.toString();
	}
}