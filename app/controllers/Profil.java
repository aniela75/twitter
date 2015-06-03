package controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Compte;
import classycle.dependency.Result;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import models.utilisateur.DAOUtilisateur;
import models.utilisateur.DAOFollow;

public class Profil extends Controller {
	
	public Result getFollowings(int id){
		List<Compte> l = DAOFollow.listFollowing(id);
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
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result getFollowers(int id){
		List<Compte> l = DAOFollow.listFollower(id);
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
}
