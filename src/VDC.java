
public class VDC extends Graphe{

	/**
	 * @param args
	 */
	
	/**CONSTRUCTEURS*/
	public VDC(String nom){
		super(nom);
	}

	/**METHODES*/
	public void ajouterVille(int x, int y, String nom){
		Ville v = new Ville(x,y,nom);
		super.AjouterNoeud(v);
	}
}
