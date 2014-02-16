package Contestants;		//States that Hare is part of Contestants
public class Tortoise
{
	int position = 0;			//Creates position integer and initializes to zero
	public int getPosition()	//Creates getPosition method
	{
		return position;		//returns position when method is called
	}
	public void FastPlod()		//Creates FastPlod method
	{
		if((position + 3) >= 50)//Tests if Fast Plod will go over finish line
		{
			position = 50;		//sets position to 50
		}
		else
		{
			position += 3;		//Raises position by 3
		}
	}
	public void SlowPlod()		//Creates Slow Plod method
	{
		if((position + 1) >= 50)//Tests if Slow Plod will go over finish line
		{
			position = 50;		//sets position to 50
		}
		else
		{
			position += 1;		//Raises position by 1
		}
	}
	public void Slip()			//Creates Slip method
	{
		if((position - 6) < 1)	//Tests if Slip will go behind start line
		{
			position = 1;		//Sets position to 1
		}
		else
		{
			position -= 6;		//Lowers position by 6
		}
	}
}
