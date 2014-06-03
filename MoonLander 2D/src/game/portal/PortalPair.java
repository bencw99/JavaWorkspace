package game.portal;

import game.lander.SuperLander;
import game.updateable.Updatable;

import java.awt.Color;
import java.awt.Graphics;
public class PortalPair implements Updatable
{
	private int type = 0;
	private final int x;
	private final int y;
	final int X;
	final int Y;
	final int xRad = 12;
	final int yRad = 30;
	double angle = 0;
	double angle2 = Math.PI;
	private double rotateSpeed = 0.015;
	private int red = (int)(255*Math.random());
	private int green = (int)(255*Math.random());
	private int blue = (int)(255*Math.random());
	private Color color = new Color(getRed(),getGreen(),getBlue()); 
	public PortalPair(int a, int b, int c, int d)
	{
		x = a;
		y = b;
		X = c;
		Y = d;
		if(getRed() >= getGreen() && getRed() >= getBlue())
		{
			setType(1);
		}
		if(getGreen() >= getRed() && getGreen() >= getBlue())
		{
			setType(2);
		}
		if(getBlue() >= getGreen() && getBlue() >= getRed())
		{
			setType(3);
		}
	}
	public void update()
	{
		
	}
	public void draw(Graphics screen)
	{
        screen.setColor(getColor());
        for(double i = 0; i <= Math.PI/2; i += 0.01)
        {
            screen.fillRect(getX() + (int)(20*Math.cos(angle + i)) - 2,getY() + (int)(50*Math.sin(angle + i)) -2, 4, 4);
            screen.fillRect(X + (int)(20*Math.cos( - angle - i)) - 2,Y + (int)(50*Math.sin( - angle - i)) -2, 4, 4);
            screen.fillRect(getX() + (int)(20*Math.cos(angle2 + i)) - 2,getY() + (int)(50*Math.sin(angle2 + i)) -2, 4, 4);
            screen.fillRect(X + (int)(20*Math.cos( - angle2 - i)) - 2,Y + (int)(50*Math.sin( - angle2 - i)) -2, 4, 4);
        }
        screen.fillOval(getX() - 12, getY() -30, 24, 60);
		screen.fillOval(X - 12, Y -30, 24, 60);
		screen.setColor(Color.BLACK);
		screen.fillOval(getX() -8, getY() - 20, 16, 40);
		screen.fillOval(X -8, Y - 20, 16, 40);
		angle += getRotateSpeed();
		angle2 += getRotateSpeed();
	}
	public int distance()
	{
		return (int)(Math.sqrt((X - getX())*(X - getX()) + (Y - getY())*(Y - getY())));
	}
	public double distance(SuperLander lander)
	{
		return Math.sqrt((getX()-lander.getxPos())*(getX()-lander.getxPos()) + (getY()-lander.getyPosDraw())*(getY()-lander.getyPosDraw()));
	}
	public int getX1()
	{
		return getX();
	}
	public int getY1()
	{
		return getY();
	}
	public int getX2()
	{
		return X;
	}
	public int getY2()
	{
		return Y;
	}
	public void setRotateSpeed(double d)
	{
		rotateSpeed = d;
	}
	public boolean range(double xPos, double yPos)
	{
		if((Math.pow((xPos - getX())/xRad,2) + Math.pow((yPos - getY())/yRad, 2)) < 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setColor(Color copy) 
	{
		color = copy;
	}
	public void setRed(int d) 
	{
		red = d;
	}
	public void setGreen(int d) 
	{
		green = d;
	}
	public void setBlue(int d) 
	{
		blue = d;
	}
	public Color getColor()
	{
		return color;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public double getRotateSpeed()
	{
		return rotateSpeed;
	}
	public int getRed()
	{
		return red;
	}
	public int getGreen()
	{
		return green;
	}
	public int getBlue()
	{
		return blue;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public void init()
	{
		
	}
}
