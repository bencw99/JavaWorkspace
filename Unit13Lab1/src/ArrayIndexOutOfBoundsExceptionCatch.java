
public class ArrayIndexOutOfBoundsExceptionCatch 
{
	//An array of length 0 is created, and the 3rd element is attempted to be accessed
	//As a result, an out of bounds exception is thrown
	public static void main(String[]args) throws ArrayIndexOutOfBoundsException
	{
		try
		{
			int[]ints = {};
			int n = ints[2];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("ArrayIndexOutOfBoundsException caught");
			System.out.println("An element of an array was accessed with an index that was not part of the array");
		}
	}
}
