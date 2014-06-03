//Triangle extends 2Dshape
public class Triangle extends TwoDimensionalShape
{
	//instance variables base and height
	private double base;
	private double height;
	//Parameterized constructor
	public Triangle(double base, double height)
	{
		this.base = base;
		this.height = height;
	}
	//implementation of getArea
	public double getArea()
	{
		return base*height/2;
	}
}
