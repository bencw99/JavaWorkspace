
public class Palindromes
{
	//main method tests testPalindrome with several cases
	public static void main(String[]args)
	{
		System.out.println(testPalindrome("radar")); //Should print true
		System.out.println(testPalindrome("able was i ere i saw elba....")); //Should print true
		System.out.println(testPalindrome("Hello")); //Should print false
		System.out.println(testPalindrome("Goodbye")); //Should print false
	}
	//Takes String as argument
	//Returns true if is palindrome and false else
	public static boolean testPalindrome(String string)
	{
		if(string.length() == 0 || string.length() == 1)
		{
			return true;
		}
		int lower = 0;
		int upper = string.length() - 1;
		while(isPunctuation(string.charAt(lower)))
		{
			lower ++;
		}
		while(isPunctuation(string.charAt(upper)))
		{
			upper --;
		}
		if(string.charAt(lower) == string.charAt(upper))
		{
			return testPalindrome(string.substring(lower + 1, upper));
		}
		else
		{
			return false;
		}
	}
	//Takes char as argument
	//returns true if punctuation and false else
	public static boolean isPunctuation(char c)
	{
		int code = (int)(c);
		if(((code <= 90) && (code >= 65)) || ((code <= 122) && (code >= 97)) || ((code <= 57) && (code >= 48)))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
