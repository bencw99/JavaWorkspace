import java.awt.Graphics;
public class AlienWeapon 
{
	enum shotType {NORMAL,MEDIUM,HARD};
	double x;
	double y;
	double xVel;
	double yVel;
	double angle;
	shotType type;
	public AlienWeapon(double xPos, double yPos, double xPosShip, double yPosShip, int typeNum)
	{
		x = xPos;
		y = yPos;
		angle = Math.atan((xPos - xPosShip)/(yPos - yPosShip)) - Math.PI/2;
		xVel = typeNum*Math.cos(angle - Math.PI/2);
		yVel = typeNum*Math.sin(angle - Math.PI/2);
		switch (typeNum)
		{
		case(1):
		{
			type = shotType.NORMAL;
			break;
		}
		case(2):
		{
			type = shotType.MEDIUM;
			break;
		}
		case(3):
		{
			type = shotType.HARD;
			break;
		}
		}
	}
	public void update()
	{
		x += xVel;
		y += yVel;
	}
	public void draw(Graphics screen)
	{
		if(type == shotType.NORMAL)
		{
			int shotX1[] = {19,22,22,19};
			int shotY1[] = {20,20,35,35};
			int shotX1Rot[] = rotateX(shotX1,shotY1,x,y,angle);
			int shotY1Rot[] = rotateY(shotX1,shotY1,x,y,angle);
			int shotX2[] = {-19,-22,-22,-19};
			int shotY2[] = {20,20,35,35};
			int shotX2Rot[] = rotateX(shotX2,shotY2,x,y,angle);
			int shotY2Rot[] = rotateY(shotX2,shotY2,x,y,angle);
			screen.fillPolygon(shotX1Rot,shotY1Rot,4);
			screen.fillPolygon(shotX2Rot,shotY2Rot,4);
		}
		if(type == shotType.MEDIUM)
		{
			int shotX1[] = {18,22,22,20,18};
			int shotY1[] = {20,20,35,40,35};
			int shotX1Rot[] = rotateX(shotX1,shotY1,x,y,angle);
			int shotY1Rot[] = rotateY(shotX1,shotY1,x,y,angle);
			int shotX2[] = {-18,-22,-22,-20,-18};
			int shotY2[] = {20,20,35,40,35};
			int shotX2Rot[] = rotateX(shotX2,shotY2,x,y,angle);
			int shotY2Rot[] = rotateY(shotX2,shotY2,x,y,angle);
			screen.fillPolygon(shotX1Rot,shotY1Rot,5);
			screen.fillPolygon(shotX2Rot,shotY2Rot,5);
		}
		if(type == shotType.HARD)
		{
			int shotX1[] = {20,18,17,18,20,22,24,25,24,22};
			int shotY1[] = {20,30,40,50,60,60,50,40,30,20};
			int shotX1Rot[] = rotateX(shotX1,shotY1,x,y,angle);
			int shotY1Rot[] = rotateY(shotX1,shotY1,x,y,angle);
			int shotX2[] = {-20,-18,-17,-18,-20,-22,-24,-25,-24,-22};
			int shotY2[] = {20,30,40,50,60,60,50,40,30,20};
			int shotX2Rot[] = rotateX(shotX2,shotY2,x,y,angle);
			int shotY2Rot[] = rotateY(shotX2,shotY2,x,y,angle);
			screen.fillPolygon(shotX1Rot,shotY1Rot,10);
			screen.fillPolygon(shotX2Rot,shotY2Rot,10);
		}
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
