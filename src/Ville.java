import java.text.DecimalFormat;


public class Ville extends Noeud {

	/**
	 * @param args
	 */
	String nom;
	double x;
	double y;

	/** CONSTRUCTEURS */
	public Ville(double x, double y, String nom) {
		super();
		this.x = x;
		this.y = y;
		this.nom = nom;
	}

	public Ville(Ville v) {
		super();
		this.x = v.x;
		this.y = v.y;
		this.nom = v.nom;
	}

	public Ville() {
		super();
	}

	/** METHODES */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setX(Double x) {
		this.x = x;
	}
	
	public void setY(Double y) {
		this.y = y;
	}

	public double calculDistanceEuc(Ville dest) {
		// Calcul distance entre la ville actuelle et une ville de destination
		double x1 = this.x;
		double x2 = dest.x;
		double y1 = this.y;
		double y2 = dest.y;
		double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return d;
	}
	public String toString() {
		DecimalFormat df = new DecimalFormat("########.00");
		StringBuffer sb = new StringBuffer();
		sb.append("Ville : ");
		sb.append(this.nom);
//		sb.append(", ID : ");
//		sb.append(this.id);
		sb.append(", x : ");
		sb.append(df.format(this.x));
		sb.append(", y : ");
		sb.append(df.format(this.y));
		return sb.toString();
	}
}