package models.utilisateur;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	/**
	 *
	 * Permet de r�cuperer les informations d'un utilisateur dans la Bdd
	 * De lire, �crire des donn�es utilisateur et partie dans la bdd
	 * @author Mohamed
	 *
	 */
	public class DAOUtilisateur extends DAO{ 

	public static void main(String[] args) {
		
		
	}
	public DAOUtilisateur()
    {
        super();
    }
	
	
	
	
	
	
	
	
	
	
	/**
	 * Permet de creer un nouveau compte utilisateur // insertion d'un joueur dans la BDD
	 * @param login login de l'utilisateur
	 * @param Mdp mot de passe de l'utilisateur
	 * @return -1 si le login est d�j� utilis� / id du compte cr��
	 */
	public static int creerCompte(String login,String Mdp,String email,String description,String urlimage){
		
		if (idcomptebylogin(login)==-1){
			
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "creerCompte:Tentative insertion enregistrement BDD avec Login:"+login);
				Connection maConnexion = DAO.getConnection();
				PreparedStatement  st = maConnexion.prepareStatement("INSERT INTO t_compte(Compte_Login, Compte_Mdp,Compte_Email,Compte_Description,Compte_Image) VALUES (?,?,?,?,?);");           
				st.setString(1, login);
				st.setString(2, encode(Mdp));
				st.setString(4, email);
				st.setString(5, description);
				st.setString(6, urlimage);
				st.executeUpdate();
				maConnexion.close();
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "creerCompte:R�ussite insertion enregistrement BDD avec Login:"+login);
				return idcomptebylogin(login);
        	
				}
			catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOUtilisateur", "creerCompte:Echec insertion enregistrement BDD avec Login:"+login);
					return -1;
				}
	
			}
		else
		{
			DAO.getLog().envoyerlog(8, "DAOUtilisateur", "creerCompte:Login:"+login +" d�j� utilis�");
			return -1;
		}
}

	

	/**
	 * 
	 * @param CompteLogin
	 * @param followerLogin
	 * @return 1 si ok -1 si pas ok
	 */
	public static int suprrimerCompte(String CompteLogin){
		try{
			DAO.getLog().envoyerlog(10, "DAOUtilisateur", "suprrimerCompte: tentative suppression enregistrement compte= "+CompteLogin );
	        Connection maConnexion = DAO.getConnection();
	        PreparedStatement  st = maConnexion.prepareStatement("DELETE  FROM t_compte WHERE Compte_Id=?;");		            
	        st.setInt(1, idcomptebylogin(CompteLogin));
	        
	        st.executeUpdate();
	        maConnexion.close();
	     
			return 1;
		}
		catch(SQLException e)
		{
			DAO.getLog().envoyerlog(2, "DAOUtilisateur", "suprrimerCompte: Echec supression enregistrement compte= "+CompteLogin  );
			return -1;
		}
	}
	
	
	
	
	
	/**
	 * permet de supprimer un compte
	 * @param Compteid
	 * @return return 1 si ok/ -1  si pas ok
	 */
	public static int suprrimerCompte(int Compteid){
		try{
			DAO.getLog().envoyerlog(10, "DAOUtilisateur", "suprrimerCompte: tentative suppression enregistrement compte= "+Compteid );
	        Connection maConnexion = DAO.getConnection();
	        PreparedStatement  st = maConnexion.prepareStatement("DELETE  FROM t_compte WHERE Compte_Id=?;");		            
	        st.setInt(1, Compteid);
	        
	        st.executeUpdate();
	        maConnexion.close();
	     
			return 1;
		}
		catch(SQLException e)
		{
			DAO.getLog().envoyerlog(2, "DAOUtilisateur", "suprrimerCompte: Echec supression enregistrement compte= "+Compteid  );
			return -1;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param login
	 * @param Mdp
	 * @param email
	 * @return retourne id du compte cr�e /-1 si erreur
	 */
	public static int creerCompte(String login,String Mdp,String email){
		
		if (idcomptebylogin(login)==-1){
			
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "creerCompte:Tentative insertion enregistrement BDD avec Login:"+login);
				Connection maConnexion = DAO.getConnection();
				PreparedStatement  st = maConnexion.prepareStatement("INSERT INTO t_compte(Compte_Login, Compte_Mdp,Compte_Email) VALUES (?,?,?);");           
				st.setString(1, login);
				st.setString(2,encode(Mdp));
				st.setString(3, email);
				st.executeUpdate();
				maConnexion.close();
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "creerCompte:R�ussite insertion enregistrement BDD avec Login:"+login);
				return idcomptebylogin(login);
        	
				}
			catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOUtilisateur", "creerCompte:Echec insertion enregistrement BDD avec Login:"+login);
					return -1;
				}
	
			}
		else
		{
			DAO.getLog().envoyerlog(8, "DAOUtilisateur", "creerCompte:Login:"+login +" d�j� utilis�");
			return -1;
		}
}









	/**
	 * Permet de verifier si un utilisateur est present dans la BDD via son login et mot de passe
	 * @param login de l'utilisateur/joueur
	 * @param mdp mot de passe de l'utilisateur/joueur
	 * @return -1 si mauvais mot de passe ou login ou inexistant // id si login et mdp bon 
	 */
	public static int verifUtilisateur(String login,String mdp){
		
		try{
			
		DAO.getLog().envoyerlog(10, "DAOUtilisateur", "verifUtilisateur:Tentative recuperation enregistrement  bdd avec login utilisateur = "+login );
        Connection maConnexion =DAO.getConnection();
        PreparedStatement  st = maConnexion.prepareStatement("SELECT Compte_Id FROM t_compte WHERE Compte_Login =? AND Compte_Mdp=?;");  
        
  
        st.setString(1, login);
        st.setString(2,encode(mdp));
        ResultSet rs = st.executeQuery();
        System.out.println("rs = " +rs);
       
        if(rs.next())
        {
        	 int id=rs.getInt(1);
        	
        	 DAO.getLog().envoyerlog(10, "DAOUtilisateur", "verifUtilisateur:R�ussite r�cuparation  enregistrement  bdd avec login utilisateur = "+login );
        	 maConnexion.close();
        	 return id;           	 
        }
        else
        {
        	maConnexion.close();
        	DAO.getLog().envoyerlog(8, "DAOUtilisateur", "verifUtilisateur:Aucun enregistrement  bdd avec login compte = "+login );
        	return -1;
        }
       
		}
		catch(SQLException e)
		{
			DAO.getLog().envoyerlog(8, "DAOUtilisateur", "verifUtilisateur:Echec recuperation   bdd avec login compte = "+login );
			return -1;
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Permet de r�cuperer un login utilisateur � partir de son id
	 * @param id de l'utilisateur/joueur
	 * @return le login de l'utilisateur/joueur ou rien "" si le joueur n'existe pas ou mauvaise connexion a la bdd
	 */
	public static String loginById(int id){
		
		try{
			
			DAO.getLog().envoyerlog(10, "DAOUtilisateur", "loginById:Tentative lecture enregistrement connexion bdd avec id utilisateur = "+id );
			Connection maConnexion =DAO.getConnection();
			PreparedStatement  st = maConnexion.prepareStatement("SELECT compte_login FROM t_compte WHERE Compte_Id =?;");  
        
  
			st.setInt(1, id);
        
			ResultSet rs = st.executeQuery();
			
       
        if(rs.next())
        {
        	 String login=rs.getString(1);
        	
        	 DAO.getLog().envoyerlog(10, "DAOUtilisateur", "loginById:Lecture enregistrement r�ussi connexion bdd avec id utilisateur = "+id + "et login :"+login );
        	 maConnexion.close();
        	 return login;           	 
        }
        else
        {
        	maConnexion.close();
        	DAO.getLog().envoyerlog(8, "DAOUtilisateur", "loginById:Aucun enregistrement  bdd avec id utilisateur = "+id );
        	return "";
        }
       
		}
		catch(SQLException e)
		{
			DAO.getLog().envoyerlog(2, "DAOUtilisateur", "loginById:Echec recuperation  bdd avec id utilisateur = "+id );
			return "";
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Permet de recuperer un id utilisateur a partir d'un login utilisateur
	 * @param login de l'utilisateur/joueur
	 * @return id si enregistrement pr�sent // "" si aucun enregistrement ou echec connexion bdd
	 */
	public static int idcomptebylogin(String login){
		
		try{
			
		DAO.getLog().envoyerlog(2, "DAOUtilisateur", "idcomptebylogin:Tentative lecture enregistrement connexion bdd avec login = "+login );
        Connection maConnexion =DAO.getConnection();
        PreparedStatement  st = maConnexion.prepareStatement("SELECT Compte_Id FROM t_compte WHERE compte_login =?;");  
        st.setString(1, login);
        ResultSet rs = st.executeQuery();
       
        if(rs.next())
        {
        	 int id=rs.getInt(1);
        	 DAO.getLog().envoyerlog(2, "DAOUtilisateur", "idcomptebylogin:Recuperation enregistrement r�ussie pour login = "+login+ " id ="+id );
        	 maConnexion.close();
        	 return id;           	 
        }
        else
        {
        	maConnexion.close();
        	DAO.getLog().envoyerlog(8, "DAOUtilisateur", "idcomptebylogin:Aucun enregistrement avec login = "+login );
        	return -1;
        }
       
		}
		catch(SQLException e)
		{
			DAO.getLog().envoyerlog(2, "DAOUtilisateur", "idcomptebylogin:Echec recuperation enregistrement bdd avec login = "+login );
			return -1;
		}
	}
	
	
	
	
	
	
	
	
	
		/**
		 * Permet de encoder une string en MD5
		 * @param mdp : mot de passe en String 
		 * @return mdp en MD5
		 */
		public static String encode(String mdp)
	    {
	        byte[] uniqueKey = mdp.getBytes();
	        byte[] hash      = null;

	        try
	        {
	            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            throw new Error("No MD5 support in this VM.");
	        }

	        StringBuilder hashString = new StringBuilder();
	        for (int i = 0; i < hash.length; i++)
	        {
	            String hex = Integer.toHexString(hash[i]);
	            if (hex.length() == 1)
	            {
	                hashString.append('0');
	                hashString.append(hex.charAt(hex.length() - 1));
	            }
	            else
	                hashString.append(hex.substring(hex.length() - 2));
	        }
	        return hashString.toString();
	    }
		
		
		
		
		
	
		
		
		
		
		
		
		/**
		 * 
		 * @param login
		 * @return 1 si ok/-1 si pas ok
		 */
		public static int AjouterCompteFollowing(String login){
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "AjouterCompteFollowing: tentative insertion enregistrement pour compte avec login = "+login );
		        Connection maConnexion = DAO.getConnection();
		        PreparedStatement  st = maConnexion.prepareStatement("UPDATE t_compte SET Compte_Nb_Following=Compte_Nb_Following+1 WHERE Utilisateur_Id=?;");		            
		        st.setInt(1, idcomptebylogin(login));
		       
		        st.executeUpdate();
		        maConnexion.close();
		        DAO.getLog().envoyerlog(10, "DAOUtilisateur", "AjouterCompteFollowing: Insertion r�ussie enregistrement pour compte avec login = "+login );
				return 1;
			}
			catch(SQLException e)
			{
				DAO.getLog().envoyerlog(2, "DAOUtilisateur", "AjouterCompteFollowing: Echec insertion enregistrement BDD avec = "+login );
				return -1;
			}
		}
		
		
		
		
		
		/**
		 * 
		 * @param login
		 * @return 1 si ok/-1 si pas ok
		 */
		public static int RetirerCompteFollowing(String login){
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "RetirerCompteFollowing: tentative insertion enregistrement pour compte avec login = "+login );
		        Connection maConnexion = DAO.getConnection();
		        PreparedStatement  st = maConnexion.prepareStatement("UPDATE t_compte SET Compte_Nb_Following=Compte_Nb_Following-1 WHERE Utilisateur_Id=?;");		            
		        st.setInt(1, idcomptebylogin(login));
		       
		        st.executeUpdate();
		        maConnexion.close();
		        DAO.getLog().envoyerlog(10, "DAOUtilisateur", "RetirerCompteFollowing: Insertion r�ussie enregistrement pour compte avec login = "+login );
				return 1;
			}
			catch(SQLException e)
			{
				DAO.getLog().envoyerlog(2, "DAOUtilisateur", "RetirerCompteFollowing: Echec insertion enregistrement BDD avec = "+login );
				return -1;
			}
		}
		
		
		
		
		
		
		/**
		 * 
		 * @param login
		 * @return 1 si ok:-1 si pas ok
		 */
		public static int AjouterCompteFollower(String login){
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "AjouterCompteFollower: tentative insertion enregistrement pour compte avec login = "+login );
		        Connection maConnexion = DAO.getConnection();
		        PreparedStatement  st = maConnexion.prepareStatement("UPDATE t_compte SET Compte_Nb_Followers=Compte_Nb_Followers+1 WHERE Utilisateur_Id=?;");		            
		        st.setInt(1, idcomptebylogin(login));
		       
		        st.executeUpdate();
		        maConnexion.close();
		        DAO.getLog().envoyerlog(10, "DAOUtilisateur", "AjouterCompteFollower: Insertion r�ussie enregistrement pour compte avec login = "+login );
				return 1;
			}
			catch(SQLException e)
			{
				DAO.getLog().envoyerlog(2, "DAOUtilisateur", "AjouterCompteFollower: Echec insertion enregistrement BDD avec = "+login );
				return -1;
			}
		}
		
		
		/**
		 * 
		 * @param login
		 * @return 1 si ok/ -1 si pas ok
		 */
		public static int ajouterCompteTweet(String login){
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "ajouterCompteTweet: tentative insertion enregistrement pour compte avec login = "+login );
		        Connection maConnexion = DAO.getConnection();
		        PreparedStatement  st = maConnexion.prepareStatement("UPDATE t_compte SET Compte_Nb_Tweets=Compte_Nb_Tweets+1 WHERE Utilisateur_Id=?;");		            
		        st.setInt(1, idcomptebylogin(login));
		       
		        st.executeUpdate();
		        maConnexion.close();
		        DAO.getLog().envoyerlog(10, "DAOUtilisateur", "ajouterCompteTweet: Insertion r�ussie enregistrement pour compte avec login = "+login );
				return 1;
			}
			catch(SQLException e)
			{
				DAO.getLog().envoyerlog(2, "DAOUtilisateur", "ajouterCompteTweet: Echec insertion enregistrement BDD avec = "+login );
				return -1;
			}
		}
		
		
		
		
		
		
		
		
		/**
		 * 
		 * @param login
		 * @return 1 si ok/ -1 si pas ok
		 */
		public static int RetirerCompteFollower(String login){
			try{
				DAO.getLog().envoyerlog(10, "DAOUtilisateur", "RetirerCompteFollower: tentative insertion enregistrement pour compte avec login = "+login );
		        Connection maConnexion = DAO.getConnection();
		        PreparedStatement  st = maConnexion.prepareStatement("UPDATE t_compte SET Compte_Nb_Followers=Compte_Nb_Followers-1 WHERE Utilisateur_Id=?;");		            
		        st.setInt(1, idcomptebylogin(login));
		       
		        st.executeUpdate();
		        maConnexion.close();
		        DAO.getLog().envoyerlog(10, "DAOUtilisateur", "RetirerCompteFollower: Insertion r�ussie enregistrement pour compte avec login = "+login );
				return 1;
			}
			catch(SQLException e)
			{
				DAO.getLog().envoyerlog(2, "DAOUtilisateur", "RetirerCompteFollower: Echec insertion enregistrement BDD avec = "+login );
				return -1;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			/**
			 *ajouter un abonn� au compte
			 * @param followerlogin
			 * @param compteLogin
			 * @return 1 si ok/ -1 si pas ok
			 */
			public static int ajouterFollower(String followerlogin,String compteLogin){
				try{
					
					if((idcomptebylogin(followerlogin)==-1) && (idcomptebylogin(compteLogin)==-1))
					{
						DAO.getLog().envoyerlog(10, "DAOUtilisateur", "Ajouterfollowers:tentative insertion enregistrement followerslogin,comptelogin= "+followerlogin+compteLogin );
				        Connection maConnexion = DAO.getConnection();
				        PreparedStatement  st = maConnexion.prepareStatement("INSERT INTO T_FOLLOWERS(Follower_cle,Followers_Id, Followers_Login, Compte_Id ) VALUES (?,?,?,?);");		            
				        st.setString(1, idcomptebylogin(followerlogin)+","+idcomptebylogin(compteLogin));
				        st.setInt(2, idcomptebylogin(followerlogin));
				        st.setString(3, followerlogin);
				        st.setInt(4, idcomptebylogin(compteLogin));
				        st.executeUpdate();
				        maConnexion.close();
				        // y suit x , alors x est suivi y 
				        ajouterFollowing(followerlogin, compteLogin);
				        AjouterCompteFollower(compteLogin);
				        DAO.getLog().envoyerlog(10,"DAOUtilisateur", "Ajouterfollowers:tentative insertion enregistrement followerslogin,comptelogin= "+followerlogin+compteLogin  );
						return 1;
					}else{
						
						 DAO.getLog().envoyerlog(10,"DAOUtilisateur", "Ajouterfollowers:echec insertion enregistrement followerslogin,comptelogin= "+followerlogin+compteLogin +" inconnu" );
						return -1;
					}
					
				}
				catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOUtilisateur", "Ajouterfollowers:Echec insertion enregistrement followerslogin,comptelogin= "+followerlogin+compteLogin  );
					return -1;
				}
			}
			
			
			
			
			
			
			
			
			
			/**
			 * retirer un abonn� au compte
			 * @param CompteLogin
			 * @param followerLogin
			 * @return 1 si ok/ -1 si pas ok
			 */
			public static int retirerFollower(String CompteLogin,String followerLogin){
				try{
					DAO.getLog().envoyerlog(10, "DAOUtilisateur", "Retirerfollowers:tentative suppression enregistrement followerslogin,comptelogin= "+followerLogin+CompteLogin );
			        Connection maConnexion = DAO.getConnection();
			        PreparedStatement  st = maConnexion.prepareStatement("DELETE  FROM T_FOLLOWERS WHERE Followers_Id=? AND Compte_Id=?;");		            
			        st.setInt(1, idcomptebylogin(followerLogin));
			        st.setInt(2, idcomptebylogin(CompteLogin));
			        st.executeUpdate();
			        maConnexion.close();
			        // y suit x , alors x est suivi y 
			        retirerFollowing(followerLogin, CompteLogin); 
			        //maj des compteurs du compte
			        RetirerCompteFollower(CompteLogin);
			        DAO.getLog().envoyerlog(10,"DAOUtilisateur", "Retirerfollowers:tentative supression enregistrement followerslogin,comptelogin= "+followerLogin+CompteLogin  );
					return 1;
				}
				catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOUtilisateur", "Retirerfollowers:Echec supression enregistrement followerslogin,comptelogin= "+followerLogin+CompteLogin  );
					return -1;
				}
			}
			
			
			
			
			
			
			
			
			/**
			 * retirer un abandonnement 
			 * @param CompteLogin
			 * @param followingLogin
			 * @return 1 si ok/ -1 si pas ok
			 */
			public static int retirerFollowing(String CompteLogin,String followingLogin){
				try{
					DAO.getLog().envoyerlog(10, "DAOUtilisateur", "Retirerfollowers:tentative suppression enregistrement followingslogin,comptelogin= "+followingLogin+CompteLogin );
			        Connection maConnexion = DAO.getConnection();
			        PreparedStatement  st = maConnexion.prepareStatement("DELETE  FROM T_FOLLOWING WHERE Following_Id=? AND Compte_Id=?;");		            
			        st.setInt(1, idcomptebylogin(followingLogin));
			        st.setInt(2, idcomptebylogin(CompteLogin));
			        st.executeUpdate();
			        maConnexion.close();
			        RetirerCompteFollowing(CompteLogin);
			        retirerFollower(followingLogin, CompteLogin);
			        DAO.getLog().envoyerlog(10,"DAOUtilisateur", "Retirerfollowers:tentative supression enregistrement followingslogin,comptelogin= "+followingLogin+CompteLogin  );
					return 1;
				}
				catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOUtilisateur", "Retirerfollowers:Echec supression enregistrement followingslogin,comptelogin= "+followingLogin+CompteLogin  );
					return -1;
				}
			}
			/**
			 * ajouter un abonnement au compte
			 * @param followinglogin le compte de celui qu'in veut suivre
			 * @param comptelogin le compte en cours
			 * @return
			 */
			public static int ajouterFollowing(String followinglogin,String comptelogin){
				try{
					if((idcomptebylogin(followinglogin)==-1) && (idcomptebylogin(comptelogin)==-1))
					{
							DAO.getLog().envoyerlog(10, "DAOUtilisateur", "Ajouterfollowing:tentative insertion enregistrement followinglogin,comptelogin= "+followinglogin+comptelogin );
					        Connection maConnexion = DAO.getConnection();
					        PreparedStatement  st = maConnexion.prepareStatement("INSERT INTO T_FOLLOWING(Following_cle,Following_Id, Following_login, Compte_Id ) VALUES (?,?,?,?);");		            
					        st.setString(1, idcomptebylogin(followinglogin)+","+idcomptebylogin(comptelogin));
					        st.setInt(2, idcomptebylogin(followinglogin));
					        st.setString(3, followinglogin);
					        st.setInt(4, idcomptebylogin(comptelogin));  
					        st.executeUpdate();
					        maConnexion.close();
					        
					        
					        //maj des compteurs du compte
					        AjouterCompteFollowing(comptelogin);
					        ajouterFollower(comptelogin, followinglogin);
					        
					        DAO.getLog().envoyerlog(10,"DAOUtilisateur", "Ajouterfollowing:tentative insertion enregistrement followinglogin,comptelogin= "+followinglogin+comptelogin  );
							return 1;
					}else
					{
						DAO.getLog().envoyerlog(10,"DAOUtilisateur", "Ajouterfollowing:echec insertion enregistrement followinglogin,comptelogin= "+followinglogin+comptelogin +" inconnu"  );
						return -1;
					}
					
				}
				catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOUtilisateur", "Ajouterfollowing:Echec insertion enregistrement followinglogin,comptelogin= "+followinglogin+comptelogin  );
					return -1;
				}
			}
			
			
			
			
			
			
			
		
			
			
			
			
		
   
}
