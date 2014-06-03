import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MyFileReader 
{
	//Instance variables fileName, input, and date
	String fileName;
	Scanner input;
	Date date;
	//Constructor initializing fileName
	public MyFileReader(String name)
	{
		fileName = name;
	}
	//Reads file and returns Date object, instance must be initialized
	public Date read() throws FileNotFoundException
	{
		String dateString = "";
		input = new Scanner(new File(fileName));
		while(input.hasNext())
		{
			dateString += input.next();
			if(input.hasNext())
			{
				dateString += " ";
			}
		}
		date = new Date(dateString);
		return date;
	}
}
