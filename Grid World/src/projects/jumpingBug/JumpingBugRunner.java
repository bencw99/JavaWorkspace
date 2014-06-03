package projects.jumpingBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
public class JumpingBugRunner 
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        JumpingBug alice = new JumpingBug();
        alice.setColor(Color.ORANGE);
        JumpingBug bob = new JumpingBug();
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.show();
    }
}
