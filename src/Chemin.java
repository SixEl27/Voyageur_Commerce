import java.util.ArrayList;
public class Chemin extends ArrayList<Ville>{
	int longueur;
	String commentaire;
	
	/**CONSTRUCTEURS*/
	Chemin(String commentaire){
		super();
		this.longueur=0;
		this.commentaire=commentaire;
	}
	
	/**METHODES*/
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		//ajout du commentaire
		sb.append(commentaire);
		sb.append(" ");
		//ajout du nom de chaque ville
		for (Ville v : this){
			sb.append(v.nom);
			sb.append(" - ");
		}
		//ajout de la longueur
		sb.append("longueur : ");
		sb.append(longueur);
		return sb.toString();
	}
	
	public boolean add(Ville v){
		//calcul distance entre le dernier element de la liste et le nouveau
		if (! this.isEmpty()){
			//dernier element
			Ville villePrec = this.get(this.size()-1);
			//mise a jour de la distance
			longueur+=villePrec.calculDistanceEuc(v);
		}
		//insertion de l'element
		return(this.add(v));
	}
	
}
