//ContactList class is an organization of contacts into a TreeMap 
import java.util.TreeMap;
public class ContactList 
{
	//instance TreeMap of key: contact last names and value: contacts
	TreeMap< String, Contact > contactList;
	//Default constructor, no contacts initially
	public ContactList()
	{
		contactList = new TreeMap<String,Contact>();
	}
	//Parameterized constructor to initialize
	public ContactList(TreeMap< String, Contact > list)
	{
		contactList = list;
	}
	//Adds a contact to the tree map, must be initialized
	public void add(Contact contact)
	{
		contactList.put(contact.getLastName(), contact);
	}
	//removes a contact from treemap, contact must be there or nothing will happen
	public void remove(Contact contact)
	{
		contactList.remove(contact.getLastName());
	}
	//toString() override
	public String toString()
	{
		String list = "";
		for(Contact contact: contactList.values())
		{
			list += contact + "\n";
		}
		return list;
	}
}
