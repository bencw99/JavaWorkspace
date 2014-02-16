public class Date 
{
	//instance variables day, month, and year declared
	int day;
	int month;
	int year;
	public Date()
	{
		//Precondition: none
		//Blank constructor sets all instance variables to 1
		setDay(1);
		setMonth(1);
		setYear(1);
		//Post condition: day, month, year are set
	}
	public Date(int m, int d, int y)
	{
		//Precondition: none
		//Constructor with three arguments
		setDay(d);
		setMonth(m);
		setYear(y);
		//Post condition: day, month, year are set
	}
	public String getDate()
	{
		//Precondition: day, month, and year have been initialized
		//Makes day, year, and month into String
		String date = "";
		if(month < 10)
		{
			date += "0";
		}
		date += month + "/";
		if(day < 10)
		{
			date += "0";
		}
		date += day + "/";
		if(year < 1000)
		{
			date += "0";
			if(year < 100)
			{
				date += "0";
				if(year<10)
				{
					date += "0";
				}
			}
		}
		date += year;
		return date;
		//Post Condition: date string returned
	}
	public int getDay() 
	{
		//Precondition: day initialized
		//day getter
		return day;
		//Postcondition: day returned
	}
	public void setDay(int day) 
	{
		//Precondition: none
		//day setter
		day = (day > 31 || day < 1)?1:day;
		this.day = day;
		//Postcondition: day set
	}
	public int getMonth() 
	{
		//Precondition: month initialized
		//month getter
		return month;
		//Postcondition: month returned
	}
	public void setMonth(int month) 
	{
		//Precondition: none
		//month setter
		month = (month > 12 || month < 1)?1:month;
		this.month = month;
		//Postcondition: month set
	}
	public int getYear() 
	{
		//Precondition: year initialized
		//year getter
		return year;
		//Postcondition: year returned
	}
	public void setYear(int year) 
	{
		//Precondition: none
		//year setter
		year = (year < 1)?1:year;
		this.year = year;
		//Postcondition: year set
	}
}