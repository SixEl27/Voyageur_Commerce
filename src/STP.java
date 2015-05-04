
public class STP extends Graphe{

	/**
	 * @param args
	 */
	
	/**CONSTRUCTEURS*/
	public STP(String nom){
		super(nom);
	}

	/**METHODES*/
	public void AjouterVille(int x, int y, String nom){
		Ville v = new Ville(x,y,nom);
		super.AjouterNoeud(v);
	}
}
