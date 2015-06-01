package models.model;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class T_Following extends Model{

	private static final long serialVersionUID = 1L;

	@Column(name = "Follwing_cle")
	public String  Follwing_cle;
	@Column(name = "Follwing_Id")
	public int  Follwing_Id;
	@Column(name = "Follwing_Login")
	public String  Follwing_Login;
	@Column(name = "Compte_Id")
	public int Compte_Id_;
	
	public T_Following(String follwing_cle, int follwing_Id,
			String follwing_Login, int compte_Id_) {
		super();
		Follwing_cle = follwing_cle;
		Follwing_Id = follwing_Id;
		Follwing_Login = follwing_Login;
		Compte_Id_ = compte_Id_;
	}
	
	public static Finder<String, T_Following> find
    = new Model.Finder<>(String.class, T_Following.class);
    
    public static List<T_Following> obtenirFollowings(){
    	List<T_Following> followings = T_Following.find.all();
    	return followings;
    }
    
    public static T_Following obtenirFollowing(String cle){
    	T_Following follow = T_Following.find.byId(cle);
    	return follow;
    }
	
}