import java.text.DecimalFormat;
import java.util.ArrayList;

public class Chemin extends ArrayList<Ville> {
	double longueur;
	long tempsCalcul;
	String commentaire;

	/** CONSTRUCTEURS */
	Chemin(String commentaire) {
		super();
		this.longueur = 0;
		this.tempsCalcul = 0;
		this.commentaire = commentaire;
	}

	/** METHODES */

	public String toString() {
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

	public void setTempsCalcul(long tmp) {
		this.tempsCalcul = tmp;
	}

}
