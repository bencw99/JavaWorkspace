
public class ShapeAnalysis 
{
	public static void main(String[]args)
	{
		rectangle A;
		circle B;
		triangle C;
		A = new rectangle();
		B = new circle();
		C = new triangle();
		String decision = "yes";
		while(decision.equals("yes"))
		{
			decision = Input.getString("Would you like to analyze a circle, rectangle, or triangle?");
			if (decision.equals("rectangle"))
			{
				A.setLength();
				A.setWidth();
				String choice;
				choice = Input.getString("Would you like to know the area or the perimeter?");
				while(choice.equals("area")||choice.equals("perimeter"))
				{
					if(choice.equals("area"))
					{
						System.out.println();
						System.out.format("The area of the rectangle is %.5f", A.getArea());
					}
					if(choice.equals("perimeter"))
					{
						System.out.println();
						System.out.print("The perimeter of the rectangle is " + A.getPerimeter());
					}
					choice = Input.getString("Would you like to know the area, perimeter, or would you like to leave?");
				}
			}
			if (decision.equals("circle"))
			{
				B.setRadius();
				String choice;
				choice = Input.getString("Would you like to know the area or the circumference?");
				while(choice.equals("area")||choice.equals("circumference"))
				{
					if(choice.equals("area"))
					{
						System.out.println();
						System.out.format("The area of the circle is %.5f", B.getArea());
					}
					if(choice.equals("circumference"))
					{
						System.out.println();
						System.out.format("The circumference of the circle is %.5f", B.getCircumference());
					}
					choice = Input.getString("Would you like to know the area, circumference, or would you like to leave?");
				}
			}
			if (decision.equals("triangle"))
			{
				C.setS1();
				C.setS2();
				C.setS3();
				String choice;
				choice = Input.getString("Would you like to know the area or the perimeter?");
				while(choice.equals("area")||choice.equals("perimeter"))
				{
					if(choice.equals("area"))
					{
						System.out.println();
						System.out.format("The area of the rectangle is %.5f", C.getArea());
					}
					if(choice.equals("perimeter"))
					{
						System.out.println();
						System.out.print("The perimeter of the rectangle is " + C.getPerimeter());
					}
					choice = Input.getString("Would you like to know the area, perimeter, or would you like to leave?");
				}
			}
			decision = Input.getString("Would you like to continue?");
		}
	}
}
