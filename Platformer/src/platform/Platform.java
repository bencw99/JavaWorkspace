package platform;

import java.awt.*;
import kinematics.*;

public class Platform extends Interacting
{
	private double width;
	private double height;
	
	public Platform(Position pos, double width, double height)
	{
		super(pos);
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics graphics)
	{
		graphics.fillRect((int)(getPos().getX() - width/2), (int)(getPos().getY() - height/2), (int)width, (int)height);
	}

	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
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
