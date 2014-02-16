import util.*;

public class Assignment10 
{
	public static void main(String[]args)
	{
		Employee employee = new Employee();
		int nID = 0;
		Name name = new Name();
		Address address = new Address();
		Date date = new Date();
		int x = Input.getInt("How many employees were hired?");
		for(nID = 1; nID <= x; nID++)
		{
			String f = Input.getString("Enter employee " + nID + "'s first name");
			String l = Input.getString("Enter employee " + nID + "'s last name");
			String c = Input.getString("Enter employee " + nID + "'s city's name");
			String s = Input.getString("Enter employee " + nID + "'s street's name");
			int n = Input.getInt("Enter employee " + nID + "'s street address number");
			String t = Input.getString("Enter employee " + nID + "'s state's initials");
			String z = Input.getString("Enter employee " + nID + "'s five-digit zip code");
			String d = Input.getString("Enter employee " + nID + "'s hire date in the form MM/DD/YYYY");
			name.setFirst(f);
			name.setLast(l);
			address.setCity(c);
			address.setStreet(s);
			address.setState(t);
			address.setNumber(n);
			address.setZip(z);
			date.setDate(d);
			employee.setName(name);
			employee.setAddress(address);
			employee.setDate(date);
			employee.setID(nID);
			System.out.println();
			display(employee);
		}
	}
	public static void display(Employee employee)
	{
		Name A = employee.getName();
		Address B = employee.getAddress();
		Date C = employee.getDate();
		System.out.println("Employee " + employee.getID() + ":");
		System.out.println("Name: " + A.getFirst() + " " + A.getLast());
		System.out.println("Address: " + B.getNumber() + " " + B.getStreet() + ", " + B.getCity() + " " + B.getState() + ", " + B.getZip());
		System.out.println("Hire date: " + C.getMonth() + " " + C.getDay() + ", " + C.getYear());
	}
}
