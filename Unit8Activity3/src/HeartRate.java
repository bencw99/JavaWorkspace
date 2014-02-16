
public class HeartRate 
{
	String firstName;
	String lastName;
	String birthDate;
	public HeartRate(String fn, String ln, String bd)
	{
		firstName = fn;
		lastName = ln;
		birthDate = bd;
	}
	public int getAge()
	{
		return 2014 - Integer.parseInt(birthDate.substring(6,10));
	}
	public int getMaxRate()
	{
		return 220 - getAge();
	}
	public int getTargetRate()
	{
		return (int)(0.85*getMaxRate());
	}
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
	public String getBirthDate() 
	{
		return birthDate;
	}
	public void setBirthDate(String birthDate) 
	{
		this.birthDate = birthDate;
	}
}
