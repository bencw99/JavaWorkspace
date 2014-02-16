package pieces;
import pieces.*;
public class CornerPiece 
{
	int ID;
	int Rotation;
	Color A;
	Color B;
	Color C;
	public void setID(int n)
	{
		if((n > 8) || (n < 1))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			ID = n;
		}
		switch (ID)
		{
			case 1:
				A.setColor(1);
				B.setColor(2);
				C.setColor(3);
			break;
			case 2:
				A.setColor(1);
				B.setColor(3);
				C.setColor(4);
			break;
			case 3:
				A.setColor(1);
				B.setColor(4);
				C.setColor(5);
			break;
			case 4:
				A.setColor(1);
				B.setColor(5);
				C.setColor(2);
			break;
			case 5:
				A.setColor(6);
				B.setColor(2);
				C.setColor(3);
			break;
			case 6:
				A.setColor(6);
				B.setColor(3);
				C.setColor(4);
			break;
			case 7:
				A.setColor(6);
				B.setColor(4);
				C.setColor(5);
			break;
			case 8:
				A.setColor(6);
				B.setColor(5);
				C.setColor(2);
			break;
		}
	}
	public int getID()
	{
		return ID;
	}
	public void setRot(int n)
	{
		if((n > 3) || (n < 1))
		{
			System.out.println("Invalid Input");
		}
		else
		{
			Rotation = n;
		}
	}
	public int getRot()
	{
		return Rotation;
	}
}
