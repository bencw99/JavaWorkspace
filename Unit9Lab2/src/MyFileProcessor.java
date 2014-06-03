import java.io.FileNotFoundException;
public class MyFileProcessor 
{
	//Instance variables writer and reader
	MyFileWriter writer;
	MyFileReader reader;
	//Constructor to initialize writer and reader
	public MyFileProcessor(String writeName, String readName)
	{
		writer = new MyFileWriter(writeName);
		reader = new MyFileReader(readName);
	}
	//returns date object from file, reader must be initialized 
	public Date read() throws FileNotFoundException
	{
		return reader.read();
	}
	//writes date to file, writer must be initialized
	public void write(Date date) throws FileNotFoundException
	{
		writer.write(date);
	}
}
