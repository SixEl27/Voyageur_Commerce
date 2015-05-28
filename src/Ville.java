import java.text.DecimalFormat;

/**
 *
 */
public class Ville extends Noeud {

	/**
	 * Attributs
	 * nom	nom de la ville
	 * x	coordonnées x sur le plan
	 * y 	coordonnées y sur le plan
	 */
	String nom;
	double x;
	double y;

	/**
	 * Constructeur produisant une ville a partir de coordonnées et d'un nom
	 * @param x		coordonées x dans le plan
	 * @param y		coordonées y dans le plan
	 * @param nom	nom de la ville
	 */
	public Ville(double x, double y, String nom) {
		super();
		this.x = x;
		this.y = y;
		this.nom = nom;
	}

	/**
	 * Constructeur de copie
	 * @param v		ville a copier
	 */
	public Ville(Ville v) {
		super();
		this.x = v.x;
		this.y = v.y;
		this.nom = v.nom;
	}

	/**
	 * Constructeur vide
	 */
	public Ville() {
		super();
	}

	/**
	 * setter de nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * setter de x
	 * @param x
	 */
	public void setX(Double x) {
		this.x = x;
	}
	
	/**
	 * setter de y
	 * @param y
	 */
	public void setY(Double y) {
		this.y = y;
	}

	/**
	 * calcul la distance euclidienne avec l'objet comme source et un argument destination
	 * @param dest	Ville de destination
	 * @return	double contenant la distance enclidienne
	 */
	public double calculDistanceEuc(Ville dest) {
		// Calcul distance entre la ville actuelle et une ville de destination
		double x1 = this.x;
		double x2 = dest.x;
		double y1 = this.y;
		double y2 = dest.y;
		double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return d;
	}
	
	/**
	 * masquage de toString
	 * imprime la ville
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("########.00");
		StringBuffer sb = new StringBuffer();
		sb.append("Ville : ");
		sb.append(this.nom);
		sb.append(", ID : ");
		sb.append(this.id);
		sb.append(", x : ");
		sb.append(df.format(this.x));
		sb.append(", y : ");
		sb.append(df.format(this.y));
		return sb.toString();
	}
}