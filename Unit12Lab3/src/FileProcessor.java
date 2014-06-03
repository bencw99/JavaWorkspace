import java.io.FileNotFoundException;
public class FileProcessor 
{
	//Instance variables writer and reader
	FileWriter writer;
	FileReader reader;
	//Constructor to initialize writer and reader
	public FileProcessor(String writeName, String readName)
	{
		writer = new FileWriter(writeName);
		reader = new FileReader(readName);
	}
	//returns ContactList object from file, reader must be initialized 
	public ContactList read() throws FileNotFoundException
	{
		return reader.read();
	}
	//writes ContactList to file, writer must be initialized
	public void write(ContactList list) throws FileNotFoundException
	{
		writer.write(list);
	}
}

