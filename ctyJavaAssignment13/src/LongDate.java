
public class LongDate extends Date
{

	String monthName;
	public LongDate(String m, int d, int y)
	{
		setDate(m, d, y);
	}
	public void setDate(String m, int d, int y)
	{
		int mNum = 0;
		String names[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		monthName = m;
		for(int x = 0; x < 12; x++)
		{
			if(m.equals(names[x]))
			{
				mNum = x + 1;
			}
		}
		super.setDate( mNum , d, y);
	}
	public String getDate()
	{
		return monthName + " " + String.valueOf(getDay()) + ", " + String.valueOf(getYear());
	}
	public String getShortDate()
	{
		return super.getDate();
	}

	public String getMonthName()
	{
		return monthName;
	}
}
