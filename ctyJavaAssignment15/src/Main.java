import java.util.Random;
public class Main 
{
	public static void main(String[]args)
	{
		Random r = new Random();
		soundmaker animals[] = new soundmaker[4];
		animals[0] = new chihuahua();
		animals[1] = new poodle();
		animals[2] = new bluebird();
		animals[3] = new robin();
		for(int x = 0; x < 10; x++)
		{
			(animals[r.nextInt(4)]).makeSound();
		}
	}
}
