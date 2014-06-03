//A reference to a null String object is created and used, throwing an exception
public class NullPointerExceptionThrown 
{
	public static void main(String[]args) throws NullPointerException
	{
		String string = null;
		string.length();
	}
}
