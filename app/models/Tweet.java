package models;


public class Tweet {
	
	public int tweet_id;
	public int compte_id;
	public int tweet_nb_retweet;
	public String tweet_description;
	
	public Tweet(int idtweet,int idcompte,int nbretweet,String strtweet)
	{
		this.tweet_id=idtweet;
		this.compte_id=idcompte;
		this.tweet_nb_retweet=nbretweet;
		this.tweet_description=strtweet;
	}

}
