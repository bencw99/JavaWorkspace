package projects.jumpingBug;
import java.awt.Color;
import info.gridworld.actor.Bug;
//Jumping bug is a class that extends Bug that jumps forward two spaces if possible, or one if only one possible, or turns and changes color if none possible
public class JumpingBug extends Bug
{
	//Act method must be overridden to cause bug to move first normally but then to turn twice and change color if acn't move, and then to move again if can
	//Will cause bug action given instance has been initialized
	public void act()
	{
		if(canMove())
		{
			move();
		}
		else
		{
			turn();
			turn();
			setColor(randomColor());
			return;
		}
		if(canMove())
		{
			move();
		}
	}
	//Returns a random color, no preconditions
	public Color randomColor()
	{
		return new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random()));
	}
}
