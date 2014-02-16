import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class AppletDemo extends Applet
{
	int i;
	public void init()
	{
        setSize(500, 500);
        setBackground(Color.WHITE);
        setVisible(true);
	}
	public void start()
	{
		repaint();
		for(i = 0; i < 500; i++)
		{
			repaint();
			try 
			{
				Thread.sleep(10);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void paint(Graphics screen)
	{
		screen.setColor(Color.BLACK);
		screen.drawLine(0, i, 500, 500 - i);
	}
}
