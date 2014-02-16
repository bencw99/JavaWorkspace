import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
public class MyFileWriter 
{
	String fileName;
	Formatter format;
	public MyFileWriter(String name)         
	{                                        
		fileName = name;                     
	}                                 
	public void write(Date date) throws FileNotFoundException
	{
		format = new Formatter(new File(fileName));
		format.format("", date.getDate());
	}
}
