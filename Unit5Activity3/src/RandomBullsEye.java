import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class RandomBullsEye extends Applet
{
	public void init()
	{
		setSize(200,200);
		setBackground(Color.WHITE);
		setVisible(true);
	}
	public void start()
	{
		repaint();
	}
	public void paint(Graphics screen)
	{
		Color a = new Color((int)(255*Math.random()), (int)(255*Math.random()), (int)(255*Math.random()));
		Color b = new Color((int)(255*Math.random()), (int)(255*Math.random()), (int)(255*Math.random()));
		for(int i = 5; i > 0; i--)
		{
			if(i%2 == 0)
			{
				screen.setColor(a);
			}
			else
			{
				screen.setColor(b);
			}
			screen.fillOval(100 - 15*i, 100 - 15*i, 30*i, 30*i);
		}
	}
}
