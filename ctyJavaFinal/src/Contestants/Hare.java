package Contestants;		//States that Hare is part of Contestants
public class Hare 
{
	int position = 0;			//Creates position integer and initializes to zero
	public int getPosition()	//Creates getPosition method
	{
		return position;		//returns position when method is called
	}
	public void BigHop()		//Creates BigHop method
	{
		if((position + 9) >= 50)//Tests if Big Hop will go over finish line
		{
			position = 50;		//Sets position to finish
		}
		else
		{
			position +=9;		//Raises position by 9
		}
	}
	public void SmallHop()		//Creates SmallHop method
	{
		if((position + 1) >= 50)//Tests if Small Hop will go over finish line
		{
			position = 50;		//Sets position to 50
		}
		else
		{
			position += 1;		//Raises position by 1
		}
	}
	public void BigSlip()		//Creates BigSlip method
	{
		if((position - 12) < 1)	//Tests if Big Slip will go behind start line
		{
			position = 1;		//Sets position to 1
		}
		else
		{
			position -= 12;		//Lowers position by 12
		}
	}
	public void SmallSlip()		//Creates SmallSlip method
	{
		if((position - 2) < 1)	//Tests if Small Slip will go behind start line
		{
			position = 1;		//Sets position to 1
		}
		else
		{
			position -= 2;		//Lowers position by 2
		}
	}
	public void FallAsleep()	//Creates FallAsleep method
	{
		//Since the hare does nothing while asleep, this method is just for clarification
	}
}
