
public class circle 
{
	double radius;
	public void setRadius()
	{
		radius = Input.getDouble("Enter the circle's radius");
	}
	public double getCircumference()
	{
		double C = 2*Math.PI*radius;
		return C;
	}
	public double getArea()
	{
		double area = Math.PI*(Math.pow(radius, 2));
		return area;
	}
}
