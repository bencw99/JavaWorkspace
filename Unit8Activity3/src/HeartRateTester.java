
public class HeartRateTester 
{
	public static void main(String[]args)
	{
		String first = Input.getString("Enter your first name");
		String last = Input.getString("Enter your last name");
		String date = Input.getString("Enter your date of birth in the form mm dd yyyy");
		HeartRate heartRate = new HeartRate(first, last, date);
		System.out.println("Name: " + heartRate.getFirstName() + " " + heartRate.getLastName());
		System.out.println("Date of birth: " + heartRate.getBirthDate());
		System.out.println("Age: " + heartRate.getAge());
		System.out.println("Maximum Heart Rate: " + heartRate.getMaxRate());
		System.out.println("Target Heart Rate: " + heartRate.getTargetRate());
	}
}
