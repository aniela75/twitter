package controllers;


import java.io.IOException;

import models.utilisateur.DAOUtilisateur;
import models.utils.AppException;
import controllers.Login;

import org.junit.Assert;
import org.junit.Test;

import play.libs.Json;
import classycle.dependency.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 */
public class LoginTest {

	 @Test
	    public void getHashString() throws AppException {
	        int id = DAOUtilisateur.verifUtilisateur("toto", "mdp");
	        Assert.assertNotNull(id);

	        boolean matches = checkId(2, id);

	        Assert.assertTrue("Id does not match but should match", matches);

	        int badId = DAOUtilisateur.verifUtilisateur("toto", "badPassword");
	        boolean badLogin = checkId(-1, badId);

	        Assert.assertFalse("Password matches but should not match", badLogin);
	    }
	 
	 public boolean checkId(int rId, int tId){
		 return rId==tId;
    }
}
