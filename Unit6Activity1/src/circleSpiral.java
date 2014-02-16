import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class circleSpiral extends Applet
{
	final int l = 50;
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
			if(i%2 == 1)
				screen.drawArc(250 - 25*i, 250 - 25*i, 50*i, 50*i, 0, 180);
			else
				screen.drawArc(225 - 25*(i), 250 - 25*(i), 50*(i), 50*(i), 180, 180);
		}
	}
}
