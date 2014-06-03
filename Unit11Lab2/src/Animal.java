//Animal extending all
public class Animal extends All
{
	//Instance variable name
	private String name;
	//Constructor
	public Animal(String name)
	{
		this.name = name;
	}
	//Setters and getters
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	//Implemented methods from All
	public void resizeObject() 
	{
		System.out.println("Resizing an Animal");
	}
	public void playSound() 
	{
		System.out.println("Animal sound");
	}
	public void rotateObject() 
	{
		System.out.println("Rotating an Animal");
	}
	public void drawObjects() 
	{
		System.out.println("Drawing an Animal");
	}
}
