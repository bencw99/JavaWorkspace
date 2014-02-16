import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MyFileReader 
{
	String fileName;
	Scanner scanner;
	public MyFileReader(String name)
	{
		fileName = name;
	}
	public Date read() throws FileNotFoundException
	{
		String dateString = "";
		scanner = new Scanner(new File(fileName));
		while(scanner.hasNext())
		{
			dateString += scanner.next();
		}
		return new Date(dateString);
	}
}
