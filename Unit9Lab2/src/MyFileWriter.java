import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
public class MyFileWriter 
{
	//Instance variables fileName, format, and date
	String fileName;
	Formatter format;
	Date date;
	//Constructor initializing fileName
	public MyFileWriter(String name)         
	{                                        
		fileName = name;                     
	}
	//Writes date to file, instance must be initialized
	public void write(Date date) throws FileNotFoundException
	{
		this.date = date;
		format = new Formatter(new File(fileName));
		format.format("%s", this.date.getDate());
		format.close();
	}
}
