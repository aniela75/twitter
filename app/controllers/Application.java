package controllers;

import models.utilisateur.DAOUtilisateur;
import models.utilisateur.DAOinterface;
import play.mvc.*;

public class Application extends Controller {

  public static Result index()  {
	  //DAOinterface dao = (DAOinterface) new DAO();
	//  dao.
    return ok(views.html.main.render());
  }

}
