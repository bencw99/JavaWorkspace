
public class ComputerGuesser {
	public static void main (String [] args)
	{
		int guessrange = 1000;
		System.out.println("Think of a number between 0 and " + guessrange);
		String A = "more";
		int numguess = 0;
		int guess;
		int x = 0;
		for ( guess = guessrange/2 , guessrange /= 2 ; A.equals("correct")==false ; )
		{
			A = Input.getString("Is your number " + guess + "? If it is, write correct. If it is higher write more. If it is lower write less");
			numguess++;
			if (guessrange % 2 == 0 || x == 1)
			{
				x = 0;
			}
			if (guessrange % 2 == 1)
			{
				x = 1;
			}
			guessrange = (guessrange / 2) + x;
			if( A.equals("more"))
			{
				guess += guessrange;
			}
			if( A.equals("less"))
			{
				guess -= guessrange;
			}
		}
		System.out.println("So, your number was " + guess + ". It took me " + numguess + " tries to guess.");
}
}
