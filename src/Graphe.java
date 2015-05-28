import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Classe modelisant un graphe de la théorie des graphes
 */
public class Graphe {

	/**
	 * Attributs
	 * nom				nom du graphe
	 * liste_noeud		liste de tout les noeuds du graphe
	 * compteurIdArc	compteur permettant d'avoir des ID uniques
	 * compteurIdNoeud	meme chose
	 */
	String nom;
	ArrayList<Noeud> liste_noeud;
	static int compteurIdArc = 0;
	static int compteurIdNoeud = 0;

	/**
	 * Constructeur produisant un graphe vide
	 * @param nom nom du graphe
	 */
	public Graphe(String nom) {
		this.nom = nom;
		this.liste_noeud = new ArrayList<Noeud>();
	}

	/**
	 * Constructeur produisant une copie d'un graphe
	 * @param G graphe recopié
	 */
	public Graphe(Graphe G) {
		Graphe H = new Graphe(G.nom + "_copie");
		for (Noeud n : G.liste_noeud) {
			H.liste_noeud.add(n);
		}
	}

	/**
	 * permet d'ajouter un noeud au graphe
	 * @param n noeud a ajouter
	 */
	public void ajouterNoeud(Noeud n) {
		// TODO verifier la présence du noeud avant de l'ajouter dans la liste
		// (hautement improbable avec le système d'ID)
		this.liste_noeud.add(n);
	}

	/**
	 * permet de recuperer un noeud a partir de son ID
	 * @param id ID a rechercher
	 * @return
	 */
	public Noeud recupererNoeud(int id) {
		for (Noeud n : this.liste_noeud) {
			if (n.id == id) {
				return n;
			}
		}
		return null;
	}

	/**
	 * supprime un noeud du graphe en retirant tout les arcs pointant vers lui
	 * @param n Noeud a supprimer
	 * @return rend un boolean indiquant la reussite de l'opération
	 */
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

	/**
	 * masquage de toString
	 * affiche la liste de noeud du graphe
	 */
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