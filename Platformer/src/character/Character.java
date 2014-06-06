package character;

import java.awt.*;
import java.util.*;

import platform.*;
import world.World;
import kinematics.*;

public abstract class Character
{
	private World world;
	
	private Position pos;
	private Velocity vel;
	
	private double width;
	private double height;
	
	public Character(Position pos, Velocity vel, double width, double height)
	{
		super();
		this.pos = pos;
		this.vel = vel;
		this.width = width;
		this.height = height;
	}

	public void update()
	{
		pos.setX(pos.getX() + vel.getXVel());
		pos.setY(pos.getY() + vel.getYVel());
		vel.setYVel(vel.getYVel() + Constants.gravity);
		for(Platform platform : world.getPlatforms())
		{
			interact(platform);
		}
	}
	
	public void interact(Platform platform)
	{
		if(isOn(platform))
		{
			if(vel.getYVel() > 0)
			{
				vel.setYVel(0);
			}
		}
	}
	
	public boolean isOn(Platform platform)
	{
		//Tests if character is in y-range of platform
		if(Math.abs(((pos.getY() + height/2) - (platform.getPos().getY() - platform.getHeight()/2))) < 1)
		{
			//Tests if character is in x-range of platform
			if(Math.abs((pos.getX() - platform.getPos().getX())) < platform.getWidth())
			{
				return true;
			}
		}
		return false;
	}
	
	public void jump()
	{
		ArrayList<Platform> possibles = world.getPlatforms();
		for(Platform possible : possibles)
		{
			if(isOn(possible))
			{
				vel.setYVel(-5);
			}
		}
	}
	
	public void draw(Graphics graphics)
	{
		graphics.fillOval((int)(pos.getX() - width/2), (int)(pos.getY() - height/2), (int)width, (int)height);
	}

	public World getWorld()
	{
		return world;
	}

	public Position getPos()
	{
		return pos;
	}

	public Velocity getVel()
	{
		return vel;
	}

	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setWorld(World world)
	{
		this.world = world;
	}

	public void setPos(Position pos)
	{
		this.pos = pos;
	}

	public void setVel(Velocity vel)
	{
		this.vel = vel;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}
}
