package projects.critters;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
public class QuickCrab extends CrabCritter
{
	//getLocationsInDirection method overridden so that locations of distance two away are returned
	//Post conditions and preconditions are same as those of superclass method
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        
        for (int d : directions)
        {
        	//NeighborLoc is set to two adjacent locations away in direction instead of one
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d).getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }
}
