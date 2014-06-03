//Song class
public class Song 
{
	//Instance variables for name, artist and price
	private String title;
	private String artist;
	private double price;
	
	//Constructor initializing instance variables
	public Song(String title, String artist, double price) 
	{
		this.title = title;
		this.artist = artist;
		this.price = price;
	}
	
	//Overriding of toString
	public String toString()
	{
		return title;
	}
	
	//method returning info asString, instance must be initialized
	public String getInfo()
	{
		return title + " " + artist + " $ " + Math.floor(100*price)/100;
	}
	
	//Getters for title, artist, and price, instance must be initialized
	public String getTitle() 
	{
		return title;
	}

	public String getArtist() 
	{
		return artist;
	}

	public double getPrice() 
	{
		return price;
	}
}
