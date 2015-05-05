
public class Ville extends Noeud{

	/**
	 * @param args
	 */
	String nom;
	int x;
	int y;
	
	/**CONSTRUCTEURS*/
	public Ville(int x, int y, String nom){
		super();
		this.x=x;
		this.y=y;
		this.nom=nom;
	}
	
	public Ville(){
		super();
		
	}

	/**METHODES*/
	public String toString(){
		StringBuffer sb= new StringBuffer();
		sb.append("Ville : ");
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