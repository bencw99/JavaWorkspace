
public class Number 
{
	public static int binaryToDecimal(String binaryString)
	{
		int decimal = 0;
		for(int i = 0; i < binaryString.length(); i++)
		{
			if(binaryString.charAt(i) == '1')
			{
				decimal += Math.pow(2, binaryString.length() - 1 - i);
			}
		}
		return decimal;
	}
	public static String binaryToHex(String binaryString)
	{
		int decimal = binaryToDecimal(binaryString);
		String hexString = "";
		int i = decimal;
		while(i >= 16)
		{
			int j = i % 16;
			switch(j)
			{
			case 10:
			{
				hexString += "a";
				break;
			}
			case 11:
			{
				hexString += "b";
				break;
			}
			case 12:
			{
				hexString += "c";
				break;
			}
			case 13:
			{
				hexString += "d";
				break;
			}
			case 14:
			{
				hexString += "e";
				break;
			}
			case 15:
			{
				hexString += "f";
				break;
			}
			default:
			{
				hexString += j;
				break;
			}
			}
			i = (i - i%16)/16;
		}
		int j = i % 16;
		switch(j)
		{
		case 10:
		{
			hexString += "a";
			break;
		}
		case 11:
		{
			hexString += "b";
			break;
		}
		case 12:
		{
			hexString += "c";
			break;
		}
		case 13:
		{
			hexString += "d";
			break;
		}
		case 14:
		{
			hexString += "e";
			break;
		}
		case 15:
		{
			hexString += "f";
			break;
		}
		default:
		{
			hexString += j;
			break;
		}
		}
		return invert(hexString);
	}
    public static String invert(String s) 
    {
        String inverted = "";
        for(int i = s.length() - 1; i >= 0; i--)
        {
        	inverted += s.charAt(i);
        }
        return inverted;
    }
    public static String decimalToHex(int decimalNumber)
    {
		String hexString = "";
		int i = decimalNumber;
		while(i >= 16)
		{
			int j = i % 16;
			switch(j)
			{
			case 10:
			{
				hexString += "a";
				break;
			}
			case 11:
			{
				hexString += "b";
				break;
			}
			case 12:
			{
				hexString += "c";
				break;
			}
			case 13:
			{
				hexString += "d";
				break;
			}
			case 14:
			{
				hexString += "e";
				break;
			}
			case 15:
			{
				hexString += "f";
				break;
			}
			default:
			{
				hexString += j;
				break;
			}
			}
			i = (i - i%16)/16;
		}
		int j = i % 16;
		switch(j)
		{
		case 10:
		{
			hexString += "a";
			break;
		}
		case 11:
		{
			hexString += "b";
			break;
		}
		case 12:
		{
			hexString += "c";
			break;
		}
		case 13:
		{
			hexString += "d";
			break;
		}
		case 14:
		{
			hexString += "e";
			break;
		}
		case 15:
		{
			hexString += "f";
			break;
		}
		default:
		{
			hexString += j;
			break;
		}
		}
		return invert(hexString);
    }
    public static String decimalToBinary(int decimalNumber)
    {
    	String binaryString = "";
    	int i = decimalNumber;
    	while(i >= 2)
    	{
    		binaryString += i%2;
    		i = (i - i%2)/2;
    	}
    	binaryString += i;
    	return invert(binaryString);
    }
}
