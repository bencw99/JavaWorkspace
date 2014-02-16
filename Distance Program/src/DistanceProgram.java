
public class DistanceProgram {
	public static void main ( String [] args )
	{
		int a = Input.getInt("This program computes the distance between two points, (x1, y1) and (x2, y2). Press ok and enter a number to continue. ");
		double x1 = Input.getDouble("Enter a value for x1");
		double y1 = Input.getDouble("Enter a value for y1");
		double x2 = Input.getDouble("Enter a value for x2");
		double y2 = Input.getDouble("Enter a value for y2");
		double d = Math.sqrt ( Math.pow ( x2 - x1 , 2 ) + Math.pow ( ( y2 - y1 ) , 2 ));
		System.out.println(d);
	}

}
