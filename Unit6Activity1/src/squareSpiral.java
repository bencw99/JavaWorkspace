import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class squareSpiral extends Applet
{
	final int l = 50;
	int x1 = 250,x2;
	int y1 = 250,y2;
	public void init()
	{
		setSize(500,500);
		setBackground(Color.WHITE);
		setVisible(true);
	}
	public void paint(Graphics screen)
	{
		for(int i = 1; i <= 10; i++)
		{
			int x,y;
			x = y =  i*l*(int) Math.pow(-1, i - 1);
			x2 = x1 + x;
			y2 = y1 + y;
			screen.drawLine(x1, y1, x1, y2);
			y1 = y2;
			screen.drawLine(x1, y1, x2, y2);
			x1 = x2;
		}
	}
}
