package controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Compte;
import models.Tweet;
import classycle.dependency.Result;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import models.utilisateur.DAOUtilisateur;

/**
 * 
 * @author Mohamed
 *
 */
public class Home extends Controller{
	
	
	/**
	 * permet de recuperer la liste des derniers tweets des personnes que l'on suit
	 * @param id
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result getTweet(int id){
		List<Tweet> l = models.utilisateur.DAOUtilisateur.listedesderniertwitte(id);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(l);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}
	/**
	 * Permet de retourner une liste de tweet � partir d'une recherche hashtag exemple:"vacance"
	 * @param id
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result getTweetbyhashtag(String hashtagtxt){
		List<Tweet> l = models.utilisateur.DAOHashtag.recuptweet(hashtagtxt);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(l);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}
	/**
	 * permet de rechercher un profil 
	 * @param idcompterecherche
	 * @return un compte
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result getComptebyId(String loginprofil){
		Compte Comptetrouve=models.utilisateur.DAOUtilisateur.comptebyid(models.utilisateur.DAOUtilisateur.idcomptebylogin(loginprofil));
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString( Comptetrouve);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}
}
