
public class Job 
{
	//Instance variable jobNum and printTime declarations
	int jobNum;
	int printTime;
	//Parameterized constructor to initialize instance variables
	public Job(int jobNum, int printTime)
	{
		this.jobNum = jobNum;
		this.printTime = printTime;
	}
	//Getters for jobNum and printTime, Job instance must be initialized
	public int getJobNum()
	{
		return jobNum;
	}
	public int getPrintTime()
	{
		return printTime;
	}
}
