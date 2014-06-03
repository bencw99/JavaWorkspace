//subclass of counter with memory
public class MemoryCounter extends Counter
{
	//instance variable memory
	private int mem;
	//Constructor setting mem to 0 and calling superclass constructor
	public MemoryCounter(int c)
	{
		super(c);
		setMem(0);
	}
	//default constructor
	public MemoryCounter()
	{
		super();
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
		setMem(getCount());
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
	
}
