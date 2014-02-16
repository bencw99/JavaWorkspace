
public class RandomArray {
	public static void main(String [] args)
	{
		int random[] = new int [100];
		for (int j = 0 ; j <= 99 ; j++)
		{
			random[j] = randomize();
		}
		int even[] = even(random);
		int odd[] = odd(random);
		System.out.println("The even array is: ");
		for (int i=0; i<even.length; i++)
		{
			System.out.println( even[i] );
		}
		System.out.println("The odd array is: ");
		for (int a=0; a<odd.length; a++)
		{
			System.out.println( odd[a] );
		}
	}
	public static int randomize()
	{
		int x = (int) (Math.random()*26);
		return x;
	}
	public static int[] even( int both[] )
	{
		int n = 0;
		int j;
		for (j = 0 ; j <= 99 ; j++ )
		{
			if ( both[j] % 2 == 0 )
			{
				n++;
			}
		}
		int even[] = new int[n];
		int m;
		for (j = 0, m = 0 ; j <= 99 ; j++)
		{
			if ( both[j] % 2 == 0 )
			{
				even[m] = both[j];
				m++;
			}
		}
		return even ;
	}
	public static int[] odd( int both[] )
	{
		int n = 0;
		int j;
		for (j = 0 ; j <= 99 ; j++ )
		{
			if ( both[j] % 2 == 1 )
			{
				n++;
			}
		}
		int odd[] = new int[n];
		int m;
		for (j = 0, m = 0 ; j <= 99 ; j++)
		{
			if ( both[j] % 2 == 1 )
			{
				odd[m] = both[j];
				m++;
			}
		}
		return odd ;
	}
}
