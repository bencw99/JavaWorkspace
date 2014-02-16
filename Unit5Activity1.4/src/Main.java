import java.util.Random;
public class Main 
{
	public static void main(String[]args)
	{
		Random random = new Random();
		random.setSeed(5);
		int total = 0;
		int six = 0;
		int ten = 0;
		int fourteen = 0;
		int twentyTwo = 0;
		int eighteen = 0;
		while(total < 100)
		{
			int n = random.nextInt(23);
			if(n == 6)
			{
				six++;
				total++;
			}
			if(n == 10)
			{
				ten++;
				total++;
			}
			if(n == 14)
			{
				fourteen++;
				total++;
			}
			if(n == 18)
			{
				eighteen++;
				total++;
			}
			if(n == 22)
			{
				twentyTwo++;
				total++;
			}
		}
		System.out.println("The numer of six's was " + six );
		System.out.println("The numer of ten's was " + ten );
		System.out.println("The numer of fourteen's was " + fourteen );
		System.out.println("The numer of eighteen's was " + eighteen );
		System.out.println("The numer of twenty two's was " + twentyTwo );
	}
}
