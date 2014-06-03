//Tetrahedron extends 3Dshape
public class Tetrahedron extends ThreeDimensionalShape
{
	//instance variable side
	private double side;
	//Parameterized constructor
	public Tetrahedron(double side)
	{
		this.side = side;
	}
	//getVolume implementation
	public double getVolume() 
	{
		return Math.pow(side, 3)/6;
	}
	//getArea implementation
	public double getArea() 
	{
		return Math.pow(side, 2)*Math.sqrt(3);
	}
}
