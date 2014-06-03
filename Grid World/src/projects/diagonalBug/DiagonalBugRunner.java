package projects.diagonalBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
public class DiagonalBugRunner 
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        DiagonalBug alice = new DiagonalBug();
        alice.setColor(Color.ORANGE);
        DiagonalBug bob = new DiagonalBug();
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.show();
    }
}
