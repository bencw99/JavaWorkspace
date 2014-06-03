/**
 * This method tests the Rectangle class and its subclass EnhancedRectangle
 * @author Benjamin Cohen-Wang
 */
public class TestRectangle 
{
	public static void main(String[] args) 
	{
		Rectangle rectangle = new Rectangle(Input.getInt("Enter rectangle length"), Input.getInt("Enter rectangle width"));
		EnhancedRectangle enhancedRectangle = new EnhancedRectangle(Input.getInt("Enter enhancedRectangle length"), Input.getInt("Enter enhancedRectangle width"));
		System.out.println("Rectangle width: " + rectangle.getWidth());
		System.out.println("Rectangle height: " + rectangle.getLength());
		System.out.println("EnhancedRectangle width: " + enhancedRectangle.getWidth());
		System.out.println("EhancedRectangle height: " + enhancedRectangle.getLength());
		System.out.println("EnhancedRectangle area: " + enhancedRectangle.getArea());
		System.out.println("EnhancedRectangle perimeter: " + enhancedRectangle.getPerimeter());
	}
}
