
public class STP extends Graph{

	/**
	 * @param args
	 */
	
	/**CONSTRUCTEURS*/
	public STP(String nom){
		super(nom);
	}
	/**MAIN*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**METHODES*/
	public void AjouterVille(int x, int y, String nom){
		Ville v = new Ville(x,y,nom);
		super.AjouterNoeud(v);
	}
}
