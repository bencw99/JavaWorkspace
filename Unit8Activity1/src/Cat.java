public class Cat 
{
	private String breed; 
	private int age; 
	private double weight; 
	private char sex; 
	private String name;
	public String eat() 
	{
		return "Munch, Munch, Munch"; 
	}
	public String meow() 
	{
		return "Meeeeeooww";
	}
	public String getBreed() 
	{
		return breed;
	}
	public void setBreed(String breed) 
	{
		this.breed = breed;
	}
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}
	public double getWeight() 
	{
		return weight;
	}
	public void setWeight(double weight) 
	{
		this.weight = weight;
	}
	public char getSex() 
	{
		return sex;
	}
	public void setSex(char sex) 
	{
		this.sex = sex;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
