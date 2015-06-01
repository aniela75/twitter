package models.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOTweet extends DAO {
	public static void main(String[] args) {
		
		
	}
	public DAOTweet()
	{
		super();
	}


/**
 * permet d'ajouter un tweet avec le text d'un tweet et l'id d'un compte
 * @param strTweet
 * @param idcompte
 * @return 1 si ok//-1 si pas ok
 */
public static int creertweet(String strTweet,int idcompte){
	
	if (veriftweet(strTweet,idcompte)==-1){
		
		try{
			DAO.getLog().envoyerlog(10, "DAOTweet", "creertweet:Tentative insertion enregistrement BDD avec id :text tweet:"+ idcompte +":"+strTweet);
			Connection maConnexion = DAO.getConnection();
			PreparedStatement  st = maConnexion.prepareStatement("INSERT INTO T_TWEET(Compte_Id, Tweet_Description) VALUES (?,?);");           
			st.setInt(1, idcompte);
			st.setString(2, strTweet);
			st.executeUpdate();
			maConnexion.close();
			DAO.getLog().envoyerlog(10, "DAOTweet", "creertweet:R�ussite insertion enregistrement BDD avec id :text tweet:"+ idcompte +":"+strTweet);
			return 1;
    	
			}
		catch(SQLException e)
			{
				DAO.getLog().envoyerlog(2, "DAOTweet", "creertweet:Echec insertion enregistrement BDD avec id :text tweet:"+ idcompte +":"+strTweet);
				return -1;
			}

		}
	else
	{
		DAO.getLog().envoyerlog(8, "DAOTweet", "creertweet: tweet :"+strTweet +"et id:"+idcompte+" d�j� utilis�");
		return -1;
	}
}


/**
 * renvoi l id d'un tweet si existant
 * @param strtweet
 * @param idcompte
 * @return id//sinon -1
 */
public static int veriftweet(String strtweet,int idcompte){
	
	try{
		
	DAO.getLog().envoyerlog(10, "DAOTweet", "veriftweet:Tentative recuperation enregistrement  bdd avec compteid = "+idcompte +":"+strtweet );
    Connection maConnexion =DAO.getConnection();
    PreparedStatement  st = maConnexion.prepareStatement("SELECT Compte_Id FROM T_TWEET WHERE Tweet_Description =? AND Compte_Id=?;");  
    st.setString(1, strtweet);
    st.setInt(2, idcompte);
    ResultSet rs = st.executeQuery();
    System.out.println("rs = " +rs);
   
    if(rs.next())
    {
    	 int id=rs.getInt(1);
    	
    	 DAO.getLog().envoyerlog(10, "DAOTweet", "veriftweet:R�ussite r�cuparation  enregistrement  bdd avec  compteid = "+idcompte +":"+strtweet );
    	 maConnexion.close();
    	 return id;           	 
    }
    else
    {
    	maConnexion.close();
    	DAO.getLog().envoyerlog(8, "DAOTweet", "veriftweet:Aucun enregistrement  bdd avec  compteid = "+idcompte +":"+strtweet );
    	return -1;
    }
   
	}
	catch(SQLException e)
	{
		DAO.getLog().envoyerlog(8, "DAOTweet", "vveriftweet:Echec recuperation   bdd avec  compteid = "+idcompte +":"+strtweet );
		return -1;
		
	}
	
	
}
/**
 * permet de recup�rer un tweetid avec un tweet
 * @param strtweet
 * @return un id si ok/-1 si pas ok
 */
public static int IdBytweet(String strtweet){
	
	try{
		
		DAO.getLog().envoyerlog(10, "DAOTweet", "IdBytweet:Tentative lecture enregistrement connexion bdd avec tweet = "+strtweet);
		Connection maConnexion =DAO.getConnection();
		PreparedStatement  st = maConnexion.prepareStatement("SELECT Tweet_Description FROM T_TWEET WHERE Tweet_Description =?;");  
		st.setString(1, strtweet);
    
		ResultSet rs = st.executeQuery();
		
   
    if(rs.next())
    {
    	 int id=rs.getInt(1);
    	
    	 DAO.getLog().envoyerlog(10, "DAOTweet", "IdBytweet:Lecture enregistrement r�ussi connexion bdd avec avec tweet = "+strtweet );
    	 maConnexion.close();
    	 return id;           	 
    }
    else
    {
    	maConnexion.close();
    	DAO.getLog().envoyerlog(8, "DAOTweet", "IdBytweet:Aucun enregistrement  bdd avec avec tweet = "+strtweet);
    	return -1;
    }
   
	}
	catch(SQLException e)
	{
		DAO.getLog().envoyerlog(2, "DAOTweet", "IdBytweet:Echec recuperation  bdd avec avec tweet = "+strtweet);
		return -1;
		
	}
}
/**
 * permet d'enregistrer un retweet d'un tweet
 * @param Strtweet
 * @return 1 si ok//-1 si pas ok
 */
public static int ajouternbretweet(String Strtweet){
	try{
		DAO.getLog().envoyerlog(10, "DAOTweet", "ajouternbretweet: tentative insertion enregistrement pour tweet = "+Strtweet );
        Connection maConnexion = DAO.getConnection();
        PreparedStatement  st = maConnexion.prepareStatement("UPDATE T_TWEET SET Tweet_Nb_retweet=Tweet_Nb_retweet+1 WHERE Tweet_Id=?;");		            
        st.setInt(1, IdBytweet(Strtweet));
       
        st.executeUpdate();
        maConnexion.close();
        DAO.getLog().envoyerlog(10, "DAOTweet", "ajouternbretweet: Insertion r�ussie enregistrement pour tweet = "+Strtweet );
		return 1;
	}
	catch(SQLException e)
	{
		DAO.getLog().envoyerlog(2, "DAOTweet", "ajouternbretweet: Echec insertion enregistrement BDD avec tweet = "+Strtweet );
		return -1;
	}
}
/**
 * permet d'enregistrer un retweet d'un tweet
 * @param idtweet
 * @return 1 si ok//-1 si pas ok
 */
public static int ajouternbretweet(int idtweet){
	try{
		DAO.getLog().envoyerlog(10, "DAOTweet", "ajouternbretweet: tentative insertion enregistrement pour idtweet = "+idtweet );
        Connection maConnexion = DAO.getConnection();
        PreparedStatement  st = maConnexion.prepareStatement("UPDATE T_TWEET SET Tweet_Nb_retweet=Tweet_Nb_retweet+1 WHERE Tweet_Id=?;");		            
        st.setInt(1, idtweet);
       
        st.executeUpdate();
        maConnexion.close();
        DAO.getLog().envoyerlog(10, "DAOTweet", "ajouternbretweet: Insertion r�ussie enregistrement pour idtweet = "+idtweet );
		return 1;
	}
	catch(SQLException e)
	{
		DAO.getLog().envoyerlog(2, "DAOTweet", "ajouternbretweet: Echec insertion enregistrement BDD avec idtweet = "+idtweet );
		return -1;
	}
}
//recuperer la liste des derniers 15 tweet des amis 
}
