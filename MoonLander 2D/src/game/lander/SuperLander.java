package game.lander;
import game.portal.PortalPair;
import game.terrain.Terrain;
import game.updateable.Updatable;
import gui.Main;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public abstract class SuperLander extends Applet implements Updatable
{
	protected boolean state = true;
    protected int fuel = 1000;
    protected int color = 2;
    protected Color landerColor = Color.RED;
    private double xPos = 500;
    protected double yPos = 100;
    protected double xPosDraw = getxPos();
    protected double yPosDraw = yPos - 35;
    protected double xVel = 0;
    protected double yVel = 0;
    protected final double g = 0.01;
    protected boolean land = false;
	public double getyPos()
	{
		return yPos;
	}
	public void setyPos(double yPos)
	{
		this.yPos = yPos;
	}
	public double getyPosDraw()
	{
		return yPosDraw;
	}
	public void setyPosDraw(double yPosDraw)
	{
		this.yPosDraw = yPosDraw;
	}
	public double getxVel()
	{
		return xVel;
	}
	public void setxVel(double xVel)
	{
		this.xVel = xVel;
	}
	public double getyVel()
	{
		return yVel;
	}
	public void setyVel(double yVel)
	{
		this.yVel = yVel;
	}
	public boolean isState()
	{
		return state;
	}
	public int getColor()
	{
		return color;
	}
    public void setColor(int c)
    {
    	color = c;
    }
    public void setLand(boolean a)
    {
    	land = a;
    }
    public void setFuel(int f)
    {
    	fuel = f;
    }
    public int getFuel()
    {
    	return fuel;
    }
    public void setXPos(double x)
    {
        setxPos(x);
    }
    public void setYPos(double y)
    {
        yPos = y;
    }
    public double getXPos()
    {
        return getxPos();
    }
    public double getYPos()
    {
        return yPos;
    }
	public void setXVel(double d) 
	{
		xVel = d;	
	}
	public void setYVel(double d) 
	{
		yVel = d;	
	}
	public boolean isLand()
	{
		return land;
	}
	public void gravity(PortalPair portals[])
	{
		for(int a = 0; a < portals.length; a++)
		{
	    	if(getxPos() < (portals[a].getX1() + 100) && (getxPos() > (portals[a].getX1() - 100)) && (yPos - 50 < (portals[a].getY1() + 115)) && (yPos - 50 > (portals[a].getY1() - 115)))
	    	{
				if(portals[a].getX1() > getxPos())
				{
					xVel += 0.01;
				}
				if(portals[a].getX1() < getxPos())
				{
					xVel -= 0.01;
				}
				if(portals[a].getY1() + 50 < yPos)
				{
					yVel -= 0.01;
				}
				if(portals[a].getY1() + 50 > yPos)
				{
					yVel += 0.01;
				}
	    	}
		}
	}
	public void teleport(PortalPair portals[])
	{
		for(int b = 0; b < portals.length; b++)
		{
	    	if(portals[b].range(xPosDraw, yPosDraw))
	    	{
//	    		flash(portals[b],screen);
		        setxPos(portals[b].getX2());
		        yPos = portals[b].getY2() + 40;
	    		if(portals[b].getType() == 2)
	    		{
					xVel = 0;
					yVel = 0;
	    			fuel += 100;
	    			if(fuel > 1000)
	    			{
	    				fuel = 1000;
	    			}
	    		}
	    		if(portals[b].getType() == 1)
	    		{
	    			yVel = - 2*Math.random();
	    			xVel = 2 - 4*Math.random();
	    		}
	    		if(portals[b].getType() == 3)
	    		{
	    			changeColor(3);
	    		}
	    	}
		}
	}
	public void setState(boolean b) 
	{
		state = b;
	}
    public void changeColor(int a)
    {
    	if(a == 0)
    	{
    		landerColor = Color.blue;
    	}
    	if(a == 1)
    	{
    		landerColor = Color.green;
    	}
    	if(a == 2)
    	{
    		landerColor = Color.red;
    	}
    	if(a == 3)
    	{    		
    		landerColor = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
    	}
    }
    public void flash(PortalPair portal, Graphics screen)
    {
		Color copy = portal.getColor();
		for(int i = 0; i < 70; i++)
		{
			draw(screen);
			portal.draw(screen);
			setXPos(getxPos() + ((double)portal.getX() - getxPos())/70);
			setYPos(yPos + ((double)portal.getY() - yPos)/70);
            portal.setRotateSpeed(portal.getRotateSpeed() + 0.01);
            if(portal.getRed() < 255)
            {
            	portal.setRed((int)(portal.getRed() + (255 - (double)portal.getRed())/70));
            }
            if(portal.getGreen() < 255)
            {
            	portal.setGreen((int)(portal.getGreen() + (255 - (double)portal.getGreen())/70));
            }
            if(portal.getBlue() < 255)
            {
            	portal.setBlue((int)(portal.getBlue() + (255 - (double)portal.getBlue())/70));
            }
            portal.setColor(new Color(portal.getRed(),portal.getGreen(),portal.getBlue()));
            try                               
            {
                Thread.sleep(10);                        
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
		}
		portal.setColor(copy);
    }
    public int[] rotateX(int[] arrayX,int[] arrayY, double x, double y, double angle)
    {
    	int rotated[] = new int[arrayX.length];
    	for(int i = 0; i < arrayX.length; i++)
    	{
    		rotated[i] = (int)(x + arrayX[i]*Math.cos(angle) - arrayY[i]*Math.sin(angle));
    	}
    	return rotated;
    }
    public int[] rotateY(int[] arrayX,int[] arrayY, double x, double y, double angle)
    {
    	int rotated[] = new int[arrayY.length];
    	for(int i = 0; i < arrayY.length; i++)
    	{
    		rotated[i] = (int)(y + arrayY[i]*Math.cos(angle) + arrayX[i]*Math.sin(angle));
    	}
    	return rotated;
    }
    public void update()
    {
    	yPosDraw = yPos - 45;
    	xPosDraw = getxPos();
        yVel += g;
        setxPos(getxPos() + xVel);
        yPos += yVel;
        if(fuel <= 0)
        {
        	fuel = 0;
        }
        if(getxPos() >= Main.getScreenWidth())
        {
        	setxPos(0.1);
        }
        if(getxPos() <= 0)
        {
        	setxPos(Main.getScreenWidth() - 0.1);
        }
    }
	public abstract void ThrustOn();
	public abstract void upThrustOn();
	public abstract void setLeftOn();
	public abstract void setRightOn();
	public abstract void leftThrustOn();
	public abstract void rightThrustOn();
	public abstract void ThrustOff();
	public abstract void upThrustOff();
	public abstract void setLeftOff();
	public abstract void setRightOff();
	public abstract void leftThrustOff();
	public abstract void rightThrustOff();
	public abstract boolean land(Terrain terrain);
	public abstract void setAngle(double d);
	public double getxPos()
	{
		return xPos;
	}
	public void setxPos(double xPos)
	{
		this.xPos = xPos;
	}
}
