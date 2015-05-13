package models.outils;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



// todo dans message log pas d'espaces mais tabulations 
/**
 * Permets d'enregistrer les logs :D
 * @author Mohamed
 *
 */
public class Log {
	private   FileWriter fichier = null;
	private int niveau_log;
	private String categorie_log;
	private String message_log;
	private String nom_fichier;
	private static  int niveaulogprint=10;
	private static List<String> Categorielist =null;
	
	/**
	 * Permet de remplacer les espaces et les caractere interdit pas ""
	 * Permet de cr�er une nouvelle Log ou acceder � une log existante 
	 *
	 * @param Nom_log (titre du fichier log)
	 */
	public Log(String Nom_log){
		// (remplacer les espaces et la ponctuation par rien)
		Nom_log.replaceAll("[\\s\\p{Punct}]","");
		// (remplacer tout sauf les lettres et les chiffres par rien)
		Nom_log.replaceAll("\\W","");
		this.nom_fichier=Nom_log;
		setFichier();
	};

	/**
	 *Permet  de
	 * @param niveau de la log : de 0 � 10 avec 0 pour CRASH et 10 pour INFO
	 * @param categorie : nom de la classe et optionnellement le nom de la 
	 * @param message
	 */
	public  void envoyerlog(int niveau,String categorie,String message){
		this.niveau_log=niveau;
		this.categorie_log=categorie;
		this.message_log=message;
		Date txtDate=new Date();
		
		//String txtDate="12/05/2014 ";

		
		try {

			fichier.write("["+txtDate+"]" +": NIV[" +niveau_log + "]  CAT[" + categorie_log+"]  "+message_log);
			fichier.write("\r\n"); // forcer le passage � la ligne
			fichier.flush();
			if(verifNiveau(niveau_log)==1){
				
				//afficher en console // si niveau fait parti des niveau voulu
				System.out.println(nom_fichier+"   ["+txtDate+"]" +": NIV[" +niveau_log + "]  CAT[" + categorie_log+"]  "+message_log);
			}
			else
			{
				
			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}


	public  FileWriter getFichier() {

		return fichier;
	}
	/**
	 * cr�er le fichier de la log ou ouvre une log deja existante // initialisation du fichier log
	 * @param Nom_fichier 
	 */
	public  void setFichier() {
		try{
			fichier = new FileWriter(this.nom_fichier+".txt",true);
		

		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		
			
	}
	/**
	 * 
	 * @return le nom de la log 
	 */
	public String getNom_fichier() {
		return nom_fichier;
	}

	/**
	 * Ferme le fichier log 
	 */
	public void fermer_log(){
		
		try {
			fichier.close();
		
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	/**
	 * Verification existance du fichier log
	 * @param nomfichier
	 * @return1 si existe sinon 0
	 */
	public int verifFichier(String nomfichier){
		File fichier = new File(nom_fichier+".txt");
		System.out.println(fichier);
		if (fichier.exists()){
		     return 1;
		}else{
		    return 0;
		}
		
		
	}
	/**
	 * Permet de recuperer le niveau
	 * @return le niveau maximun des log � afficher en console
	 */
	public static int getNiveaulogprint() {
		return niveaulogprint;
	}

	public static void setNiveaulogprint(int niveaulogprint) {
		Log.niveaulogprint = niveaulogprint;
	}
	/**
	 * Permet de verifier si le niveau de log est souhait� � l'affichage console
	 * @param niveau de la log envoy�e
	 * @return 1 si est inferieur au niveau maximun souhait� /0 si log superieure au maximun souhait�
	 */
	private static int verifNiveau(int niveau){
		if(niveau<=niveaulogprint){
			return 1;
		}
		else
		{
			return 0;
		}
		
	}

	public static  void ajouterCatList(String cat){
		if (Categorielist==null){
			Categorielist= new ArrayList<String>();
			Categorielist.add(cat);
			System.out.println(Categorielist.size());
		}
		else
		{
			Categorielist.add(cat);
			System.out.println(Categorielist.size());
			System.out.println(Categorielist);
			System.out.println("ok");
		}
		
		
	}
	public static  void retirerCatList(String cat){
		
			
			Categorielist.remove(cat);
			System.out.println(Categorielist.size());
		
		
		
	}
}
