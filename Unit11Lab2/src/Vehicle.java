//Vehicle extending all
public class Vehicle extends All
{
	//instance variables name and age
	private String name;
	private int age;
	//Constructor
	public Vehicle(String name, int age)
	{
		this.name = name;
		this.age = age;
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
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}
	//Implemented methods from All
	public void resizeObject() 
	{
		System.out.println("Resizing an Vehicle");
	}
	public void playSound() 
	{
		System.out.println("Vehicle sound");
	}
	public void rotateObject() 
	{
		System.out.println("Rotating an Vehicle");
	}
	public void drawObjects() 
	{
		System.out.println("Drawing an Vehicle");
	}
}
