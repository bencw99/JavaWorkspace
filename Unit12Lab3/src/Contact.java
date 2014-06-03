//Contact class
public class Contact 
{
	//Instance variables
	String firstName;
	String lastName;
	String phoneNumber;
	String email;
	//Parameterized constructor 1 to initialize
	public Contact(String firstName, String lastName, String phoneNumber, String email) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	//Parameterized constructor 2 to initialize
	public Contact(String contactString)
	{
		//String manipulation converting string into Contact object
		int firstNameDel = contactString.indexOf(" ");
		int lastNameDel;
		int numDel;
		for(lastNameDel = firstNameDel + 1; lastNameDel < contactString.length(); lastNameDel++)
		{
			if(contactString.charAt(lastNameDel) == ' ')
			{
				break;
			}
		}
		for(numDel = lastNameDel + 1; numDel < contactString.length(); numDel++)
		{
			if(contactString.charAt(numDel) == ' ')
			{ 
				break;
			}
		}
		firstName = contactString.substring(0,firstNameDel);
		lastName = contactString.substring(firstNameDel + 1, lastNameDel);
		phoneNumber = contactString.substring(lastNameDel + 1, numDel);
		email = contactString.substring(numDel + 1);
	}
	//Overriding of inherited toString method from Object superclass
	public String toString()
	{
		return firstName + " " + lastName + " " + phoneNumber + " " + email;
	}
	//Setters and getters. For getters to function, instance must be initialized
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
}
