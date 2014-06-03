package game.lander;

import game.terrain.Terrain;

import java.awt.Color;
import java.awt.Graphics;
public class Beginner extends SuperLander
{
    boolean upThrust = false;
    boolean rightThrust = false;
    boolean leftThrust = false;
    public void upThrustOn()
    {
    	if(fuel > 0)
        upThrust = true;
    }
    public void rightThrustOn()
    {
    	if(fuel > 0)
        rightThrust = true;
    }
    public void leftThrustOn()
    {
    	if(fuel > 0)
        leftThrust = true;
    }
    public void upThrustOff()
    {
        upThrust = false;
    }
    public void rightThrustOff()
    {
        rightThrust = false;
    }
    public void leftThrustOff()
    {
        leftThrust = false;
    }
    public void draw(Graphics screen)
    {
        int X[] = {(int)getxPos() - 10, (int)getxPos() - 15, (int)getxPos(), (int)getxPos() + 15, (int)getxPos() + 10};
        int Y[] = {(int)yPos - 15, (int)yPos - 60, (int)yPos - 80, (int)yPos - 60, (int)yPos - 15};
        int Xfin1[] = {(int)getxPos() - 10, (int)getxPos() - 12, (int)getxPos() - 18, (int)getxPos() - 18};
        int Yfin[] = {(int)yPos - 15, (int)yPos - 33, (int)yPos - 23, (int)yPos - 10};
        int Xfin2[] = {(int)getxPos() + 10, (int)getxPos() + 12, (int)getxPos() + 18, (int)getxPos() + 18};
        int flameX[] = {(int)getxPos() - 5, (int)getxPos() + 5, (int)getxPos()};
        int flameY[] = {(int)yPos - 5, (int)yPos - 5, (int)yPos + 15};
        int flameX2[] = {(int)getxPos() - 2, (int)getxPos() + 2, (int)getxPos()};
        int flameY2[] = {(int)yPos - 3, (int)yPos - 3, (int)yPos + 13};
        screen.setColor(Color.LIGHT_GRAY);
        screen.fillPolygon(X, Y, 5);
        screen.setColor(landerColor);
        screen.drawLine((int)getxPos(), (int)yPos - 15, (int)getxPos(), (int)yPos - 80);
        screen.drawLine((int)getxPos() + 5, (int)yPos - 15, (int)getxPos() + 5, (int)yPos - 70);
        screen.drawLine((int)getxPos() - 5, (int)yPos - 15, (int)getxPos() - 5, (int)yPos - 70);
        screen.fillPolygon(Xfin1, Yfin, 4);
        screen.fillPolygon(Xfin2, Yfin, 4);
        screen.setColor(Color.BLACK);
        screen.fillOval((int)getxPos() - 10, (int)yPos - 50, 20, 20);
        screen.setColor(Color.WHITE);
        screen.fillOval((int)getxPos() - 8, (int)yPos - 48, 16, 16);
        if((rightThrust || leftThrust || upThrust) && fuel > 0)
        {
        	screen.setColor(Color.ORANGE);
        	screen.fillPolygon(flameX, flameY, 3);
        	screen.setColor(Color.RED);
        	screen.fillPolygon(flameX2, flameY2, 3);
        }
    }
    public void update()
    {
    	super.update();
        if(upThrust == true && fuel > 0)
        {
            yVel -= 0.02;
            fuel--;
        }
        if(rightThrust == true && fuel > 0)
        {
            xVel += 0.01;
            fuel--;
        }
        if(leftThrust == true && fuel > 0)
        {
            xVel -= 0.01;
            fuel--;
        }
    }
	public boolean land(Terrain terrain)
	{
        if((int)yPos >= (int)(610 - terrain.getHeight((int)(getxPos()))))
        {
        	upThrust = false;
        	leftThrust = false;
        	rightThrust = false;
        	if(getxPos() >= 10*terrain.getLandingPos() + 10 && getxPos() <= 10*terrain.getLandingPos() + 90 && Math.pow(xVel*xVel + yVel*yVel, 0.5) < 1)
        	{
        		setLand(true);
        	}
        	else
        	{
        		state = false;
        	}
        	return false;
        }
        else
        {
        	return true;
        }
	}
	public void setAngle(double d)
	{
		
	}
	public void ThrustOn() 
	{
		
	}
	public void setLeftOn() 
	{
		
	}
	public void setRightOn() 
	{
		
	}
	public void ThrustOff() {
		
	}
	public void setLeftOff() {
		
	}
	public void setRightOff() {
		
	}
}
