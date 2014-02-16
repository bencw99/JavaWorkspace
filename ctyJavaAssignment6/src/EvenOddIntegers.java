
public class EvenOddIntegers {
	public static void main(String [] args)
{
		int even = 0;
		int odd = 0;
		for( int x = 0 ; x <= 25 ; ++x )
		{
			if ( x % 2 == 0 )
			{
				even += x;
			}
			else
			{
				odd += x;
			}
		}
		System.out.println( " The sum of the even integers is " + even );
		System.out.println( " The sum of the odd integers is " + odd );
}
}
