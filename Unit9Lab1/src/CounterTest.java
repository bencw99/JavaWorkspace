import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class CounterTest extends Applet
{
	CounterConsoleMenu counter = new CounterConsoleMenu(0); //declares and creates counter
	public void init()
	{
        setSize(900, 60); //sets size
        setVisible(true); //sets visible
        setBackground(Color.WHITE); //sets background
		add(counter); //adds counter object
	}
	public void start()
	{
		while(true)
		{
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			repaint(); //calls paint
		}
	}
	public void paint(Graphics screen)
	{
		counter.draw(screen); // Draws counter
	}
}
