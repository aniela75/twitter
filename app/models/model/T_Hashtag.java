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
public class T_Hashtag extends Model{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int Hashtag_Id;
	@Column(name = "Hashtag_Description")
	public String  Hashtag_Description;
	@Column(name = "Tweet_Id")
	public int Tweet_Id;
	
	public T_Hashtag(int hashtag_Id, String hashtag_Description, int tweet_Id) {
		super();
		Hashtag_Id = hashtag_Id;
		Hashtag_Description = hashtag_Description;
		Tweet_Id = tweet_Id;
	}
	
	public static Finder<Integer, T_Hashtag> find
    = new Model.Finder<>(Integer.class, T_Hashtag.class);
    
    public static List<T_Hashtag> obtenirHashtags(){
    	List<T_Hashtag> hashtags = T_Hashtag.find.all();
    	return hashtags;
    }
    
    public static T_Hashtag obtenirHashtag(int id){
    	T_Hashtag hashtag = T_Hashtag.find.byId(id);
    	return hashtag;
    }
	
	
}