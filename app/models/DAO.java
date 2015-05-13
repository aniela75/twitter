package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.outils.Log;


		//Step 1: Use interfaces from java.sql package
	/**
	 * Permet de se connecter � la BDD
	 * @author Mohamed
	 *
	 */
	public class DAO {
		    //static reference to itself
		    private static DAO instance = new DAO();
		    private Connection connection = null;
		    private static final String URL = "jdbc:mysql://localhost/twitter";
		    private static final String USER = "root";
		    private static final String PASSWORD = "";
		    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		    private static Log log = null;
		    
		    //private constructor
		    public DAO(){
		        try {
		            
		        	
		            Class.forName(DRIVER_CLASS);
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }
		    }
		    public static Log getLog(){
				if(log == null){
					SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
					log = new Log("BaseDeDonnees_"+ dateFormat.format(new Date()));
				}
				return log;
			}
		    private Connection createConnection() {	 
		        
		        try {
		            getLog().envoyerlog(10, "DAO", "tentative de connexion� la base de donn�es" );
		            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
		            getLog().envoyerlog(10, "DAO", "Connect� � la base de donn�es" );
		        } catch (SQLException e) {
		        	
		        	getLog().envoyerlog(0, "DAO", "Echec connexion base de donn�es" );
		           // System.out.println("ERROR: Unable to Connect to Database.");
		        }
		        return this.connection;
		    }  
		 
		    public static Connection getConnection() {
		        return instance.createConnection();
		    }

			//C:\Program Files (x86)\MySQL\MySQL Connector J\mysql-connector-java-5.1.30-bin.jar
		    

		}   