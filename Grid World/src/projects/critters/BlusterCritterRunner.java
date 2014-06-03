package projects.critters;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;

public class BlusterCritterRunner 
{
	public static void main(String[]args)
	{
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Bug(Color.BLUE));
        world.add(new Location(5, 5), new Bug(Color.PINK));
        world.add(new Location(1, 5), new Bug(Color.RED));
        world.add(new Location(7, 2), new Bug(Color.YELLOW));
        world.add(new Location(4, 4), new BlusterCritter(3));
        world.add(new Location(5, 8), new BlusterCritter(3));
        world.show();
	}
}
