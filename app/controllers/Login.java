package controllers;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import classycle.dependency.Result;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import models.utilisateur.DAOUtilisateur;

public class Login extends Controller {
	
	
	/*
	 * Récupère la requete Json, la convertie en java
	 * Récupère l'id de l'utilisateur s'il existe, -1 sinon
	 * Renvoie l'id ou le -1 en Json
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result authentif(){
		JsonNode json = request().body().asJson();
		String login = json.findPath("login").textValue();
		String mdp = json.findPath("mdp").textValue();
		int id = DAOUtilisateur.verifUtilisateur(login, mdp);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(id);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}
	@BodyParser.Of(BodyParser.Json.class)
	public Result authentif(String login,String mdp){
		JsonNode json = request().body().asJson();
		//String login = json.findPath("login").textValue();
		//String mdp = json.findPath("mdp").textValue();
		int id = DAOUtilisateur.verifUtilisateur(login, mdp);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(id);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}
	/*
	 * Récupère la requete Json, la convertie en java
	 * Inscrit l'utilisateur et renvoie son id, -1 sinon
	 * Renvoie l'id ou le -1 en Json
	 */
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result inscription(){
		JsonNode json = request().body().asJson();
		String login = json.findPath("login").textValue();
		String mdp = json.findPath("mdp").textValue();
		String email = json.findPath("email").textValue();

		int id = DAOUtilisateur.creerCompte(login,mdp,email);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(id);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}
	@BodyParser.Of(BodyParser.Json.class)
	public Result inscription(String login,String mdp,String email){
		JsonNode json = request().body().asJson();
		//String login = json.findPath("login").textValue();
		//String mdp = json.findPath("mdp").textValue();
		//String email = json.findPath("email").textValue();

		int id = DAOUtilisateur.creerCompte(login,mdp,email);
		try{
			final ObjectMapper mapper = new ObjectMapper();
			String r = mapper.writeValueAsString(id);
			JsonNode result = Json.parse(r);
			return (Result) ok(result);
		}
		catch(IOException e){
			e.printStackTrace();
			return (Result) internalServerError(e.toString());
		}
	}

}