
public class Main 
{
	public static void main(String[] args)
	{
		double x = Input.getInt("Enter x");
		double y = Input.getInt("Enter y");
		System.out.println("x is " + x);
		System.out.println("y is " + y);
		System.out.println("x/y rounded to an integer is " + Math.floor(((x/y)*Math.pow(10,0) + 0.5))/Math.pow(10, 0));
		System.out.println("x/y rounded to an one decimal is " + Math.floor(((x/y)*Math.pow(10,1) + 0.5))/Math.pow(10, 1));
		System.out.println("x/y rounded to an two decimals is " + Math.floor(((x/y)*Math.pow(10,2) + 0.5))/Math.pow(10, 2));
		System.out.println("x/y rounded to an three decimals is " + Math.floor(((x/y)*Math.pow(10,3) + 0.5))/Math.pow(10, 3));
	}
}
