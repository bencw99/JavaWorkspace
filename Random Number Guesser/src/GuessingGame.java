
public class GuessingGame {
	public static void main(String [] args)
{
		int z = 1;
		int x = (int) (Math.random()*1000);
		int y = Input.getInt("I am between 0 and 1000. Guess who I am!");
		while (y != x)
		{
			if ( y < x )
			{
				y = Input.getInt("I am higher!");
				z++;
			}
			if ( y > x )
			{
				y = Input.getInt("I am lower!");
				z++;
			}	
		}
		System.out.println("You guessed correctly. I am " + x);
		System.out.println( "It took you " + z + " tries to guess! ");
}
}	
