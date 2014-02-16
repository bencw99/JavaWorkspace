
public class rectangle 
{
	double length = 1;
	double width = 1;
	public void setLength()
	{
		length = Input.getDouble("Enter the rectangle's length");
	}
	public void setWidth()
	{
		width = Input.getDouble("Enter the rectangle's width");
	}
	public double getPerimeter()
	{
		double P = 2*length + 2*width;
		return P;
	}
	public double getArea()
	{
		double area = length*width;
		return area;
	}
}
