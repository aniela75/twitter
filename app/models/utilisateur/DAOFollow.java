package models.utilisateur;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Compte;

  


public class DAOFollow extends DAO{

	/**
	 * return les followers d'un compte
	 * @param idcompte
	 * @return
	 */
	public static List<Compte> listFollower(int idcompte)
	{
			List<Compte> maliste =new ArrayList<Compte>(); 
		
				try{
					
					
			        Connection maConnexion =DAO.getConnection();
			        PreparedStatement  st = maConnexion.prepareStatement("SELECT Followers_Id FROM T_FOLLOWERS WHERE Compte_Id =?;");  
			        st.setInt(1, idcompte);
			      
			        ResultSet rs = st.executeQuery();
			        System.out.println("rs = " +rs);
			       
			        while(rs.next())
			        {
			        	
			        	
			        	 int id=rs.getInt(1);
			        	 Compte followers= DAOUtilisateur.comptebyid(id);
			        	 maliste.add(followers);
			        	
			        	 
			        	        	 
			        }
			        maConnexion.close();
			        return maliste;
					}
					catch(SQLException e)
					{
						
						
						
					}
					return maliste;
	}
	
	
	/**
	 * return les followings d'un compte
	 * @param idcompte
	 * @return
	 */
	public static List<Compte> listFollowing(int idcompte)
	{
			List<Compte> maliste =new ArrayList<Compte>(); 
		
				try{
					
					
			        Connection maConnexion =DAO.getConnection();
			        PreparedStatement  st = maConnexion.prepareStatement("SELECT Following_Id FROM T_FOLLOWING WHERE Compte_Id =?;");  
			        st.setInt(1, idcompte);
			      
			        ResultSet rs = st.executeQuery();
			        
			       
			        while(rs.next())
			        {
			        	
			        	
			        	 int id=rs.getInt(1);
			        	 Compte following= DAOUtilisateur.comptebyid(id);
			        	 maliste.add(following);
			        	
			        	 
			        	        	 
			        }
			        maConnexion.close();
			        return maliste;
					}
					catch(SQLException e)
					{
						
						
						
					}
					return maliste;
	}
	
	/**
	 * Pemet de faire la liste de tous les comptes amis de mes amis 
	 * @param idcompte
	 * @return
	 */
	public static List<Compte> propositioncompte(int idcompte)
	{	
		List<Compte> maliste =new ArrayList<Compte>(); 
		
		try{
			
			
	        Connection maConnexion =DAO.getConnection();
	        PreparedStatement  st = maConnexion.prepareStatement("SELECT Followers_Id FROM T_FOLLOWERS WHERE Compte_Id =?;");  
	        st.setInt(1, idcompte);
	      
	        ResultSet rs = st.executeQuery();
	        
	       
	        while(rs.next())
	        {
	        	
	        	
	        	 int id=rs.getInt(1);
	        	 try{
	     			
	     			
	        		 Connection maConnexion2 =DAO.getConnection();
	     	        PreparedStatement  st2 = maConnexion2.prepareStatement("SELECT Followers_Id FROM T_FOLLOWERS WHERE Compte_Id =?;");  
	     	        st.setInt(1, id);
	     	      
	     	        ResultSet rs2 = st2.executeQuery();
	     	        
	     	       
	     	        while(rs2.next())
	     	        {
	     	        	
	     	        	
	     	        	 int id2=rs2.getInt(1);
	     	        	Compte followerspotentielle= DAOUtilisateur.comptebyid(id2);
	     	        	maliste.add(followerspotentielle);
	     	        	
	     	        	 
	     	        	        	 
	     	        }
	     	        maConnexion.close();
	     	        return maliste;
	     			}
	     			catch(SQLException e)
	     			{
	     				
	     				
	     				
	     			}
	     			return maliste;
	        	
	        	 
	        	        	 
	        }
	        maConnexion.close();
	        return maliste;
			}
			catch(SQLException e)
			{
				
				
				
			}
			return maliste;
		
	}
	}

