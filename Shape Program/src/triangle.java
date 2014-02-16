
public class triangle {
	double s1;
	double s2;
	double s3;
	public void setS1()
	{
		s1 = Input.getDouble("Enter the value for the first side length");
	}
	public void setS2()
	{
		s2 = Input.getDouble("Enter the value for the second side length");
	}
	public void setS3()
	{
		s3 = Input.getDouble("Enter the value for the third side length");
	}
	public double getPerimeter()
	{
		double P = s1 + s2 + s3;
		return P;
	}
	public double getArea()
	{
		double s = (s1 + s2 + s3)/2;
		double area = Math.sqrt(s*(s-s1)*(s-s2)*(s-s3));
		return area;
	}
}
