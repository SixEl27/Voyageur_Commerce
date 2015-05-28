import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 *Classe etandant d'ArrayList modélisation un chemin de la théorie des graphes
 */
public class Chemin extends ArrayList<Ville> {
	/**
	 * Attributs
	 * longueur		la longueur du chemin (somme des distances euclidiennes)
	 * tempsCalcul	le temps en milliseconde de production par l'algorithme
	 * commentaire	chaine de caractère, utilisée ici pour stocker le nom d'algo
	 */
	double longueur;
	long tempsCalcul;
	String commentaire;

	/**
	 * Constructeur
	 * fixe a 0 les longueurs et temps de calcul
	 * @param commentaire stocke le commentaire sur le chemin
	 */
	Chemin(String commentaire) {
		super();
		this.longueur = 0;
		this.tempsCalcul = 0;
		this.commentaire = commentaire;
	}

	/**
	 * Masquage de toString
	 * n'affiche pas le chemin mais seulement ses attributs
	 */
	public String toString() {
		//formatage pour nombres a deux décimales
		DecimalFormat df = new DecimalFormat("########.00");
		StringBuffer sb = new StringBuffer();
		// ajout du commentaire
		sb.append(commentaire);
		sb.append(" ");
		// ajout de la longueur
		sb.append("lng : ");
		sb.append(df.format(longueur));
		sb.append(" ");
		// ajout temps de calcul
		sb.append("tps : ");
		sb.append(tempsCalcul);
		sb.append("ms");
		return sb.toString();
	}

	/**
	 * Surcouche pour la méthode add des Arraylist
	 * permet de mettre a jour en temps reel la longueur
	 * @param v Ville a ajouter au chemin
	 * @return indique la reussite ou non de l'action
	 */
	public boolean addVille(Ville v) {
		// calcul distance entre le dernier element de la liste et le nouveau
		if (!this.isEmpty()) {
			// dernier element
			Ville villePred = this.get(this.size() - 1);
			// mise a jour de la distance
			longueur += villePred.calculDistanceEuc(v);
		}
		// insertion de l'element
		return (this.add(v));
	}

	/**
	 * Surcouche pour la méthode add(index,objet) des Arraylist
	 * permet de mettre a jour en temps reel la longueur
	 * @param index index de la liste ou l'on veut placer l'objet
	 * @param v ville a inserer
	 */
	public void addVille(int index, Ville v) {
		// insert un element a un endroit precis et calcul la nouvelle longueur
		if (!this.isEmpty()) {
			// element avant l'insertion
			// cas ou l'index est 0
			if (index == 0) {
				// la ville en 0 sera la suivante
				Ville villeSuiv = this.get(0);
				// calcul de la longueur
				longueur += v.calculDistanceEuc(villeSuiv);
				// insertion avec decalage vers la droite
				this.add(index, v);
			}
			// cas ou l'index est a la derniere position
			else if (index == this.size() - 1) {
				// on est dans le cas simple (enfilage)
				addVille(v);
			}
			// cas ou l'index est entre deux ville
			else {
				// la ville a l'index actuelle sera la suivante
				Ville villeSuiv = this.get(index);
				// la ville precedente est la precedente de l'index
				Ville villePred = this.get(index - 1);
				// on retire la longueur de villePred -> villeSuiv
				longueur -= villePred.calculDistanceEuc(villeSuiv);
				// on ajoute la longueur de villePred -> villeAAjouter ->
				// villeSuivante
				longueur += villePred.calculDistanceEuc(v)
						+ v.calculDistanceEuc(villeSuiv);
				// insertion de l'element
				this.add(index, v);
			}

		} else {
			// insertion du tout premier element
			this.add(v);
		}
	}

	/**
	 * Surcouche pour la méthode set des Arraylist
	 * permet de mettre a jour en temps reel la longueur
	 * @param index index de la liste ou remplacer la ville
	 * @param v ville qui remplacera celle etant sur l'index
	 * @return rend l'ancienne ville a la position de l'index ou null
	 */
	public Ville setVille(int index, Ville v) {
		// insert un element a un endroit precis et calcul la nouvelle longueur
		if (!this.isEmpty()) {
			// cas a un seul element
			if (this.size() == 1) {
				// on le remplace simplement
				return (this.set(index, v));
			}
			// cas ou l'index est 0
			else if (index == 0) {
				// calcul de l'ancienne distance entre le point a remplacer et
				// le suivant
				Ville villeARemplacer = this.get(index);
				Ville villeSuiv = this.get(index + 1);
				// on retire l'ancienne distance
				longueur -= villeARemplacer.calculDistanceEuc(villeSuiv);
				// on ajoute la nouvelle
				longueur += v.calculDistanceEuc(villeSuiv);
				return (this.set(index, v));
			}
			// cas ou l'index est a la derniere position
			else if (index == this.size() - 1) {
				// calcul de l'ancienne distance entre le point a remplacer et
				// le precedent
				Ville villeARemplacer = this.get(index);
				Ville villePred = this.get(index - 1);
				// on retire l'ancienne distance
				longueur -= villePred.calculDistanceEuc(villeARemplacer);
				// on ajoute la nouvelle
				longueur += villePred.calculDistanceEuc(v);
				return (this.set(index, v));
			}
			// cas ou l'index est entre deux ville
			else {
				// on recupère les 3 villes pour plus de clarté
				Ville villeARemplacer = this.get(index);
				Ville villeSuiv = this.get(index + 1);
				Ville villePred = this.get(index - 1);
				// retire la longueur de villePred->villeARemplacer->villeSuiv
				longueur -= villePred.calculDistanceEuc(villeARemplacer)
						+ villeARemplacer.calculDistanceEuc(villeSuiv);
				// ajoute la longueur de villePred->v->villeSuiv
				longueur += villePred.calculDistanceEuc(v)
						+ v.calculDistanceEuc(villeSuiv);
				// insertion de l'element
				return (this.set(index, v));
			}
		} else {
			// si il n'y avait rien, rend null
			return null;
		}
	}

	/**
	 * Setter du temps de calcul, utilisé par les algos
	 * @param tmp temps de calcul
	 */
	public void setTempsCalcul(long tmp) {
		this.tempsCalcul = tmp;
	}

}
