package models;


public class Tweet {
	
	public int tweet_id;
	public int compte_id;
	public  java.sql.Timestamp datetweet;
	public int tweet_nb_retweet;
	public String tweet_description;
	
	public Tweet(int idtweet,int idcompte,java.sql.Timestamp datetweet,int nbretweet,String strtweet)
	{
		this.tweet_id=idtweet;
		this.compte_id=idcompte;
		this.datetweet=datetweet;
		this.tweet_nb_retweet=nbretweet;
		this.tweet_description=strtweet;
	}
	public Tweet(int idtweet,int nbretweet,String strtweet)
	{
		this.tweet_id=idtweet;
		this.tweet_nb_retweet=nbretweet;
		this.tweet_description=strtweet;
	}

}
