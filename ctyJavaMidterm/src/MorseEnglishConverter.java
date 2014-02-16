
public class MorseEnglishConverter {
	public static void main(String[]args)
	{
		String Decision = Input.getString("Enter morse if you would like to decode morse or english if you would like to encode english");
		String Statement = Input.getString("Please enter the statement you wish to encode or decode.");
		if(Decision.equals("morse"))
		{
			String A = Decode(Statement);
			System.out.println(A);
		}
		if(Decision.equals("english"))
		{
			String A = Encode(Statement);
			System.out.println(A);
		}
		if((Decision.equals("morse")||Decision.equals("english")) == false)
		{
			System.out.println("Invalid Input. Please rerun program and try again.");
		}
	}
	public static String Encode(String Statement)
	{
		Statement = Statement.toLowerCase();
		String code[] = CodeArray();
		char english[] = EnglishArray();
		String result = "";
		String Space =" ";
		char space = Space.charAt(0);
		for(int i = 0; i<Statement.length(); i++)
		{
			for(int j = 0; j<english.length ;j++)
			{
				if(english[j] == Statement.charAt(i))
				{
					result += code[j] + " ";
				}
			}
		}
		return result;
	}
	public static String Decode(String Statement)
	{
		String code[] = CodeArray();
		char english[] = EnglishArray();
		String Space =" ";
		char space = Space.charAt(0);
		String result = "";
		int x;
		int j;
		String letter = "";
		for(int i = 0; i<Statement.length();i += x)
		{
			letter = "";
			x = 0;
			for(x = 0;(i + x) < Statement.length() && (Statement.charAt(i + x) == space) == false; x++)
			{
				letter += Statement.charAt(i+x);
			}
			for(j = 0; j<code.length; j++)
			{
				if(code[j].equals(letter))
				{
					result += english[j];
				}
			}
			x++;
		}
		return result;
	}
	public static char[] EnglishArray()
	{
		String space = " ";
		char english[] = new char[37];
		for(int j = 0; j<(english.length-10); j++ )
		{
			english[j] = (char)(j+97);
		}
		for(int j = 26; j<(english.length); j++)
		{
			english[j] = (char)(j+22);
		}
		english[36] = space.charAt(0);
		return english;
	}
	public static String[] CodeArray()
	{
		String code[] = new String[37];
		code[0] = ".-";
		code[1] = "-...";
		code[2] = "-.-.";
		code[3] = "-..";
		code[4] = ".";
		code[5] = "..-.";
		code[6] = "--.";
		code[7] = "....";
		code[8] = "..";
		code[9] = ".---";
		code[10] = "-.-";
		code[11] = ".-..";
		code[12] = "--";
		code[13] = "-.";
		code[14] = "---";
		code[15] = ".--.";
		code[16] = "--.-";
		code[17] = ".-.";
		code[18] = "...";
		code[19] = "-";
		code[20] = "..-";
		code[21] = "...-";
		code[22] = ".--";
		code[23] = "-..-";
		code[24] = "-.--";
		code[25] = "--..";
		code[26] = "-----";
		code[27] = ".----";
		code[28] = "..---";
		code[29] = "...--";
		code[30] = "....-";
		code[31] = ".....";
		code[32] = "-....";
		code[33] = "--...";
		code[34] = "---..";
		code[35] = "----.";
		code[36] = "|";
		return code;
	}
}
