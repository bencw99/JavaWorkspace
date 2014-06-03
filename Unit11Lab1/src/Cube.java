//Cube extends 3Dshape
public class Cube extends ThreeDimensionalShape
{
	//instance variable side
	private double side;
	//Parameterized constructor
	public Cube(double side)
	{
		this.side = side;
	}
	//getArea implementation
	public double getArea()
	{
		return 6*Math.pow(side,2);
	}
	//getVolume implementation
	public double getVolume()
	{
		return Math.pow(side, 3);
	}
}
