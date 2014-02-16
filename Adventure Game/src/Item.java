import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Item extends Applet implements Locatable
{
	int type = 0;
	double x = (int)(Game.screenWidth*Math.random());
	double y = (int)(Game.screenHeight*Math.random());
	boolean held = false;
	BufferedImage token;
	int width = 28;
	int height = 32;
	public Item(int X, int Y, int t, boolean h) throws IOException
	{
		x = X;
		y = Y;
		type = t;
		held = h;
		if(type == 0)
		{
			token = ImageIO.read(new File("Earthcoin.gif"));
		}
		if(type == 1)
		{
			token = ImageIO.read(new File("Flamecoin.gif"));
		}
		if(type == 2)
		{
			token = ImageIO.read(new File("Icecoin.gif"));
		}
		if(type == 3)
		{
			token = ImageIO.read(new File("Darkcoin.gif"));
		}
	}
	public Item(int t, boolean h) throws IOException
	{
		type = t;
		held = h;
		if(type == 0)
		{
			token = ImageIO.read(new File("Earthcoin.gif"));
		}
		if(type == 1)
		{
			token = ImageIO.read(new File("Flamecoin.gif"));
		}
		if(type == 2)
		{
			token = ImageIO.read(new File("Icecoin.gif"));
		}
		if(type == 3)
		{
			token = ImageIO.read(new File("Darkcoin.gif"));
		}
	}
	public void draw(Graphics screen)
	{
		if(!held)
		{
			screen.drawImage(token, (int)x - width/2, (int)y - height/2, width, height, this);
		}
	}
	public void setHeld(boolean b) 
	{
		held = b;
	}
	public double getXPos() 
	{
		return x;
	}
	public double getYPos() 
	{
		return y;
	}
}
