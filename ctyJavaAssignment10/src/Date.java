
public class Date {
	String month;
	int day;
	int year;
	public void setDate(String date)
	{
		String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		char Number[] = {'0','1','2','3','4','5','6','7','8','9'};
		if(date.charAt(0) == '0')
		{
			for(int j = 1; j < Number.length; j++)
			{
				if(date.charAt(1) == Number[j])
				{
					month = months[j-1];
				}
			}
		}
		if(date.charAt(0) == '1')
		{
			for(int j = 1; j<=3; j++)
			{
				if(date.charAt(1) == Number[j])
				{
					month = months[j+9];
				}
			}
		}
		if(date.charAt(3) == '0')
		{
			day = ((int)date.charAt(4) - 48);
		}
		if((date.charAt(3) == '0') == false);
		{
			day = ((int)(date.charAt(3)) - 48)*10 + ((int)date.charAt(4) - 48);
		}
		year = ((int)date.charAt(6)-48)*1000 + ((int)date.charAt(7)-48)*100 + ((int)date.charAt(8)-48)*10 + ((int)date.charAt(9)-48);
	}
	public String getMonth()
	{
		return month;
	}
	public int getDay()
	{
		return day;
	}
	public int getYear()
	{
		return year;
	}
}
