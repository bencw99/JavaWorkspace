import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class RandomShape extends Applet
{
	int screenW = 500;
	int screenH = 500;
	public void init()
	{
		setBackground(Color.WHITE);
		setSize(screenW,screenH);
		setVisible(true);
	}
	public void paint(Graphics screen)
	{
		for(int i = 0; i < 10; i++)
		{
			int a = (int)(2*Math.random());
			int x = (int)(screenW*Math.random());
			int y = (int)(screenH*Math.random());
			int w = (int)(screenW*Math.random()/2);
			int h = (int)(screenH*Math.random()/2);
			screen.setColor(new Color((int)(255*Math.random()), (int)(255*Math.random()), (int)(255*Math.random())));
			if(a == 0)
			{
				screen.fillRect(x,y,w,h);
			}
			else
			{
				screen.fillOval(x, y, w, h);
			}
		}
	}
}
