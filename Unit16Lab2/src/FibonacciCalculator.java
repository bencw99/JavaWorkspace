
public class FibonacciCalculator
{
	private static long calls = 0;
	//Main method tests fibonacci method and calculates time taken
	public static void main(String[]args)
	{
		long timeDif;
		long initTime;
		long endTime;
		initTime = System.currentTimeMillis();
		long fib30 = fibonacci(30);
		endTime = System.currentTimeMillis();
		timeDif = endTime - initTime;
		System.out.println("Thirtieth Fibonacci Number is " + fib30); // Should print 832040
		System.out.println("Calculated in " + ((double)timeDif)/1000 + " seconds");
		System.out.println("fibonacci called " + calls + " times");
	}
	//Recursive method taking int n and returning n'th fibonacci number
	public static long fibonacci(int n)
	{
		calls ++;
		if(n == 0)
		{
			return 0;
		}
		if(n == 1)
		{
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
}
