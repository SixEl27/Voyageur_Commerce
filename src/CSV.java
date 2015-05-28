import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Classe permettant la manipulation basique de fichier au format CSV
 */
public class CSV {

	/**
	 * Attributs
	 * nomFichier		nom du fichier correspondant au CSV, utilisé pour le charger ou l'ecrire
	 * separateur		caractère de séparation du CSV, virgule par défaut
	 * data				Liste(ligne) de liste(element) contenant les données du CSV
	 * ligneEnCours		Liste temporaire utilisée remplir une ligne de CSV element par element
	 */
	String nomFichier;
	String separateur;
	ArrayList<ArrayList<String>> data;
	ArrayList<String> ligneEnCours;

	/**
	 * Constructeur prenant un nom de fichier, fixe la séparation sur ','
	 * @param nom nom du fichier du CSV
	 */
	public CSV(String nom) {
		nomFichier = nom;
		separateur = ",";
		data = new ArrayList<ArrayList<String>>();
		ligneEnCours = new ArrayList<String>();
	}

	/**
	 * Constructeur prenant un nom de fichier et un caractère de séparation
	 * @param nom nom du fichier
	 * @param sep caractère de séparation du CSV
	 */
	public CSV(String nom, String sep) {
		nomFichier = nom;
		separateur = sep;
		data = new ArrayList<ArrayList<String>>();
		ligneEnCours = new ArrayList<String>();
	}

	/**
	 * Permet de charger depuis le nom de fichier les données dans l'objet
	 * @throws IOException produit dans le cas ou le fichier n'est pas lisible
	 */
	public void chargerCSV() throws IOException {
		data.clear();
		BufferedReader bfr=new BufferedReader(new FileReader(nomFichier));
		String ligne=bfr.readLine();
		while (ligne!=null){
			//recuperation de la ligne decoupé dans une arrayList
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(ligne.split(Pattern.quote(separateur))));
			ajoutLigne(list);
			ligne=bfr.readLine();
		}
		bfr.close();
	}

	/**
	 * Permet de créer un fichier CSV a partir du nom de fichier
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void imprimeCSV() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
		writer.print(this);
		writer.close();
	}
	
	
	/**
	 * Ajoute une ligne (Liste) au csv
	 * @param ligne ArrayList comprenant chaque element du CSV
	 */
	public void ajoutLigne(ArrayList<String> ligne) {
		data.add(ligne);
	}
	
	/**
	 * Ajoute un element a la ligne en cours
	 * @param o Objet stocké dans la ligne
	 */
	public void ajoutElement(Object o){
		ligneEnCours.add(o.toString());
	}
	
	/**
	 * Insert la ligne en cours dans data et la vide
	 */
	public void finElement(){
		ajoutLigne(ligneEnCours);
		ligneEnCours = new ArrayList<String>();
	}

	/**
	 * getter permettant de recuperer la liste de liste du CSV
	 * @return ArrayList d'ArrayList de String
	 */
	public ArrayList<ArrayList<String>> getData(){
		return data;
	}
	
	/**
	 * masquage de toString
	 * imprime le csv lui meme, chaque element séparé par le separateur
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (ArrayList<String> al : data) {
			for (String s : al) {
				sb.append(s);
				if (al.indexOf(s) != al.size()-1) {
					sb.append(separateur);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
