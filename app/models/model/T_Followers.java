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
public class T_Followers extends Model{


	private static final long serialVersionUID = 1L;

	@Column(name = "Followers_cle")
	public String  Followers_cle;
	@Column(name = "Followers_Id")
	public int  Followers_Id;
	@Column(name = "Followers_Login")
	public String  Followers_Login;
	@Column(name = "Compte_Id")
	public int Compte_Id_;
	
	public T_Followers(String followers_cle, int followers_Id,
			String followers_Login, int compte_Id_) {
		super();
		Followers_cle = followers_cle;
		Followers_Id = followers_Id;
		Followers_Login = followers_Login;
		Compte_Id_ = compte_Id_;
	}
	
	public static Finder<String, T_Followers> find
    = new Model.Finder<>(String.class, T_Followers.class);
    
    public static List<T_Followers> obtenirFollowers(){
    	List<T_Followers> followers = T_Followers.find.all();
    	return followers;
    }
    
    public static T_Followers obtenirFollower(String cle){
    	T_Followers follow = T_Followers.find.byId(cle);
    	return follow;
    }
}

