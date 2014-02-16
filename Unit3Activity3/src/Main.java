import java.applet.Applet;
import java.awt.Graphics;


public class Main extends Applet
{
	public void init()
	{
        setSize(300, 300);
        setVisible(true);
	}
	public void start()
	{
		repaint();
	}
	public void paint(Graphics screen)
	{
		for(int x = 0; x <= 15; x++)
		{
			screen.drawLine(0, 20*x, 20*x, 300);
			screen.drawLine(0, 20*x, 300 - 20*x, 0);
			screen.drawLine(300, 20*x, 300 - 20*x, 300);
			screen.drawLine(300, 20*x, 20*x, 0);
		}
	}
}
