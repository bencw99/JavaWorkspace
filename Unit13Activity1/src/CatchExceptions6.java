/*
 * This program allows a user to enter two integers to divide
 * If the denominator is zero, the program prompt the user to enter a new one until it is not zero
 * @ author Benjamin Cohen-Wang
 */
public class CatchExceptions6 
{
	public static void main(String[]args)
	{
		//User prompted for input
		int x = Input.getInt("Please enter the numerator");
		int y = Input.getInt("Please enter the denominator");
		//result declared and initialized
		int result = 0;
		//Sentinel set to false
		boolean ok = false;
		//Loop started
		while(!ok)
		{
			//Division attempted
			try
			{
				result = divide(x,y);
				ok = true;
			}
			//Catch block for division
			catch(DivideByZeroException e)
			{
				y = Input.getInt("Denominator must be nonzero ... enter again");
			}
		}
		//Result printed out
		System.out.println("Result: " + result);
	}
	//Divides two arguments, exception thrown if divide by zero exception found and arithmetic exceptioncaught
	public static int divide( int x, int y) throws DivideByZeroException 
	{
		int result = 0;
		try
		{
			result = x/y;
		}
		catch(ArithmeticException e)
		{
			throw new DivideByZeroException();
		}
		return result;
	}
}
