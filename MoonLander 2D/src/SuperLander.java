
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public abstract class SuperLander extends Applet
{
	boolean state = true;
    int fuel = 1000;
    int color = 2;
    Color landerColor = Color.RED;
    double xPos = 500;
    double yPos = 100;
    double xPosDraw = xPos;
    double yPosDraw = yPos - 35;
    double xVel = 0;
    double yVel = 0;
    final double g = 0.01;
    boolean land = false;
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
        xPos = x;
    }
    public void setYPos(double y)
    {
        yPos = y;
    }
    public double getXPos()
    {
        return xPos;
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
	public void gravity(PortalPair portals[])
	{
		for(int a = 0; a < portals.length; a++)
		{
	    	if(xPos < (portals[a].getX1() + 100) && (xPos > (portals[a].getX1() - 100)) && (yPos - 50 < (portals[a].getY1() + 115)) && (yPos - 50 > (portals[a].getY1() - 115)))
	    	{
				if(portals[a].getX1() > xPos)
				{
					xVel += 0.01;
				}
				if(portals[a].getX1() < xPos)
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
		        xPos = portals[b].getX2();
		        yPos = portals[b].getY2() + 40;
	    		if(portals[b].type == 2)
	    		{
					xVel = 0;
					yVel = 0;
	    			fuel += 100;
	    			if(fuel > 1000)
	    			{
	    				fuel = 1000;
	    			}
	    		}
	    		if(portals[b].type == 1)
	    		{
	    			yVel = - 2*Math.random();
	    			xVel = 2 - 4*Math.random();
	    		}
	    		if(portals[b].type == 3)
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
		Color copy = portal.color;
		for(int i = 0; i < 70; i++)
		{
			drawLander(screen);
			portal.draw(screen);
			setXPos(xPos + ((double)portal.x - xPos)/70);
			setYPos(yPos + ((double)portal.y - yPos)/70);
            portal.setRotateSpeed(portal.rotateSpeed + 0.01);
            if(portal.red < 255)
            {
            	portal.setRed((int)(portal.red + (255 - (double)portal.red)/70));
            }
            if(portal.green < 255)
            {
            	portal.setGreen((int)(portal.green + (255 - (double)portal.green)/70));
            }
            if(portal.blue < 255)
            {
            	portal.setBlue((int)(portal.blue + (255 - (double)portal.blue)/70));
            }
            portal.setColor(new Color(portal.red,portal.green,portal.blue));
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
    public abstract void drawLander(Graphics screen);
    public abstract void update();
}
