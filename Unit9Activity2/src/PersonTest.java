/**
* This program demonstrates the use of the person class and its objects
* @BenjaminCohenWang
*/
public class PersonTest 
{
	public static void main(String[]args)
	{
		Person person = new Person("Tony", "Baggadonuts", new Date(10,21,2001));//Creates person object
		System.out.println("Name: " + person.getFirstName() + " " + person.getLastName());//Print name
		System.out.println("Birthday: " + person.getBirthDayString());//Prints birthday
	}
}
