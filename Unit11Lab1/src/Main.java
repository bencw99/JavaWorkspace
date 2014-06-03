
public class Main 
{
	public static void main(String[]args)
	{
		Shape[]shapes = {new Circle(5), new Square(5), new Triangle(3,2), new Tetrahedron(5), new Sphere(2), new Cube(3)};
		for(Shape shape: shapes)
		{
			System.out.println(shape.getClass().getName() + ":");
			if(shape instanceof TwoDimensionalShape)
			{
				System.out.println("- is Two Dimensional");
			}
			else
			{
				System.out.println("- is Three Dimensional");
				System.out.println("- Volume: " + ((ThreeDimensionalShape)shape).getVolume());
			}
			System.out.println("- Area: " + shape.getArea());
		}
	}
}
