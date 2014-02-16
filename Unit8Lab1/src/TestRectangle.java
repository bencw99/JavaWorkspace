
public class TestRectangle 
{
	public static void main(String[]args)
	{
		Rectangle rect = new Rectangle(25, 30, '*');
		System.out.println("Rectangle Dimensions: " + rect.getLength() + " by " + rect.getWidth());
		System.out.println("Area: " + rect.getArea());
		System.out.println("Perimeter: " + rect.getPerim());
		rect.draw();
	}
}
