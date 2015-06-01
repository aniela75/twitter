package models.utilisateur;

public interface DAOinterface {

	
	
	public  int creerCompte(String login,String Mdp,String email,String description,String urlimage);
	public  int creerCompte(String login,String Mdp,String email);
	public  int verifUtilisateur(String login,String mdp);
	public  String loginById(int id);
	public  int idcomptebylogin(String login);
	public  String encode(String mdp);
	public  int gagnerPartie(String login);
	public  int ajouterFollower(String followerlogin,String compteLogin);
	public  int retirerFollower(String CompteLogin,String followerLogin);
	public  int retirerFollowing(String CompteLogin,String followingLogin);
	public  int ajouterFollowing(String followinglogin,String comptelogin);
	public  int getPartiesJoueesById(int Id);
	public  int getPartiesGagneesById(int Id);
	public  String now();

}
