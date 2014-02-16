
public class Time2 
{
	private int second;
	public Time2()
	{
		this(0, 0, 0);
	}
	public Time2(int h)
	{
		this(h, 0, 0);
	}
	public Time2(int h, int m)
	{
		this(h, m, 0);
	}
	public Time2(int h, int m, int s)
	{
		setTime(h, m, s);
	}
	public Time2( Time2 time)
	{
		this(time.getHour(), time.getMinute(), time.getSecond());
	}
	public void setTime( int h, int m, int s)
	{
		setSecond(s);
		setMinute(m);
		setHour(h);
	}
	public void setHour(int h)
	{
		h = ((h >= 0 && h < 24)?h:0);
		second = second%3600;
		second += h*3600;
	}
	public void setMinute(int m)
	{
		m = ((m >= 0 && m < 60)?m:0);
		int hour = second - second%3600;
		int minute = second - (second - hour)%60;
		second -= minute;
		second += m*60;
	}
	public void setSecond(int s)
	{
		s = ((s >= 0 && s < 60)?s:0);
		int sec = second%60;
		second -= sec;
		second += s;
	}
	public int getHour()
	{
		return (second - second%3600)/3600;
	}
	public int getMinute()
	{
		return ((second - second%60)%3600)/60;
	}
	public int getSecond()
	{
		return second%60;
	}
	public String toUniversalString()
	{
		return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	}
	public String toString()
	{
		return String.format("%d:%02d:%02d %s", ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12 ), getMinute(), getSecond(), (getHour() <12 ? "AM" : "PM"));
	}
}
