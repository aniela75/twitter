package models;

public class Compte {

	public int Compte_Id; 
	public String Compte_Login; 
	public String Compte_Mdp;
	public String Compte_Email;
	public String Compte_Description;
	public int Compte_Nb_Tweets;
	public int Compte_Nb_Followers;
	public int Compte_Nb_Following;
	public String Compte_Mention;
	public String Compte_Image;
	
	public Compte(int id,String login,String mdp,String email,String description,int nbtweet,int nbfollowers,int nbfollowing,String mention,String urlimage)
	{
		this.Compte_Id=id;
		this.Compte_Login=login;
		this.Compte_Mdp=mdp;
		this.Compte_Email=email;
		this.Compte_Description=description;
		this.Compte_Nb_Tweets=nbtweet;
		this.Compte_Nb_Followers=nbfollowers;
		this.Compte_Nb_Following=nbfollowing;
		this.Compte_Mention=mention;
		this.Compte_Image=urlimage;
		
				
		
	}
	public Compte(int id,String login,String description,int nbtweet,int nbfollowers,int nbfollowing,String mention,String urlimage)
	{
		this.Compte_Id=id;
		this.Compte_Login=login;
		this.Compte_Description=description;
		this.Compte_Nb_Tweets=nbtweet;
		this.Compte_Nb_Followers=nbfollowers;
		this.Compte_Nb_Following=nbfollowing;
		this.Compte_Mention=mention;
		this.Compte_Image=urlimage;
		
				
		
	}
}
