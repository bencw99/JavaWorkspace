public class ArrayIndexOutOfBoundsExceptionThrown
{
	//An array of length 0 is created, and the 3rd element is attempted to be accessed
	//As a result, an out of bounds exception is thrown
	public static void main(String[]args) throws ArrayIndexOutOfBoundsException
	{
		int[]ints = {};
		int n = ints[2];
	}
}
