//This program calls setNum with an argument that is less than 0, so an IllegalArgumentException is thrown
public class IllegalArgumentExceptionThrown 
{
	public static void main(String[] args) throws IllegalArgumentException
	{
		setNum(-1);
	}
	//Sets num to argument if num is greater than or equal to zero
	public static void setNum(int n)
	{
		if(n < 0)
		{
			throw new IllegalArgumentException();
		}
		int num = n;
	}
}
