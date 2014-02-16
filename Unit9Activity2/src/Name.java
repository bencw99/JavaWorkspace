
public class Name 
{
	private String first; // declares first name string
	private String last; // declares last name string
	public Name(String fn, String ln)
	{
		first = fn;
		last = ln;
		//PostC: name initialized
	}
	public String getFirst()
	{
		//PreC: first initialized
		return first;
	}
	public String getLast()
	{
		//PreC: last initialized
		return last;
	}
	public void setFirst(String fn)
	{
		first = fn;
		//PostC: first initialized
	}
	public void setLast(String ln)
	{
		last = ln;
		//PostC: last initialized
	}
}
