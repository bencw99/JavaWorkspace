package pieces;
import pieces.*;
public class SidePiece 
{
	int ID;
	int Rotation;
	Color A;
	Color B;
	public void setID(int n)
	{
		if((n > 12) || (n < 1))
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
			break;
			case 2:
				A.setColor(1);
				B.setColor(3);
			break;
			case 3:
				A.setColor(1);
				B.setColor(4);
			break;
			case 4:
				A.setColor(1);
				B.setColor(5);
			break;
			case 5:
				A.setColor(6);
				B.setColor(2);
			break;
			case 6:
				A.setColor(6);
				B.setColor(3);
			break;
			case 7:
				A.setColor(6);
				B.setColor(4);
			break;
			case 8:
				A.setColor(6);
				B.setColor(5);
			break;
			case 9:
				A.setColor(2);
				B.setColor(3);
			break;
			case 10:
				A.setColor(3);
				B.setColor(4);
			break;
			case 11:
				A.setColor(4);
				B.setColor(5);
			break;
			case 12:
				A.setColor(5);
				B.setColor(2);
			break;
			
		}
	}
	public int getID()
	{
		return ID;
	}
	public void setRot(int n)
	{
		if((n > 2) || (n < 1))
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
