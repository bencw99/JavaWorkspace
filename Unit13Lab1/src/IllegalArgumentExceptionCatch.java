//This program calls setNum with an argument that is less than 0, so an IllegalArgumentException is thrown
public class IllegalArgumentExceptionCatch 
{
	static int num;
	public static void main(String[] args) throws IllegalArgumentException
	{
		setNum(-1);
	}
	//Sets num to argument if num is greater than or equal to zero
	public static void setNum(int n)
	{
		try
		{
			if(n < 0)
			{
				throw new IllegalArgumentException();
			}
			else
			{
				num = n;
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("IllegalArgumentException caught");
			System.out.println("An inappropriate argument was passed to a method");
		}
	}
}
