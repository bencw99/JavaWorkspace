package pieces;
import pieces.*;
public class MiddlePiece 
{
	Color Color;
	public void setID(int n)
	{
		if((n > 4) || (n < 1))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			Color.setColor(n);
		}
	}
	public int getID()
	{
		return Color.getColor();
	}
}
