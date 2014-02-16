
public class TestTime2 
{
	public static void main(String[]args)
	{
		Time2 time = new Time2(13, 52, 32);
		System.out.println(time.toUniversalString());
		System.out.println(time.toString());
		System.out.println("Hour is " + time.getHour());
		System.out.println("Minute is " + time.getMinute());
		System.out.println("Second is  " + time.getSecond());
		time.setTime(2, 36, 10);
		System.out.println(time.toUniversalString());
		System.out.println(time.toString());
		System.out.println("Hour is " + time.getHour());
		System.out.println("Minute is " + time.getMinute());
		System.out.println("Second is  " + time.getSecond());
	}
}
