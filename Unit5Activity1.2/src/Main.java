
public class Main 
{
	public static void main(String[]args)
	{
		double degrees = Input.getDouble("Enter the angle in degrees");
		System.out.println("The angle in degrees is " + degrees);
		System.out.println("The anle in radians is " + degrees*Math.PI/180);
		System.out.println("The Sine is " + Math.sin(degrees*Math.PI/180));
		System.out.println("The Cosine is " + Math.cos(degrees*Math.PI/180));
		System.out.println("The Tangent is " + Math.tan(degrees*Math.PI/180));
	}
}
