
public class Format 
{
	public static void main(String[]args)
	{
		if(args[0].equals("FORMAT"))
		{
			String formatter = args[1].substring(0,args[1].lastIndexOf(','));
			double value = Double.parseDouble(args[1].substring(args[1].lastIndexOf(',') + 1));
			StringBuffer outputString = new StringBuffer("" + value);
			outputString = fit(formatter, outputString);
			if(formatter.indexOf(',') >= 0)
			{
				outputString = commaInsert(outputString);
			}
			if(formatter.indexOf('E') == formatter.length() - 1)
			{
				
			}
			if(formatter.indexOf('$') > 0)
			{
				if(formatter.indexOf('*') < 0)
				{
					if(outputString.indexOf("*") > -1)
					{
						outputString.delete(0,outputString.lastIndexOf("*") + 1);
					}
					outputString.insert(0, '$');
				}
				else
				{
					outputString.insert(outputString.lastIndexOf("*") + 1, '$');
				}
			}
			System.out.println(outputString);
		}
	}
	public static StringBuffer commaInsert(StringBuffer string)
	{
		for(int i = string.indexOf(".") - 3; i > 0; i -= 3)
		{
			string = string.insert(i, ',');
		}
		return string;
	}
	public static StringBuffer fit(String formatter, StringBuffer outputString)
	{
		int decCharNum = 0;
		int charNum = 0;
		int formatDecCharNum = 0;
		int formatCharNum = 0;
		if(outputString.indexOf(".") < 0)
		{
			for(int i = 0; i < outputString.length(); i++)
			{
				if((int)(outputString.charAt(i)) < 58 && (int)(outputString.charAt(i)) > 47)
				{
					charNum ++;
				}
			}
		}
		else
		{
			for(int i = 0; i < outputString.indexOf("."); i++)
			{
				if((int)(outputString.charAt(i)) < 58 && (int)(outputString.charAt(i)) > 47)
				{
					charNum ++;
				}
			}
			for(int i = outputString.indexOf(".") + 1; i < outputString.length(); i++)
			{
				if((int)(outputString.charAt(i)) < 58 && (int)(outputString.charAt(i)) > 47)
				{
					decCharNum ++;
				}
			}
		}
		if(formatter.indexOf(".") < 0)
		{
			for(int i = 0; i < formatter.length(); i++)
			{
				if(formatter.charAt(i) == '&')
				{
					formatCharNum ++;
				}
			}
		}
		else
		{
			for(int i = 0; i < formatter.indexOf("."); i++)
			{
				if(formatter.charAt(i) == '&')
				{
					formatCharNum ++;
				}
			}
			for(int i = formatter.indexOf(".") + 1; i < formatter.length(); i++)
			{
				if(formatter.charAt(i) == '&')
				{
					formatDecCharNum ++;
				}
			}
		}
		if(formatDecCharNum >= decCharNum)
		{
			for(int i = 0; i < formatDecCharNum - decCharNum; i++)
			{
				outputString.insert(outputString.length() - 1, "0");
			}
		}
		else 
		{
			double d = roundDecimal(Double.parseDouble(outputString.toString()),formatDecCharNum);
			if(decCharNum == 0)
			{
				outputString = new StringBuffer((int)d + "");
			}
			else
			{
				outputString = new StringBuffer(d + "");
			}
		}
		if(formatCharNum >= charNum)
		{
			for(int i = 0; i < formatCharNum - charNum; i++)
			{
				outputString.insert(0, "*");
			}
		}
		
		return outputString;
	}
	public static double roundDecimal(double d, int digits)
	{
		double roundingFactor = 0;
		if(d*Math.pow(10,digits) - (int)(d*Math.pow(10,digits)) >= 0.5)
		{
			roundingFactor = 1/Math.pow(10,digits);
		}
		return ((int)(d*Math.pow(10,digits)))/Math.pow(10, digits) + roundingFactor;
	}
}
