import util.*;

public class Employee 
{
	int nID;
	Name name = new Name();
	Address address = new Address();
	Date date = new Date();
	public void setName(Name name)
	{
		this.name = name;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public void setAddress(Address address)
	{
		this.address = address;
	}
	public void setID(int nID)
	{
		this.nID = nID;
	}
	public Name getName()
	{
		return name;
	}
	public Date getDate()
	{
		return date;
	}
	public Address getAddress()
	{
		return address;
	}
	public int getID()
	{
		return nID;
	}
}
