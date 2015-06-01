package models.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class T_Compte extends Model{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	@Required
	@Column(name = "Compte_Login")
	public String Compte_Login;
	@Required
	@Column(name = "Compte_Mdp")
	public String Compte_Mdp;
	@Column(name = "Compte_Date_Crea")
	public Date Compte_Date_Crea;
	@Column(name = "Compte_Email")
	public String Compte_Description;
	@Column(name = "Compte_Nb_Tweets")
	public int Compte_Nb_Tweets;
	@Column(name = "Compte_Nb_Followers")
	public int Compte_Nb_Followers;
	@Column(name = "Compte_Nb_Following")
	public int Compte_Nb_Following;
	@Column(name = "Compte_Mention")
	public String Compte_Mention;
	@Column(name = "Compte_Image")
	public String Compte_Image;
	@OneToMany
	public List<T_Followers> Followers=new ArrayList<T_Followers>();
	@OneToMany
	public List<T_Following> Followings= new ArrayList<T_Following>();


	public T_Compte(int id, String compte_Login, String compte_Mdp,
			Date compte_Date_Crea, String compte_Description,
			int compte_Nb_Tweets, int compte_Nb_Followers,
			int compte_Nb_Following, String compte_Mention, String compte_Image) {
		super();
		this.id = id;
		Compte_Login = compte_Login;
		Compte_Mdp = compte_Mdp;
		Compte_Date_Crea = compte_Date_Crea;
		Compte_Description = compte_Description;
		Compte_Nb_Tweets = compte_Nb_Tweets;
		Compte_Nb_Followers = compte_Nb_Followers;
		Compte_Nb_Following = compte_Nb_Following;
		Compte_Mention = compte_Mention;
		Compte_Image = compte_Image;
	}
	
	public static Finder<Integer, T_Compte> find
    = new Model.Finder<>(Integer.class, T_Compte.class);
    
    public static List<T_Compte> obtenirComptes(){
    	List<T_Compte> comptes = T_Compte.find.all();
    	return comptes;
    }
    
    public static T_Compte obtenirCompte(int id){
    	T_Compte compte = T_Compte.find.byId(id);
    	return compte;
    }
    
    public void ajouterCompte(T_Compte cpt){
    	cpt.save();
    }
    
    public void supprimerCompte(T_Compte cpt){
    	cpt.delete(); 
    }
    
    public static T_Compte authentification (String compte_Login, String Compte_Mdp) {
        return find.where().eq("compte_Login", compte_Login	)
            .eq("Compte_Mdp", Compte_Mdp).findUnique();
    }
    
    public void ajouterFollower(T_Followers follower){
    	this.Followers.add(follower);
    	this.save();
    }
    public void ajouterFollowing(T_Following following){
    	this.Followings.add(following);
    	this.save();
    }
}
