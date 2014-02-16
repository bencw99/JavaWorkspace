
public class Employee 
{
	public static void main(String[]args)
	{
		Name A = new Name();
		Address B = new Address();
		Date C = new Date();
		int x = Input.getInt("How many employees were hired?");
			for(int j = 1; j <= x; j++)
			{
				String f = Input.getString("Enter employee " + j + "'s first name");
				String l = Input.getString("Enter employee " + j + "'s last name");
				String c = Input.getString("Enter employee " + j + "'s city's name");
				String s = Input.getString("Enter employee " + j + "'s street's name");
				int n = Input.getInt("Enter employee " + j + "'s street address number");
				String t = Input.getString("Enter employee " + j + "'s state's initials");
				String z = Input.getString("Enter employee " + j + "'s five-digit zip code");
				String d = Input.getString("Enter employee " + j + "'s hire date in the form MM/DD/YYYY");
				A.setFirst(f);
				A.setLast(l);
				B.setCity(c);
				B.setStreet(s);
				B.setState(t);
				B.setNumber(n);
				B.setZip(z);
				C.setDate(d);
				System.out.println("Employee " + j + ":");
				System.out.println("Name: " + A.getFirst() + " " + A.getLast());
				System.out.println("Address: " + B.getNumber() + " " + B.getStreet() + ", " + B.getCity() + " " + B.getState() + ", " + B.getZip());
				System.out.println("Hire date: " + C.getMonth() + " " + C.getDay() + ", " + C.getYear());
			}
	}
}
