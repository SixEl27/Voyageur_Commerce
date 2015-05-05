import java.util.ArrayList;
import java.util.ListIterator;

//TODO Vérifier hsitoire abstraction
public class Graphe {

	/**
	 * @param args
	 */
	String nom;
	ArrayList<Noeud> liste_noeud;
	static int compteurIdArc = 0;
	static int compteurIdNoeud = 0;

	/** CONSTRUCTEURS */
	public Graphe(String nom) {
		this.nom = nom;
		this.liste_noeud = new ArrayList<Noeud>();
	}

	public Graphe(Graphe G) {
		Graphe H = new Graphe(G.nom + "_copie");
		for (Noeud n : G.liste_noeud) {
			H.liste_noeud.add(n);
		}
	}

	/** METHODES */
	public void AjouterNoeud(Noeud n) {
		// TODO verifier la présence du noeud avant de l'ajouter dans la liste
		// (hautement improbable avec le système d'ID)
		this.liste_noeud.add(n);
	}

	public Noeud RecupererNoeud(int id) {
		for (Noeud n : this.liste_noeud) {
			if (n.id == id) {
				return n;
			}
		}
		// TODO gestion erreur retour null
		return null;
	}

	public Boolean supprimerNoeud(Noeud n) {
		if (this.liste_noeud.remove(n)) {
			// il faut supprimer tout les arcs des autres noeuds pointant vers le noeud supprimé
			for (Noeud n_parcours : this.liste_noeud) {
				//utilisation de l'iterator pour supprimer des éléments pendant le parcours
				ListIterator<Arc> itr = n_parcours.liste_arc.listIterator();
				while(itr.hasNext()){
					if (itr.next().dest.id==n.id){
						itr.remove();
					}
				}
			}
			// la suppresion a été réalisée
			return true;
		}
		// la suppresion n'a pas été possible
		return false;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Graphe : ");
		sb.append(nom);
		sb.append("\n");
		// met le toString de chaque noeud dans la chaine
		for (Noeud n : this.liste_noeud) {
			sb.append(n.toString());
			sb.append("\n");
		}
		return sb.toString().trim();
	}
}