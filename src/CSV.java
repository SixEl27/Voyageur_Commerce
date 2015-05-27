import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CSV {

	String nomFichier;
	String separateur;
	ArrayList<ArrayList<String>> data;
	ArrayList<String> ligneEnCours;

	/** CONSTRUCTEUR */
	public CSV(String nom) {
		nomFichier = nom;
		separateur = ",";
		data = new ArrayList<ArrayList<String>>();
		ligneEnCours = new ArrayList<String>();
	}

	public CSV(String nom, String sep) {
		nomFichier = nom;
		separateur = sep;
		data = new ArrayList<ArrayList<String>>();
		ligneEnCours = new ArrayList<String>();
	}

	/** METHODES */
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

	public void imprimeCSV() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(nomFichier, "UTF-8");
		writer.print(this);
		writer.close();
	}
	
	public void ajoutLigne(ArrayList<String> ligne) {
		data.add(ligne);
	}
	
	public void ajoutElement(Object o){
		ligneEnCours.add(o.toString());
	}
	
	public void finElement(){
		ajoutLigne(ligneEnCours);
		ligneEnCours = new ArrayList<String>();
	}

	public ArrayList<ArrayList<String>> getData(){
		return data;
	}
	
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
