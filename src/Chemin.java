import java.util.ArrayList;

import org.w3c.dom.ranges.RangeException;

public class Chemin extends ArrayList<Ville> {
	double longueur;
	String commentaire;

	/** CONSTRUCTEURS */
	Chemin(String commentaire) {
		super();
		this.longueur = 0;
		this.commentaire = commentaire;
	}

	/** METHODES */

	public String toString() {
		StringBuffer sb = new StringBuffer();
		// ajout du commentaire
		sb.append("commentaire :\t");
		sb.append(commentaire);
		sb.append(" ");
		sb.append("\n");
		sb.append("chemin :\t");
		// ajout du nom de chaque ville
		for (Ville v : this) {
			sb.append(v.nom);
			sb.append(" - ");
		}
		sb.append("\n");
		// ajout de la longueur
		sb.append("longueur :\t");
		sb.append(longueur);
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
			//cas ou l'index est 0
			if (index==0){
				//la ville en 0 sera la suivante
				Ville villeSuiv = this.get(0);
				//calcul de la longueur
				longueur += v.calculDistanceEuc(villeSuiv);
				//insertion avec decalage vers la droite
				this.add(index,v);
			}
			//cas ou l'index est a la derniere position
			else if (index==this.size()-1){
				//on est dans le cas simple (enfilage)
				addVille(v);
			}
			//cas ou l'index est entre deux ville
			else{
				//la ville a l'index actuelle sera la suivante
				Ville villeSuiv = this.get(index);
				//la ville precedente est la precedente de l'index
				Ville villePred = this.get(index-1);
				//on retire la longueur de villePred -> villeSuiv
				longueur -= villePred.calculDistanceEuc(villeSuiv);
				//on ajoute la longueur de villePred -> villeAAjouter -> villeSuivante
				longueur += villePred.calculDistanceEuc(v) + v.calculDistanceEuc(villeSuiv);
				//insertion de l'element
				this.add(index,v);
			}
						
		} else {
		// insertion de l'element
		this.add(v);
		}
	}

}
