import java.awt.Color;
import java.awt.Graphics;
public class PortalPair 
{
	enum colorType {RED, GREEN, BLUE};
	final int x;
	final int y;
	final int X;
	final int Y;
	final int xRad = 12;
	final int yRad = 30;
	double angle = 0;
	double angle2 = Math.PI;
	double rotateSpeed = 0.015;
	int red = (int)(255*Math.random());
	int green = (int)(255*Math.random());
	int blue = (int)(255*Math.random());
	Color color = new Color(red,green,blue); 
	colorType type;
	public PortalPair(int a, int b, int c, int d)
	{
		x = a;
		y = b;
		X = c;
		Y = d;
		if(red > green && red > blue)
		{
			type = colorType.RED;
		}
		if(green > red && green > blue)
		{
			type = colorType.GREEN;
		}
		if(blue > green && blue > red)
		{
			type = colorType.BLUE;
		}
	}
	public void draw(Graphics screen)
	{
        screen.setColor(color);
        for(double i = 0; i <= Math.PI/2; i += 0.01)
        {
            screen.fillRect(x + (int)(20*Math.cos(angle + i)) - 2,y + (int)(50*Math.sin(angle + i)) -2, 4, 4);
            screen.fillRect(X + (int)(20*Math.cos( - angle - i)) - 2,Y + (int)(50*Math.sin( - angle - i)) -2, 4, 4);
            screen.fillRect(x + (int)(20*Math.cos(angle2 + i)) - 2,y + (int)(50*Math.sin(angle2 + i)) -2, 4, 4);
            screen.fillRect(X + (int)(20*Math.cos( - angle2 - i)) - 2,Y + (int)(50*Math.sin( - angle2 - i)) -2, 4, 4);
        }
        screen.fillOval(x - 12, y -30, 24, 60);
		screen.fillOval(X - 12, Y -30, 24, 60);
		screen.setColor(Color.BLACK);
		screen.fillOval(x -8, y - 20, 16, 40);
		screen.fillOval(X -8, Y - 20, 16, 40);
		angle += rotateSpeed;
		angle2 += rotateSpeed;
	}
	public int distance()
	{
		return (int)(Math.sqrt((X - x)*(X - x) + (Y - y)*(Y - y)));
	}
	public double distance(SuperLander lander)
	{
		return Math.sqrt((x-lander.xPos)*(x-lander.xPos) + (y-lander.yPosDraw)*(y-lander.yPosDraw));
	}
	public int getX1()
	{
		return x;
	}
	public int getY1()
	{
		return y;
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
		if((Math.pow((xPos - x)/xRad,2) + Math.pow((yPos - y)/yRad, 2)) < 1)
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
}