package projects.diagonalBug;
import info.gridworld.actor.Bug;
//This class represents a bug that moves diagonally, extending the bug class
public class DiagonalBug extends Bug
{
	//Constructor overridden to cause bug to start in one of four diagonal positions
	//Initializes DiagonalBug instance, no preconditions
	public DiagonalBug()
	{
		super();
		setDirection(45 + 90*((int)(4*Math.random())));
	}
	//Turns 4 times every turn so that bug turns 180 degrees
	//Post condition: bug turned 180 degrees
	//Precondition: instance initialized
	public void turn()
	{
		super.turn();
		super.turn();
		super.turn();
		super.turn();
	}
}
