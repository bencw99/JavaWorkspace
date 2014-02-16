
import java.awt.Color;
import java.awt.Graphics;
public class Advanced extends SuperLander
{
    double angle = 0;
    boolean Thrust = false;
    boolean left;
    boolean right;
    public void ThrustOn()
    {
    	if(fuel > 0)
        Thrust = true;
    }
    public void ThrustOff()
    {
        Thrust = false;
    }
    public void drawLander(Graphics screen)
    {
        int X[] = { - 10,  - 15, 0,  + 15, + 10};
        int Y[] = { + 30,  - 15, - 35, - 15, + 30};
        int XRot[] = rotateX(X,Y,xPosDraw,yPosDraw,angle);
        int YRot[] = rotateY(X,Y,xPosDraw,yPosDraw,angle);
        int Xfin1[] = { - 10, - 12, - 18, - 18};
        int Yfin[] = { + 30, + 12, + 22, + 35};
        int Xfin2[] = { + 10, + 12, + 18, + 18};
        int Xfin2Rot[] = rotateX(Xfin2,Yfin,xPosDraw,yPosDraw,angle);
        int Xfin1Rot[] = rotateX(Xfin1,Yfin,xPosDraw,yPosDraw,angle);
        int Yfin1Rot[] = rotateY(Xfin1,Yfin,xPosDraw,yPosDraw,angle);
        int Yfin2Rot[] = rotateY(Xfin2,Yfin,xPosDraw,yPosDraw,angle);
        int flameX[] = {(int)xPosDraw + (int)(- 5*Math.cos(angle) - 40*Math.sin(angle)), (int)xPosDraw + (int)(5*Math.cos(angle) - 40*Math.sin(angle)), (int)xPosDraw - (int)(60*Math.sin(angle))};
        int flameY[] = {(int)yPosDraw + (int)( - 5*Math.sin(angle) + 40*Math.cos(angle)), (int)yPosDraw + (int)( + 5*Math.sin(angle) + 40*Math.cos(angle)), (int)yPosDraw + (int)(60*Math.cos(angle))};
        int flameX2[] = {(int)xPosDraw + (int)(- 2*Math.cos(angle) - 42*Math.sin(angle)), (int)xPosDraw + (int)(2*Math.cos(angle) - 42*Math.sin(angle)), (int)xPosDraw - (int)(58*Math.sin(angle))};
        int flameY2[] = {(int)yPosDraw + (int)( - 2*Math.sin(angle) + 42*Math.cos(angle)), (int)yPosDraw + (int)( + 2*Math.sin(angle) + 42*Math.cos(angle)), (int)yPosDraw + (int)(58*Math.cos(angle))};
        screen.setColor(Color.LIGHT_GRAY);
        screen.fillPolygon(XRot, YRot, 5);
        screen.setColor(landerColor);
        screen.drawLine((int)xPosDraw - (int)(30*Math.sin(angle)), (int)yPosDraw + (int)(30*Math.cos(angle)), (int)xPosDraw +(int)(35*Math.sin(angle)), (int)yPosDraw - (int)(35*Math.cos(angle)));
        screen.drawLine((int)xPosDraw + (int)( + 5*Math.cos(angle) - 30*Math.sin(angle)), (int)yPosDraw + (int)( + 5*Math.sin(angle) + 30*Math.cos(angle)),(int)xPosDraw + (int)( + 5*Math.cos(angle) + 25*Math.sin(angle)), (int)yPosDraw + (int)( + 5*Math.sin(angle) - 25*Math.cos(angle)));
        screen.drawLine((int)xPosDraw + (int)( - 5*Math.cos(angle) - 30*Math.sin(angle)), (int)yPosDraw + (int)( - 5*Math.sin(angle) + 30*Math.cos(angle)),(int)xPosDraw + (int)( - 5*Math.cos(angle) + 25*Math.sin(angle)), (int)yPosDraw + (int)( - 5*Math.sin(angle) - 25*Math.cos(angle)));
        screen.fillPolygon(Xfin1Rot, Yfin1Rot, 4);
        screen.fillPolygon(Xfin2Rot, Yfin2Rot, 4);
        screen.setColor(Color.BLACK);
        screen.fillOval((int)xPos - 10, (int)yPosDraw - 10, 20, 20);
        screen.setColor(Color.WHITE);
        screen.fillOval((int)xPos - 8, (int)yPosDraw - 8, 16, 16);
        if((Thrust) && fuel > 0)
        {
        	screen.setColor(Color.ORANGE);
        	screen.fillPolygon(flameX, flameY, 3);
        	screen.setColor(Color.RED);
        	screen.fillPolygon(flameX2, flameY2, 3);
        }
    }
    public void update()
    {
    	yPosDraw = yPos - 45;
    	xPosDraw = xPos;
        yVel += g;
        if(Thrust == true && fuel > 0)
        {
            yVel -= 0.03*Math.cos(angle);
            xVel += 0.03*Math.sin(angle);
            fuel -= 1.5;
        }
        if(right)
        {
        	angle += 0.01;
        }
        if(left)
        {
        	angle -= 0.01;
        }
        xPos += xVel;
        yPos += yVel;
        if(xPos >= 999.9)
        {
        	xPos = 0.2;
        }
        if(xPos <= 0.1)
        {
        	xPos = 999.8;
        }
        if(fuel <= 0)
        {
        	fuel = 0;
        }
    }
	public boolean land(Terrain terrain)
	{
        if((int)yPos >= (int)(610 - terrain.getHeight((int)(xPos))))
        {
        	Thrust = false;
        	right = false;
        	left = false;
        	if(xPos >= 10*terrain.a + 10 && xPos <= 10*terrain.a + 90 && Math.pow(xVel*xVel + yVel*yVel, 0.5) < 1)
        	{
        		land = true;
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
	public double getAngle()
	{
		return angle;
	}
	public void setAngle(double d)
	{
		angle = d;
	}
	public void setLeftOn() 
	{
		left = true;
	}
	public void setLeftOff() 
	{
		left = false;
	}
	public void setRightOn() 
	{
		right = true;
	}
	public void setRightOff() 
	{
		right = false;
	}
	public void upThrustOn() 
	{
		
	}
	public void leftThrustOn() 
	{
		
	}
	public void rightThrustOn() 
	{
		
	}
	public void upThrustOff() 
	{
		
	}
	public void leftThrustOff() 
	{
		
	}
	public void rightThrustOff() 
	{
		
	}
}
