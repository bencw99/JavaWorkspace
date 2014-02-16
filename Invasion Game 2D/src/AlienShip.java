import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
public class AlienShip
{
	int type = 1;
    double xPos = 500;
    double yPos = 100;
    double xVel = 0;
    double yVel = 0;
    double angle = 0;
    Color shipColor = Color.red;
    int weaponNum = 0;
    List<AlienWeapon> weapons = new ArrayList<AlienWeapon>();
    public void setWeapon(SpaceShip ship)
    {
    	AlienWeapon weapon = new AlienWeapon(xPos,yPos,ship.xPos,ship.yPos,type);
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
        int X[] = {0,-4,-7,-9,- 10, - 9, - 7,  - 4, 0,  + 4, + 7, + 9, + 10,9,7,4};
        int Y[] = {-10,-9,-7,-4, 0, 4, 7, 9 , 10 , 9 , 7 , 4 , 0 , -4 , -7 , -9};
        int XRot[] = rotateX(X,Y,xPos,yPos,angle);
        int YRot[] = rotateY(X,Y,xPos,yPos,angle);
        int Xshoot[] = {-25, -30, -30, -25};
        int Yshoot[] = {42, 38, 16, 12};
        int XshootRot[] = rotateX(Xshoot,Yshoot,xPos,yPos,angle);
        int YshootRot[] = rotateX(Xshoot,Yshoot,xPos,yPos,angle);
        screen.setColor(Color.DARK_GRAY);
        screen.fillPolygon(XRot, YRot, 16);
        screen.setColor(shipColor);
        for(int i = 0; i < weapons.size(); i++)
        {
        	weapons.get(i).draw(screen);
        }
        screen.setColor(Color.DARK_GRAY);
        screen.fillPolygon(XshootRot, YshootRot,4);
	}
    public void update()
    {
        for(int i = 0; i < weapons.size(); i++)
        {
        	weapons.get(i).update();
        }
    	xPos += xVel;
    	yPos += yVel;
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
