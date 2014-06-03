package projects.critters;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
	//processActors overridden to push away one more spot away and to remove them if moved off grid
	//Same preconditions as parent class method
	//post conditions: actors moved or removed
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            int dir = this.getLocation().getDirectionToward(a.getLocation());
            Location dest = a.getLocation().getAdjacentLocation(dir);
            if(getGrid().isValid(dest))
            {
            	a.moveTo(dest);
            }
            else
            {
            	a.removeSelfFromGrid();
            }
        }
    }
}
