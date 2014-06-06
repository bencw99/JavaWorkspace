package platform;

import world.World;
import drawable.*;
import kinematics.*;

public abstract class Interacting implements Drawable
{
	private World world;
	private Position pos;
	
	public Interacting(Position pos)
	{
		this.pos = pos;
	}

	public Position getPos()
	{
		return pos;
	}
	
	public World getWorld()
	{
		return world;
	}

	public void setPos(Position pos)
	{
		this.pos = pos;
	}
	
	public void setWorld(World world)
	{
		this.world = world;
	}
}
