import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
public class FileReader 
{
	//instance variables input and fileName
	String fileName;
	Scanner input;
	//Constructor to initialize
	public FileReader(String name)
	{
		fileName = name;
	}
	//Read method using fileName and returning a contact list, this instance must be initialized
	public ContactList read() throws FileNotFoundException
	{
		String string = "";
		TreeMap<String,Contact> contactList = new TreeMap<String,Contact>();
		Scanner scan = new Scanner(new File(fileName));
		scan.useDelimiter("\\n");
		while(scan.hasNext())
		{
			Contact c = new Contact(scan.next());
			contactList.put(c.getLastName(), c);
		}
		return new ContactList(contactList);
	}
}
