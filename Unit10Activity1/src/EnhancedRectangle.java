public class EnhancedRectangle extends Rectangle
{
	//Parameterized Constructor that initializes EnhancedRectangle instance
	public EnhancedRectangle(int l, int w)
	{
		super(l,w);
	}
	//getArea() method returning area given instance was initialized
	public double getArea()
	{
		return this.getLength() * this.getWidth();
	}
	//getPerimeter() method returning perimeter given instance was initialized
	public double getPerimeter()
	{
		return 2 * ( this.getLength() + this.getWidth() );
	}
}