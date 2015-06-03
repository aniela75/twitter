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
	        int id = DAOUtilisateur.verifUtilisateur("", "");
	        Assert.assertNotNull(id);

	        boolean matches = Hash.checkPassword("fooTest", id);

	        Assert.assertTrue("Password does not match but should match", matches);

	        boolean badLogin = Hash.checkPassword("badPassword", password);

	        Assert.assertFalse("Password matches but should not match", badPassword);
	    }
    }
}
