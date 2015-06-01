package models;

public class Hashtag {

	public int Hashtag_Id;
	public String Hashtag_Description;
	public int Tweet_id;
	
	
	public Hashtag(int idhashtag,String descHashtag,int idtweet)
	{
		this.Hashtag_Id=idhashtag;
		this.Hashtag_Description=descHashtag;
		this.Tweet_id=idtweet;
		
	}
	
}
