//Square extends 2Dshape
public class Square extends TwoDimensionalShape
{
	//instance variable side
	private double side;
	//Parameterized constructor
	public Square(double side)
	{
		this.side = side;
	}
	//getArea implementation
	public double getArea() 
	{
		return Math.pow(side, 2);
	}
}
