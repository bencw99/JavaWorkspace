
public class Address {
	String city;
	String street;
	String state;
	int streetnum;
	String zip;
	public void setCity(String c)
	{
		city = c;
	}
	public void setStreet(String s)
	{
		street = s;
	}
	public void setNumber(int a)
	{
		streetnum = a;
	}
	public void setZip(String z)
	{
		zip = z.substring(0,5);
	}
	public void setState(String t)
	{
		t = t.toUpperCase();
		state = t.substring(0,2);
	}
	public String getCity()
	{
		return city;
	}
	public String getStreet()
	{
		return street;
	}
	public int getNumber()
	{
		return streetnum;
	}
	public String getZip()
	{
		return zip;
	}
	public String getState()
	{
		return state;
	}
}
