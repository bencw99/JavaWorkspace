import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
public class FileWriter 
{
	//instance variables formatter and fileName
	String fileName;
	Formatter format;
	//Constructor to initialize
	public FileWriter(String name)         
	{                                        
		fileName = name;                     
	}
	//Write method using fileName and a contact list and writing data to the file, this instance must be initialized
	public void write(ContactList list) throws FileNotFoundException
	{
		format = new Formatter(new File(fileName));
		format.format("%s", list);
		format.close();
	}
}
