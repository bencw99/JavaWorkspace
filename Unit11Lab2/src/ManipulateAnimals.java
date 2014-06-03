//Test program for an array with 2 Vehicles and 2 Animals
public class ManipulateAnimals
{
	public static void main(String[]args)
	{
		All[]objects = {new Vehicle("Honda",16), new Animal("Cat"), new Vehicle("Toyota",9), new Animal("Dog")};
		for(All object: objects)
		{
			object.drawObjects();
			object.playSound();
			object.resizeObject();
			object.rotateObject();
		}
	}
}
