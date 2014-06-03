/*
 * This program represents a FIFO type print queue in which the user adds jobs to the printer and their attributes are later displayed after all jobs were added
 * @ author Benjamin Cohen-Wang
 */
import java.util.LinkedList;
import java.util.Random;
public class Queue 
{
	public static void main(String[]args)
	{
		//Linked list for queue
		LinkedList<Job> queue = new LinkedList<Job>();
		//boolean for sentinel
		boolean report = true;
		int i = 0;
		//Loop prompting user for another job
		while(report)
		{
			i++;
			if(Input.getString("Would you like to add another Job?").equals("yes"))
			{
				Random random = new Random();
				int time = random.nextInt(1000) + 10;
				queue.add(new Job(i, time));
			}
			else
			{
				report = false;
			}
		}
		//Iterator to display data
		System.out.println("Print Queue: ");
		for(Job job : queue)
		{
			System.out.println(" - Job #" + job.getJobNum() + " took " + job.getPrintTime() + " seconds");
		}
	}
}
