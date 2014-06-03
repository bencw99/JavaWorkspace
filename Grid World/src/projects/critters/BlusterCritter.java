package projects.critters;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter
{
	//Instance variables light factor and courage
	private final double LIGHTENING_FACTOR = 0.05;
	private int courage;
	//Constructor is same but initializes courage too
	public BlusterCritter(int c)
	{
		super();
		courage = c;
	}
	//Overriding of process actors. pre and post conditions are inherited
	public void processActors(ArrayList<Actor> actors)
	{
		if(actors.size() > courage)
		{
		    Color c = getColor();
	        int red = (int) (c.getRed() * (1 - LIGHTENING_FACTOR));
	        int green = (int) (c.getGreen() * (1 - LIGHTENING_FACTOR));
	        int blue = (int) (c.getBlue() * (1 - LIGHTENING_FACTOR));
	        setColor(new Color(red, green, blue));
		}
		else
		{
		    Color c = getColor();
	        int red = Math.min((int) (c.getRed() * (1 + LIGHTENING_FACTOR)), 255);
	        int green = Math.min((int) (c.getGreen() * (1 + LIGHTENING_FACTOR)), 255);
	        int blue = Math.min((int) (c.getBlue() * (1 + LIGHTENING_FACTOR)), 255);
	        setColor(new Color(red, green, blue));
		}
		super.processActors(actors);
	}
	//gets actors from two spots away, no specific pre or post condidtions
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> retVal = new ArrayList<Actor>();
		HashSet<Actor> actors = new HashSet<Actor>();
		for(Location loc : getGrid().getValidAdjacentLocations(getLocation()))
		{
			for(Actor actor: getGrid().getNeighbors(loc))
			{
				actors.add(actor);
			}
		}
		for(Actor a: actors)
		{
			retVal.add(a);
		}
		return retVal;
	}
}
