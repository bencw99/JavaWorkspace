//Sphere extends 3Dshape
public class Sphere extends ThreeDimensionalShape
{
	//Instance variable radius
	private double radius;
	//Parameterized constructor
	public Sphere(double radius)
	{
		this.radius = radius;
	}
	//getArea Implementation
	public double getArea()
	{
		return 4*Math.PI*Math.pow(radius, 2);
	}
	//getVolume implementation
	public double getVolume()
	{
		return 4*Math.PI*Math.pow(radius, 3)/3;
	}
}
