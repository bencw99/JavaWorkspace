//Animal is parent class of Dog and Cat. Animal reference to a Dog is made, and is attempted to be cast to a cat, throwing an exception
public class ClassCastExceptionCatch 
{
	//Creates an Animal that is a Dog instance, and casts to a Cat
	public static void main(String[]args) throws ClassCastException
	{
		try
		{
			Animal animal = new Dog();
			Cat cat = (Cat)(animal);
		}
		catch(ClassCastException e)
		{
			System.out.println("ClassCastException caught");
			System.out.println("A cast was made on a refernce that was not an instance of the object casted to");
		}
	}
	private abstract static class Animal
	{
		
	}
	private static class Cat extends Animal
	{
		
	}
	private static class Dog extends Animal
	{
		
	}
}