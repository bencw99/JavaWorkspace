
public class DaysOfWeek {
public static void main(String[]args)
{
	final int MONDAY = 1;
	final int TUESDAY = 2;
	final int WEDNESDAY = 3;
	final int THURSDAY = 4;
	final int FRIDAY = 5;
	final int SATURDAY = 6;
	final int SUNDAY = 7;
	int day_of_week = 2;
	switch ( day_of_week )
	{
	case MONDAY:
	System.out.println( "Today is Monday" );
	break;
	case TUESDAY:
	System.out.println( "Today is Tuesday" );
	break;
	case WEDNESDAY:
	System.out.println( "Today is Wednesday" );
	break;
	case THURSDAY:
	System.out.println( "Today is Thursday" );
	break;
	case FRIDAY:
	System.out.println( "Today is Friday" );
	break;
	case SATURDAY:
	System.out.println( "Today is Saturday" );
	break;
	case SUNDAY:
	System.out.println( "Today is Sunday" );
	break;
	default:
	System.out.println( "Invalid Data" );
	}
}
}
