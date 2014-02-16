import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Spell extends Applet implements Locatable
{
	BufferedImage spritePage;
	BufferedImage[][] sprites;
	int type;
	int phase;
	int a = 0;
	int z = 0;
	int direction;
	double x;
	double y;
	double speed = 7;
	final double xVel;
	final double yVel;
	final int width = 32;
	final int height = 32;
	public Spell(double X, double Y, int d, int a) throws IOException
	{
		type = a;
		x = X;
		y = Y;
		direction = d;
		if(type == 1)
		{
			spritePage = ImageIO.read(new File("Fireball.png"));
		}
		if(type == 2)
		{
			spritePage = ImageIO.read(new File("Iceball.png"));
		}
		sprites = new BufferedImage[3][4];
		for (int i = 0; i < sprites.length; i++)
		{
		    for (int j = 0; j < sprites[0].length; j++)
		    {
		        sprites[i][j] = spritePage.getSubimage(i * width, j * height, width, height);
		    }
		}
		switch(direction)
		{
		case 0:
		{
			yVel = speed;
			xVel = 0;
			break;
		}
		case 1:
		{
			yVel = 0;
			xVel = -speed;
			break;
		}
		case 2:
		{
			yVel = 0;
			xVel = speed;
			break;
		}
		case 3:
		{
			yVel = -speed;
			xVel = 0;
			break;
		}
		default:
		{
			xVel = 0;
			yVel = 0;
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
		a += 1;
			z++;
			if(z % 4 == 0)
			{
				phase = 2;
			}
			if(z % 4 == 1)
			{
				phase = 1;
			}
			if(z % 4 == 2)
			{
				phase = 0;
			}
		screen.drawImage(sprites[phase][direction], (int)x - width/2, (int)y - height/2, width, height, this);
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
