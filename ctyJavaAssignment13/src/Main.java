
public class Main 
{	
	public static void main(String[]args)
	{
		LongDate date = new LongDate(Input.getString("Enter the month name"),Input.getInt("Enter the day"),Input.getInt("Enter the year"));
		boolean a = false;
		String monthNames[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		while (a == false)
		{
			int x;
			for(x = 0; x < 12; x++)
			{
				if(monthNames[x].equals(date.getMonthName()))
				{
					a = true;
					break;
				}
			}
			if(a == true)
			{
				break;
			}
			date.setDate(Input.getString("Please re-enter the month name"), date.getDay(), date.getYear());
		}
		date.setDate(date.getMonthName(), date.getDay(), date.getYear());
		System.out.println("Date: " + date.getDate());
	}
}
