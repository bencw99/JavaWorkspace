
public class Counter 
{
	private int count; //declares count
	public Counter(int c)
	{
		//initializes count
		count = c;
	}
	public Counter()
	{
		//initializes count to 0
		this(0);
	}
	public void increment()
	{
		//increases count by 1 requires count to be initialized
		count++;
	}
	public void decrement()
	{
		//decreases count by 1 requires count to be initialized
		count--;
	}
	public void reset()
	{
		//sets count to 0
		count = 0;
	}
	public void setCount(int c)
	{
		//sets count
		count = c;
	}
	public int getCount()
	{
		//return count, count must be initialized
		return count;
	}
}