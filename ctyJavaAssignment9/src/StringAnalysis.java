
public class StringAnalysis {
	public static void main(String[]args)
	{
		String Letters = Input.getString("Please enter a string of words:");
		int x[] = new int[26];
		x = StringAnalysis(Letters);
		for(int j = 0; j<x.length; j++)
		{
			System.out.println(((char)(j+65)) + " appeared " + x[j] + " times.");
		}
	}
	public static int[] StringAnalysis(String s) 
	{
		int count[] = new int[26];
		s = s.toUpperCase();
		for(int j = 65; j <= 90; j++)
		{
			for(int i = 0; i< s.length(); i++)
			{ 
				if ((char)j == s.charAt(i))
				{
					count[j-65]++;
				}
			}
		}
		return count;
	}
}
