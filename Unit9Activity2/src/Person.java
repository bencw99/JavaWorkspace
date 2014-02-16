public class Person
{
	private Name name; //declares name
	private Date birthDay; //declares date
	public String getFirstName()
	{
		//PreC: first name created
		return name.getFirst(); 
	}	   
	public String getLastName()
	{
		//PreC: last name created
		return name.getLast();
	}
	public String getBirthDayString()
	{
		//PreC: date initialized
		return birthDay.getDate();
	}
	public Person( String fn, String ln, Date bd )
	{
		name  = new Name(fn, ln);
		birthDay = bd;
		//PostC: person initialized
	}
	public Person()  // Default constructor
	{
		name.setFirst("None");
		name.setLast("None");
		birthDay = new Date( 99, 99, 9999 );
		//PostC: person initialized
	}
}