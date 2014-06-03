//A reference to a null String object is created and used, throwing an exception
public class NullPointerExceptionCatch 
{
	public static void main(String[]args) throws NullPointerException
	{
		try
		{
			String string = null;
			string.length();
		}
		catch(NullPointerException e)
		{
			System.out.println("NullPointerException occured");
			System.out.println("A reference to an object that has not yet been initialized has been used");
		}
	}
}
