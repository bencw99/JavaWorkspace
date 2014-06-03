//2D shape circle extending 2dshape
public class Circle extends TwoDimensionalShape
{
	//instance variable radius
	private double radius;
	//Parameterized constructor
	public Circle(double radius)
	{
		this.radius = radius;
	}
	//getArea implementation
	public double getArea() 
	{
		return Math.PI*Math.pow(radius, 2);
	}
}
