package models.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOHashtag extends DAO {
	
	public static void main(String[] args) {
		
		
	}
	public DAOHashtag()
	{
		super();
	}
	/**
	 * permet d'ajouter les hashtag et les associer aux tweet
	 * @param IdTweet
	 * @param strtweet
	 * @return 1 si ok/-1 ou 0 si pas ok
	 */
	public static int ajouterHashtag(int IdTweet,String strtweet){
		
		int notif=0;
		
		//on d�termine la premiere position du #
		int debuthasthtag=strtweet.indexOf("#"); 
		//on redefinit la string � analyser 
		String strhashtag=strtweet.substring(debuthasthtag, strtweet.length());
		 
		//on d�termine la fin du hashtag
		int finhashtag=strhashtag.indexOf(" ");
		//on redefinit la string � analyser
		String finalhashtag=strhashtag.substring(1, finhashtag);
		
		//on stocke les hashtag dans un tableau
		String Tableau[] = finalhashtag.split("#"); 
		for(int i = 0; i < Tableau.length; i++)
		{
			//insertion en base donn�es
			String Hashtag=Tableau[i];
			try{
				DAO.getLog().envoyerlog(10, "DAOHashtag", "ajouterHashTag:Tentative insertion enregistrement BDD avec hashtag:"+Hashtag +"idtwwet:" +IdTweet);
				Connection maConnexion = DAO.getConnection();
				PreparedStatement  st = maConnexion.prepareStatement("INSERT INTO T_HASHTAG(Hashtag_Description, Tweet_Id) VALUES (?,?);");           
				st.setString(1, Hashtag);
				st.setInt(2, IdTweet);
				st.executeUpdate();
				maConnexion.close();
				DAO.getLog().envoyerlog(10, "DAOHashtag", "ajouterHashTag::R�ussite insertion enregistrement BDD avec Login:"+Hashtag+"idtwwet:" +IdTweet);
				 notif=1;
        	
				}
			catch(SQLException e)
				{
					DAO.getLog().envoyerlog(2, "DAOHashtag", "ajouterHashTag::Echec insertion enregistrement BDD avec Login:"+Hashtag+"idtwwet:" +IdTweet);
					notif= -1;
				}
		  
		}
		
		
			return notif;
			
	
			
	}
	/**
	 * permet de retourner un tableau de int contenant 20 tweet pour un hashtag 
	 * @param hashtag
	 * @return une liste de idtweet//null si ne marche pas 
	 */
	public static int[] recupIdtweet(String hashtag){
		int i=0;
		int [] tableauidtwwet=null;
		tableauidtwwet = new int [30];
		try{
			
			DAO.getLog().envoyerlog(10, "DAOHashtag", "recupIdtweet:Tentative recuperation listye idtwwet  bdd avec hashtag = "+hashtag );
	        Connection maConnexion =DAO.getConnection();
	        PreparedStatement  st = maConnexion.prepareStatement("SELECT Tweet_Id FROM T_HASHTAG WHERE Hashtag_Description =?;");  
	        
	  
	        st.setString(1, hashtag);
	      
	        ResultSet rs = st.executeQuery();
	        System.out.println("rs = " +rs);
	       
	        while(rs.next())
	        {
	        	 int id=rs.getInt(1);
	        	 System.out.println(i +"  "+ id);
	        	 tableauidtwwet[i]=id;
	        	 i++;
	        	 DAO.getLog().envoyerlog(10,"DAOHashtag", "recupIdtweet: recuperation listye idtwwet  bdd avec hashtag = "+hashtag );
	        	        	 
	        }
	        maConnexion.close();
	       
			}
			catch(SQLException e)
			{
				DAO.getLog().envoyerlog(8, "DAOHashtag", "recupIdtweet:Echec recuperation listye idtwwet  bdd avec hashtag = "+hashtag ); 
				
				
			}
		return tableauidtwwet;
	}
		

}
