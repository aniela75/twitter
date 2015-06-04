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
import models.utilisateur.DAOFollow;

/**
 * 
 * @author Mohamed
 *
 */
public class PropositionAmis extends Controller {
	
	
	/**
	 * permet de recuperer la liste des propositions d'amis
	 * @param id
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result getTweet(int id){
		List<Compte> amispossible = models.utilisateur.DAOFollow.propositioncompte(id);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(amispossible);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}

}
