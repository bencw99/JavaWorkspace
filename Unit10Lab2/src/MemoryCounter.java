//subclass of counter with memory
public class MemoryCounter
{
	//instance variables memory and counter
	private int mem;
	Counter counter;
	//Constructor setting mem to 0 and initializing counter
	public MemoryCounter(int c)
	{
		counter = new Counter(c);
		setMem(0);
	}
	//default constructor
	public MemoryCounter()
	{
		counter = new Counter();
		setMem(0);
	}
	//Sets mem to 0
	public void resetMem()
	{
		setMem(0);
	}
	//sets mem to count given instance was initialized
	public void addMem()
	{
		setMem(counter.getCount());
	}
	//returns mem given instance was initialized
	public int getMem() 
	{
		return mem;
	}
	//Setter for mem
	public void setMem(int mem) 
	{
		this.mem = mem;
	}
	public void increment()
	{
		//calls counter's increment requires counter to be initialized
		counter.increment();
	}
	public void decrement()
	{
		//calls counter's decrement requires counter to be initialized
		counter.decrement();
	}
	public void reset()
	{
		//calls counter's reset, counter must be initialized
		counter.reset();
	}
	public void setCount(int c)
	{
		//sets counter's count
		counter.setCount(c);
	}
	public int getCount()
	{
		//return counter's count, counter must be initialized
		return counter.getCount();
	}
}
