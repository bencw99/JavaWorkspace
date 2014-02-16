import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
public class SpaceShip 
{
	int type = 1;
    double xPos = 500;
    double yPos = 100;
    double xVel = 0;
    double yVel = 0;
    double angle = 0;
    boolean thrust = false;
    boolean left = false;
    boolean right = false;
    Color shipColor = Color.red;
    int weaponNum = 0;
    List<Weapon> weapons = new ArrayList<Weapon>();
    public void setWeapon()
    {
    	xVel -= 0.05*Math.cos(angle - Math.PI/2);
    	yVel -= 0.05*Math.sin(angle - Math.PI/2);
    	Weapon weapon = new Weapon(xPos,yPos,angle,xVel,yVel,type);
    	weapons.add(weapon); 
    }
    public void upgrade()
    {
    	type += 1;
    	if(type == 4)
    	{
    		type = 1;
    	}
    }
	public void draw(Graphics screen)
	{
        int X[] = { - 10,  - 12, 0,  + 12, + 10};
        int Y[] = { + 30,  - 15, - 35, - 15, + 30};
        int XRot[] = rotateX(X,Y,xPos,yPos,angle);
        int YRot[] = rotateY(X,Y,xPos,yPos,angle);
        int Xfin1[] = { - 10, - 10, - 25, - 25};
        int Yfin[] = { + 30, + 12, + 22, + 35};
        int Xfin2[] = { + 10, + 10, + 25, + 25};
        int Xshoot1[] = {-25, -30, -30, -25};
        int Xshoot2[] = {25, 30, 30, 25};
        int Xshoot3[] = {-15, -20, -20, -15};
        int Xshoot4[] = {15, 20, 20, 15};
//        int Xshoot5[] = {-11, -16, -16, -11};
//        int Xshoot6[] = {11, 16, 16, 11};
        int Yshoot[] = {42, 38, 16, 12};
        int Yshoot2[] = {40, 36, 14, 10};
//        int Yshoot3[] = {32, 28, 6, 2};
        int Xshoot1Rot[] = rotateX(Xshoot1,Yshoot,xPos,yPos,angle);
        int Yshoot1Rot[] = rotateY(Xshoot1,Yshoot,xPos,yPos,angle);        
        int Xshoot2Rot[] = rotateX(Xshoot2,Yshoot,xPos,yPos,angle);
        int Yshoot2Rot[] = rotateY(Xshoot2,Yshoot,xPos,yPos,angle);
        int Xshoot3Rot[] = rotateX(Xshoot3,Yshoot2,xPos,yPos,angle);
        int Yshoot3Rot[] = rotateY(Xshoot3,Yshoot2,xPos,yPos,angle);        
        int Xshoot4Rot[] = rotateX(Xshoot4,Yshoot2,xPos,yPos,angle);
        int Yshoot4Rot[] = rotateY(Xshoot4,Yshoot2,xPos,yPos,angle);
//        int Xshoot5Rot[] = rotateX(Xshoot5,Yshoot3,xPos,yPos,angle);
//        int Yshoot5Rot[] = rotateY(Xshoot5,Yshoot3,xPos,yPos,angle);        
//        int Xshoot6Rot[] = rotateX(Xshoot6,Yshoot3,xPos,yPos,angle);
//        int Yshoot6Rot[] = rotateY(Xshoot6,Yshoot3,xPos,yPos,angle);
        int Xfin2Rot[] = rotateX(Xfin2,Yfin,xPos,yPos,angle);
        int Xfin1Rot[] = rotateX(Xfin1,Yfin,xPos,yPos,angle);
        int Yfin1Rot[] = rotateY(Xfin1,Yfin,xPos,yPos,angle);
        int Yfin2Rot[] = rotateY(Xfin2,Yfin,xPos,yPos,angle);
        int flameX[] = {(int)xPos + (int)(- 5*Math.cos(angle) - 40*Math.sin(angle)), (int)xPos + (int)(5*Math.cos(angle) - 40*Math.sin(angle)), (int)xPos - (int)(60*Math.sin(angle))};
        int flameY[] = {(int)yPos + (int)( - 5*Math.sin(angle) + 40*Math.cos(angle)), (int)yPos + (int)( + 5*Math.sin(angle) + 40*Math.cos(angle)), (int)yPos + (int)(60*Math.cos(angle))};
        int flameX2[] = {(int)xPos + (int)(- 2*Math.cos(angle) - 42*Math.sin(angle)), (int)xPos + (int)(2*Math.cos(angle) - 42*Math.sin(angle)), (int)xPos - (int)(58*Math.sin(angle))};
        int flameY2[] = {(int)yPos + (int)( - 2*Math.sin(angle) + 42*Math.cos(angle)), (int)yPos + (int)( + 2*Math.sin(angle) + 42*Math.cos(angle)), (int)yPos + (int)(58*Math.cos(angle))};
        screen.setColor(Color.DARK_GRAY);
        screen.fillPolygon(XRot, YRot, 5);
        screen.setColor(shipColor);
        screen.drawLine((int)xPos - (int)(30*Math.sin(angle)), (int)yPos + (int)(30*Math.cos(angle)), (int)xPos +(int)(35*Math.sin(angle)), (int)yPos - (int)(35*Math.cos(angle)));
        screen.drawLine((int)xPos + (int)( + 5*Math.cos(angle) - 30*Math.sin(angle)), (int)yPos + (int)( + 5*Math.sin(angle) + 30*Math.cos(angle)),(int)xPos + (int)( + 5*Math.cos(angle) + 25*Math.sin(angle)), (int)yPos + (int)( + 5*Math.sin(angle) - 25*Math.cos(angle)));
        screen.drawLine((int)xPos + (int)( - 5*Math.cos(angle) - 30*Math.sin(angle)), (int)yPos + (int)( - 5*Math.sin(angle) + 30*Math.cos(angle)),(int)xPos + (int)( - 5*Math.cos(angle) + 25*Math.sin(angle)), (int)yPos + (int)( - 5*Math.sin(angle) - 25*Math.cos(angle)));
        screen.fillPolygon(Xfin1Rot, Yfin1Rot, 4);
        screen.fillPolygon(Xfin2Rot, Yfin2Rot, 4);
        for(int i = 0; i < weapons.size(); i++)
        {
        	weapons.get(i).draw(screen);
        }
        screen.setColor(Color.DARK_GRAY);
        screen.fillPolygon(Xshoot1Rot, Yshoot1Rot,4);
        screen.fillPolygon(Xshoot2Rot, Yshoot2Rot,4);
        screen.fillPolygon(Xshoot3Rot, Yshoot3Rot,4);
        screen.fillPolygon(Xshoot4Rot, Yshoot4Rot,4);
//        screen.fillPolygon(Xshoot5Rot, Yshoot5Rot,4);
//        screen.fillPolygon(Xshoot6Rot, Yshoot6Rot,4);
        if(thrust)
        {
        	screen.setColor(Color.ORANGE);
        	screen.fillPolygon(flameX, flameY, 3);
        	screen.setColor(Color.RED);
        	screen.fillPolygon(flameX2, flameY2, 3);
        }
	}
    public void ThrustOn()
    {
        thrust = true;
    }
    public void ThrustOff()
    {
        thrust = false;
    }
    public void update()
    {
        for(int i = 0; i < weapons.size(); i++)
        {
        	weapons.get(i).update();
        }
    	xPos += xVel;
    	yPos += yVel;
        if(thrust == true)
        {
            yVel -= 0.03*Math.cos(angle);
            xVel += 0.03*Math.sin(angle);
        }
        if(right)
        {
        	angle += 0.02;
        }
        if(left)
        {
        	angle -= 0.02;
        }
        if(xPos > 999.9)
        {
        	xPos = 0.1;
        }
        if(xPos < 0.1)
        {
        	xPos = 999.9;
        }
        if(yPos > 599.9)
        {
        	yPos = 0.1;
        }
        if(yPos < 0.1)
        {
        	yPos = 599.9;
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
    public void setColor(Color c)
    {
    	shipColor = c;
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
}
