package models.model;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;


@Entity
public class T_Tweet extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int  Tweet_Id;
	@Column(name = "Compte_Id")
	public int  Compte_Id;	
	@Column(name = "Tweet_Date")
	public Date  Tweet_Date;
	@Column(name = "Tweet_Nb_retweet")
	public int  Tweet_Nb_retweet;
	@Column(name = "Tweet_Description")
	public String  Tweet_Description;
	
	public T_Tweet(int tweet_Id, int compte_Id, Date tweet_Date,
			int tweet_Nb_retweet, String tweet_Description) {
		super();
		Tweet_Id = tweet_Id;
		Compte_Id = compte_Id;
		Tweet_Date = tweet_Date;
		Tweet_Nb_retweet = tweet_Nb_retweet;
		Tweet_Description = tweet_Description;
	}
	
	public static Finder<Integer, T_Tweet> find
    = new Model.Finder<>(Integer.class, T_Tweet.class);
    
    public static List<T_Tweet> obtenirTweets(){
    	List<T_Tweet> tweets = T_Tweet.find.all();
    	return tweets;
    }
    
    public static T_Tweet obtenirTweet(int id){
    	T_Tweet tweet = T_Tweet.find.byId(id);
    	return tweet;
    }
    
	
	
}